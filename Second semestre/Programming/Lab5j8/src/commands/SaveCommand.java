package commands;

import main.PersonManager;

/**
 * Класс команды, которая сохраняет коллекцию в файл
 */
public class SaveCommand implements Command{
    private PersonManager personManager;

    /**
     * Конструктор
     * @param personManager
     */
    SaveCommand(PersonManager personManager){
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
