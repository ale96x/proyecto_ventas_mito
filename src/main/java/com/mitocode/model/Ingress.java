package com.mitocode.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Ingress {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idIngress")
    private Integer idIngress;

    @ManyToOne
    @JoinColumn(name = "id_provider", nullable = false, foreignKey = @ForeignKey(name = "fk_ingress_provider"))
    private Provider provider;

    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false, foreignKey = @ForeignKey(name = "fk_ingress_user"))
    private User user;

    @Column(name = "datetime", nullable = false)
    private LocalDateTime datetime;

    @Column(name = "total", columnDefinition = "decimal(6,2)", nullable = false)
    private double total;

    @Column(name = "tax", columnDefinition = "decimal(6,2)", nullable = false)
    private double tax;

    @Column(name = "serialNumber", nullable = false)
    private String serialNumber;

    @Column(name = "enabled", nullable = false)
    private boolean enabled;
}
