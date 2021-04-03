package serverCommands;

import collectionManager.CollectionManager;
import messages.Messenger;

/**
 * Класс команды, которая выводит значения поля eyeColor всех элементов в порядке убывания
 */
public class PrintFieldDescendingEyeColorCommand implements ServerCommand{
    private CollectionManager collectionManager;
    private Messenger messenger;

    /**
     * @param collectionManager менеджер коллекции
     */
    public PrintFieldDescendingEyeColorCommand(CollectionManager collectionManager, Messenger messenger){
        this.collectionManager = collectionManager;
        this.messenger = messenger;
    }

    @Override
    public String execute() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(messenger.getMsg("printFieldDescendingEyeColorOutput"))
                .append(":\n");
        collectionManager.getPersonStream()
                .sorted((o1, o2) -> o2.getEyeColor().getHex() - o1.getEyeColor().getHex())
                .map(person -> person.getEyeColor().toString().concat("\n"))
                .forEachOrdered(stringBuilder::append);
        return stringBuilder.toString();
    }
}