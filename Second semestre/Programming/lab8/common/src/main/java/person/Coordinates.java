package person;

import java.io.Serializable;
import java.util.Objects;

/**
 * Класс координат
 */
public class Coordinates implements Serializable {
    private static final long serialVersionUID = 1610850390120831496L;
    private final Double x; //Максимальное значение поля: 882, Поле не может быть null
    private final long y; //Значение поля должно быть больше -266

    /**
     * @param x координата x
     * @param y координата y
     */
    public Coordinates(Double x, long y){
        this.x = x;
        this.y = y;
    }

    /**
     * @return координата x
     */
    public Double getX() {
        return x;
    }

    /**
     * @return координата y
     */
    public long getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinates that = (Coordinates) o;
        return y == that.y && x.equals(that.x);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "Coordinates{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
