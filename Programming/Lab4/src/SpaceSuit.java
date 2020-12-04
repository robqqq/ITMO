import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class SpaceSuit extends Item implements Containing{
    ArrayList<Item> itemList;

    public SpaceSuit(Location location, boolean hasParachute) {
        super("spacesuit", location);
        itemList = new ArrayList<Item>();
        if (hasParachute){
            this.addItems(new Parachute());
        }
    }

    public boolean hasParachute(){
        boolean aBool = false;
        for (Item item: itemList){
            if (item.getClass().equals(Parachute.class)){
                aBool = true;
            }
        }
        return aBool;
    }

    public void useParachute(){
        App.Narrator.println("with a parachute opening.");
        itemList.remove(getNumClass(Parachute.class));
    }

    @Override
    public boolean hasItemClass(Class aClass){
        boolean aBool = false;
        for (Item item: itemList){
            if (item.getClass().equals(aClass)){
                aBool = true;
            }
        }
        return aBool;
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

    class Parachute extends Item{

        public Parachute() {
            super("parachute", Location.UNKNOWN);
        }
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
    public boolean contains(Item item) {
        return itemList.contains(item);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        SpaceSuit spaceSuit = (SpaceSuit) o;
        return Objects.equals(itemList, spaceSuit.itemList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), itemList);
    }
}
