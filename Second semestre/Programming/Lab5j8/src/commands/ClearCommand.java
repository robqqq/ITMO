package commands;

import main.ObjectManager;

/**
 * Класс команды, которая очищает коллекцию
 */
public class ClearCommand implements Command{
    private ObjectManager personManager;

    /**
     * Конструктор
     * @param personManager
     */
    ClearCommand(ObjectManager personManager){
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
