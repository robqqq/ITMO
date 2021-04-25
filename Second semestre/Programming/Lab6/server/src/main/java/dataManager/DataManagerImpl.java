package dataManager;

import exceptions.BrokenDataException;
import exceptions.InvalidFieldException;
import exceptions.NoDataException;
import exceptions.NoEnvVarException;
import person.Person;

import java.util.Collection;

public class DataManagerImpl implements DataManager {
    private DataWriter dataWriter;
    private DataReader dataReader;
    
    public DataManagerImpl(DataReader dataReader, DataWriter dataWriter){
        this.dataReader = dataReader;
        this.dataWriter = dataWriter;
    }

    @Override
    public Collection<Person> readElements() throws NoEnvVarException, InvalidFieldException, NoDataException, BrokenDataException {
        return dataReader.readElements();
    }

    @Override
    public void writeElements(Collection<Person> collection) {
        dataWriter.writeElements(collection);
    }


}
