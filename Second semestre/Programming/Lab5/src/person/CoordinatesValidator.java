package person;

/**
 * Интерфейс для валдиации полей объектов класса Coordinates
 */
public interface CoordinatesValidator {

    /**
     * @param x координата x
     * @return true - корректно, false - некорректно
     */
    boolean validateCoordinatesX(Double x);

    /**
     * @param y координата y
     * @return true - корректно, false - некорректно
     */
    boolean validateCoordinatesY(long y);
}
