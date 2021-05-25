package serverCommands;

import collectionManager.CollectionManager;
import person.Person;

import java.util.Comparator;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Класс команды, которая выводит любой объект из коллекции, значение поля eyeColor которого является максимальным
 */
public class MaxByEyeColorCommand implements ServerCommand{
    private final CollectionManager collectionManager;
    private Locale locale;

    /**
     * @param collectionManager менеджер коллекции
     */
    public MaxByEyeColorCommand(CollectionManager collectionManager){
        this.collectionManager = collectionManager;
    }

    @Override
    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    @Override
    public String execute() {
        ResourceBundle rb = ResourceBundle.getBundle("messages", locale);
        Person max = collectionManager.getPersonStream()
                .max(Comparator.comparingInt(o -> o.getEyeColor().getHex())).get();
        return String.format("""
                        %s: %d;
                        %s: %s;
                        %s: %f;
                        %s: %d;
                        %s: %s;
                        %s: %d;
                        %s: %s;
                        %s: %s;
                        %s: %s;
                        %s: %f;
                        %s: %d;
                        %s: %s;
                        %s: %s
                        """,
                rb.getString("column.id"), max.getId(),
                rb.getString("column.name"), max.getName(),
                rb.getString("column.coordinates_x"), max.getCoordinates().getX(),
                rb.getString("column.coordinates_y"), max.getCoordinates().getY(),
                rb.getString("column.creation_date"), max.getCreationDate().toLocalDate(),
                rb.getString("column.height"), max.getHeight(),
                rb.getString("column.birthday"), max.getBirthday().toLocalDate(),
                rb.getString("column.eye_color"), max.getEyeColor(),
                rb.getString("column.hair_color"), max.getHairColor(),
                rb.getString("column.location_x"), max.getLocation().getX(),
                rb.getString("column.location_y"), max.getLocation().getY(),
                rb.getString("column.location_name"), max.getLocation().getName(),
                rb.getString("column.owner"), max.getOwner());
    }
}
