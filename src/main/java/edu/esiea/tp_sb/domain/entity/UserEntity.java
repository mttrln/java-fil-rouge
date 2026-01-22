package edu.esiea.tp_sb.domain.entity;

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


    @Column(unique = true) @NotNull
    private String login;

    @Column(unique = true) @NotNull
    private String password;

    @Column(unique = true) @Enumerated(EnumType.STRING) @NotNull
    private Role role;
}

enum Role {
    ADMIN,
    USER
}