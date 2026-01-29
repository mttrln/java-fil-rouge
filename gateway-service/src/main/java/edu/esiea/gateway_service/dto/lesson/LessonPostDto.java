package edu.esiea.gateway_service.dto.lesson;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class LessonPostDto {
    @NotNull
    @NotBlank
    public String name;
    @NotNull
    public int durationMinutes;
}
