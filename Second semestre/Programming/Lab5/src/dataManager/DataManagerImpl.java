package dataManager;

import exceptions.BrokenDataException;
import exceptions.InvalidFieldException;
import exceptions.NoDataException;
import exceptions.NoEnvVarException;
import messages.Messenger;
import person.Person;

import java.util.Collection;

/**
 * Реализация интерфейса DataManager
 */
public class DataManagerImpl implements DataManager {
    private DataWriter dataWriter;
    private DataReader dataReader;
    private Messenger messenger;

    /**
     * @param dataReader объект класса чтения данных
     * @param dataWriter объект класса записи данных
     * @param messenger мессенджер
     */
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
