package person;

/**
 * Интерфейс для валдиации полей объектов класса Location
 */
public interface LocationValidator {

    /**
     * @param x координата x локации
     * @return true - корректно, false - некорректно
     */
    boolean validateLocationX(float x);

    /**
     * @param y координата y локации
     * @return true - корректно, false - некорректно
     */
    boolean validateLocationY(long y);

    /**
     * @param name название локации
     * @return true - корректно, false - некорректно
     */
    boolean validateLocationName(String name);
}
