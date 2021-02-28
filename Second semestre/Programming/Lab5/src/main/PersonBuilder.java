package main;

import exceptions.InvalidArgumentException;
import exceptions.NoIdLeftException;
import exceptions.NotUniqueIdException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.TreeSet;

public class PersonBuilder implements PersonBuilderInterface {
    private int id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.time.LocalDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private long height; //Значение поля должно быть больше 0
    private java.time.LocalDateTime birthday; //Поле не может быть null
    private EyeColor eyeColor; //Поле не может быть null
    private HairColor hairColor; //Поле не может быть null
    private Location location; //Поле не может быть null
    private static Set<Integer> usedId = new TreeSet<>();

    @Override
    public void removeId(int id){
        usedId.remove(id);
    }

    @Override
    public void removeAllId(){
        usedId.clear();
    }

    @Override
    public void setId() throws NoIdLeftException {
        boolean full = true;
        for (int i = 1; i <= Integer.MAX_VALUE; i++){
            if (!usedId.contains(i)){
                id = i;
                full = false;
                usedId.add(i);
                break;
            }
        }
        if (full) {
            throw new NoIdLeftException();
        }
    }

    @Override
    public void setId(int id) throws InvalidArgumentException {
        if (id <= 0){
            throw new InvalidArgumentException("Invalid id value");
        }
        if (!usedId.contains(id)) {
            this.id = id;
            usedId.add(id);
        } else {
            throw new NotUniqueIdException();
        }
    }

    /**
     * Метод, который устанавливает имя человеку
     * @param name
     * @throws InvalidArgumentException
     */
    @Override
    public void setName(String name) throws InvalidArgumentException {
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
    @Override
    public void setCoordinates(Coordinates coordinates) throws InvalidArgumentException {
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
    @Override
    public void setCoordinatesX(Double x) throws InvalidArgumentException {
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
    @Override
    public void setCoordinatesY(long y) throws InvalidArgumentException {
        if (coordinates == null){
            coordinates = new Coordinates();
        }
        coordinates.setY(y);
    }

    /**
     * Метод, который устанвливает дату создания человеку
     */
    @Override
    public void setCreationDate(LocalDate creationDate){
        this.creationDate = creationDate.atStartOfDay();
    }

    /**
     * Метод, который устанавливает высоту человеку
     * @param height
     * @throws InvalidArgumentException
     */
    @Override
    public void setHeight(long height) throws InvalidArgumentException {
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
    @Override
    public void setBirthday(LocalDateTime birthday) throws InvalidArgumentException {
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
    @Override
    public void setEyeColor(EyeColor eyeColor) throws InvalidArgumentException {
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
    @Override
    public void setHairColor(HairColor hairColor) throws InvalidArgumentException {
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
    @Override
    public void setLocation(Location location) throws InvalidArgumentException {
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
    @Override
    public void setLocationX(float x){
        if (location == null){
            location = new Location();
        }
        location.setX(x);
    }

    /**
     * Метод, который устанавливает значение y локации человека
     * @param y
     */
    @Override
    public void setLocationY(long y){
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
    @Override
    public void setLocationName(String name) throws InvalidArgumentException {
        if (location == null){
            location = new Location();
        }
        if (name.trim() != ""){
            location.setName(name);
        }
    }

    @Override
    public Person getPerson() throws InvalidArgumentException {
        if (creationDate == null){
            creationDate = LocalDate.now().atStartOfDay();
        }
        return new Person(id, name, coordinates, creationDate, height, birthday, eyeColor, hairColor, location);
    }
}
