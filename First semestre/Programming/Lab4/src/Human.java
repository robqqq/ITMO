import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public abstract class Human implements Containing {
    private Location location;
    private Status status;
    private final String name;
    private String lastEvents;
    private List<Item> itemList;

    protected Human(String name, Location location) {
        this.name = name;
        this.location = location;
        this.status = Status.UNKNOWN;
        lastEvents = "";
        itemList = new ArrayList<Item>();
    }

    public void tired(){
        status = Status.TIRED;
    }

    public void sleep(){
        if (status == Status.TIRED){
            status = Status.SLEEPING;
            App.Narrator.println(this + "'s eyes closed by themselves and he fell asleep.");
        } else {
            App.Narrator.println(this + " can sleep only if he is tired.");
        }
    }

    public void wakeUp(){
        if (status == Status.SLEEPING){
            status = Status.FULL_OF_ENERGY;
            App.Narrator.println(this + " waked up.");
        } else {
            App.Narrator.println(this + " can wake up only if he is sleeping.");
        }
    }

    public Status getStatus(){
        return this.status;
    }

    public void writeToLastEvents(String s){
        lastEvents += s;
    }

    public void writelnToLastEvents(String s){
        lastEvents += s + '\n';
    }

    public String getLastEvents() {
        return lastEvents;
    }

    public Location getLocation(){
        return location;
    }

    public void scared(){
        status = Status.SCARED;
        App.Narrator.print(this + " was so scared");
    }

    public void calmed(){
        status = Status.CALM;
        App.Narrator.print(this + " was calmed");
    }

    public void addItems(Item... items){
        itemList.addAll(Arrays.asList(items));
    }

    public void lostItem(){
        App.Narrator.println(this + " lost " + itemList.get(0) + ".");
        itemList.get(0).setLocation(location);
        itemList.remove(0);
    }

    public void goOut() throws InventoryException {
        if (this.location == Location.OUTSIDE){
            throw new LocationException();
        }
        if (!hasItemClass(SpaceSuit.class)) {
            throw new InventoryException(this + " doesn't have spacesuit.");
        }
        App.Narrator.println(this + " went out from " + location + ".");
        location = Location.OUTSIDE;
    }

    public void goTo(Location location){
        if(this.location != Location.OUTSIDE){
            throw new LocationException();
        }
        App.Narrator.println(this + " went to the " + location + ", but the sun was so hot that the " +
                this + " could not stand it.");
        run(location);

    }

    public void changeLocation(Location location){
        if (this.location.equals(Location.OUTSIDE) && location.equals(Location.SPACESHIP)){
            App.Narrator.println(this + " pressed the button in the tail section of "
                    + location + " The airlock door opened hospitably. ");
            int num = getNumClass(SpaceSuit.class);
            itemList.get(num).setLocation(location);
            itemList.remove(num);
        }
        App.Narrator.println(this + " went into the " + location + ".");
        this.location = location;
        if (location == Location.ICE_TUNNEL){
            App.Narrator.println("The " + this + " slipped and, rolling on his stomach on an inclined plane, " +
                    "flew into the " + Location.SUBLUNAR_WELL + ".");
            this.location = Location.SUBLUNAR_WELL;
            App.Narrator.print("After a while, he noticed that he had jumped out of the well" +
                    " and was flying at a terrible height ");
            try {
                if (!hasParachute()) {
                    throw new InventoryException(this + " doesn't have parachute.");
                }
            } catch(InventoryException e){
                e.printStackTrace();
            }
            useParachute();
            App.Narrator.print("A strong wind carried him away. Gradually descending, ");
            try {
                fly(Location.LOS_SVINOS_AIRSPACE);
                fly(Location.LOS_KABANOS_AIRSPACE);
                App.Narrator.print("Having already dropped significantly, ");
                fly(Location.LOS_PAGANOS_AIRSPACE);
                App.Narrator.print("But wind change his direction, and ");
                fly(Location.SEA_AIRSPACE);
            } catch (LocationException e) {
                e.printStackTrace();
            }
            App.Narrator.println(this + " saw that he was going to have a swim.");
            if (this.getClass() == Ponchik.class){
                App.Narrator.println(this + " was not afraid of drowning, because he was fat, " +
                        "and fat little men, as you know, do not drown in water.");
                App.Narrator.println("The only thing he was afraid of was being bitten by a shark.");
            } else {
                App.Narrator.println(this + " was afraid of drowning");
            }
        }
    }

    public boolean hasParachute(){
        if (hasItemClass(SpaceSuit.class)){
            return ((SpaceSuit) itemList.get(getNumClass(SpaceSuit.class))).hasParachute();
        } else {
            return false;
        }
    }

    public void useParachute(){
        ((SpaceSuit) itemList.get(getNumClass(SpaceSuit.class))).useParachute();
    }

    public void run(Location location){
        if (getStatus() == Status.SCARED) {
            String item = itemList.get(0).toString();
            App.Narrator.print(this + " ran to the " + location + ", but because the fast running, ");
            lostItem();
            App.Narrator.println(this + " didn't bother to pick it up, because running without " + item +
                    " was much easier.");
            App.Narrator.println("It took him less than twenty minutes to reach the " + location + ".");
        } else {
            App.Narrator.println(this + " ran to the " + location + ".");
        }
        changeLocation(location);
    }

    public void notPleased(){
        status = Status.NOT_PLEASED;
        App.Narrator.println(this + " was disturbed by an unpleasant feeling.");
    }

    public void pleased(){
        if (status == Status.NOT_PLEASED) {
            status = Status.PLEASED;
            App.Narrator.println(this + " began to had a pleasant feeling.");
        }
    }

    public void take(Containing container, Item... items){
        if (!container.getLocation().equals(getLocation())){
            throw new LocationException();
        }
        for (Item item: items){
            if (container.contains(item)) {
                container.removeItems(item);
                addItems(item);
                item.setOwner(this);
                App.Narrator.println(this + " took the " + item + " out of the " + container + ".");
            } else {
                App.Narrator.println(this + " couldn't take the " + item + " out of the " +
                        container + ", because this item is not in the container.");
            }
        }
    }

    public void take(Item... items){
        for (Item item: items){
            if (!item.getLocation().equals(location)) {
                throw new LocationException();
            }
            addItems(item);
            item.setOwner(this);
            App.Narrator.println(this + " took the " + item + ".");
        }
    }

    public boolean has(Item item){
        return itemList.contains(item);
    }

    public boolean hasItemClass(Class aClass){
        boolean aBool = false;
        for (Item item: itemList){
            if (item.getClass().equals(aClass)){
                aBool = true;
            }
        }
        return aBool;
    }

    public void removeItems(Item... items){
        itemList.removeAll(Arrays.asList(items));
    }

    public int getNumClass(Class aClass){
        int num = -1;
        for (int i = 0; i < itemList.size(); i++){
            if (itemList.get(i).getClass().equals(aClass)) {
                num = i;
            }
        }
        return num;
    }

    public void fly(Location location){
        if (!((this.location.airspace() || this.location == Location.SUBLUNAR_WELL) && location.airspace())){
            throw new LocationException();
        }
        App.Narrator.println(this + " flew " + location + ".");
        this.location = location;
    }

    @Override
    public boolean contains(Item item) {
        return itemList.contains(item);
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Human human = (Human) o;
        return location == human.location &&
                status == human.status &&
                Objects.equals(name, human.name) &&
                Objects.equals(lastEvents, human.lastEvents) &&
                Objects.equals(itemList, human.itemList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(location, status, name, lastEvents, itemList);
    }
}
