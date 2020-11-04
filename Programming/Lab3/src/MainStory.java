public class MainStory {
    public static void main(String[] args){
        Eater ponchik = new Eater("Ponchik",false);
        Storage[] storageList = new Storage[3];
        storageList[0] = new Storage("Thermostat",2);
        storageList[1] = new Storage("Fridge",1);
        storageList[2] = new Storage("Self-regulating space oven",2);
        storageList[0].addPack(new Package("Plastic tube"), 0);
        storageList[0].addPack(new Package("PVC tube"), 1);
        storageList[1].addPack(new Package("Tube"), 0);
        storageList[2].addPack(new Package("Bag"), 0);
        storageList[2].addPack(new Package("Plastic bag"), 1);
        storageList[0].packList[0].setFood(new Food(2000),110);
        storageList[0].packList[1].setFood(new Food(1500),140);
        storageList[1].packList[0].setFood(new Food(3500),60);
        storageList[2].packList[0].setFood(new Food(2500),80);
        storageList[2].packList[1].setFood(new Food(3000),50);
        ponchik.eatAllFromStorage(storageList[1]);
        ponchik.changeLocation(Location.FOODROOM);
        for(Storage curentStorage: storageList ){
            ponchik.eatAllFromStorage(curentStorage);
        }
        Thinker neznaika = new Thinker("Neznaika");
        Eater typicalEater = new Eater("Typical eater");
        typicalEater.setSameReceivedCalories(ponchik);
        int foodStockForTypicalEater = neznaika.calculationOfFoodStock(typicalEater);
        int foodStockForPonchik = neznaika.calculationOfFoodStock(ponchik);
        System.out.println("Discrepancies between calculations is " + (foodStockForTypicalEater - foodStockForPonchik));
    }
}
