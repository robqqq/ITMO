package main;

import exceptions.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Класс человека
 */
public class Person implements Comparable{
    private static int maxId = 0;
    private int id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.time.LocalDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private long height; //Значение поля должно быть больше 0
    private java.time.LocalDateTime birthday; //Поле не может быть null
    private EyeColor eyeColor; //Поле не может быть null
    private HairColor hairColor; //Поле не может быть null
    private Location location; //Поле не может быть null

    /**
     * Конструктор
     * @param name
     * @param coordinates
     * @param height
     * @param birthday
     * @param eyeColor
     * @param hairColor
     * @param location
     * @throws InvalidArgumentException
     */
    public Person(String name, Coordinates coordinates, long height, LocalDateTime birthday,
                  EyeColor eyeColor, HairColor hairColor, Location location) throws InvalidArgumentException{
        setId();
        setName(name);
        setCoordinates(coordinates);
        setCreationDate();
        setHeight(height);
        setBirthday(birthday);
        setEyeColor(eyeColor);
        setHairColor(hairColor);
        setLocation(location);
    }

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
     * @throws InvalidArgumentException
     */
    public Person(int id, String name, Coordinates coordinates, LocalDateTime creationDate, long height, LocalDateTime birthday,
                  EyeColor eyeColor, HairColor hairColor, Location location) throws InvalidArgumentException{
        setId(id);
        setName(name);
        setCoordinates(coordinates);
        this.creationDate = creationDate;
        setHeight(height);
        setBirthday(birthday);
        setEyeColor(eyeColor);
        setHairColor(hairColor);
        setLocation(location);
    }

    /**
     * Конструктор
     * @param name
     * @param height
     * @param birthday
     * @throws InvalidArgumentException
     */
    Person(String name, long height, LocalDateTime birthday) throws InvalidArgumentException {
        setId();
        setName(name);
        setHeight(height);
        setBirthday(birthday);
        setCreationDate();
    }

    /**
     * Конструктор
     * @param id
     * @param name
     * @param height
     * @param birthday
     * @param creationDate
     * @throws InvalidArgumentException
     */
    Person(int id, String name, long height, LocalDateTime birthday, LocalDateTime creationDate) throws InvalidArgumentException {
        setId(id);
        setName(name);
        setHeight(height);
        setBirthday(birthday);
        this.creationDate = creationDate;
    }

    private void setId(){
        maxId++;
        id = maxId;
    }

    private void setId(int id){
        this.id = id;
        if (id > maxId){
            maxId = id;
        }
    }

    /**
     * Метод, который устанавливает имя человеку
     * @param name
     * @throws InvalidArgumentException
     */
    void setName(String name) throws InvalidArgumentException {
        if (name != null && name != "") {
            this.name = name;
        } else {
            throw new InvalidArgumentException("Invalid value for the person name field");
        }
    }

    /**
     * Метод, который устанавливает локацию человеку
     * @param coordinates
     * @throws InvalidArgumentException
     */
    void setCoordinates(Coordinates coordinates) throws InvalidArgumentException {
        if (coordinates != null){
            this.coordinates = coordinates;
        } else {
            throw new InvalidArgumentException("Invalid value for the person coordinates field");
        }
    }

    /**
     * Метод, который устанавливает координату X человеку
     * @param x
     * @throws InvalidArgumentException
     */
    void setCoordinatesX(Double x) throws InvalidArgumentException {
        if (coordinates == null){
            coordinates = new Coordinates();
        }
        coordinates.setX(x);
    }

    /**
     * Метод, который устанавливает координату Y человеку
     * @param y
     * @throws InvalidArgumentException
     */
    void setCoordinatesY(long y) throws InvalidArgumentException {
        if (coordinates == null){
            coordinates = new Coordinates();
        }
        coordinates.setY(y);
    }

    /**
     * Метод, который устанвливает дату создания человеку
     */
    void setCreationDate(){
        creationDate = LocalDate.now().atStartOfDay();
    }

    /**
     * Метод, который устанавливает высоту человеку
     * @param height
     * @throws InvalidArgumentException
     */
    void setHeight(long height) throws InvalidArgumentException {
        if (height > 0){
            this.height = height;
        } else {
            throw new InvalidArgumentException("Invalid value for the person height field");
        }
    }

    /**
     * Метод, который устанавливает дату рождения человеку
     * @param birthday
     * @throws InvalidArgumentException
     */
    void setBirthday(LocalDateTime birthday) throws InvalidArgumentException {
        if (birthday != null){
            this.birthday = birthday;
        } else {
            throw new InvalidArgumentException("Invalid value for the person birthday field");
        }
    }

    /**
     * Метод, который устанавливает цвет глаз человеку
     * @param eyeColor
     * @throws InvalidArgumentException
     */
    void setEyeColor(EyeColor eyeColor) throws InvalidArgumentException {
        if (eyeColor != null){
            this.eyeColor = eyeColor;
        } else {
            throw new InvalidArgumentException("Invalid value for the person eyeColor field");
        }
    }

    /**
     * Метод, который устанавливает цвет волос человеку
     * @param hairColor
     * @throws InvalidArgumentException
     */
    void setHairColor(HairColor hairColor) throws InvalidArgumentException {
        if (hairColor != null){
            this.hairColor = hairColor;
        } else {
            throw new InvalidArgumentException("Invalid value for the person hairColor field");
        }
    }

    /**
     * Метод, который устанавливает локацию человеку
     * @param location
     * @throws InvalidArgumentException
     */
    void setLocation(Location location) throws InvalidArgumentException {
        if (location != null){
            this.location = location;
        } else {
            throw new InvalidArgumentException("Invalid value for the person location field");
        }
    }

    /**
     * Метод, который устанавливает значение X локации челвоека
     * @param x
     */
    void setLocationX(float x){
        if (location == null){
            location = new Location();
        }
        location.setX(x);
    }

    /**
     * Метод, который устанавливает значение y локации человека
     * @param y
     */
    void setLocationY(long y){
        if (location == null){
            location = new Location();
        }
        location.setY(y);
    }

    /**
     * Метод, который устанавливает название локации человека
     * @param name
     * @throws InvalidArgumentException
     */
    void setLocationName(String name) throws InvalidArgumentException {
        if (location == null){
            location = new Location();
        }
        if (name.trim() != ""){
            location.setName(name);
        }
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
     * Метод, который возвращает цветт волос человека
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
     * @param o
     * @return
     */
    @Override
    public int compareTo(Object o) {
        Person p = (Person) o;
        if (height == p.height){
            return id - p.id;
        } else {
            return (int) (height - p.height);
        }
    }
}
