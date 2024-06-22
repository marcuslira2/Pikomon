package br.com.pikomon.pikomon.service;

import br.com.pikomon.pikomon.persistence.Move;
import br.com.pikomon.pikomon.repository.MoveRepository;
import org.springframework.stereotype.Service;

@Service
public class MoveService {


    private final MoveRepository moveRepository;

    public MoveService(MoveRepository moveRepository) {
        this.moveRepository = moveRepository;
    }


    public Move findMoveById(Integer id){
        Move move = moveRepository.findById(id).orElseThrow(() -> new RuntimeException("Move note found"));
        return move;
    }

    public Move findMoveByName(String name){
        Move move = moveRepository.findByName(name);
        return move;
    }
}
