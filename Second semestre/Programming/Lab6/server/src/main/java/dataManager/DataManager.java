package dataManager;

import exceptions.BrokenDataException;
import exceptions.InvalidFieldException;
import exceptions.NoDataException;
import exceptions.NoEnvVarException;
import person.Person;

import java.util.Collection;

public interface DataManager {
    
    Collection<Person> readElements() throws NoEnvVarException, InvalidFieldException, NoDataException, BrokenDataException;
    
    void writeElements(Collection<Person> collection);
}
