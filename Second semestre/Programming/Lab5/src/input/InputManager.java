package input;

import person.Person;

import java.time.LocalDateTime;

/**
 * Интерфейс менджера ввода
 */
public interface InputManager {

    /**
     * @return true - готов к чтению, false - не готов
     */
    boolean ready();

    /**
     * Метод, который читает команду
     * @return строка команды
     */
    String readCommand();

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
