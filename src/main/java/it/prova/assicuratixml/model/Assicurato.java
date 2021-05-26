package it.prova.assicuratixml.model;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "assicurato")
public class Assicurato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull(message = "{esperienzaMin.notnull}")
    @DecimalMin("0.0")
    @Column(name = "esperienza_min")
    private Double esperienzaMin;

    @NotNull(message = "{cifraMinima.notnull}")
    @DecimalMin("0.0")
    @Column(name = "cifra_minima")
    private Double cifraMinima;

    @NotNull(message = "{denominazione.notblank}")
    @Column(name = "denominazione")
    private String denominazione;

    @NotNull(message = "{dataCreazione.notnull}")
    @Column(name = "data_creazione")
    private Date dataCreazione;

}
