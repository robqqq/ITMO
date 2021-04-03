package person;

import java.time.LocalDateTime;

public class PersonValidatorImpl implements PersonValidator{

    @Override
    public boolean validateName(String name) {
        return name != null && !name.equals("");
    }

    @Override
    public boolean validateId(int id){
        return id > 0;
    }

    @Override
    public boolean validateCoordinates(Coordinates coordinates) {
        return coordinates != null;
    }

    @Override
    public boolean validateCreationDate(LocalDateTime creationDate) {
        return creationDate != null;
    }

    @Override
    public boolean validateHeight(Long height) {
        return height > 0;
    }

    @Override
    public boolean validateBirthday(LocalDateTime birthday) {
        return birthday != null;
    }

    @Override
    public boolean validateEyeColor(EyeColor eyeColor) {
        return eyeColor != null;
    }

    @Override
    public boolean validateHairColor(HairColor hairColor) {
        return hairColor != null;
    }

    @Override
    public boolean validateLocation(Location location) {
        return location != null;
    }
}
