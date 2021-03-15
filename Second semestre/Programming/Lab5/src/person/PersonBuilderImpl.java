package person;

import collectionManager.IdManager;
import collectionManager.PersonIdManager;
import exceptions.InvalidFieldException;
import exceptions.NotUniqueIdException;
import messages.Messenger;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class PersonBuilderImpl implements PersonBuilder {
    private IdManager idManager;
    private int id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private CoordinatesBuilder coordinatesBuilder; //Поле не может быть null
    private java.time.LocalDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private long height; //Значение поля должно быть больше 0
    private java.time.LocalDateTime birthday; //Поле не может быть null
    private EyeColor eyeColor; //Поле не может быть null
    private HairColor hairColor; //Поле не может быть null
    private LocationBuilder locationBuilder; //Поле не может быть null
    private PersonValidator validator;
    private Messenger messenger;

    public PersonBuilderImpl(Messenger messenger){
        idManager = PersonIdManager.getInstance();
        validator = new PersonValidatorImpl();
        this.messenger = messenger;
    }

    @Override
    public void setId(){
        id = idManager.getFreeId();
    }

    @Override
    public void setId(int id) throws InvalidFieldException {
        if (validator.validateId(id)){
            if (idManager.idIsFree(id)) {
                this.id = id;
            } else {
                throw new NotUniqueIdException(messenger.getExceptionMsg("notUniqueId"));
            }
        } else {
            throw new InvalidFieldException(messenger.getExceptionMsg("invalidId"));
        }
    }

    /**
     * Метод, который устанавливает имя человеку
     * @param name
     * @throws InvalidFieldException
     */
    @Override
    public void setName(String name) throws InvalidFieldException {
        if (validator.validateName(name)) {
            this.name = name;
        } else {
            throw new InvalidFieldException(messenger.getExceptionMsg("invalidName"));
        }
    }

    /**
     * Метод, который устанавливает координату X человеку
     * @param x
     * @throws InvalidFieldException
     */
    @Override
    public void setCoordinatesX(Double x) throws InvalidFieldException {
        if (coordinatesBuilder == null){
            coordinatesBuilder = new CoordinatesBuilderImpl(messenger);
        }
        coordinatesBuilder.setX(x);
    }

    /**
     * Метод, который устанавливает координату Y человеку
     * @param y
     * @throws InvalidFieldException
     */
    @Override
    public void setCoordinatesY(long y) throws InvalidFieldException {
        if (coordinatesBuilder == null){
            coordinatesBuilder = new CoordinatesBuilderImpl(messenger);
        }
        coordinatesBuilder.setY(y);
    }

    /**
     * Метод, который устанвливает дату создания человеку
     */
    @Override
    public void setCreationDate(LocalDateTime creationDate) throws InvalidFieldException {
        if (validator.validateCreationDate(creationDate)) {
            this.creationDate = creationDate;
        } else {
            throw new InvalidFieldException(messenger.getExceptionMsg("invalidCreationDate"));
        }
    }

    /**
     * Метод, который устанавливает высоту человеку
     * @param height
     * @throws InvalidFieldException
     */
    @Override
    public void setHeight(long height) throws InvalidFieldException {
        if (validator.validateHeight(height)){
            this.height = height;
        } else {
            throw new InvalidFieldException(messenger.getExceptionMsg("invalidHeight"));
        }
    }

    /**
     * Метод, который устанавливает дату рождения человеку
     * @param birthday
     * @throws InvalidFieldException
     */
    @Override
    public void setBirthday(LocalDateTime birthday) throws InvalidFieldException {
        if (validator.validateBirthday(birthday)){
            this.birthday = birthday;
        } else {
            throw new InvalidFieldException(messenger.getExceptionMsg("invalidBirthday"));
        }
    }

    /**
     * Метод, который устанавливает цвет глаз человеку
     * @param eyeColor
     * @throws InvalidFieldException
     */
    @Override
    public void setEyeColor(EyeColor eyeColor) throws InvalidFieldException {
        if (validator.validateEyeColor(eyeColor)){
            this.eyeColor = eyeColor;
        } else {
            throw new InvalidFieldException(messenger.getExceptionMsg("invalidEyeColor"));
        }
    }

    /**
     * Метод, который устанавливает цвет волос человеку
     * @param hairColor
     * @throws InvalidFieldException
     */
    @Override
    public void setHairColor(HairColor hairColor) throws InvalidFieldException {
        if (validator.validateHairColor(hairColor)){
            this.hairColor = hairColor;
        } else {
            throw new InvalidFieldException(messenger.getExceptionMsg("invalidHairColor"));
        }
    }

    /**
     * Метод, который устанавливает значение X локации челвоека
     * @param x
     */
    @Override
    public void setLocationX(float x){
        if (locationBuilder == null){
            locationBuilder = new LocationBuilderImpl(messenger);
        }
        locationBuilder.setX(x);
    }

    /**
     * Метод, который устанавливает значение y локации человека
     * @param y
     */
    @Override
    public void setLocationY(long y){
        if (locationBuilder == null){
            locationBuilder = new LocationBuilderImpl(messenger);
        }
        locationBuilder.setY(y);
    }

    /**
     * Метод, который устанавливает название локации человека
     * @param name
     * @throws InvalidFieldException
     */
    @Override
    public void setLocationName(String name) throws InvalidFieldException {
        if (locationBuilder == null){
            locationBuilder = new LocationBuilderImpl(messenger);
        }
        if (!name.trim().equals("")){
            locationBuilder.setName(name);
        }
    }

    @Override
    public Person getPerson(){
        if (!validator.validateCreationDate(creationDate)){
            creationDate = LocalDate.now().atStartOfDay();
        }
        idManager.addId(id);
        return new Person(id, name, coordinatesBuilder.getCoordinates(), creationDate, height, birthday, eyeColor,
                hairColor, locationBuilder.getLocation());
    }
}
