package edu.esiea.catalog_service.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class LessonEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idLesson;

    @Column @NotNull
    private String name;

    @Column
    private int durationMinutes;

    @ManyToOne(targetEntity = ThemeEntity.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "theme_id", nullable = false, foreignKey = @ForeignKey(name = "fk_lesson_theme"))
    ThemeEntity theme;
}
