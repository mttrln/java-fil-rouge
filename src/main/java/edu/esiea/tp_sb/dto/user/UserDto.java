package edu.esiea.tp_sb.dto.user;

import edu.esiea.tp_sb.domain.enums.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    private Long idUser;
    private String login;
    private Role role;
}
