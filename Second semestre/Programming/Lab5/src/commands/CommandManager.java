package commands;

import exceptions.NoSuchCommandException;

/**
 * Интерфейс менеджера команд
 */
public interface CommandManager {

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

    /**
     * Метод, который запускает команду
     * @param command имя команды
     * @param arg аргумент команды
     * @throws NoSuchCommandException если такой команды не существует
     */
    void executeCommand(String command, String arg) throws NoSuchCommandException;

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
