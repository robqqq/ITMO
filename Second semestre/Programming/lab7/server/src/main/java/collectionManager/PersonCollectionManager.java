package collectionManager;

import person.*;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Реализация интерфейса CollectionManager
 */
public class PersonCollectionManager implements CollectionManager {
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private final Lock readLock = readWriteLock.readLock();
    private final Lock writeLock = readWriteLock.writeLock();
    private final LocalDate initDate;
    private final TreeSet<Person> personTreeSet;
    private static final Logger logger = LoggerFactory.getLogger(PersonCollectionManager.class);

    public PersonCollectionManager() {
        initDate = LocalDate.now();
        personTreeSet = new TreeSet<>();
    }

    @Override
    public Class getType() {
        readLock.lock();
        try {
            return personTreeSet.getClass();
        } finally {
            readLock.unlock();
        }
    }

    @Override
    public int getSize() {
        readLock.lock();
        try {
            return personTreeSet.size();
        } finally {
            readLock.unlock();
        }
    }

    @Override
    public LocalDate getInitDate() {
        readLock.lock();
        try {
            return initDate;
        } finally {
            readLock.unlock();
        }
    }

    @Override
    public void addElement(Person person) {
        writeLock.lock();
        try {
            personTreeSet.add(person);
        } finally {
            writeLock.unlock();
        }
    }

    @Override
    public void removeElement(int id) {
        writeLock.lock();
        try {
            for (Person person : personTreeSet) {
                if (person.getId() == id) {
                    personTreeSet.remove(person);
                    return;
                }
            }
        } finally {
            writeLock.unlock();
        }
    }

    @Override
    public void clear() {
        writeLock.lock();
        try {
            personTreeSet.clear();
        } finally {
            writeLock.unlock();
        }
    }

    @Override
    public void setCollection(Collection<Person> persons){
        writeLock.lock();
        try{
            personTreeSet.addAll(persons);
        } finally {
            writeLock.unlock();
        }
    }

    @Override
    public Stream<Person> getPersonStream() {
        readLock.lock();
        try {
            return personTreeSet.stream();
        } finally {
            readLock.unlock();
        }
    }
}