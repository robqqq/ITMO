package messages;

import person.Person;

public class EngPersonCollectionMessages implements CollectionMessages{
    private final String collectionTypeMsg;
    private final String collectionInitDateMsg;
    private final String collectionSizeMsg;
    private final String inputNameMsg;
    private final String inputCoordinatesXMsg;
    private final String inputCoordinatesYMsg;
    private final String inputHeightMsg;
    private final String inputBirthdayMsg;
    private final String inputEyeColorMsg;
    private final String inputHairColorMsg;
    private final String inputLocationXMsg;
    private final String inputLocationYMsg;
    private final String inputLocationNameMsg;

    public EngPersonCollectionMessages() {
        collectionTypeMsg = "Collection type";
        collectionInitDateMsg = "Initialization date";
        collectionSizeMsg = "Number of elements";
        inputNameMsg = "Name";
        inputCoordinatesXMsg = "coordinates.x";
        inputCoordinatesYMsg = "coordinates.y";
        inputHeightMsg = "height";
        inputBirthdayMsg = "birthday (yyyy-MM-dd)";
        inputEyeColorMsg = "eyeColor (BLACK, ORANGE, WHITE)";
        inputHairColorMsg = "hairColor (GREEN, BLACK, BLUE, YELLOW, WHITE)";
        inputLocationXMsg = "location.x";
        inputLocationYMsg = "location.y";
        inputLocationNameMsg = "location.name";
    }

    @Override
    public String getPersonString(Person person){
        return String.format("id=%d; name=%s; coordinates=(%.2f, %d); creationDate=%s; height=%d; birthday=%s; " +
                "eyeColor=%s; hairColor=%s; location=(%.2f, %d), name=%s", person.getId(), person.getName(),
                person.getCoordinates().getX(), person.getCoordinates().getY(), person.getCreationDate().toLocalDate(),
                person.getHeight(), person.getBirthday().toLocalDate(), person.getEyeColor(), person.getHairColor(),
                person.getLocation().getX(), person.getLocation().getY(), person.getLocation().getName());
    }

    @Override
    public String getCollectionTypeMsg() {
        return collectionTypeMsg;
    }

    @Override
    public String getCollectionInitDateMsg() {
        return collectionInitDateMsg;
    }

    @Override
    public String getCollectionSizeMsg() {
        return collectionSizeMsg;
    }

    @Override
    public String getInputNameMsg() {
        return inputNameMsg;
    }

    @Override
    public String getInputCoordinatesXMsg() {
        return inputCoordinatesXMsg;
    }

    @Override
    public String getInputCoordinatesYMsg() {
        return inputCoordinatesYMsg;
    }

    @Override
    public String getInputHeightMsg() {
        return inputHeightMsg;
    }

    @Override
    public String getInputBirthdayMsg() {
        return inputBirthdayMsg;
    }

    @Override
    public String getInputEyeColorMsg() {
        return inputEyeColorMsg;
    }

    @Override
    public String getInputHairColorMsg() {
        return inputHairColorMsg;
    }

    @Override
    public String getInputLocationXMsg() {
        return inputLocationXMsg;
    }

    @Override
    public String getInputLocationYMsg() {
        return inputLocationYMsg;
    }

    @Override
    public String getInputLocationNameMsg() {
        return inputLocationNameMsg;
    }
}
