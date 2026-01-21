package edu.esiea.tp_sb.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class ThemeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTheme;

    @Column(unique = true) @NotNull
    private String name;

    @OneToMany(mappedBy = "theme", cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.DETACH, CascadeType.REFRESH}, targetEntity = LessonEntity.class, fetch = FetchType.LAZY)
    List<LessonEntity> lessons;
}
