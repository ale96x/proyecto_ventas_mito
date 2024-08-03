package com.mitocode.model;

import com.mitocode.dto.IProcedureDTO;
import com.mitocode.dto.ProcedureDTO;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity

@NamedNativeQuery(
        name = "Sale.fn_sales",
        query = "select * from fn_sales()",
        resultSetMapping = "Procedure.ProcedureDTO"
)
@SqlResultSetMapping(
        name = "Procedure.ProcedureDTO",
        classes = @ConstructorResult(targetClass = ProcedureDTO.class,
                columns = {@ColumnResult(name = "quantityfn", type = Integer.class),
                        @ColumnResult(name = "datetimefn", type = String.class)}
        )
)

@NamedStoredProcedureQuery(
        name = "getFnSales",
        procedureName = "fn_sales",
        resultClasses = IProcedureDTO.class
)

@NamedStoredProcedureQuery(
        name = "getFnSales2",
        procedureName = "fn_salesParameter",
        resultClasses = IProcedureDTO.class,
        parameters = {
                @StoredProcedureParameter(name = "p_id_client", type = Integer.class, mode = ParameterMode.IN)
        }
)
public class Sale {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idSale")
    private Integer idSale;

    @ManyToOne
    @JoinColumn(name = "id_client", nullable = false, foreignKey = @ForeignKey(name = "fk_sale_client"))
    private Client client;

    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false, foreignKey = @ForeignKey(name = "fk_sale_user"))
    private User user;

    @Column(name = "datetime", nullable = false)
    private LocalDateTime datetime;

    @Column(name = "total", columnDefinition = "decimal(6,2)", nullable = false)
    private double total;

    @Column(name = "tax", columnDefinition = "decimal(6,2)", nullable = false)
    private double tax;

    @Column(name = "enabled", nullable = false)
    private boolean enabled;

    @OneToMany(mappedBy = "sale", cascade = CascadeType.ALL, fetch = FetchType.EAGER)   //devuelve una consulta asociada a este Sale
    private List<SaleDetail> details;
}
