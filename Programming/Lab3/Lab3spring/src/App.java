import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public final class App {
    public static void main(String[] args){
        ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        Eater ponchik = ctx.getBean("ponchik", Eater.class);
        Storage[] storages = ctx.getBean(Storage[].class);
        storages[0].addPack(ctx.getBean("pack1", Package.class), 0);
        storages[0].addPack(ctx.getBean("pack2", Package.class), 1);
        storages[1].addPack(ctx.getBean("pack3", Package.class), 0);
        storages[2].addPack(ctx.getBean("pack4", Package.class), 0);
        storages[2].addPack(ctx.getBean("pack5", Package.class), 1);
        storages[0].packs[0].setFood(ctx.getBean("food1", Food.class), 110);
        storages[0].packs[1].setFood(ctx.getBean("food2", Food.class), 140);
        storages[1].packs[0].setFood(ctx.getBean("food3", Food.class), 60);
        storages[2].packs[0].setFood(ctx.getBean("food4", Food.class), 80);
        storages[2].packs[1].setFood(ctx.getBean("food5", Food.class), 50);
        ponchik.eatAllFromStorage(storages[0]);
        ponchik.changeLocation(Location.FOODROOM);
        for(Storage currentStorage: storages ){
            ponchik.eatAllFromStorage(currentStorage);
        }
        Thinker neznaika = ctx.getBean(Thinker.class);
        Eater typicalEater = ctx.getBean("typicalEater", Eater.class);
        typicalEater.setSameReceivedCalories(ponchik);
        int foodStockForTypicalEater = neznaika.calculationOfFoodStock(typicalEater);
        int foodStockForPonchik = neznaika.calculationOfFoodStock(ponchik);
        System.out.println("Discrepancies between calculations is " + (foodStockForTypicalEater - foodStockForPonchik));
    }
}
