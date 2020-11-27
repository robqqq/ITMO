public class Neznaika extends Human{
    private static Neznaika neznaika;

    private Neznaika(){
        super("Neznaika", Location.UNKNOWN);
    }

    public static Neznaika neznaika(){
        if(neznaika == null){
            neznaika = new Neznaika();
        }
        return neznaika;
    }

    public int calculateFoodStock(int oldFoodStock, int oldCountOfPeople, Human... humans){
        int foodStock;
        if (getStatus() != Status.SLEEPING && getStatus() != Status.TIRED) {
            Narrator.println(this + " calculate food stock to find out how many days there wood be enough food" +
                    " if instead of " + oldCountOfPeople + " people there was only " + humans.length + ".");
            foodStock = oldCountOfPeople / humans.length * oldFoodStock;
            Narrator.println(this + " calculated that food stock is " + foodStock + " days.");
            int foodStock1 = foodStock;
            if(foodStock >= 30){
                int foodStockMonths = foodStock / 30;
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
            Narrator.println("Calculations made " + this + " so tired.");
            tired();
        } else {
            Narrator.println(this + " can't calculate when he is tired or sleeping.");
            foodStock = 0;
        }
        return foodStock;
    }

    public void makeSureAboutSafe(int foodStock, Human... humans){
        if (foodStock / humans.length > 3) {
            Narrator.print(this + " maked sure that the death from lack of food doesn't threaten ");
            for (int i = 0; i < humans.length - 1; i++) {
                System.out.print(humans[i] + ", ");
            }
            Narrator.println(humans[humans.length - 1] + ".");
        } else {
            Narrator.print(this + " find out that death from lack of food threatens ");
            for (int i = 0; i < humans.length - 1; i++) {
                System.out.print(humans[i] + ", ");
            }
            Narrator.println(humans[humans.length - 1] + ".");
        }
    }
}
