package br.com.mgobo.web.dto;

import java.io.Serializable;

public record CategoryDto(
        Long id,
        String name
) implements Serializable {
    private static final long serialVersionUID = 1L;
}
