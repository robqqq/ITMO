package input;

import auth.Auth;
import inputManager.CommandReader;
import messages.Messenger;
import output.OutputManager;
import person.RawPerson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleInputManager implements InputManager {
    private final BufferedReader reader;
    private final CommandReader commandReader;
    private final PersonReader personReader;
    private final AuthReader authReader;

    public ConsoleInputManager(Messenger messenger, OutputManager outputManager) {
        reader = new BufferedReader(new InputStreamReader(System.in));
        commandReader = new ConsoleCommandReader(reader);
        personReader = new ConsolePersonReader(reader, messenger, outputManager);
        authReader = new ConsoleAuthReader(reader, messenger, outputManager, "salt");
    }

    @Override
    public boolean ready() throws IOException {
        return reader.ready();
    }

    @Override
    public String readCommand() throws IOException {
        return commandReader.readCommand();
    }

    @Override
    public RawPerson readPerson() throws IOException {
        return personReader.readPerson();
    }

    @Override
    public Auth readAuth() throws IOException {
        return authReader.readAuth();
    }
}