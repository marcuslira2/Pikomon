package br.com.pikomon.Pikomon.dto;

import br.com.pikomon.Pikomon.persistence.Trainer;

import java.util.List;

public record UserDTO(Integer id,String name, List<Trainer> trainers) {
}
