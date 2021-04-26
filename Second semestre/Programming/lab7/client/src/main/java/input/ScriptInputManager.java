package input;

import auth.Auth;
import exceptions.ScriptException;
import inputManager.CommandReader;
import messages.Messenger;
import person.RawPerson;

import java.io.BufferedReader;
import java.io.IOException;

public class ScriptInputManager implements InputManager {
    private final BufferedReader reader;
    private final CommandReader commandReader;
    private final PersonReader personReader;

    public ScriptInputManager(BufferedReader reader, Messenger messenger){
        this.reader = reader;
        commandReader = new ScriptCommandReader(reader);
        personReader = new ScriptPersonReader(reader);
    }

    @Override
    public boolean ready() {
        try {
            return reader.ready();
        } catch (IOException e) {
            return false;
        }
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
    public Auth readAuth(){
       throw new ScriptException();
    }
}
