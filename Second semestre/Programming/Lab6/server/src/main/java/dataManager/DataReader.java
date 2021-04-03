package dataManager;

import exceptions.BrokenDataException;
import exceptions.InvalidFieldException;
import exceptions.NoDataException;
import exceptions.NoEnvVarException;
import person.Person;

import java.util.Collection;

public interface DataReader {
    
    Collection<Person> readElements()  throws InvalidFieldException, NoEnvVarException, NoDataException, BrokenDataException;
}
