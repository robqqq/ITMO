package collectionManager;

public interface IdManager {

    /**
     * Метод, который проверяет, свободен ли id
     * @param id значение id
     * @return true, если свободен, false, если занят
     */
    boolean idIsFree(int id);

    /**
     * @return первый свободный идентификатор
     */
    int getFreeId();

    /**
     * Метод, который добавляет идентификатор в использованные
     * @param id идентификатор
     */
    void addId(int id);

    /**
     * Метод, который удаляет идентификатор из использованных
     * @param id идентификатор
     */
    void removeId(int id);

    /**
     * Метод, который удаляет все использованные идентификаторы
     */
    void clearIdentifiers();
}
