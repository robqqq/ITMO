import java.util.Objects;

public class FoodPackage extends Item{
    private boolean full = true;
    private boolean tasty;

    public FoodPackage(String name, Location location, boolean tasty){
        super(name, location);
        this.tasty = tasty;
    }

    public void eat(Human human){
        if (full = true){
            App.Narrator.println(human + " destroyed the contents of the " + this);
            full = false;
            setLocation(human.getLocation());
        } else {
            App.Narrator.println(human + "can't eat food from " + this + ", because it's empty.");
        }
    };

    public boolean getTasty(){
        return tasty;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        FoodPackage that = (FoodPackage) o;
        return full == that.full &&
                tasty == that.tasty;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), full, tasty);
    }
}
