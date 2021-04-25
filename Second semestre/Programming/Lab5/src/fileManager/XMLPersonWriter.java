package fileManager;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import person.Coordinates;
import person.Location;
import person.Person;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.util.Collection;
import java.util.logging.Level;

import static log.Log.logger;

/**
 * Реализация интерфейса DataWriter для работы с XML файлом
 */
public class XMLPersonWriter implements DataWriter {
    private Document doc;
    private FileOutputStream outputStream;
    private OutputStreamWriter streamWriter;
    private String fileName;

    /**
     * @param fileName имя файла
     */
    public XMLPersonWriter(String fileName){
        this.fileName = fileName;
    }

    @Override
    public void writeElements(Collection<? extends Person> collection) {
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            doc = dBuilder.newDocument();
        } catch (ParserConfigurationException e) {
            logger.log(Level.WARNING, e.toString(), e);
        }
        logger.log(Level.INFO, "start write to " + fileName);
        Element rootElement = doc.createElement("personTreeSet");
        doc.appendChild(rootElement);
        for (Person person : collection) {
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
            logger.log(Level.INFO, "end write to " + fileName);
        } catch (TransformerException | FileNotFoundException e) {
            logger.log(Level.WARNING, e.toString(), e);
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
        element.appendChild(getElements("coordinatesX", String.valueOf(coordinates.getX())));
        element.appendChild(getElements("coordinatesY", String.valueOf(coordinates.getY())));
        return element;
    }

    private Element getLocationElement(Location location){
        Element element = doc.createElement("location");
        element.appendChild(getElements("locationX", String.valueOf(location.getX())));
        element.appendChild(getElements("locationY", String.valueOf(location.getY())));
        element.appendChild(getElements("locationName", location.getName() == null ? "" : location.getName()));
        return element;
    }
}
