package commands;

import collectionManager.CollectionManager;
import messages.Messenger;
import output.OutputManager;

import java.util.Comparator;

/**
 * Класс команды, которая выводит любой объект из коллекции, значение поля eyeColor которого является максимальным
 */
public class MaxByEyeColorCommand implements Command{
    private CollectionManager collectionManager;
    private Messenger messenger;
    private OutputManager outputManager;

    /**
     * @param collectionManager менеджер коллекции
     * @param messenger мессенджер
     * @param outputManager менеджер вывода
     */
    public MaxByEyeColorCommand(CollectionManager collectionManager, Messenger messenger, OutputManager outputManager){
        this.collectionManager = collectionManager;
        this.messenger = messenger;
        this.outputManager = outputManager;
    }

    @Override
    public void execute() {
        outputManager.printMsg(messenger.getPersonString(collectionManager.getPersonStream()
                .max(Comparator.comparingInt(o -> o.getEyeColor().getHex()))
                .get()) + "\n");
    }
}
