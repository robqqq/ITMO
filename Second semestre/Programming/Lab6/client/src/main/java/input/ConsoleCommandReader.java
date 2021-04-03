package input;

import inputManager.CommandReader;

import java.io.BufferedReader;
import java.io.IOException;

public class ConsoleCommandReader implements CommandReader {
    private BufferedReader reader;

    public ConsoleCommandReader(BufferedReader reader){
        this.reader = reader;
    }

    @Override
    public String readCommand() throws IOException {
        String input = reader.readLine();
        if (input == null) throw new IOException();
        return input.trim().toLowerCase();
    }
}
