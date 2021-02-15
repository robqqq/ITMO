package main;

import exceptions.InvalidArgumentException;

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
     * @throws InvalidArgumentException
     */
    public Coordinates(Double x, long y) throws InvalidArgumentException {
        setX(x);
        setY(y);
    }

    /**
     * Конструктор
     */
    public Coordinates(){}

    /**
     * Метод, который устанавливают координату X
     * @param x
     * @throws InvalidArgumentException
     */
    public void setX(Double x) throws InvalidArgumentException {
        if (x <= 882 && x != null) {
            this.x = x;
        } else {
            throw new InvalidArgumentException("Invalid value for the coordinates x field");
        }
    }

    /**
     * Метод, который устанавливает координату Y
     * @param y
     * @throws InvalidArgumentException
     */
    public void setY(long y) throws InvalidArgumentException {
        if (y > -266) {
            this.y = y;
        } else {
            throw new InvalidArgumentException("Invalid value for the coordinates y field");
        }
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
