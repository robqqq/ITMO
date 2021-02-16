package main;

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
    void addPerson(String name, Double coordinatesX, long coordinatesY, long height, LocalDateTime birthday, EyeColor eyeColor,
                   HairColor hairColor, float locationX, long locationY, String locationName);
    boolean updatePerson(int id, String name, Double coordinatesX, long coordinatesY, long height, LocalDateTime birthday, EyeColor eyeColor,
                         HairColor hairColor, float locationX, long locationY, String locationName);
    boolean updatePerson(int id, String name, long height, LocalDateTime birthday, CommandManagerInterface commandManager);
    void addPersonIfMax(String name, Double coordinatesX, long coordinatesY, long height, LocalDateTime birthday, EyeColor eyeColor,
                        HairColor hairColor, float locationX, long locationY, String locationName);
    void addPersonIfMax(String name, long height, LocalDateTime birthday, CommandManagerInterface commandManager);
    void addPersonIfMin(String name, Double coordinatesX, long coordinatesY, long height, LocalDateTime birthday, EyeColor eyeColor,
                        HairColor hairColor, float locationX, long locationY, String locationName);
    void addPersonIfMin(String name, long height, LocalDateTime birthday, CommandManagerInterface commandManager);
    Person getMax(Comparator<Person> comparator);
    Comparator<Person> getComparatorByEyeColor();
    Comparator<Person> getDescendingComparatorByEyeColor();
    void addPerson(String arg, long parseLong, LocalDateTime atStartOfDay, CommandManagerInterface commandManager);
}
