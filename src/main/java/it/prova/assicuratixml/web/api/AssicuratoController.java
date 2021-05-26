package it.prova.assicuratixml.web.api;

import it.prova.assicuratixml.generated.Assicurati;
import it.prova.assicuratixml.model.Assicurato;
import it.prova.assicuratixml.service.AssicuratoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/assicurato")
public class AssicuratoController {

    @Autowired
    private AssicuratoService assicuratoService;


    @GetMapping("/start")
    public void start() {
        try {
            //getting the xml file to read
            File file = new File("assicurati.xml");
            //creating the JAXB context
            JAXBContext jContext = JAXBContext.newInstance(Assicurati.class);
            //creating the unmarshall object
            Unmarshaller unmarshallerObj = jContext.createUnmarshaller();
            //calling the unmarshall method
            Assicurati assicurati = (Assicurati) unmarshallerObj.unmarshal(file);
            List<Assicurati.Assicurato> listAssicuratiPojo = assicurati.getAssicurato();
            List<Assicurato> listAssicuratiModel = new ArrayList<>();
            for (Assicurati.Assicurato item : listAssicuratiPojo) {

                Assicurato assicuratoModel = new Assicurato();
                assicuratoModel.setNuoviSinistri(item.getNuovisinistri());
                assicuratoModel.setCodiceFiscale(item.getCodicefiscale());
                assicuratoModel.setDataNascita(item.getDatanascita().toGregorianCalendar().getTime());
                assicuratoModel.setCognome(item.getCognome());
                assicuratoModel.setNome(item.getNome());
                listAssicuratiModel.add(assicuratoModel);
            }

            for (Assicurato item:listAssicuratiModel ) {
                Assicurato assicurato = null;
                if ((assicurato = assicuratoService.findByCodiceFiscaleAndNomeAndCognomeAndDataNascita(item.getCodiceFiscale(),item.getNome(),item.getCognome(),item.getDataNascita())  ) == null){
                    assicuratoService.inserisciNuovo(item);
                }
                else  {
                    assicuratoService.aggiorna(assicurato);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        // return new ResponseEntity<>(utenteService.trovaTavoloGiocatore(userInSessione), HttpStatus.OK);
    }

}
