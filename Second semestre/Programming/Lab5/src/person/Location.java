package person;

/**
 * Класс локации человека
 */
public class Location {
    private float x;
    private long y;
    private String name; //Длина строки не должна быть больше 480, Поле может быть null

    public Location(float x, long y, String name){
        this.x = x;
        this.y = y;
        this.name = name;
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
