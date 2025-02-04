package br.com.mgobo.api.entities;

import jakarta.persistence.*;
import lombok.Builder;

import java.io.Serializable;
import java.util.Set;

@Builder
@Entity(name = "category")
@SequenceGenerator(name = "category_seq", sequenceName = "category_seq", allocationSize = 1)
public record Category(
        @Id
        @GeneratedValue(generator = "category_seq")
        Long id,
        String name,

        @ManyToMany(mappedBy = "category")
        Set<BrandCategory> brandCategoryCollection
) implements Serializable {
    private static final long serialVersionUID = 1L;
}
