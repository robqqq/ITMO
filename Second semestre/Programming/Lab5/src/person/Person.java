package person;

import exceptions.*;

import java.time.LocalDateTime;

/**
 * Класс человека
 */
public class Person implements Comparable<Person>{
    private final int id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private final String name; //Поле не может быть null, Строка не может быть пустой
    private final Coordinates coordinates; //Поле не может быть null
    private final java.time.LocalDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private final long height; //Значение поля должно быть больше 0
    private final java.time.LocalDateTime birthday; //Поле не может быть null
    private final EyeColor eyeColor; //Поле не может быть null
    private final HairColor hairColor; //Поле не может быть null
    private final Location location; //Поле не может быть null

    /**
     * Конструктор
     * @param id идентификатор
     * @param name имя
     * @param coordinates координаты
     * @param creationDate дата создания
     * @param height рост
     * @param birthday дата рождения
     * @param eyeColor цвет глаз
     * @param hairColor цвет волос
     * @param location локация
     */
    Person(int id, String name, Coordinates coordinates, LocalDateTime creationDate, long height, LocalDateTime birthday,
                  EyeColor eyeColor, HairColor hairColor, Location location){
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.height = height;
        this.birthday = birthday;
        this.eyeColor = eyeColor;
        this.hairColor = hairColor;
        this.location = location;
    }

    /**
     * @return идентификатор
     */
    public int getId() {
        return id;
    }

    /**
     * @return имя
     */
    public String getName() {
        return name;
    }

    /**
     * @return координаты
     */
    public Coordinates getCoordinates() {
        return coordinates;
    }

    /**
     * @return дата создания
     */
    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    /**
     * @return рост
     */
    public long getHeight() {
        return height;
    }

    /**
     * @return дата рождения
     */
    public LocalDateTime getBirthday() {
        return birthday;
    }

    /**
     * @return цвет глаз
     */
    public EyeColor getEyeColor() {
        return eyeColor;
    }

    /**
     * @return цвет волос
     */
    public HairColor getHairColor() {
        return hairColor;
    }

    /**
     * @return локация
     */
    public Location getLocation() {
        return location;
    }

    @Override
    public int compareTo(Person p){
        int result = Long.compare(height, p.height);
        if (result != 0){
            return result;
        }
        return Integer.compare(id, p.id);
    }
}
