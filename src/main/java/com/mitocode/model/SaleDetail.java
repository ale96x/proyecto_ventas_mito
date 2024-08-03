package com.mitocode.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class SaleDetail {
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sale_detail")
    private Integer idSaleDetail;

    @ManyToOne
    @JoinColumn(name = "id_sale", nullable = false, foreignKey = @ForeignKey(name = "fk_detail_sale"))
    private Sale sale;

    @ManyToOne
    @JoinColumn(name = "id_product", nullable = false, foreignKey = @ForeignKey(name = "fk_sale_detail_product"))
    private Product product;

    @Column(name = "quantity", nullable = false)
    private short quantity;

    @Column(name = "sale_price", columnDefinition = "decimal(6,2)", nullable = false, length = 50)
    private double salePrice;

    @Column(name = "discount", columnDefinition = "decimal(6,2)", nullable = false, length = 50)
    private double discount;
}
