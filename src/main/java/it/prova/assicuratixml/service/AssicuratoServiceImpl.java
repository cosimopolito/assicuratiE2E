package it.prova.assicuratixml.service;

import it.prova.assicuratixml.model.Assicurato;
import it.prova.assicuratixml.repository.AssicuratoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class AssicuratoServiceImpl implements AssicuratoService {

    @Autowired
    private AssicuratoRepository repository;

    @Override
    public  Assicurato caricaSingoloAssicurato(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public  Assicurato aggiorna( Assicurato assicuratoInstance) {
        return repository.save(assicuratoInstance);
    }

    @Override
    public  Assicurato inserisciNuovo( Assicurato assicuratoInstance) {
        return repository.save(assicuratoInstance);
    }

    @Override
    public void rimuovi( Assicurato assicuratoInstance) throws Exception {
        repository.delete(assicuratoInstance);
    }

    @Override
    public Assicurato findByCodiceFiscaleAndNomeAndCognomeAndDataNascita(String codiceFiscale, String nome, String cognome, Date dataNascita) {
      return repository.findByCodiceFiscaleAndNomeAndCognomeAndDataNascita(codiceFiscale,nome,cognome,dataNascita);
    }

}


