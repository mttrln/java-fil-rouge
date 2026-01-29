package edu.esiea.auth_service.domain.entity;

import edu.esiea.auth_service.domain.enums.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idUser;

    @Column(unique = true)
    @NotNull
    private String login;

    @Column
    @NotNull
    private String password;

    @Column
    @Enumerated(EnumType.STRING)
    @NotNull
    private Role role;
}
