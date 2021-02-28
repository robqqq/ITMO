package io;

import exceptions.InvalidArgumentException;
import main.Person;
import java.util.TreeSet;
import java.util.logging.Level;

import static log.Log.logger;

/**
 * Класс, который управляет чтением коллекции из файла и записью коллекцией в файл
 */
public class FileManager implements FileManagerInterface{
    private TreeSetReaderFromXML reader;
    private TreeSetWriterToXML writer;
    String fileName;

    /**
     * Конструктор
     */
    public FileManager(){
        fileName = System.getenv("Lab5");
        reader = new TreeSetReaderFromXML(fileName);
        writer = new TreeSetWriterToXML(fileName);
    }

    /**
     * Метод, который читает коллекцию из файла
     * @param personsTreeSet
     * @throws InvalidArgumentException
     */
    public void read(TreeSet<Person> personsTreeSet) throws InvalidArgumentException{
        reader.readPersons(personsTreeSet);
    }

    /**
     * Метод, который записывает коллекцию в файл
     * @param personsTreeSet
     */
    public void write(TreeSet<Person> personsTreeSet){
        writer.writePersonsToXML(personsTreeSet);
    }
}
