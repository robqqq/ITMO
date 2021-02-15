package commands;

import main.PersonManager;

/**
 * Класс команды, которая очищает коллекцию
 */
public class ClearCommand implements Command{
    private PersonManager personManager;

    /**
     * Конструктор
     * @param personManager
     */
    ClearCommand(PersonManager personManager){
        this.personManager = personManager;
    }

    /**
     * Метод, который запускает команду
     * @param args
     */
    @Override
    public void execute(String[] args) {
        personManager.removeAll();
    }
}
