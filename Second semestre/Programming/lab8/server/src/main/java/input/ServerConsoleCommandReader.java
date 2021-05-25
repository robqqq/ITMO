package input;

import inputManager.CommandReader;

import java.io.BufferedReader;
import java.io.IOException;

public class ServerConsoleCommandReader implements CommandReader {
    private final BufferedReader reader;

    public ServerConsoleCommandReader(BufferedReader reader){
        this.reader = reader;
    }

    @Override
    public String readCommand() throws IOException {
        String input = reader.readLine();
        if (input == null) throw new IOException();
        return input.trim().toLowerCase().split("\\s+")[0];
    }
}
