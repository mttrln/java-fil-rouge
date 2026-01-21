package edu.esiea.tp_sb.dto.mappers;

import edu.esiea.tp_sb.domain.entity.LessonEntity;
import edu.esiea.tp_sb.dto.PageDto;
import edu.esiea.tp_sb.dto.lesson.LessonDto;
import edu.esiea.tp_sb.dto.lesson.LessonPostDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

@Mapper(uses = ThemeMapper.class)
public interface LessonMapper {
    LessonMapper INSTANCE = Mappers.getMapper(LessonMapper.class);
    
    @Mapping(source = "theme", target = "theme")
    LessonDto lessonEntityToDTO(LessonEntity lesson);
    
    LessonEntity lessonDTOToEntity(LessonPostDto lesson);

    default PageDto<LessonDto> toPageDto(Page<LessonEntity> entityPage) {
        return new PageDto<>(
                entityPage.getContent().stream()
                        .map(this::lessonEntityToDTO)
                        .toList(),
                entityPage.getNumber(),
                entityPage.getTotalPages()
        );
    }
}
