package it.prova.assicuratixml.utlity;

import it.prova.assicuratixml.generated.Assicurati;
import it.prova.assicuratixml.model.Assicurato;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

public class ConvertionUtlity {

    public static List<Assicurati.Assicurato> convertListModelToPojo(List<Assicurato> list) {
        List<Assicurati.Assicurato> listPojo = new ArrayList<>();
        for (Assicurato itemModel : list) {
            Assicurati.Assicurato assicuratoPojo = new Assicurati.Assicurato();
            assicuratoPojo.setNuovisinistri(itemModel.getNuoviSinistri());
            assicuratoPojo.setCodicefiscale(itemModel.getCodiceFiscale());

            GregorianCalendar c = new GregorianCalendar();
            XMLGregorianCalendar xmlGregorianCalendar = null;
            c.setTime(itemModel.getDataNascita());
            try {
                xmlGregorianCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
            } catch (DatatypeConfigurationException e) {
                e.printStackTrace();
            }
            assicuratoPojo.setDatanascita(xmlGregorianCalendar);
            assicuratoPojo.setCognome(itemModel.getCognome());
            assicuratoPojo.setNome(itemModel.getNome());

            listPojo.add(assicuratoPojo);

        }
        return listPojo;
    }

    public static Assicurati.Assicurato convertModelToPojo(Assicurato assicuratoModel) {
        Assicurati.Assicurato itemPojo = new Assicurati.Assicurato();

        itemPojo.setNuovisinistri(assicuratoModel.getNuoviSinistri());
        itemPojo.setCodicefiscale(assicuratoModel.getCodiceFiscale());

        GregorianCalendar c = new GregorianCalendar();
        XMLGregorianCalendar xmlGregorianCalendar = null;
        c.setTime(assicuratoModel.getDataNascita());
        try {
            xmlGregorianCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
        } catch (DatatypeConfigurationException e) {
            e.printStackTrace();
        }
        itemPojo.setDatanascita(xmlGregorianCalendar);
        itemPojo.setCognome(assicuratoModel.getCognome());
        itemPojo.setNome(assicuratoModel.getNome());

        return itemPojo;
    }




    public static List<Assicurato> convertListPojoToModel(List<Assicurati.Assicurato> list) {
        List<Assicurato> listModel = new ArrayList<>();
        for (Assicurati.Assicurato itemPojo : list) {
            Assicurato assicuratoModel = new Assicurato();
            assicuratoModel.setNuoviSinistri(itemPojo.getNuovisinistri());
            assicuratoModel.setCodiceFiscale(itemPojo.getCodicefiscale());
            assicuratoModel.setDataNascita(itemPojo.getDatanascita().toGregorianCalendar().getTime());
            assicuratoModel.setCognome(itemPojo.getCognome());
            assicuratoModel.setNome(itemPojo.getNome());
            listModel.add(assicuratoModel);

        }
        return listModel;
    }

    public static void convertToXml(List<Assicurati.Assicurato> listAssicuratiPojo, File file) throws IOException, JAXBException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
        JAXBContext jContext = JAXBContext.newInstance(Assicurati.class);
        Marshaller marshaller = jContext.createMarshaller();
        Assicurati assicuratiJAXB = new Assicurati();
        assicuratiJAXB.setAssicurato(listAssicuratiPojo);
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(assicuratiJAXB, bufferedWriter);
    }


}
