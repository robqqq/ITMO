package messages;

import person.Person;

import java.util.HashMap;

public class MessengerImpl implements Messenger{
    private final HashMap<String, String> messages;

    public MessengerImpl(){
        messages = new HashMap<>();
        messages.put("start","program has started");
        messages.put("finish","program has finished");
        messages.put("invalidId", "invalid id value, must be more than 0");
        messages.put("notUniqueId", "not unique id");
        messages.put("invalidName", "invalid name value, must be not null");
        messages.put("invalidCoordinates", "invalid coordinates value, must be not null");
        messages.put("invalidCoordinatesX", "invalid coordinates.x value, must be no more than 882 and not null");
        messages.put("invalidCoordinatesY", "invalid coordinates.y value, must be more than -266");
        messages.put("invalidCoordinatesName", "invalid creationDate value, must be not null");
        messages.put("invalidCreationDate", "invalid height value, must be more than 0");
        messages.put("invalidHeight", "invalid height value, must be more than 0");
        messages.put("invalidBirthday", "invalid birthday value, must be not null");
        messages.put("invalidEyeColor", "invalid eyeColor value, must be not null");
        messages.put("invalidHairColor", "invalid hairColor value, must be not null");
        messages.put("invalidLocation", "invalid location value, must be not null");
        messages.put("invalidLocationName", "invalid location.name value, length must be no more than 480");
        messages.put("noInteger", "value must be integer number");
        messages.put("noFloat", "value must be float number");
        messages.put("noDate", "date must be in the specified format");
        messages.put("noEnum", "value must be selected from the suggested options");
        messages.put("noArg", "this command needs 1 argument");
        messages.put("wrongFieldType", "wrong field type");
        messages.put("noSuchCommand", "no such command");
        messages.put("noSuchId", "An element with this id does not exist");
        messages.put("endOfInput", "end of input");
        messages.put("scriptError", "script error, script execution stopped");
        messages.put("noFile", "this file does not exist");
        messages.put("scriptRecursion", "script calls recursion, script execution stopped");
        messages.put("addDescription", "add {element} : добавить новый элемент в коллекцию");
        messages.put("add_if_maxDescription", "add_if_max {element} : добавить новый элемент в коллекцию, " +
                "если его значение превышает значение наибольшего элемента этой коллекции");
        messages.put("add_if_minDescription", "add_if_min {element} : добавить новый элемент в коллекцию, " +
                "если его значение меньше, чем у наименьшего элемента этой коллекции");
        messages.put("clearDescription", "clear : очистить коллекцию");
        messages.put("execute_scriptDescription", "execute_script file_name : считать и исполнить скрипт " +
                "из указанного файла");
        messages.put("exitDescription", "exit : завершить программу");
        messages.put("filter_contains_nameDescription", "filter_contains_name name : вывести элементы, " +
                "значение поля name которых содержит заданную подстроку");
        messages.put("helpDescription", "help : вывести справку по доступным командам");
        messages.put("historyDescription", "history : вывести последние 9 команд (без их аргументов)");
        messages.put("infoDescription", "info : вывести в стандартный поток вывода информацию о коллекции");
        messages.put("max_by_eye_colorDescription", "max_by_eye_color : вывести любой объект из коллекции, " +
                "значение поля eyeColor которого является максимальным");
        messages.put("print_field_descending_eye_colorDescription", "print_field_descending_eye_color : " +
                "вывести значения поля eyeColor всех элементов в порядке убывания");
        messages.put("remove_by_idDescription", "remove_by_id id : удалить элемент из коллекции по его id");
        messages.put("showDescription", "show : вывести в стандартный поток вывода все элементы коллекции " +
                "в строковом представлении");
        messages.put("updateDescription", "update id {element} : обновить значение элемента коллекции, " +
                "id которого равен заданному");
        messages.put("inputName", "Name");
        messages.put("inputCoordinatesX", "coordinates.x");
        messages.put("inputCoordinatesY", "coordinates.y");
        messages.put("inputHeight", "height");
        messages.put("inputBirthday", "birthday (yyyy-MM-dd)");
        messages.put("inputEyeColor", "eyeColor (BLACK, ORANGE, WHITE)");
        messages.put("inputHairColor", "hairColor (GREEN, BLACK, BLUE, YELLOW, WHITE)");
        messages.put("inputLocationX", "location.x");
        messages.put("inputLocationY", "location.y");
        messages.put("inputLocationName", "location.name");
        messages.put("collectionType", "Collection type");
        messages.put("collectionSize", "Number of elements");
        messages.put("collectionInitDate", "Initialization date");
        messages.put("exitOutput", "exit application");
        messages.put("addOutput", "element added to the collection");
        messages.put("showOutput","all elements in the collection");
        messages.put("helpOutput","help for commands");
        messages.put("notAddOutput","element didn't add to the collection");
        messages.put("clearOutput","collection cleared");
        messages.put("filterContainsNameOutput","elements, contains name in the collection");
        messages.put("historyOutput","command history");
        messages.put("maxByEyeColorOutput","largest element by eyeColor");
        messages.put("printFieldDescendingEyeColorOutput","eye colors in descending order");
        messages.put("removeOutput","element removed from collection");
        messages.put("notRemoveOutput","element didn't remove from the collection because element with this id does not exist");
        messages.put("updateOutput","element updated successfully");
        messages.put("notUpdateOutput","element was not updated because element with this id does not exist");
        messages.put("saveOutput", "collection is saved to a file");
        messages.put("scriptOutput", "script is executed");
        messages.put("noConnection", "failed to establish a connection to the server, please try again");
    }

    @Override
    public String getMsg(String msgName){
        return messages.get(msgName);
    }
}
