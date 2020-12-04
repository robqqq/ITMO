import java.util.Objects;

public class Ponchik extends Human {
    private static Ponchik ponchik;
    private boolean eatingDiscipline;
    private double foodStock;

    private Ponchik() {
        super("Ponchik", Location.MOON_CAVE);
        eatingDiscipline = false;
        foodStock = 0;
        writelnToLastEvents(this + " went with the " + Neznaika.get() +
                " to the moon cave and lost one of his space boots.");
        writelnToLastEvents(Neznaika.get() + " at that moment just fell through the lunar shell.");
    }

    public static Ponchik get() {
        if (ponchik == null) {
            ponchik = new Ponchik();
        }
        return ponchik;
    }

    public void makeSureThatNearby(Human human) {
        App.Narrator.print(this + " calling out to " + human + " several times and ");
        if (this.getLocation() == human.getLocation()) {
            App.Narrator.println("making sure that he is nearby.");
            calmed();
            App.Narrator.println(" and go to " + human + ".");
        } else {
            App.Narrator.println("making sure that he is not nearby.");
            scared();
            App.Narrator.println(" and, instead of going in search of " + human + ", " +
                    this + " decided to return to the spaceship as soon as possible.");
        }
    }

    public void checkSafety() {
        if (getLocation() == Location.SPACESHIP) {
            App.Narrator.println(this + " was safe here. Nothing was threatening him now.");
        } else {
            App.Narrator.println(this + " wasn't safe here.");
        }
        if (Neznaika.get().getLocation() != getLocation()) {
            notPleased();
            App.Narrator.println("Because he left the " + Neznaika.get() + " and didn't help him.");
        }
    }

    public void decideToEat() {
        if (getStatus() == Status.NOT_PLEASED) {
            App.Narrator.println("Knowing from experience that any unpleasant  feeling can be replaced by some opposite," +
                    " that is, a pleasant feeling.");
            App.Narrator.println(this + " decided to go to the " + Location.FOOD_COMPARTMENT +
                    " and a few, as he used to say, refuel there.");
        }
    }

    public void eat(FoodPackage... packs) throws InventoryException{
        for (FoodPackage pack : packs) {
            if (!has(pack)) {
                throw new InventoryException(this + " don't have this pack");
            }
            pack.eat(this);
            removeItems(pack);
            if (eatingDiscipline) {
                foodStock += 96;
            } else {
                foodStock += 0.9;
            }
        }
        pleased();
    }

    public void getRealFoodStock(double calculatedFoodStock){
        if (!eatingDiscipline){
            App.Narrator.println(this + ", which, as you know, was not a fool to eat, showed by a clear example");
            App.Narrator.println("how great the discrepancy between theoretical calculations and practical life can be.");
            if (calculatedFoodStock > 360){
                App.Narrator.println("It was found that the " + this + " was provided with a supply of food for more than " +
                        (int) calculatedFoodStock / 365 + " year,");
            } else if (calculatedFoodStock > 30){
                App.Narrator.println("It was found that the " + this + " was provided with a supply of food for more than " +
                        (int) calculatedFoodStock / 30 + " months,");
            } else {
                App.Narrator.println("It was found that the " + this + " was provided with a supply of food for more than " +
                        (int) calculatedFoodStock + " days,");
            }
            App.Narrator.println("since all calculations based on an ordinary eater," +
                    " without taking into account his individual, that is, personal, eating properties.");
            App.Narrator.println("The trouble was that the " + this +
                    "'s personal eating qualities lay in its extreme eating indiscipline.");
            App.Narrator.println("Simply put, he could eat anything, anywhere, anytime, and in any quantity.");
            App.Narrator.print("What was supposed to last for a ");
            if (calculatedFoodStock > 30){
                int foodStockMonths = (int) calculatedFoodStock / 30;
                if (foodStockMonths > 12){
                    App.Narrator.print( foodStockMonths / 12 + " year and " + foodStockMonths % 12 + " months ");
                } else {
                    App.Narrator.print(foodStockMonths + " months " + calculatedFoodStock % 30 + " days ");
                }
            } else {
                App.Narrator.print(calculatedFoodStock + " days ");
            }
            App.Narrator.println("was actually only enough for the " + this + " for " + foodStock + " days.");
        } else {
            App.Narrator.println("Theoretical calculations coincide with practical life. Enough food for " + foodStock + " days.");
        }
    }

    public void thinkAbout(Human human){
        if (!human.getLocation().equals(getLocation())){
            App.Narrator.println("The " + this + " remembered the "+ human + ": \"Probably, " + human +
                    " have returned to the " + getLocation() + " if he hadn't found food somewhere");
            App.Narrator.println("And since he didn't come back, it means that the food was found somewhere, " +
                    "and if that's the case,");
            App.Narrator.println("then there's no point in me sitting in the " + getLocation() +
                    " and I need to go in search of\".");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Ponchik ponchik = (Ponchik) o;
        return eatingDiscipline == ponchik.eatingDiscipline &&
                Double.compare(ponchik.foodStock, foodStock) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), eatingDiscipline, foodStock);
    }
}
