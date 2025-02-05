package br.com.mgobo.web.dto;

import java.io.Serializable;

public record BrandCategoryDto(
        Long id,
        Long categoryId,
        Long brandId
) implements Serializable {
    private static final long serialVersionUID = 1L;
}
