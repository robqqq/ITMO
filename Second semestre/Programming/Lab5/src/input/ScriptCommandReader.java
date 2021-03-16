package input;

import exceptions.ScriptException;
import messages.Messenger;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Реализация интерфейса CommandReader для считывания из скрипта
 */
public class ScriptCommandReader implements CommandReader{
    private BufferedReader reader;
    private Messenger messenger;

    /**
     * @param reader объект BufferedReader
     * @param messenger мессенджер
     */
    public ScriptCommandReader(BufferedReader reader, Messenger messenger){
        this.reader = reader;
        this.messenger = messenger;
    }

    @Override
    public String readCommand(){
        try {
            String input = reader.readLine();
            return input.trim().toLowerCase();
        } catch (IOException e){
            throw new ScriptException(messenger.getExceptionMsg("script"));
        }
    }
}
