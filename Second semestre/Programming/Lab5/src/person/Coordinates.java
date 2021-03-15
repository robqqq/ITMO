package person;

import exceptions.InvalidFieldException;

/**
 * Класс координат человека
 */
public class Coordinates {
    private Double x; //Максимальное значение поля: 882, Поле не может быть null
    private long y; //Значение поля должно быть больше -266

    /**
     * Конструктор
     * @param x
     * @param y
     * @throws InvalidFieldException
     */
    public Coordinates(Double x, long y){
        this.x = x;
        this.y = y;
    }

    /**
     * Метод, который возвращает координату X
     * @return
     */
    public Double getX() {
        return x;
    }

    /**
     * Метод, который возвращает координату Y
     * @return
     */
    public long getY() {
        return y;
    }
}
