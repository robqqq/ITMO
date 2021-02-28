package commands;

import cleint.ClientManagerInterface;

/**
 * Интерфейс менеджера комманд
 */
public interface CommandManagerInterface {

    void usedScriptAdd(String scriptName);

    boolean scriptIsUsed(String scriptName);

    void executeCommand(String[] input, ClientManagerInterface clientManager);

    void clearUsedScripts();
}
