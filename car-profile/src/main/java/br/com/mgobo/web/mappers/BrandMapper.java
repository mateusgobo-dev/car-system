package br.com.mgobo.web.mappers;

import br.com.mgobo.api.entities.Brand;
import br.com.mgobo.web.dto.BrandDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BrandMapper {
    BrandMapper INSTANCE = Mappers.getMapper(BrandMapper.class);

    BrandDto toDto(Brand brand);

    Brand toEntity(BrandDto brandDto);
}
