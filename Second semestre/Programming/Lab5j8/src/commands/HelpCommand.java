package commands;

/**
 * Класс команды, которая выводит справку по доступным командам
 */
public class HelpCommand implements Command{

    /**
     * Метод, который запускает команду
     * @param args
     */
    @Override
    public void execute(String[] args) {
        System.out.println("help: вывести справку по доступным командам");
        System.out.println("info: вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, " +
                "количество элементов и т.д.)");
        System.out.println("show: вывести в стандартный поток вывода все элементы коллекции в строковом представлении");
        System.out.println("add name height birthday: добавить новый элемент в коллекцию");
        System.out.println("add name coordinates.x coordinates.y height birthday eyeColor hairColor location.x " +
                "location.y location.name: добавить новый элемент в коллекцию");
        System.out.println("update id name height birthday: обновить значение элемента коллекции, id которого равен " +
                "заданному");
        System.out.println("update id name coordinates.x coordinates.y height birthday eyeColor hairColor location.x: " +
                "обновить значение элемента коллекции, id которого равен заданному");
        System.out.println("remove_by_id id: удалить элемент из коллекции по его id");
        System.out.println("clear: очистить коллекцию");
        System.out.println("save: сохранить коллекцию в файл");
        System.out.println("execute_script file_name: считать и исполнить скрипт из указанного файла. В скрипте " +
                "содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме");
        System.out.println("exit: завершить программу (без сохранения в файл)");
        System.out.println("add_if_max name height birthday: добавить новый элемент в коллекцию, если его значение" +
                " превышает значение наибольшего элемента этой коллекции");
        System.out.println("add_if_max name coordinates.x coordinates.y height birthday eyeColor hairColor location.x " +
                "location.y location.name: добавить новый элемент в коллекцию, если его значение" +
                " превышает значение наибольшего элемента этой коллекции");
        System.out.println("add_if_min name height birthday: добавить новый элемент в коллекцию, если его значение " +
                "меньше, чем у наименьшего элемента этой коллекции");
        System.out.println("add_if_min name coordinates.x coordinates.y height birthday eyeColor hairColor location.x " +
                "location.y location.name: добавить новый элемент в коллекцию, если его значение " +
                "меньше, чем у наименьшего элемента этой коллекции");
        System.out.println("history : вывести последние 9 команд (без их аргументов)");
        System.out.println("max_by_eye_color: вывести любой объект из коллекции, значение поля eyeColor " +
                "которого является максимальным");
        System.out.println("filter_contains_name name: вывести элементы, значение поля name которых " +
                "содержит заданную подстроку");
        System.out.println("print_field_descending_eye_color: вывести значения поля eyeColor всех " +
                "элементов в порядке убывания");
    }
}
