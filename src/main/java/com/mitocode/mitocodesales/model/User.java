package com.mitocode.mitocodesales.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "user_data")
public class User {

    @EqualsAndHashCode.Include
    @Id
    @Column(name = "id_user")
    private Integer idUser;

    //Columna Foreig Key
    @ManyToOne
    @JoinColumn(name = "id_role", nullable = false, foreignKey = @ForeignKey(name = "fk_user_role"))
    private Role role;

    @Column(name = "username", length = 50, nullable = false)
    private String username;

    @Column(name = "password", length = 60, nullable = false)
    private String password;

    @Column(name = "enabled", nullable = false)
    private boolean enabled;
}
