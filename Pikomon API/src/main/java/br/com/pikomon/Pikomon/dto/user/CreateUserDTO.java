package br.com.pikomon.Pikomon.dto.user;

import jakarta.validation.constraints.NotNull;

public record CreateUserDTO(@NotNull String login,@NotNull String name,@NotNull String password ) {
}
