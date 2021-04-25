package person;

import exceptions.*;
import java.time.LocalDateTime;

/**
 * Реализация интерфеса PersonBuilder
 */
public class RawPersonBuilderImpl implements RawPersonBuilder {
    private int id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private CoordinatesBuilder coordinatesBuilder; //Поле не может быть null
    private LocalDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private long height; //Значение поля должно быть больше 0
    private LocalDateTime birthday; //Поле не может быть null
    private EyeColor eyeColor; //Поле не может быть null
    private HairColor hairColor; //Поле не может быть null
    private LocationBuilder locationBuilder; //Поле не может быть null
    private final PersonValidator validator;

    public RawPersonBuilderImpl(){
        validator = new PersonValidatorImpl();
    }

    @Override
    public void setName(String name) throws InvalidNameException {
        if (validator.validateName(name)) {
            this.name = name;
        } else {
            throw new InvalidNameException();
        }
    }

    @Override
    public void setCoordinatesX(Double x) throws InvalidCoordinatesXException {
        if (coordinatesBuilder == null){
            coordinatesBuilder = new CoordinatesBuilderImpl();
        }
        coordinatesBuilder.setX(x);
    }

    @Override
    public void setCoordinatesY(long y) throws InvalidCoordinatesYException {
        if (coordinatesBuilder == null){
            coordinatesBuilder = new CoordinatesBuilderImpl();
        }
        coordinatesBuilder.setY(y);
    }

    @Override
    public void setHeight(long height) throws InvalidHeightException {
        if (validator.validateHeight(height)){
            this.height = height;
        } else {
            throw new InvalidHeightException();
        }
    }

    @Override
    public void setBirthday(LocalDateTime birthday) throws InvalidBirthdayException {
        if (validator.validateBirthday(birthday)){
            this.birthday = birthday;
        } else {
            throw new InvalidBirthdayException();
        }
    }

    @Override
    public void setEyeColor(EyeColor eyeColor) throws InvalidEyeColorException {
        if (validator.validateEyeColor(eyeColor)){
            this.eyeColor = eyeColor;
        } else {
            throw new InvalidEyeColorException();
        }
    }

    @Override
    public void setHairColor(HairColor hairColor) throws InvalidHairColorException {
        if (validator.validateHairColor(hairColor)){
            this.hairColor = hairColor;
        } else {
            throw new InvalidHairColorException();
        }
    }

    @Override
    public void setLocationX(float x){
        if (locationBuilder == null){
            locationBuilder = new LocationBuilderImpl();
        }
        locationBuilder.setX(x);
    }

    @Override
    public void setLocationY(long y){
        if (locationBuilder == null){
            locationBuilder = new LocationBuilderImpl();
        }
        locationBuilder.setY(y);
    }

    @Override
    public void setLocationName(String name) throws InvalidLocationNameException {
        if (locationBuilder == null){
            locationBuilder = new LocationBuilderImpl();
        }
        if (name == null || name.trim().equals("")){
            locationBuilder.setName(null);
        } else {
            locationBuilder.setName(name);
        }
    }

    @Override
    public RawPerson getRawPerson(){
        return new RawPerson(name, coordinatesBuilder.getCoordinates(), height, birthday, eyeColor,
                hairColor, locationBuilder.getLocation());
    }
}
