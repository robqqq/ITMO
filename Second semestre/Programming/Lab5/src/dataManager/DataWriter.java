package dataManager;

import person.Person;

import java.util.Collection;

/**
 * Интерфейс класса записи данных
 */
public interface DataWriter {

    /**
     * Метод, который записывает коллекцию в хранилище
     * @param collection коллекция
     */
    void writeElements(Collection<? extends Person> collection);
}
