public interface Containing {

    void addItems(Item... items);

    void removeItems(Item... items);

    Location getLocation();

    boolean contains(Item item);

    boolean hasItemClass(Class aClass);

    int getNumClass(Class aClass);
}
