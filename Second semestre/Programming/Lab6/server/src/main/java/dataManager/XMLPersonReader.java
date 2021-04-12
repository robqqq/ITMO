package dataManager;

import exceptions.BrokenDataException;
import exceptions.InvalidFieldException;
import exceptions.NoDataException;
import exceptions.NoEnvVarException;
import org.w3c.dom.*;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import person.*;

import javax.xml.parsers.*;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Collection;
import java.util.TreeSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Реализация интерфейса DataReader для работы с XML файлами
 */
public class XMLPersonReader implements DataReader {
    private final String fileName;
    private static final Logger logger = LoggerFactory.getLogger(XMLPersonReader.class);

    /**
     * @param fileName имя файла
     */
    public XMLPersonReader(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public Collection<Person> readElements() throws InvalidFieldException, NoEnvVarException, NoDataException, BrokenDataException {
        if (fileName == null){
            throw new NoEnvVarException();
        }
        Document doc;
        try (InputStreamReader streamReader = new InputStreamReader(new FileInputStream(fileName))){
            InputSource inputSource = new InputSource(streamReader);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            dBuilder.setErrorHandler(new ErrorHandler() {
                public void warning(SAXParseException e) throws SAXException {
                    log("Warning", e);
                }

                public void error(SAXParseException e) throws SAXException {
                    log("Error", e);
                }

                public void fatalError(SAXParseException e) throws SAXException {
                    log("Fatal Error", e);
                }

                private void log(String type, SAXParseException e) throws SAXException {
                    logger.error(type + ": " + e.getMessage(), e);
                    throw new SAXException();
                }
            });
            doc = dBuilder.parse(inputSource);
        } catch (SAXException | ParserConfigurationException e) {
            logger.error("Parsing file problem", e);
            throw new BrokenDataException();
        } catch (IOException e){
            logger.error("Data file does not exist", e);
            throw new NoDataException();
        }
        Collection<Person> personCollection = new TreeSet<>();
        try {
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("person");
            logger.info("read " + nList.getLength() + " objects from " + fileName);
            for (int i = 0; i < nList.getLength(); i++) {
                Element element = (Element) nList.item(i);
                personCollection.add(createPerson(element));
            }
        }catch(NullPointerException e){
            throw new BrokenDataException();
        }
        return personCollection;
    }

    private Person createPerson(Element element) throws InvalidFieldException {
        try {
            RawPersonBuilder personBuilder = new RawPersonBuilderImpl();
            int id = Integer.parseInt(element.getElementsByTagName("id").item(0).getTextContent());
            personBuilder.setName(
                    element.getElementsByTagName("name").item(0).getTextContent());
            Element coordinatesElement = (Element) element.getElementsByTagName("coordinates").item(0);
            personBuilder.setCoordinatesX(
                    Double.parseDouble(coordinatesElement.getElementsByTagName("coordinatesX").item(0).getTextContent()));
            personBuilder.setCoordinatesY(
                    Long.parseLong(coordinatesElement.getElementsByTagName("coordinatesY").item(0).getTextContent()));
            LocalDateTime creationDate =
                    LocalDateTime.parse(element.getElementsByTagName("creationDate").item(0).getTextContent());
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
            String locationName = locationElement.getElementsByTagName("locationName").item(0).getTextContent();
            personBuilder.setLocationName(locationName);
            return new Person(id, personBuilder.getRawPerson(), creationDate);
        }catch(DateTimeParseException | IllegalArgumentException e){
            logger.error("File has fields with wrong types", e);
            throw new InvalidFieldException();
        }
    }
}
