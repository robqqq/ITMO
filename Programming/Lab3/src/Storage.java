public class Storage {
    public Storage(String name, int amountPacks){
        this.name = name;
        this.amountPacks = amountPacks;
        this.packList = new Package[amountPacks];

    }
    private String name = new String();
    private int amountPacks;
    Package[] packList;

    public String getName() {
        return name;
    }

    public Package getPack(int number){
        return packList[number];
    }

    public void addPack(Package pack, int number) {
        packList[number] = pack;
    }

    public int getAmountPacks(){
        return this.amountPacks;
    }

    public void outOfStorage(int numberOfPack){
        packList[numberOfPack] = null;
    }
}
