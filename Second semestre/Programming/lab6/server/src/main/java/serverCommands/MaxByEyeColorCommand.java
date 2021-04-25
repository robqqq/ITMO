package serverCommands;

import collectionManager.CollectionManager;
import messages.Messenger;

import java.util.Comparator;

/**
 * Класс команды, которая выводит любой объект из коллекции, значение поля eyeColor которого является максимальным
 */
public class MaxByEyeColorCommand implements ServerCommand{
    private CollectionManager collectionManager;
    private Messenger messenger;

    /**
     * @param collectionManager менеджер коллекции
     * @param messenger мессенджер
     */
    public MaxByEyeColorCommand(CollectionManager collectionManager, Messenger messenger){
        this.collectionManager = collectionManager;
        this.messenger = messenger;
    }

    @Override
    public String execute() {
        return messenger.getMsg("maxByEyeColorOutput") + ":\n" +
                messenger.getPersonString(collectionManager.getPersonStream()
                .max(Comparator.comparingInt(o -> o.getEyeColor().getHex()))
                .get()) + "\n";
    }
}
