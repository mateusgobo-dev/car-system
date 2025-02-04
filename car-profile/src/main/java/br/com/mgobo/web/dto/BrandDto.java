package br.com.mgobo.web.dto;

import lombok.Builder;

import java.io.Serializable;

@Builder
public record BrandDto(Long id,
                       String name) implements Serializable {
    private static final long serialVersionUID = 1L;
    public BrandDto {
        if (id == null) id = null;
    }
}
