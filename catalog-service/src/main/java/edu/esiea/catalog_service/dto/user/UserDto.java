package edu.esiea.catalog_service.dto.user;

import edu.esiea.catalog_service.domain.enums.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    private Long idUser;
    private String login;
    private Role role;
}
