package collectionManager;

import exceptions.BrokenDataException;
import exceptions.InvalidFieldException;
import exceptions.NoDataException;
import exceptions.NoEnvVarException;
import person.Person;

import java.time.LocalDate;
import java.util.stream.Stream;

/**
 * Интерфейс класса, который управляет коллекцией
 */
public interface CollectionManager {

    /**
     * @return тип коллекции
     */
    Class getType();

    /**
     * @return количество элементов коллекции
     */
    int getSize();

    /**
     * @return дата инициализации коллекции
     */
    LocalDate getInitDate();

    void addElement(Person person);

    /**
     * Удаление элемента из коллекции по его id
     * @param id значение поля id элемента
     * @return true, если элемент был найден и удален; false, если не был удален
     */
    boolean removeElement(int id);

    /**
     * Метод, который очищает коллекцию
     */
    void clear();

    /**
     * Метод, который загружает объекты Person в коллекцию, используя DataManager
     * @throws NoEnvVarException если отсутсвует переменная окружения, указывающая имя файла
     * @throws InvalidFieldException если какое-то из полей имеет неверный формат или неверный тип данных
     * @throws NoDataException если отсутсвует хранилище данных
     * @throws BrokenDataException если хранилище данных сломано
     */
    void loadPersons() throws NoEnvVarException, InvalidFieldException, NoDataException, BrokenDataException;

    void savePersons();

    Stream<Person> getPersonStream();

}
