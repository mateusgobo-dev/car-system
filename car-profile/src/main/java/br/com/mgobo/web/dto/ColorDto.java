package br.com.mgobo.web.dto;

import lombok.Builder;

@Builder
public record ColorDto(Long id,
                       String description) {
    public ColorDto{
        if(id == null) id = null;
    }
}
