package edu.esiea.tp_sb.api;

import edu.esiea.tp_sb.app.ThemeService;
import edu.esiea.tp_sb.dto.PageDto;
import edu.esiea.tp_sb.dto.lesson.LessonDto;
import edu.esiea.tp_sb.dto.lesson.LessonPostDto;
import edu.esiea.tp_sb.dto.theme.ThemeDto;
import edu.esiea.tp_sb.dto.theme.ThemePostDto;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/themes")
public class ThemeController {
    private final ThemeService themeService;

    public ThemeController(ThemeService themeService) {
        this.themeService = themeService;
    }

    @GetMapping
    @PreAuthorize("hasRole('LEARNER')")
    public PageDto<ThemeDto> getThemes(@RequestParam int page, @RequestParam int pageSize)
    {
        return themeService.getThemes(page, pageSize);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteTheme(@PathVariable Long id)
    {
        themeService.deleteTheme(id);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ThemeDto createTheme(@Valid @RequestBody ThemePostDto themeDto)
    {
        return themeService.createTheme(themeDto);
    }

    @PostMapping("/{id}/lessons")
    @PreAuthorize("hasAnyRole('AUTHOR', 'ADMIN')")
    public LessonDto createLesson(@Valid @RequestBody LessonPostDto lessonDto, @PathVariable long id)
    {
        return themeService.createLesson(lessonDto, id);
    }
}
