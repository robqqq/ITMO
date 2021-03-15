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
     * @param id
     * @param name
     * @param coordinates
     * @param creationDate
     * @param height
     * @param birthday
     * @param eyeColor
     * @param hairColor
     * @param location
     * @throws InvalidFieldException
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
     * Метод, который возвращает id человека
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     * Метод, который возвращает имя человека
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * Метод, который возвращает координаты человека
     * @return
     */
    public Coordinates getCoordinates() {
        return coordinates;
    }

    /**
     * Метод, который возвращает дату создания человека
     * @return
     */
    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    /**
     * Метод, который возвращает высоту человека
     * @return
     */
    public long getHeight() {
        return height;
    }

    /**
     * Метод, который возвращает дату рождения человека
     * @return
     */
    public LocalDateTime getBirthday() {
        return birthday;
    }

    /**
     * Метод, который возвращает цвет глаз человека
     * @return
     */
    public EyeColor getEyeColor() {
        return eyeColor;
    }

    /**
     * Метод, который возвращает цвет волос человека
     * @return
     */
    public HairColor getHairColor() {
        return hairColor;
    }

    /**
     * Метод, который возвращает локацию человека
     * @return
     */
    public Location getLocation() {
        return location;
    }

    /**
     * Переопредление метода класса Comparable для того, чтобы объект мог храниться и сортироваться в коллекции TreeSet
     * @param p
     * @return
     */
    @Override
    public int compareTo(Person p){
        int result = Long.compare(height, p.height);
        if (result != 0){
            return result;
        }
        return Integer.compare(id, p.id);
    }
}
