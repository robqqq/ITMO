package commands;

import main.ObjectManager;

/**
 * Класс команды, которая выводит в стандартный поток вывода все элементы коллекции в строковом представлении
 */
public class ShowCommand implements Command{
    private ObjectManager personManager;

    /**
     * Конструктор
     * @param personManager
     */
    ShowCommand(ObjectManager personManager){
        this.personManager = personManager;
    }

    /**
     * Метод, который запускает команду
     * @param args
     */
    @Override
    public void execute(String[] args) {
        personManager.printCollection();
    }
}
