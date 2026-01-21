package edu.esiea.tp_sb.dto.mappers;

import edu.esiea.tp_sb.domain.entity.ThemeEntity;
import edu.esiea.tp_sb.dto.PageDto;
import edu.esiea.tp_sb.dto.theme.ThemeDto;
import edu.esiea.tp_sb.dto.theme.ThemePostDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

@Mapper
public interface ThemeMapper {
    ThemeMapper INSTANCE = Mappers.getMapper(ThemeMapper.class);
    ThemeDto themeEntityToDTO(ThemeEntity theme);
    ThemeEntity dtoToThemeEntity(ThemePostDto theme);

    default PageDto<ThemeDto> toPageDto(Page<ThemeEntity> entityPage) {
        return new PageDto<>(
                entityPage.getContent().stream()
                        .map(this::themeEntityToDTO)
                        .toList(),
                entityPage.getNumber(),
                entityPage.getTotalPages()
        );
    }
}
