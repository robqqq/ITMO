import java.util.Objects;

public class Package implements Nameable{
    private String name;
    private int weight;
    private Food food;

    public Package(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWeight() {
        return weight;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food, int weight) {
        this.food = food;
        this.weight = weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Package aPackage = (Package) o;
        return weight == aPackage.weight &&
                Objects.equals(name, aPackage.name) &&
                Objects.equals(food, aPackage.food);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, weight, food);
    }

    @Override
    public String toString() {
        return "Package: " +
                "name='" + name + '\'' +
                ", weight=" + weight;
    }
}