package person;

import java.time.LocalDateTime;

/**
 * Интерфейс для валдиации полей объектов класса Person
 */
public interface PersonValidator {

    /**
     * @param name имя
     * @return true - корректно, false - некорректно
     */
    boolean validateName(String name);

    /**
     * @param id идентификатор
     * @return true - корректно, false - некорректно
     */
    boolean validateId(int id);

    /**
     * @param coordinates координаты
     * @return true - корректно, false - некорректно
     */
    boolean validateCoordinates(Coordinates coordinates);

    /**
     * @param creationDate дата создания
     * @return true - корректно, false - некорректно
     */
    boolean validateCreationDate(LocalDateTime creationDate);

    /**
     * @param height рост
     * @return true - корректно, false - некорректно
     */
    boolean validateHeight(Long height);

    /**
     * @param birthday дата рождения
     * @return true - корректно, false - некорректно
     */
    boolean validateBirthday(LocalDateTime birthday);

    /**
     * @param eyeColor цвет глаз
     * @return true - корректно, false - некорректно
     */
    boolean validateEyeColor(EyeColor eyeColor);

    /**
     * @param hairColor цвет волос
     * @return true - корректно, false - некорректно
     */
    boolean validateHairColor(HairColor hairColor);

    /**
     * @param location локация
     * @return true - корректно, false - некорректно
     */
    boolean validateLocation(Location location);
}
