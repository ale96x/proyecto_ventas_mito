package com.mitocode.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Role {
    @EqualsAndHashCode.Include
    @Id
    @Column(name = "id_role")
    private Integer idRole;

    @Column(name = "name", nullable = false, length = 10)
    private String name;

    @Column(name = "enabled", nullable = false)
    private boolean enabled;
}
