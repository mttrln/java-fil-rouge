package edu.esiea.gateway_service.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class ThemeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idTheme;

    @Column(unique = true, nullable = false)
    private String name;

    @OneToMany(mappedBy = "theme", cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.DETACH, CascadeType.REFRESH}, targetEntity = LessonEntity.class, fetch = FetchType.LAZY)
    List<LessonEntity> lessons;
}
