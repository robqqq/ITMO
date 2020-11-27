public abstract class Item {
    private final String name;
    private Human owner;
    private Location location;

    public Item(String name, Human owner){
        this.name = name;
        this.owner = owner;
    }

    public void setLocation(Location location){
        owner = null;
        this.location = location;
    }

    @Override
    public String toString() {
        return name;
    }
}
