package br.com.mgobo.api.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "brand_category")
@SequenceGenerator(name = "default_seq", sequenceName = "brand_category_seq", allocationSize = 1)
public class BrandCategory extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @JoinTable(joinColumns = {
            @JoinColumn(name = "brandId", referencedColumnName = "id")
    }, inverseJoinColumns = {@JoinColumn(name = "brandCategoryId")}, name = "brand_ref")
    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Brand> brand = new HashSet<>();

    @JoinTable(joinColumns = {
            @JoinColumn(name = "categoryId", referencedColumnName = "id")
    }, inverseJoinColumns = {@JoinColumn(name = "brandCategoryId")}, name = "brand_ref")
    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Category> category = new HashSet<>();
}
