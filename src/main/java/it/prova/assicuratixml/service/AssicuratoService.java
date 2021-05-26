package it.prova.assicuratixml.service;



import it.prova.assicuratixml.model.Assicurato;

import java.util.Date;
import java.util.List;

public interface AssicuratoService {


    Assicurato caricaSingoloAssicurato(Long id);

    Assicurato aggiorna( Assicurato assicuratoInstance);

    Assicurato inserisciNuovo( Assicurato assicuratoInstance);

    void rimuovi( Assicurato assicuratoInstance) throws Exception;

    Assicurato findByCodiceFiscaleAndNomeAndCognomeAndDataNascita( String codiceFiscale, String nome, String cognome, Date dataNascita);

}
