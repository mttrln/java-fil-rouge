package edu.esiea.catalog_service.dto.user;

import edu.esiea.catalog_service.domain.enums.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserPostDto {
    @NotBlank(message = "Can't be blank")
    private String login;
    @NotBlank(message = "Can't be blank")
    private String password;
    @NotNull(message = "Can't be null")
    private Role role;
}
