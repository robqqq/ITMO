import java.util.Objects;

public class Eater extends Human implements Eatable {
    private boolean eatingDiscipline;
    private int receivedCalories = 0;

    public Eater(String name) {
        super(name);
        eatingDiscipline = true;
    }

    public Eater(String name, boolean discipline) {
        super(name);
        eatingDiscipline = discipline;
    }

    public boolean getEatingDiscipline() {
        return this.eatingDiscipline;
    }

    public void eat(Package pack) {
        if (getLocation() == Location.FOODROOM) {
            receivedCalories += pack.getWeight() * pack.getFood().getCalories();
            System.out.println(getName() + " eats food from " + pack.getName());
        } else {
            System.out.println(getName() + " can eat only in " + Location.FOODROOM);
        }

    }

    public void eatAllFromStorage(Storage storage) {
        if (getLocation() == Location.FOODROOM) {
            System.out.println(getName() + " opens " + storage.getName());
            for (int i = 0; i < storage.getAmountPacks(); i++) {
                this.eat(storage.getPack(i));
                storage.outOfStorage(i);
            }

        } else {
            System.out.println(getName() + " can eat only in " + Location.FOODROOM);
        }
    }

    public int getReceivedCalories() {
        return receivedCalories;
    }

    public void setSameReceivedCalories(Eater eater) {
        this.receivedCalories = eater.getReceivedCalories();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Eater eater = (Eater) o;
        return eatingDiscipline == eater.eatingDiscipline &&
                receivedCalories == eater.receivedCalories;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), eatingDiscipline, receivedCalories);
    }

    @Override
    public String toString() {
        return super.toString() +
                " eatingDiscipline=" + eatingDiscipline +
                ", receivedCalories=" + receivedCalories;
    }
}