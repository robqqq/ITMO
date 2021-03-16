package person;

/**
 * Класс локации
 */
public class Location {
    private float x;
    private long y;
    private String name; //Длина строки не должна быть больше 480, Поле может быть null

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
}
