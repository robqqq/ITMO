package commands;

import java.io.BufferedReader;

/**
 * Интерфейс менеджера комманд
 */
public interface CommandManagerInterface {
    void start();
    void readCommands(BufferedReader reader, String startSymbol);
    void usedScriptAdd(String scriptName);
    boolean scriptIsUsed(String scriptName);
    Double askCoordinatesX();
    long askCoordinatesY();
    String askEyeColor();
    String askHairColor();
    float askLocationX();
    long askLocationY();
    String askLocationName();

}
