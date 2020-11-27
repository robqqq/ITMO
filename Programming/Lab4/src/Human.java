import java.util.ArrayList;
import java.util.Arrays;

public abstract class Human {
    private Location location;
    private Status status;
    private final String name;
    private String lastEvents;
    private ArrayList<Item> itemList;

    public Human(String name, Location location) {
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
            Narrator.println(this + "'s eyes closed by themselves and he fell asleep.");
        } else {
            Narrator.println(this + " can sleep only if he is tired.");
        }
    }

    public void wakeUp(){
        if (status == Status.SLEEPING){
            status = Status.FULLOFENERGY;
            Narrator.println(this + " waked up.");
        } else {
            Narrator.println(this + " can wake up only if he is sleeping.");
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
        Narrator.print(this + " was so scared");
    }

    public void calmed(){
        status = Status.CALM;
        Narrator.print(this + " was calmed");
    }

    public void addItems(Item... items){
        itemList.addAll(Arrays.asList(items));
    }

    public void lostItem(){
        Narrator.println(this + " lost " + itemList.get(0) + ".");
        itemList.get(0).setLocation(location);
        itemList.remove(0);
    }

    public void goOut(){
        Narrator.println(this + " go out from " + location + ".");
        location = Location.OUTSIDE;
    }

    public void goTo(Location location){
        if(this.location == Location.OUTSIDE){
            Narrator.println(this + " went to the " + location + ", but the sun was so hot that the " +
                    this + " could not stand it.");
            run(location);
            changeLocation(location);
        }
    }

    public void changeLocation(Location location){
        if (this.location == Location.OUTSIDE && location == Location.SPACESHIP){
            Narrator.print("The airlock door opened hospitably. ");
        }
        Narrator.println(this + " went to the " + location + ".");
        this.location = location;
    }

    public void run(Location location){
        String item = itemList.get(0).toString();
        Narrator.print(this + " ran to the " + location + ", but because the fast running, ");
        lostItem();
        Narrator.println(this + " didn't bother to pick it up, because running without " + item +
                " was much easier.");
        Narrator.println("It took him less than twenty minutes to reach the " + location + ".");
    }

    @Override
    public String toString() {
        return name;
    }
}
