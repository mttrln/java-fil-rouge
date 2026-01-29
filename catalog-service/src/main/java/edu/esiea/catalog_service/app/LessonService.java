package edu.esiea.catalog_service.app;

import edu.esiea.catalog_service.domain.repository.LessonRepository;
import edu.esiea.catalog_service.dto.PageDto;
import edu.esiea.catalog_service.dto.lesson.LessonDto;
import edu.esiea.catalog_service.dto.mappers.LessonMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LessonService {
    private final LessonRepository lessonRepository;

    public LessonService(LessonRepository lessonRepository) {
        this.lessonRepository = lessonRepository;
    }

    public PageDto<LessonDto> getLessons(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        var pages = lessonRepository.findAll(pageable);
        return LessonMapper.INSTANCE.toPageDto(pages);
    }
}
