package br.com.pikomon.Pikomon.service;

import br.com.pikomon.Pikomon.persistence.Pokemon;
import br.com.pikomon.Pikomon.persistence.Trainer;
import br.com.pikomon.Pikomon.repository.TrainerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TrainerService {

    @Autowired
    TrainerRepository trainerRepository;

    @Autowired
    PokemonService pokemonService;

    public Trainer save(String name, Integer money, Integer pokemonId) {
        Trainer trainer = new Trainer();

        trainer.setName(name);
        trainer.setMoney(money);
        trainer.setCreateDate(new Date());
        Pokemon pokemon = pokemonService.save(pokemonId,5,name);
        trainer.add(pokemon);
        trainer.setDeleted(false);
        return trainerRepository.save(trainer);


    }

    public List<Trainer> listAll() {
        return  trainerRepository.findAll().stream().filter(trainer -> !trainer.getDeleted()).toList();
    }

    public Optional<Trainer> findById(Integer id) {
        return trainerRepository.findById(id);
    }


    public void deleteById(Integer id) {
        Optional<Trainer> trainer = this.findById(id);
        if (trainer.isPresent()){
            trainer.get().setDeleted(true);
            trainer.get().setDeletedDate(new Date());
            trainerRepository.save(trainer.get());
        }

    }
}
