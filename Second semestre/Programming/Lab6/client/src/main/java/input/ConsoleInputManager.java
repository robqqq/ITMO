package input;

import inputManager.CommandReader;
import inputManager.InputManager;
import inputManager.PersonReader;
import messages.Messenger;
import output.OutputManager;
import person.RawPerson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleInputManager implements InputManager {
    private BufferedReader reader;
    private CommandReader commandReader;
    private PersonReader personReader;

    public ConsoleInputManager(Messenger messenger, OutputManager outputManager) {
        reader = new BufferedReader(new InputStreamReader(System.in));
        commandReader = new ConsoleCommandReader(reader);
        personReader = new ConsolePersonReader(reader, messenger, outputManager);
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
}