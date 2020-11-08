import java.util.Objects;

public final class Package implements IPackage{
    private String name;
    private int weight;
    private IFood food;

    public Package(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getWeight() {
        return weight;
    }

    @Override
    public IFood getFood() {
        return food;
    }

    @Override
    public void setFood(IFood food, int weight) {
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