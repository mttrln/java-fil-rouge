package edu.esiea.auth_service.dto.lesson;

import edu.esiea.auth_service.dto.theme.ThemeDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LessonDto {
    public long id;
    public String name;
    public int durationMinutes;
    public ThemeDto theme;
}
