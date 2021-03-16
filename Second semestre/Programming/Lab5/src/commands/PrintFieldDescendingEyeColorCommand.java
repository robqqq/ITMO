package commands;

import collectionManager.CollectionManager;
import output.OutputManager;

/**
 * Класс команды, которая выводит значения поля eyeColor всех элементов в порядке убывания
 */
public class PrintFieldDescendingEyeColorCommand implements Command{
    private CollectionManager collectionManager;
    private OutputManager outputManager;

    /**
     * @param collectionManager менеджер коллекции
     * @param outputManager менеджер вывода
     */
    public PrintFieldDescendingEyeColorCommand(CollectionManager collectionManager, OutputManager outputManager){
        this.collectionManager = collectionManager;
        this.outputManager = outputManager;
    }

    @Override
    public void execute() {
        collectionManager.getPersonStream()
                .sorted((o1, o2) -> o2.getEyeColor().getHex() - o1.getEyeColor().getHex())
                .map(person -> person.getEyeColor().toString().concat("\n"))
                .forEachOrdered(outputManager::printMsg);
    }
}