package person;

import java.time.LocalDateTime;

public interface PersonValidator {

    boolean validateName(String name);

    boolean validateId(int id);

    boolean validateCoordinates(Coordinates coordinates);

    boolean validateCreationDate(LocalDateTime creationDate);

    boolean validateHeight(Long height);

    boolean validateBirthday(LocalDateTime birthday);

    boolean validateEyeColor(EyeColor eyeColor);

    boolean validateHairColor(HairColor hairColor);

    boolean validateLocation(Location location);
}
