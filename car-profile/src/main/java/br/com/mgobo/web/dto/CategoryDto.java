package br.com.mgobo.web.dto;

import lombok.Builder;

import java.io.Serializable;

@Builder
public record CategoryDto(
        Long id,
        String name
) implements Serializable {
    private static final long serialVersionUID = 1L;
}
