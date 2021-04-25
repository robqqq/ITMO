package fileManager;

import exceptions.BrokenDataException;
import exceptions.InvalidFieldException;
import exceptions.NoDataException;
import exceptions.NoEnvVarException;
import person.Person;

import java.util.Collection;

/**
 * интерфейс менджера данных коллекции
 */
public interface DataManager {

    /**
     * Метод, который читает коллекцию
     * @return коллекция
     * @throws NoEnvVarException при отсутсвии переменной окружения
     * @throws InvalidFieldException при некорректном поле объекта
     * @throws NoDataException при отсутствии данных
     * @throws BrokenDataException при некорректных данных
     */
    Collection<? extends Person> readElements() throws NoEnvVarException, InvalidFieldException, NoDataException, BrokenDataException;

    /**
     * Метод, который записывает коллекцию в хранилище
     * @param collection коллекция
     */
    void writeElements(Collection<? extends Person> collection);
}
