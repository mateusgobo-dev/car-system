package br.com.mgobo.api.entities;

import jakarta.persistence.*;
import lombok.Builder;

import java.io.Serializable;
import java.util.Set;

@Builder
@Entity(name = "brand")
@SequenceGenerator(name = "brand_seq", sequenceName = "brand_seq", allocationSize = 1)
public record Brand(
        @Id
        @GeneratedValue(generator = "brand_seq")
        Long id,
        String name,

        @ManyToMany(mappedBy = "brand")
        Set<BrandCategory> brandCategoryCollection
) implements Serializable {
    private static final long serialVersionUID = 1L;
}
