package io;

import main.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.util.TreeSet;
import java.util.logging.Level;

import static log.Log.logger;

/**
 * Класс, который записывает коллекцию объектов в XML файл
 */
public class TreeSetWriterToXML {
    Document doc;
    FileOutputStream outputStream;
    OutputStreamWriter streamWriter;
    String fileName;

    /**
     * Конструктор
     * @param fileName
     */
    public TreeSetWriterToXML(String fileName){
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            doc = dBuilder.newDocument();
            this.fileName = fileName;
        } catch (ParserConfigurationException e) {
            System.err.println(e.toString());
            logger.log(Level.WARNING, e.toString(), e);
            System.exit(1);
        }
    }

    /**
     * Метод, который записывает коллекцию объектов в XML файл
     * @param personsTreeSet
     */
    public void writePersonsToXML(TreeSet<Person> personsTreeSet) {
        logger.log(Level.INFO, "start write to file");
        Element rootElement = doc.createElement("personsTreeSet");
        doc.appendChild(rootElement);
        for (Person person : personsTreeSet) {
            rootElement.appendChild(getPerson(person));
        }
        try {
            outputStream = new FileOutputStream(fileName);
            streamWriter = new OutputStreamWriter(outputStream);
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(streamWriter);
            transformer.transform(source, result);
            logger.log(Level.INFO, "end write to file");
        } catch (TransformerException | FileNotFoundException e) {
            System.err.println(e.toString());
            logger.log(Level.WARNING, e.toString(), e);
            System.exit(1);
        }
    }

    private Element getPerson(Person person) {
        Element element = doc.createElement("person");
        element.appendChild(getElements("id", String.valueOf(person.getId())));
        element.appendChild(getElements("name", person.getName()));
        element.appendChild(getCoordinatesElement(person.getCoordinates()));
        element.appendChild(getElements("creationDate", person.getCreationDate().toString()));
        element.appendChild(getElements("height", String.valueOf(person.getHeight())));
        element.appendChild(getElements("birthday", person.getBirthday().toString()));
        element.appendChild(getElements("eyeColor", person.getEyeColor().toString()));
        element.appendChild(getElements("hairColor", person.getHairColor().toString()));
        element.appendChild(getLocationElement(person.getLocation()));
        return element;
    }

    private Element getElements(String name, String value) {
        Element element = doc.createElement(name);
        element.appendChild(doc.createTextNode(value));
        return element;
    }

    private Element getCoordinatesElement(Coordinates coordinates){
        Element element = doc.createElement("coordinates");
        element.appendChild(getElements("x", String.valueOf(coordinates.getX())));
        element.appendChild(getElements("y", String.valueOf(coordinates.getY())));
        return element;
    }

    private Element getLocationElement(Location location){
        Element element = doc.createElement("location");
        element.appendChild(getElements("x", String.valueOf(location.getX())));
        element.appendChild(getElements("y", String.valueOf(location.getY())));
        element.appendChild(getElements("name", location.getName()));
        return element;
    }
}
