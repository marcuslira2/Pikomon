package br.com.pikomon.Pikomon.service;

import br.com.pikomon.Pikomon.dto.CreateTrainerDTO;
import br.com.pikomon.Pikomon.dto.TrainerDTO;
import br.com.pikomon.Pikomon.infra.exceptions.ObjectBadRequestException;
import br.com.pikomon.Pikomon.infra.exceptions.ObjectNotFoundException;
import br.com.pikomon.Pikomon.persistence.Pokemon;
import br.com.pikomon.Pikomon.persistence.Trainer;
import br.com.pikomon.Pikomon.repository.TrainerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TrainerService {

    @Autowired
    TrainerRepository trainerRepository;

    @Autowired
    PokemonService pokemonService;

    private static final Logger log = LoggerFactory.getLogger(TrainerService.class);

    private TrainerDTO converterToDTO(Trainer trainer) {
        log.info("Converting trainer into DTO");
        return new TrainerDTO(
                trainer.getName(),
                trainer.getMoney(),
                trainer.getPokemons()
        );
    }

    public ResponseEntity<?> save(CreateTrainerDTO dto) throws ObjectBadRequestException {
        Trainer trainer = new Trainer();

        trainer.setName(dto.name());
        trainer.setMoney(dto.money());
        trainer.setCreateDate(new Date());
        Pokemon pokemon = pokemonService.save(dto.pokemonId(),5,dto.name());
        trainer.add(pokemon);
        trainer.setDeleted(0);
        this.trainerRepository.save(trainer);
        return ResponseEntity.status(HttpStatus.CREATED).body("Trainer "+trainer.getName()+" created.");


    }

    public List<TrainerDTO> listAll() {
        return trainerRepository.findAll().stream()
                .filter(trainer -> trainer.getDeleted()==0).toList()
                .stream().map(this::converterToDTO).toList();
    }

    public ResponseEntity<?> findById(Integer id) throws ObjectNotFoundException {
        Trainer trainer = trainerRepository.findByIdAndDeletedFalse(id).orElseThrow(ObjectNotFoundException::new);
        log.info("Searching trainer...");
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(trainer);
    }


    public ResponseEntity<?> deleteById(Integer id) throws ObjectNotFoundException {
        Trainer trainer = trainerRepository.findByIdAndDeletedFalse(id).orElseThrow(ObjectNotFoundException::new);
        log.info("Deleting trainer...");
        trainer.setDeleted(1);
        trainer.setDeletedDate(new Date());
        trainerRepository.save(trainer);
        return ResponseEntity.status(HttpStatus.OK).body("Trainer: "+trainer.getName()+" was deleted.");
    }
}
