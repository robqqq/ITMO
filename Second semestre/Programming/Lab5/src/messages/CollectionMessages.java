package messages;

import person.Person;

/**
 * Интерфейс класса, с сообщениями коллекции
 */
public interface CollectionMessages {

    String getPersonString(Person person);

    String getCollectionTypeMsg();

    String getCollectionInitDateMsg();

    String getCollectionSizeMsg();

    String getInputNameMsg();

    String getInputCoordinatesXMsg();

    String getInputCoordinatesYMsg();

    String getInputHeightMsg();

    String getInputBirthdayMsg();

    String getInputEyeColorMsg();

    String getInputHairColorMsg();

    String getInputLocationXMsg();

    String getInputLocationYMsg();

    String getInputLocationNameMsg();
}
