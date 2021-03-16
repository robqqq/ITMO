package collectionManager;

import exceptions.*;
import fileManager.DataManager;
import messages.Messenger;
import person.*;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Stream;

/**
 * Реализация интерфейса CollectionManager
 */
public class PersonCollectionManager implements CollectionManager {
    private final LocalDate initDate;
    private TreeSet<Person> personTreeSet;
    private DataManager dataManager;

    /**
     * @param dataManager менеджер данных коллекции
     */
    public PersonCollectionManager(DataManager dataManager) {
        initDate = LocalDate.now();
        personTreeSet = new TreeSet<>();
        this.dataManager = dataManager;
    }

    @Override
    public Class getType() {
        return personTreeSet.getClass();
    }

    @Override
    public int getSize() {
        return personTreeSet.size();
    }

    @Override
    public LocalDate getInitDate() {
        return initDate;
    }

    @Override
    public void addElement(Person person) {
        personTreeSet.add(person);
    }

    @Override
    public boolean removeElement(int id) {
        for (Person person : personTreeSet) {
            if (person.getId() == id) {
                PersonIdManager.getInstance().removeId(id);
                personTreeSet.remove(person);
                return true;
            }
        }
        return false;
    }

    @Override
    public void clear() {
        PersonIdManager.getInstance().clearIdentifiers();
        personTreeSet.clear();
    }

    @Override
    public void loadPersons() throws NoEnvVarException, InvalidFieldException, NoDataException, BrokenDataException {
        personTreeSet.addAll(dataManager.readElements());
    }

    @Override
    public void savePersons() {
        dataManager.writeElements(personTreeSet);
    }

    @Override
    public Stream<Person> getPersonStream() {
        return personTreeSet.stream();
    }
}