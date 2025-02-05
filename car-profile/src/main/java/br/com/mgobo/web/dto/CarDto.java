package br.com.mgobo.web.dto;


import java.io.Serializable;

public record CarDto(Long id,
                     String vehicle,
                     String potency,
                     Long colorId,
                     Long brandId,
                     Long categoryId) implements Serializable {
    private static final long serialVersionUID	= 1L;
}
