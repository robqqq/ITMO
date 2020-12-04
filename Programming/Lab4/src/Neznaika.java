public class Neznaika extends Human{
    private static Neznaika neznaika;

    private Neznaika(){
        super("Neznaika", Location.UNKNOWN);
    }

    public static Neznaika get(){
        if(neznaika == null){
            neznaika = new Neznaika();
        }
        return neznaika;
    }

    public double calculateFoodStock(double oldFoodStock, int oldCountOfPeople, Human... humans){
        double foodStock;
        if (getStatus() != Status.SLEEPING && getStatus() != Status.TIRED) {
            App.Narrator.println(this + " calculate food stock to find out how many days there wood be enough food" +
                    " if instead of " + oldCountOfPeople + " people there was only " + humans.length + ".");
            foodStock = oldCountOfPeople / humans.length * oldFoodStock;
            App.Narrator.println(this + " calculated that food stock is " + (int) foodStock + " days.");
            int foodStock1 = (int) foodStock;
            if(foodStock >= 30){
                int foodStockMonths = foodStock1 / 30;
                foodStock1 %= 30;
                if(foodStockMonths >= 12){
                    int foodStockYears = foodStockMonths / 12;
                    foodStockMonths %= 12;
                    System.out.println("Knowing that in a year, in round numbers, there are 360 days, " +
                            "and in a month there are 30 days, ");
                    System.out.println(this + " calculated that food in a spaceship should be enough for " +
                            foodStockYears + " years and " + foodStockMonths + " months.");
                }else{
                    System.out.println("Knowing that in a months, in round numbers, there are 30 days, ");
                    System.out.println(this + " calculated that food in a spaceship should be enough for " +
                            foodStockMonths + " months and " + foodStock1 + " days.");
                }
            }
            App.Narrator.println("Calculations made " + this + " so tired.");
            tired();
        } else {
            App.Narrator.println(this + " can't calculate when he is tired or sleeping.");
            foodStock = 0;
        }
        return foodStock;
    }

    public void makeSureAboutSafe(double foodStock, Human... humans){
        if (foodStock / humans.length > 3) {
            App.Narrator.print(this + " made sure that the death from lack of food doesn't threaten ");
            for (int i = 0; i < humans.length - 1; i++) {
                System.out.print(humans[i] + ", ");
            }
        } else {
            App.Narrator.print(this + " find out that death from lack of food threatens ");
            for (int i = 0; i < humans.length - 1; i++) {
                System.out.print(humans[i] + ", ");
            }
        }
        App.Narrator.println(humans[humans.length - 1] + ".");
    }
}
