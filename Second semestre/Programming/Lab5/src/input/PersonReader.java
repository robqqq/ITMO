package input;

import person.Person;

import java.time.LocalDateTime;

/**
 * Интерфейс класса считывания элементов коллекции
 */
public interface PersonReader {

    /**
     * Метод, который считывает элемент коллекции
     * @return элемент коллекции
     */
    Person readPerson();

    /**
     * Метод, который считывает элемент коллекции для обновления существуюшего элемента
     * @param id идентификатор существующего элемента
     * @param creationDate дата создания существуюшего элемента
     * @return обновленный элемент коллекции
     */
    Person readPerson(int id, LocalDateTime creationDate);
}
