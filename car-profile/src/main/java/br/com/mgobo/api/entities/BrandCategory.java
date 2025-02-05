package br.com.mgobo.api.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "brand_category")
@SequenceGenerator(name = "default_seq", sequenceName = "brand_category_seq", allocationSize = 1)
public class BrandCategory extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @JoinColumn(name = "brandId", referencedColumnName = "id")
    @ManyToOne
    private Brand brand;

    @JoinColumn(name = "categoryId", referencedColumnName = "id")
    @ManyToOne
    private Category category;
}
