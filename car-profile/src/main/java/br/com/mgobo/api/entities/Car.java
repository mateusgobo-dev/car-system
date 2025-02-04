package br.com.mgobo.api.entities;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "car")
@SequenceGenerator(sequenceName = "car_seq", name = "car_seq", allocationSize = 1)
public record Car (
        @Id
        Long id,
        String vehicle,
        String potency,

        @JoinColumn(referencedColumnName = "id", name = "colorId")
        @ManyToOne
        Color color,

        @JoinColumn(referencedColumnName = "id", name = "brandCategoryId")
        @ManyToOne
         BrandCategory brandCategory
) implements Serializable {
    private static final long serialVersionUID = 1L;
}
