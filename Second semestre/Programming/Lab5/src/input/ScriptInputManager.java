package input;

import messages.Messenger;
import output.OutputManager;
import person.Person;

import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalDateTime;

/**
 * Реализация интерфейса InputManager для чтения из скрипта
 */
public class ScriptInputManager implements InputManager{
    private BufferedReader reader;
    private CommandReader commandReader;
    private PersonReader personReader;

    public ScriptInputManager(BufferedReader reader, Messenger messenger){
        this.reader = reader;
        commandReader = new ScriptCommandReader(reader, messenger);
        personReader = new ScriptPersonReader(reader, messenger);
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
