import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class Storage implements Containing{
    private final String name;
    private ArrayList<Item> itemList;
    private Location location;

    public Storage(String name, Location location){
        this.name = name;
        this.location = location;
        itemList = new ArrayList<Item>();
    }

    @Override
    public int getNumClass(Class aClass){
        int num = -1;
        for (int i = 0; i < itemList.size(); i++){
            if (itemList.get(i).getClass().equals(aClass)) {
                num = i;
            }
        }
        return num;
    }

    @Override
    public void addItems(Item... items) {
        itemList.addAll(Arrays.asList(items));
        for (Item item: items){
            item.setContainer(this);
        }
    }

    @Override
    public void removeItems(Item... items) {
        itemList.removeAll(Arrays.asList(items));
    }

    @Override
    public Location getLocation() {
        return location;
    }

    @Override
    public boolean contains(Item item) {
        return itemList.contains(item);
    }

    @Override
    public boolean hasItemClass(Class aClass) {
        boolean aBool = false;
        for (Item item: itemList){
            if (item.getClass().equals(aClass)){
                aBool = true;
            }
        }
        return aBool;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Storage storage = (Storage) o;
        return Objects.equals(name, storage.name) &&
                Objects.equals(itemList, storage.itemList) &&
                location == storage.location;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, itemList, location);
    }
}
