package edu.esiea.catalog_service.dto.theme;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class ThemePostDto {
    @NotBlank(message = "Can't be blank")
    private String name;
}
