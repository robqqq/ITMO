package main;

import cleint.ClientManagerInterface;
import commands.CommandManagerInterface;
import exceptions.InvalidArgumentException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Comparator;

/**
 * Интерфейс менеджера объектов
 */
public interface ObjectManager {
    Class getType();
    int getLength();
    LocalDate getInitDate();
    boolean removeElement(int id);
    void removeAll();
    void readFromFile() throws InvalidArgumentException;
    void writeToFile();
    void printElement(Object object);
    void printCollection();
    void printSortedPersonsEyeColorField(Comparator<Person> comparator);
    void printPersonsContainsName(String name);
    boolean updatePerson(int id, ClientManagerInterface clientManager);
    void addPersonIfMax(ClientManagerInterface clientManager);
    void addPersonIfMin(ClientManagerInterface clientManager);
    Person getMax(Comparator<Person> comparator);
    Comparator<Person> getComparatorByEyeColor();
    Comparator<Person> getDescendingComparatorByEyeColor();
    void addPerson(ClientManagerInterface clientManager);
}
