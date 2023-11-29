package org.example;

import org.example.savedom_jaxb.Address;
import org.example.savedom_jaxb.City;
import org.example.savedom_jaxb.Places;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        Main main = new Main();
        main.createXML_dom();
        main.marshallingXML_jaxb();
        main.unmarshallingXML_jaxb();
    }
    public void marshallingXML_jaxb()
    {
        Places places = new Places();
        places.add(new Address(new City("big(jaxb)","Kyiv"), "Khreshchatyk", "26"));
        places.add(new Address(new City("medium(jaxb)","Brovary"), "Nezalezhnosti boulevard", "11-A"));
        try
        {
            File file = new File("address_jaxb.xml");
            JAXBContext context = JAXBContext.newInstance(Places.class);
            Marshaller marshaller = context.createMarshaller();

            // френдлі форматування
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            //пишемо в ф-л - раз, пишемо в аут - два
            marshaller.marshal(places, file);
            marshaller.marshal(places, System.out);
        }
        catch (JAXBException e) {
            throw new RuntimeException(e);
        }

    }
    public void unmarshallingXML_jaxb()
    {
        try
        {
//            File file = new File("address.xml");
            File file = new File("address_jaxb.xml");
            JAXBContext context = JAXBContext.newInstance(Places.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            Places places = (Places) unmarshaller.unmarshal(file);
            System.out.println(places);
        }
        catch (JAXBException e) {
//            throw new RuntimeException(e);
            e.printStackTrace();
        }
    }
    public void createXML_dom()
    {
//отримуємо посилання на фабрику
        DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
//отримуємо об'єкт DocumentBuilder
        DocumentBuilder docBuilder;
        try {
            docBuilder = docBuilderFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        }
//створюємо чистий документ xml
        Document doc = docBuilder.newDocument();
//створюємо root елемент
        Element rootElement = doc.createElement("places");
        doc.appendChild(rootElement);

//add address 1
        Element address1 = doc.createElement("address");
        rootElement.appendChild(address1);
//add city 1
        Element city1 = doc.createElement("city");
        city1.setAttribute("size", "big");
        city1.setTextContent("Kyiv");
        address1.appendChild(city1);
//add street 1
        Element street1 = doc.createElement("street");
        street1.setTextContent("Khreshchatyk");
        address1.appendChild(street1);
//add building 1
        Element building1 = doc.createElement("building");
        building1.setTextContent("26");
        address1.appendChild(building1);

//add address 2
        Element address2 = doc.createElement("address");
        rootElement.appendChild(address2);
//add city 2
        Element city2 = doc.createElement("city");
        city2.setAttribute("size", "medium");
        city2.setTextContent("Brovary");
        address2.appendChild(city2);
//add street 2
        Element street2 = doc.createElement("street");
        street2.setTextContent("Nezalezhnosti boulevard");
        address2.appendChild(street2);
//add building 2
        Element building2 = doc.createElement("building");
        building2.setTextContent("11-A");
        address2.appendChild(building2);

//save throw Transformer
// 1. create Transformer
        TransformerFactory transFactory = TransformerFactory.newInstance();
        Transformer transformer;
        try {
            transformer = transFactory.newTransformer();
        } catch (TransformerConfigurationException e) {
            throw new RuntimeException(e);
        }
// 2. create DOMSource
        DOMSource domSource = new DOMSource(doc);
// 3. create StreamResult
        StreamResult streamResult = new StreamResult(new File("address.xml"));
// 4. трансформуємо з джерела xml в файл xml
        try {
            transformer.transform(domSource, streamResult);
        } catch (TransformerException e) {
            throw new RuntimeException(e);
        }
    }
}