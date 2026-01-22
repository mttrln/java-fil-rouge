package edu.esiea.tp_sb.app;

import edu.esiea.tp_sb.domain.entity.ThemeEntity;
import edu.esiea.tp_sb.domain.repository.LessonRepository;
import edu.esiea.tp_sb.domain.repository.ThemeRepository;
import edu.esiea.tp_sb.dto.PageDto;
import edu.esiea.tp_sb.dto.lesson.LessonDto;
import edu.esiea.tp_sb.dto.lesson.LessonPostDto;
import edu.esiea.tp_sb.dto.mappers.LessonMapper;
import edu.esiea.tp_sb.dto.mappers.ThemeMapper;
import edu.esiea.tp_sb.dto.theme.ThemeDto;
import edu.esiea.tp_sb.dto.theme.ThemePostDto;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ThemeService {
    private final ThemeRepository themeRepository;
    private final LessonRepository lessonRepository;
    private final EntityManager entityManager;

    public ThemeService(ThemeRepository themeRepository, LessonRepository lessonRepository, EntityManager entityManager) {
        this.themeRepository = themeRepository;
        this.lessonRepository = lessonRepository;
        this.entityManager = entityManager;
    }

    public PageDto<ThemeDto> getThemes(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        var pages = themeRepository.findAll(pageable);
        return ThemeMapper.INSTANCE.toPageDto(pages);
    }

    public ThemeDto createTheme(ThemePostDto themeDto) {
        return ThemeMapper.INSTANCE.themeEntityToDTO(themeRepository.save(ThemeMapper.INSTANCE.dtoToThemeEntity(themeDto)));
    }

    public LessonDto createLesson(LessonPostDto lessonDto, long themeId) {
        var theme = entityManager.getReference(ThemeEntity.class, themeId);
        var lecture = LessonMapper.INSTANCE.lessonDTOToEntity(lessonDto);
        lecture.setTheme(theme);
        return LessonMapper.INSTANCE.lessonEntityToDTO(lessonRepository.save(lecture));
    }

    public void deleteTheme(Long id) {
        themeRepository.deleteById(id);
    }
}
