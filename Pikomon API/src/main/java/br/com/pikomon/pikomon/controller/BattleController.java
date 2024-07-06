package br.com.pikomon.pikomon.controller;

import br.com.pikomon.pikomon.dto.battle.BattleActionDTO;
import br.com.pikomon.pikomon.dto.battle.CreateBattleDTO;
import br.com.pikomon.pikomon.persistence.Battle;
import br.com.pikomon.pikomon.service.BattleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/battle")
public class BattleController {


    private final BattleService battleService;

    public BattleController(BattleService battleService) {
        this.battleService = battleService;
    }

    @PostMapping()
    public ResponseEntity<Battle> create(@RequestBody CreateBattleDTO dto) {
        Battle battle = battleService.create(dto);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(battle);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Battle> findBattle(@PathVariable Long id) {
        Battle battle = battleService.findById(id);
        return ResponseEntity.status(HttpStatus.FOUND).body(battle);
    }

    @PostMapping(path = "/{uuid}")
    public ResponseEntity<String> makeAMove(@PathVariable String uuid,@RequestBody BattleActionDTO dto) {
        String battle = battleService.makeAMove(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(battle);
    }


}
