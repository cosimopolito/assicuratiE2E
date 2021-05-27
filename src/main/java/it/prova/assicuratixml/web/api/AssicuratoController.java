package it.prova.assicuratixml.web.api;

import it.prova.assicuratixml.generated.Assicurati;
import it.prova.assicuratixml.model.Assicurato;
import it.prova.assicuratixml.service.AssicuratoService;
import it.prova.assicuratixml.utlity.ConvertionUtlity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.util.ArrayList;
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


            List<Assicurato> assicuratiProcessati = new ArrayList<>();
            List<Assicurati.Assicurato> assicuratiScartati = new ArrayList<>();


                    List<Assicurato> assicuratiModel=  ConvertionUtlity.convertListPojoToModel(listAssicuratiPojo);


            for (Assicurato modelItem : assicuratiModel) {
                if (modelItem.getNuoviSinistri() < 10) {
                    Assicurato assicuratoModelDB = null;
                    if ((assicuratoModelDB = assicuratoService.findByCodiceFiscaleAndNomeAndCognomeAndDataNascita(modelItem.getCodiceFiscale(), modelItem.getNome(), modelItem.getCognome(), modelItem.getDataNascita())) == null) {
                        assicuratoService.inserisciNuovo(modelItem);
                    } else {
                        modelItem.setId(assicuratoModelDB.getId());
                        Assicurato assicuratoAggiornato = assicuratoService.aggiorna(modelItem);
                    }

                    assicuratiProcessati.add(modelItem);

                } else {
                    assicuratiScartati.add(ConvertionUtlity.convertModelToPojo(modelItem));
                }
            }

            if (!assicuratiProcessati.isEmpty()){
                List<Assicurati.Assicurato> listPojo =ConvertionUtlity.convertListModelToPojo(assicuratiProcessati);
                ConvertionUtlity.convertToXml(listPojo, new File("processati.xml"));
            }
            if (!assicuratiScartati.isEmpty()) {
                ConvertionUtlity.convertToXml(assicuratiScartati, new File("scartati.xml"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        // return new ResponseEntity<>(utenteService.trovaTavoloGiocatore(userInSessione), HttpStatus.OK);
    }


}
