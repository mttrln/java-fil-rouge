package edu.esiea.tp_sb.dto.lesson;

import edu.esiea.tp_sb.dto.theme.ThemeDto;
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
