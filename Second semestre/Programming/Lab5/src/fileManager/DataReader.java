package fileManager;

import exceptions.BrokenDataException;
import exceptions.InvalidFieldException;
import exceptions.NoDataException;
import exceptions.NoEnvVarException;
import messages.Messenger;
import person.Person;

import java.util.Collection;

public interface DataReader {
    Collection<? extends Person> readElements(Messenger messenger)  throws InvalidFieldException, NoEnvVarException, NoDataException, BrokenDataException;
}
