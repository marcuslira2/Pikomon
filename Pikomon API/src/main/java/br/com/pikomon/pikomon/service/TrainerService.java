package br.com.pikomon.pikomon.service;

import br.com.pikomon.pikomon.dto.trainer.CreateTrainerDTO;
import br.com.pikomon.pikomon.dto.trainer.TrainerDTO;
import br.com.pikomon.pikomon.persistence.Pokemon;
import br.com.pikomon.pikomon.persistence.Trainer;
import br.com.pikomon.pikomon.persistence.User;
import br.com.pikomon.pikomon.repository.TrainerRepository;
import br.com.pikomon.pikomon.repository.UserRepository;
import br.com.pikomon.pikomon.service.pokemon.PokemonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

@Service
public class TrainerService {

    private final TrainerRepository trainerRepository;

    private final PokemonService pokemonService;

    private final UserRepository userRepository;

    private final LogService logService;

    private static final Logger log = LoggerFactory.getLogger(TrainerService.class);

    private static final String TRAINER_NOT_FOUND = "Trainer not found";

    private static final String USER_NOT_FOUND = "User not found";

    public TrainerService(TrainerRepository trainerRepository, PokemonService pokemonService, UserRepository userRepository, LogService logService) {
        this.trainerRepository = trainerRepository;
        this.pokemonService = pokemonService;
        this.userRepository = userRepository;
        this.logService = logService;
    }

    private TrainerDTO converterToDTO(Trainer trainer) {
        log.info("Converting trainer into DTO");
        return new TrainerDTO(
                trainer.getName(),
                trainer.getMoney(),
                trainer.getPokemons(),
                trainer.getUserId()
        );
    }

    public TrainerDTO save(CreateTrainerDTO dto) throws ExecutionException {
        User user = userRepository.findById(dto.userID()).orElseThrow(() -> new NoSuchElementException(USER_NOT_FOUND));
        Trainer trainer = new Trainer();
        trainer.setName(dto.name());
        trainer.setMoney(dto.money());
        trainer.setCreateDate(new Date());
        trainer.setDeleted(false);
        trainer.setUserId(user.getId());
        trainer.setUuid(UUID.randomUUID().toString());
        this.trainerRepository.save(trainer);
        Pokemon pokemon = pokemonService.save(dto.pokemonId(), 5, dto.name());
        trainer.getPokemons().add(pokemon);
        this.trainerRepository.save(trainer);
        user.getTrainers().add(trainer);
        this.userRepository.save(user);
        TrainerDTO trainerDTO = this.converterToDTO(trainer);

        String logMsg = user.getUsername() + " create new trainer: " + trainer.getName();
        this.logService.save(user.getUuid(), trainer.getUuid(), logMsg);

        return trainerDTO;

    }

    public List<TrainerDTO> listAll() {
        return trainerRepository.findAll().stream()
                .filter(trainer -> !trainer.getDeleted()).toList()
                .stream().map(this::converterToDTO).toList();
    }

    public TrainerDTO findDTOById(Integer id) {
        log.info("Searching trainer...");
        Trainer trainer = trainerRepository.findByIdAndDeletedFalse(id).orElseThrow(() -> new NoSuchElementException(TRAINER_NOT_FOUND));
        return this.converterToDTO(trainer);
    }
    public Trainer findById(Integer id) {
        return trainerRepository.findByIdAndDeletedFalse(id).orElseThrow(() -> new NoSuchElementException(TRAINER_NOT_FOUND));
    }



    public String deleteById(Integer id) {
        Trainer trainer = trainerRepository.findByIdAndDeletedFalse(id).orElseThrow(() -> new NoSuchElementException(TRAINER_NOT_FOUND));
        User user = userRepository.findById(trainer.getUserId()).orElseThrow(() -> new NoSuchElementException(USER_NOT_FOUND));
        log.info("Deleting trainer...");
        trainer.setDeleted(true);
        trainer.setDeletedDate(new Date());
        trainerRepository.save(trainer);

        String logMsg = user.getUsername() + " deleted trainer: " + trainer.getName();
        this.logService.save(user.getUuid(), trainer.getUuid(), logMsg);

        return trainer.getName();
    }
}
