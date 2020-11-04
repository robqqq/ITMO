public class Thinker extends Human{
    public Thinker(String name){
        super(name);

    }
    public int calculationOfFoodStock(Eater eater){
        if(eater.getEatingDiscipline()){
            int foodStock = eater.getReceivedCalories() / 2700;
            System.out.println(getName() + " calculate, that Food stock for " + eater.getName() + " is " + foodStock + " days ");
            return foodStock;
        } else {
            System.out.println(getName() + " can't calculate food stock, because " + eater.getName() + " doesn't have eating discipline, he can eat whatever, whenever, however much he wants");
            return 0;
        }
    }
}
