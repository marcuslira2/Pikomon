package br.com.pikomon.Pikomon.service;

import br.com.pikomon.Pikomon.persistence.Move;
import br.com.pikomon.Pikomon.repository.MoveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MoveService {

    @Autowired
    private MoveRepository moveRepository;

    public Move findMoveById(Integer id){
        Move move = moveRepository.findById(id).orElseThrow(() -> new RuntimeException("Move note found"));
        return move;
    }

    public Move findMoveByName(String name){
        Move move = moveRepository.findByName(name);
        return move;
    }
}
