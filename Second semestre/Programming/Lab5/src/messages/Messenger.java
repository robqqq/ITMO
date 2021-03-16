package messages;

import exceptions.NoSuchCommandException;
import person.Person;

/**
 * Интерфейс мессенджера
 */
public interface Messenger {

    String getExceptionMsg(String msgName);

    String getCommandDescription(String commandName) throws NoSuchCommandException;

    String getCollectionTypeMsg();

    String getCollectionInitDateMsg();

    String getCollectionSizeMsg();

    String getPersonString(Person person);

    String getFieldInputMsg(String fieldName);

    String getStartMsg();

    String getFinishMsg();
}
