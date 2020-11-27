public class Ponchik extends Human{
    private static Ponchik ponchik;

    private Ponchik() {
        super("Ponchik", Location.MOONCAVE);
        writelnToLastEvents(this + " went with the " + Neznaika.neznaika() +
                " to the moon cave and lost one of his space boots.");
        writelnToLastEvents(Neznaika.neznaika() + " at that moment just fell through the lunar shell.");
    }

    public static Ponchik ponchik(){
        if (ponchik == null){
            ponchik = new Ponchik();
            ponchik.addItems(new SpaceBoot(ponchik));
        }
        return ponchik;
    }

    public void makeSureThatNearby(Human human){
        Narrator.print(this + " calling out to " + human + " several times and ");
        if (this.getLocation() == human.getLocation()){
            Narrator.println("making sure that he is nearby.");
            calmed();
            Narrator.println(" and go to " + human + ".");
        } else {
            Narrator.println("making sure that he is not nearby.");
            scared();
            Narrator.println(" and, instead of going in search of " + human + ", " +
                    this + " decided to return to the spaceship as soon as possible.");
        }
    }
}
