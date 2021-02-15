package main;

import exceptions.InvalidArgumentException;

/**
 * Класс локации человека
 */
public class Location {
    private float x;
    private long y;
    private String name; //Длина строки не должна быть больше 480, Поле может быть null

    /**
     * Конструктор
     * @param x
     * @param y
     * @param name
     * @throws InvalidArgumentException
     */
    public Location(float x, long y, String name) throws InvalidArgumentException {
        this.x = x;
        this.y = y;
        setName(name);
    }

    /**
     * Конструктор
     * @return
     */
    Location(){}

    /**
     * Метод, который устанавливает название локации
     * @param name
     * @throws InvalidArgumentException
     */
    void setName(String name) throws InvalidArgumentException {
        if (name.length() <= 480){
            this.name = name;
        } else {
            throw new InvalidArgumentException("Invalid value for the location name field");
        }
    }

    /**
     * Метод, который устанавливает значение X локации
     * @param x
     */
    void setX(float x) {
        this.x = x;
    }

    /**
     * Метод, который устанавливает значение Y локации
     * @param y
     */
    void setY(long y) {
        this.y = y;
    }

    /**
     * Метод, который возвращает значение X локации
     * @return
     */
    public float getX() {
        return x;
    }

    /**
     * Метод, который возвращает значение Y локации
     * @return
     */
    public long getY() {
        return y;
    }

    /**
     * Метод, который возвращает значение название локации
     * @return
     */
    public String getName() {
        return name;
    }
}
