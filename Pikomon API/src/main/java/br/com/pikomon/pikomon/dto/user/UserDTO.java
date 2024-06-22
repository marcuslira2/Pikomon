package br.com.pikomon.pikomon.dto.user;

import br.com.pikomon.pikomon.persistence.Trainer;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record UserDTO(@NotNull Integer id,@NotBlank String login, List<Trainer> trainers) {
}
