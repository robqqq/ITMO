package commands;

import collectionManager.CollectionManager;
import input.InputManager;
import messages.Messenger;
import output.OutputManager;
import person.EyeColor;
import person.Person;

import java.util.Comparator;

/**
 * Класс команды, которая выводит значения поля eyeColor всех элементов в порядке убывания
 */
public class PrintFieldDescendingEyeColorCommand implements Command{
    private CollectionManager collectionManager;
    private OutputManager outputManager;

    public PrintFieldDescendingEyeColorCommand(CollectionManager collectionManager, OutputManager outputManager){
        this.collectionManager = collectionManager;
        this.outputManager = outputManager;
    }

    /**
     * Метод, который запускает команду
     */
    @Override
    public void execute() {
        collectionManager.getPersonStream()
                .sorted((o1, o2) -> o2.getEyeColor().getHex() - o1.getEyeColor().getHex())
                .map(person -> person.getEyeColor().toString().concat("\n"))
                .forEachOrdered(outputManager::printMsg);
    }
}