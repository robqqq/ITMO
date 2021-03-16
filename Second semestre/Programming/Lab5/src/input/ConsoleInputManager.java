package input;

import messages.Messenger;
import output.OutputManager;
import person.Person;

import java.time.LocalDateTime;
import java.util.Scanner;

/**
 * Реализация интерфейса InputManager для чтения из консоли
 */
public class ConsoleInputManager implements InputManager {
    private Scanner scanner;
    private CommandReader commandReader;
    private PersonReader personReader;

    /**
     * @param messenger мессенджер
     * @param outputManager менеджер вывода
     */
    public ConsoleInputManager(Messenger messenger, OutputManager outputManager) {
        scanner = new Scanner(System.in);
        commandReader = new ConsoleCommandReader(scanner);
        personReader = new ConsolePersonReader(scanner, messenger, outputManager);
    }

    @Override
    public boolean ready() {
        return scanner.hasNext();
    }

    @Override
    public String readCommand() {
        return commandReader.readCommand();
    }

    @Override
    public Person readPerson() {
        return personReader.readPerson();
    }

    @Override
    public Person readPerson(int id, LocalDateTime creationDate) {
        return personReader.readPerson(id, creationDate);
    }
}