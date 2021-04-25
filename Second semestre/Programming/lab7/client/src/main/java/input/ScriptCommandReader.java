package input;

import exceptions.ScriptException;
import inputManager.CommandReader;

import java.io.BufferedReader;
import java.io.IOException;

public class ScriptCommandReader implements CommandReader {
    private final BufferedReader reader;

    public ScriptCommandReader(BufferedReader reader){
        this.reader = reader;
    }

    @Override
    public String readCommand(){
        try {
            String input = reader.readLine();
            return input.trim().toLowerCase();
        } catch (IOException e){
            throw new ScriptException();
        }
    }
}
