package br.com.mgobo.web.mappers;

import br.com.mgobo.api.entities.BrandCategory;
import br.com.mgobo.web.dto.BrandCategoryDto;
import br.com.mgobo.web.dto.BrandDto;
import jakarta.persistence.ManyToOne;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BrandCategoryMapper {
    BrandCategoryMapper INSTANCE = Mappers.getMapper(BrandCategoryMapper.class);

    @Mappings({
            @Mapping(source = "brand.id", target = "brandId"),
            @Mapping(source = "category.id", target = "categoryId")
    })
    BrandCategoryDto toDto(BrandCategory brandCategory);

    @Mappings({
            @Mapping(target = "brand.id", source = "brandId"),
            @Mapping(target = "category.id", source = "categoryId")
    })
    BrandCategory toEntity(BrandCategoryDto brandCategoryDto);

}
