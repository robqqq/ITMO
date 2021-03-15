package fileManager;

import exceptions.BrokenDataException;
import exceptions.InvalidFieldException;
import exceptions.NoDataException;
import exceptions.NoEnvVarException;
import messages.Messenger;
import person.Person;

import java.util.Collection;

public class DataManagerImpl implements DataManager {
    private DataWriter dataWriter;
    private DataReader dataReader;
    private Messenger messenger;

    public DataManagerImpl(DataReader dataReader, DataWriter dataWriter, Messenger messenger){
        this.dataReader = dataReader;
        this.dataWriter = dataWriter;
        this.messenger = messenger;
    }

    @Override
    public Collection<? extends Person> readElements() throws NoEnvVarException, InvalidFieldException, NoDataException, BrokenDataException {
        return dataReader.readElements(messenger);
    }

    @Override
    public void writeElements(Collection<? extends Person> collection) {
        dataWriter.writeElements(collection);
    }


}
