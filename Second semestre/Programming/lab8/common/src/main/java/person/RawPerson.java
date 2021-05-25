package person;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class RawPerson implements Comparable<RawPerson>, Serializable {
    private static final long serialVersionUID = 5366370939317668205L;
    private final String name; //Поле не может быть null, Строка не может быть пустой
    private final Coordinates coordinates; //Поле не может быть null
    private final long height; //Значение поля должно быть больше 0
    private final LocalDateTime birthday; //Поле не может быть null
    private final EyeColor eyeColor; //Поле не может быть null
    private final HairColor hairColor; //Поле не может быть null
    private final Location location; //Поле не может быть null

    /**
     * Конструктор
     * @param name имя
     * @param coordinates координаты
     * @param height рост
     * @param birthday дата рождения
     * @param eyeColor цвет глаз
     * @param hairColor цвет волос
     * @param location локация
     */
    public RawPerson(String name, Coordinates coordinates, long height, LocalDateTime birthday,
                  EyeColor eyeColor, HairColor hairColor, Location location){
        this.name = name;
        this.coordinates = coordinates;
        this.height = height;
        this.birthday = birthday;
        this.eyeColor = eyeColor;
        this.hairColor = hairColor;
        this.location = location;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RawPerson rawPerson = (RawPerson) o;
        return height == rawPerson.height && name.equals(rawPerson.name) && coordinates.equals(rawPerson.coordinates)
                && birthday.equals(rawPerson.birthday) && eyeColor == rawPerson.eyeColor
                && hairColor == rawPerson.hairColor && location.equals(rawPerson.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, coordinates, height, birthday, eyeColor, hairColor, location);
    }

    @Override
    public String toString() {
        return "RawPerson{" +
                "name='" + name + '\'' +
                ", coordinates=" + coordinates +
                ", height=" + height +
                ", birthday=" + birthday +
                ", eyeColor=" + eyeColor +
                ", hairColor=" + hairColor +
                ", location=" + location +
                '}';
    }

    @Override
    public int compareTo(RawPerson o) {
        return getName().compareTo(o.getName());
    }
}
