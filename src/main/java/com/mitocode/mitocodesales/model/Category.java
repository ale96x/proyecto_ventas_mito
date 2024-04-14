package com.mitocode.mitocodesales.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Category {
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_category")
//    @SequenceGenerator()    con esto podemos crear una secuencia personalizada y referenciarlo aca
    private Integer idCategory;

    @Column(name = "description", nullable = false, length = 150)
    private String description;

    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @Column(name = "enabled", nullable = false)
    private boolean enabled;

    public Category(String description, String name) {
        this.description = description;
        this.name = name;
    }
}
