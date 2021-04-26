package clientCommands;

import java.io.IOException;

public interface ClientCommandManager {

    /**
     * Метод, который добавляет скрипт в список использованных
     * @param scriptName имя скрипта
     */
    void usedScriptAdd(String scriptName);

    /**
     * @param scriptName имя скрипта
     * @return true, если использован, false, если нет
     */
    boolean scriptIsUsed(String scriptName);

    void executeCommand(String command, String arg) throws IOException;

    /**
     * Метод, который очищает список использованных скриптов
     */
    void clearUsedScripts();

    /**
     * Метод, который удаляет скрипт из списка использованных
     * @param scriptName имя скрипта
     */
    void usedScriptRemove(String scriptName);
}
