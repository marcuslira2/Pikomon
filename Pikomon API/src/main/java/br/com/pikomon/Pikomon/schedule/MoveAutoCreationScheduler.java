package br.com.pikomon.Pikomon.schedule;

import br.com.pikomon.Pikomon.modal.MoveRequestData;
import br.com.pikomon.Pikomon.persistence.Move;
import br.com.pikomon.Pikomon.repository.MoveRepository;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class MoveAutoCreationScheduler {


    private final MoveRepository moveRepository;

    private final RestTemplate restTemplate;


    private static final Logger log = LoggerFactory.getLogger(MoveAutoCreationScheduler.class);

    public MoveAutoCreationScheduler(MoveRepository moveRepository, RestTemplate restTemplate) {
        this.moveRepository = moveRepository;
        this.restTemplate = restTemplate;
    }

    @PostConstruct
    public void create (){
        try{
//            for (int i=1; i<901; i++){
            for (int i=1; i<166; i++){

                Optional<Move> isMoveExists = moveRepository.findById(i);
                if(isMoveExists.isEmpty()){
                    String url = "https://pokeapi.co/api/v2/move/";
                    MoveRequestData movesResquest = restTemplate.getForObject(url+i,MoveRequestData.class);
                    assert movesResquest !=null;
                    Move move = new Move();
                    move.setId(movesResquest.getId());
                    move.setName(movesResquest.getName());
                    move.setPower(movesResquest.getPower());
                    move.setPp(movesResquest.getPp());
                    move.setAccuracy(movesResquest.getAccuracy());
                    move.setDamageClass(movesResquest.getDamage_class().getName());
                    move.setPriority(movesResquest.getPriority());
                    move.setType(movesResquest.getType().getName());
                    String moveSaved = "Saving move: " + move.getName();
                    log.info(moveSaved);
                    moveRepository.save(move);
                }


            }
        }catch (Exception e){
            log.info(e.getMessage());
        }
    }
}
