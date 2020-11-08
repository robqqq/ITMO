import java.util.Objects;

public final class Food implements IFood{
    private int calories;

    Food(int calories) {
        this.calories = calories;
    }

    @Override
    public int getCalories() {
        return calories;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Food food = (Food) o;
        return calories == food.calories;
    }

    @Override
    public int hashCode() {
        return Objects.hash(calories);
    }

    @Override
    public String toString() {
        return "Food: " +
                "calories=" + calories;
    }
}