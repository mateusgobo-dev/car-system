package br.com.mgobo.web.mappers;

import br.com.mgobo.api.entities.BrandCategory;
import br.com.mgobo.web.dto.BrandCategoryDto;
import br.com.mgobo.web.dto.BrandDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BrandCategoryMapper {
    BrandCategoryMapper INSTANCE = Mappers.getMapper(BrandCategoryMapper.class);

    BrandDto toDto(BrandCategory brandCategory);
    BrandCategory toEntity(BrandCategoryDto brandCategoryDto);
}
