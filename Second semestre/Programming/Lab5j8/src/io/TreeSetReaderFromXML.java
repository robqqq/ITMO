package io;

import  exceptions.InvalidArgumentException;
import exceptions.NotUniqueIdException;
import main.*;
import org.w3c.dom.*;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import javax.xml.parsers.*;
import java.io.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Level;

import static log.Log.logger;

/**
 * Класс, который создает коллекцию объектов из XML файла
 */
public class TreeSetReaderFromXML {
    Document doc;
    private Set<Integer> allId;

    /**
     * Конструктор
     * @param fileName
     */
    public TreeSetReaderFromXML(String fileName){
        allId = new HashSet<>();
        try {
            InputStreamReader streamReader = new InputStreamReader(new FileInputStream(fileName));
            InputSource inputSource = new InputSource(streamReader);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            doc = dBuilder.parse(inputSource);
        } catch (SAXException | IOException | ParserConfigurationException e) {
            System.err.println(e.toString());
            logger.log(Level.WARNING, e.toString(), e);
            System.exit(1);
        } catch (NullPointerException e){
            logger.log(Level.WARNING, e.toString(), e);
            System.err.println("Environmental variable Lab5 does not exist");
            System.exit(1);
        }
    }

    /**
     * Метод, который создает коллекцию объектов из XML файла
     * @param personsTreeSet
     * @throws InvalidArgumentException
     */
    public void readPersons(TreeSet<Person> personsTreeSet) throws InvalidArgumentException {
        doc.getDocumentElement().normalize();
        NodeList nList = doc.getElementsByTagName("person");
        logger.log(Level.INFO, "read " + nList.getLength() + " objects from file");
        for (int i = 0; i < nList.getLength(); i++) {
            Element element = (Element) nList.item(i);
            personsTreeSet.add(createPerson(element));
        }

    }

    private Person createPerson(Element element) throws InvalidArgumentException {
        int id = Integer.parseInt(element.getElementsByTagName("id").item(0).getTextContent());
        if (allId.contains(id)){
            throw new NotUniqueIdException();
        }
        allId.add(id);
        String name = element.getElementsByTagName("name").item(0).getTextContent();
        Element coordinatesElement = (Element) element.getElementsByTagName("coordinates").item(0);
        Double coordinatesX = Double.parseDouble(coordinatesElement.getElementsByTagName("x").item(0).getTextContent());
        long coordinatesY = Long.parseLong(coordinatesElement.getElementsByTagName("y").item(0).getTextContent());
        Coordinates coordinates = new Coordinates(coordinatesX, coordinatesY);
        LocalDateTime creationDate = LocalDateTime.parse(element.getElementsByTagName("creationDate").item(0).getTextContent());
        long height = Long.parseLong(element.getElementsByTagName("height").item(0).getTextContent());
        LocalDateTime birthday = LocalDateTime.parse(element.getElementsByTagName("birthday").item(0).getTextContent());
        EyeColor eyeColor = EyeColor.valueOf(element.getElementsByTagName("eyeColor").item(0).getTextContent());
        HairColor hairColor = HairColor.valueOf(element.getElementsByTagName("hairColor").item(0).getTextContent());
        Element locationElement = (Element) element.getElementsByTagName("location").item(0);
        float locationX = Float.parseFloat(element.getElementsByTagName("x").item(0).getTextContent());
        long locationY = Long.parseLong(locationElement.getElementsByTagName("y").item(0).getTextContent());
        String locationName = locationElement.getElementsByTagName("name").item(0).getTextContent();
        Location location = new Location(locationX, locationY, locationName);
        Person person = new Person(id, name, coordinates, creationDate, height, birthday, eyeColor, hairColor, location);
        return person;
    }
}
