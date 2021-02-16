package io;

import exceptions.InvalidArgumentException;
import main.Person;
import java.util.TreeSet;

/**
 * Интерфейс менеджера файлов
 */
public interface FileManagerInterface {
    void read(TreeSet<Person> personsTreeSet) throws InvalidArgumentException;
    void write(TreeSet<Person> personsTreeSet);
}
