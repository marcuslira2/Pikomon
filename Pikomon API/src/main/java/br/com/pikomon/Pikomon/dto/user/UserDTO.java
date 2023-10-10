package br.com.pikomon.Pikomon.dto.user;

import br.com.pikomon.Pikomon.persistence.Trainer;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record UserDTO(@NotNull Integer id,@NotBlank String name, List<Trainer> trainers) {
}
