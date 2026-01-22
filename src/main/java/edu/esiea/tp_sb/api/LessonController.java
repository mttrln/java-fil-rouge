package edu.esiea.tp_sb.api;

import edu.esiea.tp_sb.app.LessonService;
import edu.esiea.tp_sb.dto.PageDto;
import edu.esiea.tp_sb.dto.lesson.LessonDto;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/lessons")
class LessonController {
    private final LessonService lessonService;

    public LessonController(LessonService lessonService) {
        this.lessonService = lessonService;
    }

    @GetMapping
    @PreAuthorize("hasRole('LEARNER')")
    public PageDto<LessonDto> getLessons(@RequestParam int page, @RequestParam int pageSize)
    {
        return lessonService.getLessons(page, pageSize);
    }
}
