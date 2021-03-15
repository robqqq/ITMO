package input;

import messages.Messenger;
import output.OutputManager;
import person.Person;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;

public class ScriptInputManager implements InputManager{
    private BufferedReader reader;
    private Messenger messenger;
    private CommandReader commandReader;
    private PersonReader personReader;
    private OutputManager outputManager;

    public ScriptInputManager(BufferedReader reader, Messenger messenger, OutputManager outputManager){
        this.reader = reader;
        this.messenger = messenger;
        this.outputManager = outputManager;
        commandReader = new ScriptCommandReader(reader, messenger);
        personReader = new ScriptPersonReader(reader, messenger, outputManager);
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
    public String readCommand(){
        return commandReader.readCommand();
    }

    @Override
    public Person readPerson(){
        return personReader.readPerson();
    }

    @Override
    public Person readPerson(int id, LocalDateTime creationDate){
        return personReader.readPerson(id, creationDate);
    }
}
