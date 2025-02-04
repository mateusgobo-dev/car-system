package br.com.mgobo.api.entities;


import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "car")
@SequenceGenerator(sequenceName = "car_seq", name = "car_seq", allocationSize = 1)
public record Color (
        @Id
        @GeneratedValue(generator = "color_seq", strategy = GenerationType.SEQUENCE)
        Long id,
        String description
) implements Serializable {
    private static final long serialVersionUID = 1L;
}
