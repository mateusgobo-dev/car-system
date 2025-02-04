package br.com.mgobo.api.entities;

import jakarta.persistence.*;
import lombok.Builder;

import java.io.Serializable;
import java.util.Set;

@Builder
@Entity
@Table(name = "brand_category")
@SequenceGenerator(name = "brand_category_seq", sequenceName = "brand_category_seq", allocationSize = 1)
public record BrandCategory(
        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "brand_category_seq")
        Long id,

        @JoinTable(joinColumns = {
                @JoinColumn(name = "brandId", referencedColumnName = "id")
        }, inverseJoinColumns = {@JoinColumn(name="brandCategoryId")}, name = "brand_ref")
        @ManyToMany
        Set<Brand> brand,

        @JoinTable(joinColumns = {
                @JoinColumn(name = "categoryId", referencedColumnName = "id")
        },  inverseJoinColumns = {@JoinColumn(name="brandCategoryId")}, name = "brand_ref")
        @ManyToMany
        Set<Category> category
) implements Serializable {
    private static final long serialVersionUID = 1L;
}
