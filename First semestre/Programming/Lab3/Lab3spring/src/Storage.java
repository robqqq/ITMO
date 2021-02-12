import java.util.Arrays;
import java.util.Objects;

public final class Storage{
    private String name;
    private int amountPacks;
    IPackage[] packs;

    public Storage(String name, int amountPacks) {
        this.name = name;
        this.amountPacks = amountPacks;
        this.packs = new IPackage[amountPacks];

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public IPackage getPack(int numberOfPack) {
        return packs[numberOfPack];
    }

    public void addPack(Package pack, int numberOfPack) {
        packs[numberOfPack] = pack;
    }

    public int getAmountPacks() {
        return this.amountPacks;
    }

    public void outOfStorage(int numberOfPack) {
        packs[numberOfPack] = null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Storage storage = (Storage) o;
        return amountPacks == storage.amountPacks &&
                Objects.equals(name, storage.name) &&
                Arrays.equals(packs, storage.packs);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(name, amountPacks);
        result = 31 * result + Arrays.hashCode(packs);
        return result;
    }

    @Override
    public String toString() {
        return "Storage: " +
                "name='" + name + '\'' +
                ", amountPacks=" + amountPacks;
    }
}