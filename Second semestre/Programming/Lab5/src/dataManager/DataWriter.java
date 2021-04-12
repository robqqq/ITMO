package dataManager;

import person.Person;

import java.util.Collection;

/**
 * ��������� ������ ������ ������
 */
public interface DataWriter {

    /**
     * �����, ������� ���������� ��������� � ���������
     * @param collection ���������
     */
    void writeElements(Collection<? extends Person> collection);
}
