package br.com.mgobo.web.mappers;

import br.com.mgobo.api.entities.Color;
import br.com.mgobo.web.dto.ColorDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ColorMapper {
    ColorMapper INSTANCE = Mappers.getMapper(ColorMapper.class);

    Color toEntity(ColorDto colorDto);

    ColorDto toDto(Color color);
}
