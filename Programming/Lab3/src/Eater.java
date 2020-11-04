public class Eater extends Human{
    public Eater(String name){
        super(name);
        eatingDiscipline = true;
    }

    public Eater(String name, boolean discipline){
        super(name);
        eatingDiscipline = discipline;
    }

    private boolean eatingDiscipline;
    private int receivedCalories = 0;

    public boolean getEatingDiscipline(){
        return this.eatingDiscipline;
    }

    public void eat(Package pack){
        if(getLocation() == Location.FOODROOM){
            receivedCalories += pack.getWeight() * pack.getFood().getCalories();
            System.out.println(getName() + " eat food from " + pack.getName());
        } else {
            System.out.println(getName() + " can eat only in " + Location.FOODROOM);
        }

    }

    public void eatAllFromStorage(Storage storage){
        if(getLocation() == Location.FOODROOM){
            System.out.println(getName() + " open " + storage.getName());
            for(int i = 0; i < storage.getAmountPacks(); i++){
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

    public void setSameReceivedCalories(Eater eater){
        this.receivedCalories = eater.getReceivedCalories();
    }
}
