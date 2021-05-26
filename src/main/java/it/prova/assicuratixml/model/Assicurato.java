package it.prova.assicuratixml.model;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "assicurato")
public class Assicurato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;


    @NotNull()
    @DecimalMin("0.0")
    @Column(name = "nuovisinistri")
    private Float nuoviSinistri;

    @NotBlank()
    @Column(name = "nome")
    private String nome;

    @NotBlank()
    @Column(name = "cognome")
    private String cognome;

    @NotBlank()
    @Size(min = 16, max = 16)

    private String codiceFiscale;

    @NotNull()
    @Column(name = "datanascita")
    private Date dataNascita;

    public Assicurato() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getNuoviSinistri() {
        return nuoviSinistri;
    }

    public void setNuoviSinistri(Float nuoviSinistri) {
        this.nuoviSinistri = nuoviSinistri;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getCodiceFiscale() {
        return codiceFiscale;
    }

    public void setCodiceFiscale(String codiceFiscale) {
        this.codiceFiscale = codiceFiscale;
    }

    public Date getDataNascita() {
        return dataNascita;
    }

    public void setDataNascita(Date dataNascita) {
        this.dataNascita = dataNascita;
    }
}
