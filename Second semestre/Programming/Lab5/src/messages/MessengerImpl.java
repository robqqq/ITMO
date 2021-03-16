package messages;

import exceptions.NoMsgException;
import exceptions.NoSuchCommandException;
import exceptions.NoSuchFieldException;
import person.Person;

/**
 * Реализация интерфеса мессенджера
 */
public class MessengerImpl implements Messenger{
    private CommandMessages commandMessages;
    private ExceptionMessages exceptionMessages;
    private CollectionMessages collectionMessages;
    private String startMsg;
    private String finishMsg;

    /**
     * @param commandMessages сообщения команд
     * @param exceptionMessages сообщения ошибок
     * @param collectionMessages сообщения коллекции
     */
    public MessengerImpl(CommandMessages commandMessages, ExceptionMessages exceptionMessages, CollectionMessages collectionMessages){
        this.commandMessages = commandMessages;
        this.exceptionMessages = exceptionMessages;
        this.collectionMessages = collectionMessages;
        startMsg = "Program started";
        finishMsg = "Program finished";
    }

    @Override
    public String getStartMsg() {
        return startMsg;
    }

    @Override
    public String getFinishMsg() {
        return finishMsg;
    }

    @Override
    public String getExceptionMsg(String msgName){
        switch (msgName){
            case "invalidId":
                return exceptionMessages.getInvalidIdMsg();

            case "notUniqueId":
                return exceptionMessages.getNotUniqueIdMsg();

            case "noIdLeft":
                return exceptionMessages.getNoIdLeftMsg();

            case "invalidName":
                return exceptionMessages.getInvalidNameMsg();

            case "invalidCoordinates":
                return exceptionMessages.getInvalidCoordinatesMsg();

            case "invalidCoordinatesX":
                return exceptionMessages.getInvalidCoordinatesXMsg();

            case "invalidCoordinatesY":
                return exceptionMessages.getInvalidCoordinatesYMsg();

            case "invalidCreationDate":
                return exceptionMessages.getInvalidCreationDateMsg();

            case "invalidHeight":
                return exceptionMessages.getInvalidHeightMsg();

            case "invalidBirthday":
                return exceptionMessages.getInvalidBirthdayMsg();

            case "invalidEyeColor":
                return exceptionMessages.getInvalidEyeColorMsg();

            case "invalidHairColor":
                return exceptionMessages.getInvalidHairColorMsg();

            case "invalidLocation":
                return exceptionMessages.getInvalidLocationMsg();

            case "invalidLocationName":
                return exceptionMessages.getInvalidLocationNameMsg();

            case "noInteger":
                return exceptionMessages.getNoIntegerMsg();

            case "noFloat":
                return exceptionMessages.getNoFloatMsg();

            case "noDate":
                return exceptionMessages.getNoDateMsg();

            case "noEnum":
                return exceptionMessages.getNoEnumMsg();

            case "noArg":
                return exceptionMessages.getNoArgMsg();

            case "brokenData":
                return exceptionMessages.getBrokenDataMsg();

            case "noEnvVar":
                return exceptionMessages.getNoEnvVarMsg();

            case "noData":
                return exceptionMessages.getNoDataMsg();

            case "wrongFieldType":
                return exceptionMessages.getWrongFieldTypeMsg();

            case "noSuchCommand":
                return exceptionMessages.getNoSuchCommandMsg();

            case "noSuchId":
                return exceptionMessages.getNoSuchIdMsg();

            case "noSuchField":
                return exceptionMessages.getNoSuchFieldMsg();

            case "noSuchElement":
                return exceptionMessages.getNoSuchElementMsg();

            case "script":
                return exceptionMessages.getScriptMsg();

            case "noFile":
                return exceptionMessages.getNoFileMsg();

            case "scriptRecursion":
                return exceptionMessages.getScriptRecursionMsg();

            default:
                throw new NoMsgException(exceptionMessages.getNoMsgMsg());
        }
    }

    @Override
    public String getCommandDescription(String commandName) throws NoSuchCommandException {
        switch (commandName){
            case "add":
                return commandMessages.getAddDescription();

            case "add_if_max":
                return commandMessages.getAddIfMaxDescription();

            case "add_if_min":
                return commandMessages.getAddIfMinDescription();

            case "clear":
                return commandMessages.getClearDescription();

            case "execute_script":
                return commandMessages.getExecuteScriptDescription();

            case "exit":
                return commandMessages.getExitDescription();

            case "filter_contains_name":
                return commandMessages.getFilterContainsNameDescription();

            case "help":
                return commandMessages.getHelpDescription();

            case "history":
                return commandMessages.getHistoryDescription();

            case "info":
                return commandMessages.getInfoDescription();

            case "max_by_eye_color":
                return commandMessages.getMaxByEyeColorDescription();

            case "print_field_descending_eye_color":
                return commandMessages.getPrintFieldDescendingEyeColorDescription();

            case "remove_by_id":
                return commandMessages.getRemoveByIdDescription();

            case "save":
                return commandMessages.getSaveDescription();

            case "show":
                return commandMessages.getShowDescription();

            case "update":
                return commandMessages.getUpdateDescription();

            default:
                throw new NoSuchCommandException(exceptionMessages.getNoSuchCommandMsg());
        }
    }

    @Override
    public String getFieldInputMsg(String fieldName) {
        switch(fieldName){
            case "name":
                return collectionMessages.getInputNameMsg();

            case "coordinatesX":
                return collectionMessages.getInputCoordinatesXMsg();

            case "coordinatesY":
                return collectionMessages.getInputCoordinatesYMsg();

            case "height":
                return collectionMessages.getInputHeightMsg();

            case "birthday":
                return collectionMessages.getInputBirthdayMsg();

            case "eyeColor":
                return collectionMessages.getInputEyeColorMsg();

            case "hairColor":
                return collectionMessages.getInputHairColorMsg();

            case "locationX":
                return collectionMessages.getInputLocationXMsg();

            case "locationY":
                return collectionMessages.getInputLocationYMsg();

            case "locationName":
                return collectionMessages.getInputLocationNameMsg();

            default:
                throw new NoSuchFieldException(exceptionMessages.getNoSuchFieldMsg());
        }
    }

    @Override
    public String getCollectionTypeMsg() {
        return collectionMessages.getCollectionTypeMsg();
    }

    @Override
    public String getCollectionInitDateMsg() {
        return collectionMessages.getCollectionInitDateMsg();
    }

    @Override
    public String getCollectionSizeMsg() {
        return collectionMessages.getCollectionSizeMsg();
    }

    @Override
    public String getPersonString(Person person) {
        return collectionMessages.getPersonString(person);
    }
}
