package br.com.mgobo.web.dto;

import lombok.Builder;

import java.io.Serializable;

@Builder
public record BrandCategoryDto(
        Long id,
        Long categoryId,
        Long branchId
) implements Serializable {
    private static final long serialVersionUID = 1L;
}
