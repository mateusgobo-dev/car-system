package br.com.mgobo.web.mappers;

import br.com.mgobo.api.entities.Brand;
import br.com.mgobo.api.entities.Car;
import br.com.mgobo.api.entities.Category;
import br.com.mgobo.web.dto.BrandDto;
import br.com.mgobo.web.dto.CategoryDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CategoryMapper {
    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);
    CategoryDto toDto(Category category);

    Category toEntity(CategoryDto brandDto);
}
