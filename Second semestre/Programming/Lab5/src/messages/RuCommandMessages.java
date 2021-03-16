package messages;

/**
 * Реализация интерфейса CommandMessages с русской локализацией
 */
public class RuCommandMessages implements CommandMessages{
    private final String addDescription;
    private final String addIfMaxDescription;
    private final String addIfMinDescription;
    private final String clearDescription;
    private final String executeScriptDescription;
    private final String exitDescription;
    private final String filterContainsNameDescription;
    private final String helpDescription;
    private final String historyDescription;
    private final String infoDescription;
    private final String maxByEyeColorDescription;
    private final String printFieldDescendingEyeColorDescription;
    private final String removeByIdDescription;
    private final String saveDescription;
    private final String showDescription;
    private final String updateDescription;

    public RuCommandMessages() {
        addDescription = "add {element} : добавить новый элемент в коллекцию";
        addIfMaxDescription = "add_if_max {element} : добавить новый элемент в коллекцию, если его значение превышает " +
                "значение наибольшего элемента этой коллекции";
        addIfMinDescription = "add_if_min {element} : добавить новый элемент в коллекцию, если его значение меньше, " +
                "чем у наименьшего элемента этой коллекции";
        clearDescription = "clear : очистить коллекцию";
        executeScriptDescription = "execute_script file_name : считать и исполнить скрипт из указанного файла. " +
                "В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.";
        exitDescription = "exit : завершить программу (без сохранения в файл)";
        filterContainsNameDescription = "filter_contains_name name : вывести элементы, значение поля name которых " +
                "содержит заданную подстроку";
        helpDescription = "help : вывести справку по доступным командам";
        historyDescription = "history : вывести последние 9 команд (без их аргументов)";
        infoDescription = "info : вывести в стандартный поток вывода информацию о коллекции";
        maxByEyeColorDescription = "max_by_eye_color : вывести любой объект из коллекции, значение поля eyeColor " +
                "которого является максимальным";
        printFieldDescendingEyeColorDescription = "print_field_descending_eye_color : вывести значения поля eyeColor " +
                "всех элементов в порядке убывания";
        removeByIdDescription = "remove_by_id id : удалить элемент из коллекции по его id";
        saveDescription = "save : сохранить коллекцию в файл";
        showDescription = "show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении";
        updateDescription = "update id {element} : обновить значение элемента коллекции, id которого равен заданному";
    }

    @Override
    public String getAddDescription() {
        return addDescription;
    }

    @Override
    public String getAddIfMaxDescription() {
        return addIfMaxDescription;
    }

    @Override
    public String getAddIfMinDescription() {
        return addIfMinDescription;
    }

    @Override
    public String getClearDescription() {
        return clearDescription;
    }

    @Override
    public String getExecuteScriptDescription() {
        return executeScriptDescription;
    }

    @Override
    public String getExitDescription() {
        return exitDescription;
    }

    @Override
    public String getFilterContainsNameDescription() {
        return filterContainsNameDescription;
    }

    @Override
    public String getHelpDescription() {
        return helpDescription;
    }

    @Override
    public String getHistoryDescription() {
        return historyDescription;
    }

    @Override
    public String getInfoDescription() {
        return infoDescription;
    }

    @Override
    public String getMaxByEyeColorDescription() {
        return maxByEyeColorDescription;
    }

    @Override
    public String getPrintFieldDescendingEyeColorDescription() {
        return printFieldDescendingEyeColorDescription;
    }

    @Override
    public String getRemoveByIdDescription() {
        return removeByIdDescription;
    }

    @Override
    public String getSaveDescription() {
        return saveDescription;
    }

    @Override
    public String getShowDescription() {
        return showDescription;
    }

    @Override
    public String getUpdateDescription() {
        return updateDescription;
    }
}
