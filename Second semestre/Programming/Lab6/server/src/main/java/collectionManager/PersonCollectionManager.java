package collectionManager;

import exceptions.*;
import dataManager.DataManager;
import person.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Реализация интерфейса CollectionManager
 */
public class PersonCollectionManager implements CollectionManager {
    private final LocalDate initDate;
    private final TreeSet<Person> personTreeSet;
    private final DataManager dataManager;
    private static final Logger logger = LoggerFactory.getLogger(PersonCollectionManager.class);

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
    public void addElement(RawPerson rawPerson){
        int id = PersonIdManager.INSTANCE.getFreeId();
        Person person = new Person(id, rawPerson, LocalDateTime.now());
        PersonIdManager.INSTANCE.addId(id);
        personTreeSet.add(person);
    }


    @Override
    public boolean removeElement(int id) {
        for (Person person : personTreeSet) {
            if (person.getId() == id) {
                PersonIdManager.INSTANCE.removeId(id);
                personTreeSet.remove(person);
                return true;
            }
        }
        return false;
    }

    @Override
    public void clear() {
        PersonIdManager.INSTANCE.clearIdentifiers();
        personTreeSet.clear();
    }

    @Override
    public void loadPersons() throws NoEnvVarException, InvalidFieldException, NoDataException, BrokenDataException {
        personTreeSet.addAll(dataManager.readElements());
        for (Person person: personTreeSet){
            int id = person.getId();
            if (PersonIdManager.INSTANCE.idIsFree(id)){
                PersonIdManager.INSTANCE.addId(id);
            } else {
                personTreeSet.clear();
                PersonIdManager.INSTANCE.clearIdentifiers();
                logger.error("File is broken, objects has no unique identifiers");
                return;
            }
        }
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