package br.com.pikomon.Pikomon.dto.user;

import jakarta.validation.constraints.NotBlank;

public record CreateUserDTO(@NotBlank(message = "{login.not.null.or.blank}") String login, @NotBlank String password ) {
}
