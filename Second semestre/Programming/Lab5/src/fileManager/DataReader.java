package fileManager;

import exceptions.BrokenDataException;
import exceptions.InvalidFieldException;
import exceptions.NoDataException;
import exceptions.NoEnvVarException;
import messages.Messenger;
import person.Person;

import java.util.Collection;

/**
 * Интерфейс класса чтения данных
 */
public interface DataReader {

    /**
     * Метод, который читает коллекцию
     * @return коллекция
     * @throws NoEnvVarException при отсутсвии переменной окружения
     * @throws InvalidFieldException при некорректном поле объекта
     * @throws NoDataException при отсутствии данных
     * @throws BrokenDataException при некорректных данных
     */
    Collection<? extends Person> readElements(Messenger messenger)  throws InvalidFieldException, NoEnvVarException, NoDataException, BrokenDataException;
}
