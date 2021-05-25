package serverCommands;

import collectionManager.CollectionManager;

import java.util.Locale;

/**
 * Класс команды, которая выводит значения поля eyeColor всех элементов в порядке убывания
 */
public class PrintFieldDescendingEyeColorCommand implements ServerCommand{
    private final CollectionManager collectionManager;
    private Locale locale;

    /**
     * @param collectionManager менеджер коллекции
     */
    public PrintFieldDescendingEyeColorCommand(CollectionManager collectionManager){
        this.collectionManager = collectionManager;
    }

    @Override
    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    @Override
    public String execute() {
        StringBuilder stringBuilder = new StringBuilder();
        collectionManager.getPersonStream()
                .sorted((o1, o2) -> o2.getEyeColor().getHex() - o1.getEyeColor().getHex())
                .map(person -> person.getEyeColor().toString().concat("\n"))
                .forEachOrdered(stringBuilder::append);
        return stringBuilder.toString();
    }
}