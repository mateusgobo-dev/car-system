package br.com.mgobo.web.dto;

import java.io.Serializable;

public record ColorDto(Long id,
                       String description) implements Serializable {
    private static final long serialVersionUID = 1L;
    public ColorDto{
        if(id == null) id = null;
    }
}
