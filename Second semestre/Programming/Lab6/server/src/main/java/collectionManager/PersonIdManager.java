package collectionManager;

import java.util.Set;
import java.util.TreeSet;

public enum PersonIdManager implements IdManager{
    INSTANCE;

    private Set<Integer> usedIdentifiers;

    private PersonIdManager(){
        usedIdentifiers = new TreeSet<>();
    }

    @Override
    public boolean idIsFree(int id) {
        return !usedIdentifiers.contains(id);
    }

    @Override
    public int getFreeId() {
        int prevId = 0;
        for (int id: usedIdentifiers) {
            if (id - prevId != 1) {
                break;
            }
            prevId = id;
        }
        return prevId + 1;
    }

    @Override
    public void addId(int id) {
        usedIdentifiers.add(id);
    }

    @Override
    public void removeId(int id) {
        usedIdentifiers.remove(id);
    }

    @Override
    public void clearIdentifiers() {
        usedIdentifiers.clear();
    }
}
