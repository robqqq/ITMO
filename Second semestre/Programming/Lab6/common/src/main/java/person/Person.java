package person;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Класс человека
 */
public class Person implements Comparable<Person>{
    private final int id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private final String name; //Поле не может быть null, Строка не может быть пустой
    private final Coordinates coordinates; //Поле не может быть null
    private final LocalDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private final long height; //Значение поля должно быть больше 0
    private final LocalDateTime birthday; //Поле не может быть null
    private final EyeColor eyeColor; //Поле не может быть null
    private final HairColor hairColor; //Поле не может быть null
    private final Location location; //Поле не может быть null

    public Person(int id, RawPerson rawPerson, LocalDateTime creationDate){
        this.id = id;
        this.name = rawPerson.getName();
        this.coordinates = rawPerson.getCoordinates();
        this.creationDate = creationDate;
        this.height = rawPerson.getHeight();
        this.birthday = rawPerson.getBirthday();
        this.eyeColor = rawPerson.getEyeColor();
        this.hairColor = rawPerson.getHairColor();
        this.location = rawPerson.getLocation();
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

    public RawPerson getRawPerson(){
        return new RawPerson(getName(), getCoordinates(), getHeight(), getBirthday(), getEyeColor(), getHairColor(),
                getLocation());
    }

    @Override
    public int compareTo(Person p){
        return getName().compareTo(p.getName());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return id == person.id && height == person.height && name.equals(person.name)
                && coordinates.equals(person.coordinates) && creationDate.equals(person.creationDate)
                && birthday.equals(person.birthday) && eyeColor == person.eyeColor && hairColor == person.hairColor
                && location.equals(person.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, coordinates, creationDate, height, birthday, eyeColor, hairColor, location);
    }

    @Override
    public String toString() {
        return String.format("Person(id=%d, name=%s, coordinates=%s, creationDate=%s, height=%d, birthday=%s, " +
                        "eyeColor=%s, hairColor=%s, location=%s)", id, name, coordinates, creationDate, height,
                birthday, eyeColor, hairColor, location);
    }
}
