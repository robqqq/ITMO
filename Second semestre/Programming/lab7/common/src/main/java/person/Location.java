package person;

import java.io.Serializable;
import java.util.Objects;

/**
 * Класс локации
 */
public class Location implements Serializable {
    private static final long serialVersionUID = -418147180688417077L;
    private final float x;
    private final long y;
    private final String name; //Длина строки не должна быть больше 480, Поле может быть null

    /**
     * @param x координата x локации
     * @param y координата y локации
     * @param name название локации
     */
    public Location(float x, long y, String name){
        this.x = x;
        this.y = y;
        this.name = name;
    }

    /**
     * @return координата x локации
     */
    public float getX() {
        return x;
    }

    /**
     * @return координата y локации
     */
    public long getY() {
        return y;
    }

    /**
     * @return название локации
     */
    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return Float.compare(location.x, x) == 0 && y == location.y && Objects.equals(name, location.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, name);
    }

    @Override
    public String toString() {
        return "Location{" +
                "x=" + x +
                ", y=" + y +
                ", name='" + name + '\'' +
                '}';
    }
}
