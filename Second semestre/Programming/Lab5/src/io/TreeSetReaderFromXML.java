package io;

import  exceptions.InvalidArgumentException;
import main.*;
import org.w3c.dom.*;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import javax.xml.parsers.*;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.TreeSet;
import java.util.logging.Level;

import static log.Log.logger;

/**
 * Класс, который создает коллекцию объектов из XML файла
 */
public class TreeSetReaderFromXML {
    Document doc;
    String fileName;

    public TreeSetReaderFromXML(String fileName) {
        this.fileName = fileName;
    }

        /**
     * Метод, который создает коллекцию объектов из XML файла
     * @param personsTreeSet
     * @throws InvalidArgumentException
     */
    public void readPersons(TreeSet<Person> personsTreeSet) throws InvalidArgumentException {
        if (fileName == null){
            System.err.println("Environmental variable Lab5 does not exist");
            System.exit(1);
        }
        try (InputStreamReader streamReader = new InputStreamReader(new FileInputStream(fileName))){
            InputSource inputSource = new InputSource(streamReader);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            dBuilder.setErrorHandler(new ErrorHandler() {
                public void warning(SAXParseException e){
                    show("Warning", e);
                }

                public void error(SAXParseException e){
                    show("Error", e);
                }

                public void fatalError(SAXParseException e) {
                    show("Fatal Error", e);
                }

                private void show(String type, SAXParseException e) {
                    logger.log(Level.WARNING, type + ": " + e.getMessage(), e);
                    System.err.println("File is broken, program starts with empty collection");
                }
            });
            doc = dBuilder.parse(inputSource);
        } catch (SAXException | ParserConfigurationException e) {
            return;
        } catch (IOException e){
            logger.log(Level.WARNING, e.toString(), e);
            System.err.println("The file2.xml does not exist");
            return;
        }
        try {
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("person");
            logger.log(Level.INFO, "read " + nList.getLength() + " objects from file2.xml");
            for (int i = 0; i < nList.getLength(); i++) {
                Element element = (Element) nList.item(i);
                personsTreeSet.add(createPerson(element));
            }
        }catch(NullPointerException e){
            System.out.println("File is broken, program starts with empty collection");
        }
    }

    private Person createPerson(Element element) throws InvalidArgumentException {
        try {
            PersonBuilderInterface personBuilder = new PersonBuilder();
            personBuilder.setId(
                    Integer.parseInt(element.getElementsByTagName("id").item(0).getTextContent()));
            personBuilder.setName(
                    element.getElementsByTagName("name").item(0).getTextContent());
            Element coordinatesElement = (Element) element.getElementsByTagName("coordinates").item(0);
            personBuilder.setCoordinatesX(
                    Double.parseDouble(coordinatesElement.getElementsByTagName("coordinatesX").item(0).getTextContent()));
            personBuilder.setCoordinatesY(
                    Long.parseLong(coordinatesElement.getElementsByTagName("coordinatesY").item(0).getTextContent()));
            personBuilder.setCreationDate(
                    LocalDateTime.parse(element.getElementsByTagName("creationDate").item(0).getTextContent())
                            .toLocalDate());
            personBuilder.setHeight(
                    Long.parseLong(element.getElementsByTagName("height").item(0).getTextContent()));
            personBuilder.setBirthday(
                    LocalDateTime.parse(element.getElementsByTagName("birthday").item(0).getTextContent()));
            personBuilder.setEyeColor(
                    EyeColor.valueOf(element.getElementsByTagName("eyeColor").item(0).getTextContent()));
            personBuilder.setHairColor(
                    HairColor.valueOf(element.getElementsByTagName("hairColor").item(0).getTextContent()));
            Element locationElement = (Element) element.getElementsByTagName("location").item(0);
            personBuilder.setLocationX(
                    Float.parseFloat(element.getElementsByTagName("locationX").item(0).getTextContent()));
            personBuilder.setLocationY(
                    Long.parseLong(locationElement.getElementsByTagName("locationY").item(0).getTextContent()));
            personBuilder.setLocationName(
                    locationElement.getElementsByTagName("locationName").item(0).getTextContent());
            return personBuilder.getPerson();
        }catch(DateTimeParseException | IllegalArgumentException e){
            logger.log(Level.WARNING, e.toString(), e);
            throw new InvalidArgumentException("Wrong field type");
        }
    }
}
