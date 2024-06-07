package br.com.pikomon.Pikomon.controller;

import br.com.pikomon.Pikomon.dto.battle.BattleActionDTO;
import br.com.pikomon.Pikomon.dto.battle.CreateBattleDTO;
import br.com.pikomon.Pikomon.persistence.Battle;
import br.com.pikomon.Pikomon.service.BattleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/battle")
public class BattleController {

    @Autowired
    private BattleService battleService;

    @PostMapping()
    public ResponseEntity<Battle> create(@RequestBody CreateBattleDTO dto) throws Exception {
        Battle battle = battleService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(battle);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Battle> findBattle(@PathVariable Long id) throws Exception {
        Battle battle = battleService.findById(id);
        return ResponseEntity.status(HttpStatus.FOUND).body(battle);
    }

    @PostMapping(path = "/{uuid}")
    public ResponseEntity<String> makeAMove(@PathVariable String uuid,@RequestBody BattleActionDTO dto) throws Exception {
        String battle = battleService.makeAMove(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(battle);
    }


}
