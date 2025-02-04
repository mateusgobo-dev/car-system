package br.com.mgobo.api.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "car")
@SequenceGenerator(name = "default_seq", sequenceName = "car_seq", allocationSize = 1)
public class Car extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(nullable = false)
    private String vehicle;

    @Column(nullable = false)
    private String potency;

    @JoinColumn(referencedColumnName = "id", name = "colorId")
    @ManyToOne
    private Color color;

    @JoinColumn(referencedColumnName = "id", name = "brandCategoryId")
    @ManyToOne
    private BrandCategory brandCategory;
}
