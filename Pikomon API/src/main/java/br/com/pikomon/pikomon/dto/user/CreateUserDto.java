package br.com.pikomon.pikomon.dto.user;

import jakarta.validation.constraints.NotBlank;

public record CreateUserDto(@NotBlank(message = "{login.not.null.or.blank}") String login, @NotBlank String password ) {
}
