package collectionManager;

public interface IdManager {

    boolean idIsFree(int id);

    int getFreeId();

    void addId(int id);

    void removeId(int id);

    void clearIdentifiers();
}
