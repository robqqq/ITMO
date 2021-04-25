package fileManager;

import exceptions.BrokenDataException;
import exceptions.InvalidFieldException;
import exceptions.NoDataException;
import exceptions.NoEnvVarException;
import messages.Messenger;
import person.Person;

import java.util.Collection;

/**
 * ��������� ������ ������ ������
 */
public interface DataReader {

    /**
     * �����, ������� ������ ���������
     * @return ���������
     * @throws NoEnvVarException ��� ��������� ���������� ���������
     * @throws InvalidFieldException ��� ������������ ���� �������
     * @throws NoDataException ��� ���������� ������
     * @throws BrokenDataException ��� ������������ ������
     */
    Collection<? extends Person> readElements(Messenger messenger)  throws InvalidFieldException, NoEnvVarException, NoDataException, BrokenDataException;
}
