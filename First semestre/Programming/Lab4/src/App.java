import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

 class App {
    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        double foodStock = Neznaika.get().calculateFoodStock(10, 48, Ponchik.get());
        Neznaika.get().makeSureAboutSafe(foodStock, Ponchik.get());
        Neznaika.get().sleep();

        class Chapter{
            String name;
            int number;

            Chapter(int number, String name){
                this.number = number;
                this.name = name;
            }

            void newChapter() {
                System.out.println();
                System.out.println("    Chapter " + number);
                System.out.println("    " + name);
                System.out.println();
                number += 1;
            }
        }

        Chapter chapter = new Chapter(24, "Ponchik's adventures");
        chapter.newChapter();
        Narrator.changeMainPerson(Ponchik.get());
        SpaceBoot spaceBoot = ctx.getBean("spaceBoot", SpaceBoot.class);
        SpaceSuit spacesuit = ctx.getBean(SpaceSuit.class);
        Ponchik.get().take(spaceBoot, spacesuit);
        Ponchik.get().makeSureThatNearby(Neznaika.get());

        try {
            Ponchik.get().goOut();
        } catch (InventoryException e) {
            e.printStackTrace();
        }

        Ponchik.get().goTo(Location.SPACESHIP);
        Ponchik.get().checkSafety();
        Ponchik.get().decideToEat();
        Ponchik.get().changeLocation(Location.FOOD_COMPARTMENT);
        FoodPackage plasticTube = ctx.getBean("plasticTube", FoodPackage.class);
        FoodPackage pvcTube = ctx.getBean("pvcTube", FoodPackage.class);
        FoodPackage tube = ctx.getBean("tube", FoodPackage.class);
        FoodPackage bag = ctx.getBean("bag", FoodPackage.class);
        FoodPackage plasticBag = ctx.getBean("plasticBag", FoodPackage.class);
        Storage thermostat = ctx.getBean("thermostat", Storage.class);
        Storage fridge = ctx.getBean("fridge", Storage.class);
        Storage oven = ctx.getBean("oven", Storage.class);
        thermostat.addItems(plasticTube, pvcTube);
        fridge.addItems(tube);
        oven.addItems(bag, plasticBag);
        Ponchik.get().take(thermostat, plasticTube, pvcTube);
        Ponchik.get().take(fridge, tube);
        Ponchik.get().take(oven, bag, plasticBag);

        try {
            Ponchik.get().eat(plasticTube, pvcTube, tube, bag, plasticBag);
        } catch (InventoryException e) {
            e.printStackTrace();
        }

        Ponchik.get().getRealFoodStock(foodStock);
        Ponchik.get().changeLocation(Location.TAIL_SECTION);
        FoodPackage seeds = ctx.getBean("seeds", FoodPackage.class);
        Ponchik.get().take(seeds);

        try {
            Ponchik.get().eat(seeds);
        } catch (InventoryException e) {
            e.printStackTrace();
        }

        Ponchik.get().changeLocation(Location.SPACESHIP);
        Ponchik.get().thinkAbout(Neznaika.get());
        SpaceBoot newSpaceBoots = ctx.getBean("newSpaceBoots", SpaceBoot.class);
        Ponchik.get().take((Item) spacesuit, newSpaceBoots);

        try {
            Ponchik.get().goOut();
        } catch (InventoryException e) {
            e.printStackTrace();
        }

        Ponchik.get().run(Location.MOON_CAVE);
        Ponchik.get().changeLocation(Location.ICICLE_GROTTO);
        Ponchik.get().changeLocation(Location.ICE_TUNNEL);
    }

    static class Narrator{
        private static Human mainPerson = Neznaika.get();

        static void changeMainPerson(Human human){
            mainPerson = human;
            System.out.println("What about the " + human + "? We forgotten about him.");
            System.out.println("This is not good, perhaps, since many readers may be interested in his fate.");
            System.out.print("We broke up with the " + human + " when " + human.getLastEvents());
        }

        static void println(String s){
            System.out.println(s);
            mainPerson.writelnToLastEvents(s);
        }

        static void print(String s){
            System.out.print(s);
            mainPerson.writeToLastEvents(s);
        }
    }
}