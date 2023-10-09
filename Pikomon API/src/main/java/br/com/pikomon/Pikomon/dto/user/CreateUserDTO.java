package br.com.pikomon.Pikomon.dto.user;

import jakarta.validation.constraints.NotBlank;

public record CreateUserDTO(@NotBlank String login, @NotBlank String name, @NotBlank String password ) {
}
