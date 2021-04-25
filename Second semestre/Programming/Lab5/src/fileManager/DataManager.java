package fileManager;

import exceptions.BrokenDataException;
import exceptions.InvalidFieldException;
import exceptions.NoDataException;
import exceptions.NoEnvVarException;
import person.Person;

import java.util.Collection;

/**
 * ��������� �������� ������ ���������
 */
public interface DataManager {

    /**
     * �����, ������� ������ ���������
     * @return ���������
     * @throws NoEnvVarException ��� ��������� ���������� ���������
     * @throws InvalidFieldException ��� ������������ ���� �������
     * @throws NoDataException ��� ���������� ������
     * @throws BrokenDataException ��� ������������ ������
     */
    Collection<? extends Person> readElements() throws NoEnvVarException, InvalidFieldException, NoDataException, BrokenDataException;

    /**
     * �����, ������� ���������� ��������� � ���������
     * @param collection ���������
     */
    void writeElements(Collection<? extends Person> collection);
}
