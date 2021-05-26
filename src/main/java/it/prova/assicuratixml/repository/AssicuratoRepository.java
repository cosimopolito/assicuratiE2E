package it.prova.assicuratixml.repository;


import it.prova.assicuratixml.model.Assicurato;

import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface AssicuratoRepository extends CrudRepository<Assicurato, Long>   {

    Assicurato findByCodiceFiscaleAndNomeAndCognomeAndDataNascita(String codiceFiscale, String nome, String cognome, Date dataNascita);

}
