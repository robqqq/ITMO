package commands;

import main.PersonManager;

/**
 * Класс команды, которая выводит в стандартный поток вывода все элементы коллекции в строковом представлении
 */
public class ShowCommand implements Command{
    private PersonManager personManager;

    /**
     * Конструктор
     * @param personManager
     */
    ShowCommand(PersonManager personManager){
        this.personManager = personManager;
    }

    /**
     * Метод, который запускает команду
     * @param args
     */
    @Override
    public void execute(String[] args) {
        personManager.printPersonsTreeSet();
    }
}
