import java.util.Arrays;
import java.util.Objects;

public class Storage implements Nameable{
    private String name;
    private int amountPacks;
    Package[] packList;

    public Storage(String name, int amountPacks) {
        this.name = name;
        this.amountPacks = amountPacks;
        this.packList = new Package[amountPacks];

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Package getPack(int number) {
        return packList[number];
    }

    public void addPack(Package pack, int number) {
        packList[number] = pack;
    }

    public int getAmountPacks() {
        return this.amountPacks;
    }

    public void outOfStorage(int numberOfPack) {
        packList[numberOfPack] = null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Storage storage = (Storage) o;
        return amountPacks == storage.amountPacks &&
                Objects.equals(name, storage.name) &&
                Arrays.equals(packList, storage.packList);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(name, amountPacks);
        result = 31 * result + Arrays.hashCode(packList);
        return result;
    }

    @Override
    public String toString() {
        return "Storage: " +
                "name='" + name + '\'' +
                ", amountPacks=" + amountPacks;
    }
}