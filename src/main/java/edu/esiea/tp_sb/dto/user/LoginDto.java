package edu.esiea.tp_sb.dto.user;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDto {
    @NotBlank(message = "Can't be blank")
    public String username;

    @NotBlank(message = "Can't be blank")
    public String password;
}