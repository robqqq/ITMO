 class Story {
    public static void main(String[] args) {
        int foodStock = Neznaika.neznaika().calculateFoodStock(10, 48, Ponchik.ponchik());
        Neznaika.neznaika().makeSureAboutSafe(foodStock, Ponchik.ponchik());
        Neznaika.neznaika().sleep();
        Narrator.newChapter(24, "Ponchik's adventures");
        Narrator.changeMainPerson(Ponchik.ponchik());
        Ponchik.ponchik().makeSureThatNearby(Neznaika.neznaika());
        Ponchik.ponchik().goOut();
        Ponchik.ponchik().goTo(Location.SPACESHIP);
    }
}