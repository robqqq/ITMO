package collectionManager;

import person.Person;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.stream.Stream;

public class ClientCollectionManagerImpl implements ClientCollectionManager{
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private final Lock readLock = readWriteLock.readLock();
    private final Lock writeLock = readWriteLock.writeLock();
    private List<Person> persons = new ArrayList<>();

    @Override
    public Stream<Person> getPersonStream() {
        readLock.lock();
        try {
            return persons.stream();
        } finally {
            readLock.unlock();
        }
    }

    @Override
    public void setPersons(Collection<Person> persons) {
        writeLock.lock();
        try {
            this.persons = new ArrayList<>(persons);
        } finally {
            writeLock.unlock();
        }
    }

    @Override
    public int size() {
        return persons.size();
    }

    @Override
    public int fieldCount() {
        return 13;
    }

    @Override
    public Object getField(int rowIndex, int columnIndex) {
        return switch (columnIndex) {
            case 0 -> persons.get(rowIndex).getId();
            case 1 -> persons.get(rowIndex).getName();
            case 2 -> persons.get(rowIndex).getCoordinates().getX();
            case 3 -> persons.get(rowIndex).getCoordinates().getY();
            case 4 -> persons.get(rowIndex).getCreationDate().toLocalDate();
            case 5 -> persons.get(rowIndex).getHeight();
            case 6 -> persons.get(rowIndex).getBirthday().toLocalDate();
            case 7 -> persons.get(rowIndex).getEyeColor();
            case 8 -> persons.get(rowIndex).getHairColor();
            case 9 -> persons.get(rowIndex).getLocation().getX();
            case 10 -> persons.get(rowIndex).getLocation().getY();
            case 11 -> persons.get(rowIndex).getLocation().getName();
            case 12 -> persons.get(rowIndex).getOwner();
            default -> "";
        };
    }
}
