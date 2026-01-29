package edu.esiea.gateway_service.app;

import edu.esiea.gateway_service.domain.entity.ThemeEntity;
import edu.esiea.gateway_service.domain.repository.LessonRepository;
import edu.esiea.gateway_service.domain.repository.ThemeRepository;
import edu.esiea.gateway_service.dto.PageDto;
import edu.esiea.gateway_service.dto.lesson.LessonDto;
import edu.esiea.gateway_service.dto.lesson.LessonPostDto;
import edu.esiea.gateway_service.dto.mappers.LessonMapper;
import edu.esiea.gateway_service.dto.mappers.ThemeMapper;
import edu.esiea.gateway_service.dto.theme.ThemeDto;
import edu.esiea.gateway_service.dto.theme.ThemePostDto;
import jakarta.persistence.EntityManager;
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
