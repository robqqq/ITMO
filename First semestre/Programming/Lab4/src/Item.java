import java.util.Objects;

public abstract class Item {
    private final String name;
    private Human owner;
    private Location location;
    private Containing container;

    protected Item(String name, Location location){
        this.name = name;
        this.location = location;
    }

    public void setLocation(Location location){
        owner = null;
        container = null;
        this.location = location;
    }

    public void setContainer(Containing container) {
        owner = null;
        location = null;
        this.container = container;
    }

    public void setOwner(Human owner){
        container = null;
        location = null;
        this.owner = owner;
    }

    public Location getLocation() {
        return location;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Objects.equals(name, item.name) &&
                Objects.equals(owner, item.owner) &&
                location == item.location &&
                Objects.equals(container, item.container);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, owner, location, container);
    }
}
