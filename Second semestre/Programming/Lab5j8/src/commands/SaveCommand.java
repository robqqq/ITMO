package commands;

import main.ObjectManager;

/**
 * Класс команды, которая сохраняет коллекцию в файл
 */
public class SaveCommand implements Command{
    private ObjectManager personManager;

    /**
     * Конструктор
     * @param personManager
     */
    SaveCommand(ObjectManager personManager){
        this.personManager = personManager;
    }

    /**
     * Метод, который запускает команду
     * @param args
     */
    @Override
    public void execute(String[] args) {
        personManager.writeToFile();
    }
}
