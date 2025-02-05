package br.com.mgobo.web.mappers;

import br.com.mgobo.api.entities.Car;
import br.com.mgobo.web.dto.CarDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CarMapper {
    CarMapper INSTANCE = Mappers.getMapper(CarMapper.class);

    @Mappings(value = {
            @Mapping(source = "color.id", target = "colorId"),
            @Mapping(source = "brandCategory.brand.id", target = "brandId"),
            @Mapping(source = "brandCategory.category.id", target = "categoryId")
    })
    CarDto toDto(Car car);

    @Mappings(value = {
            @Mapping(target = "color.id", source = "colorId"),
            @Mapping(target = "brandCategory.brand.id", source = "brandId"),
            @Mapping(target = "brandCategory.category.id", source = "categoryId")
    })
    Car toEntity(CarDto carMapper);
}
