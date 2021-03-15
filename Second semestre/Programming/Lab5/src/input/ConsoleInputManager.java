package input;

import messages.Messenger;
import output.OutputManager;
import person.Person;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Scanner;

public class ConsoleInputManager implements InputManager {
    private Scanner scanner;
    private Messenger messenger;
    private CommandReader commandReader;
    private PersonReader personReader;
    private OutputManager outputManager;

    public ConsoleInputManager(Messenger messenger, OutputManager outputManager){
        this.outputManager = outputManager;
        this.messenger = messenger;
        scanner = new Scanner(System.in);
        commandReader = new ConsoleCommandReader(scanner);
        personReader = new ConsolePersonReader(scanner, messenger, outputManager);
    }

    @Override
    public boolean ready() {
        return scanner.hasNext();
    }

    @Override
    public String readCommand(){
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

    /*
    public void readCommands(){
        try {
            while (!exit && (reader.ready() == script)) {
                if (!script) {
                    printStream.print("> ");
                }
                String inputString = reader.readLine();
                if (inputString == null){
                    logger.log(Level.WARNING, "EOF found. Exit from the program.");
                    System.out.println("EOF found. Exit from the program.");
                    exit();
                } else {
                    input = inputString.trim().split("\\s+");
                    if (input[0].length() != 0) {
                        commandManager.executeCommand(input, this);
                    }
                }
            }
        }catch (IOException e) {
            logger.log(Level.WARNING, e.toString(), e);
            printStream.println(e.getMessage());
        }
    }

    public void readScript(FileInputStream fileInputStream){
        reader = new BufferedReader(new InputStreamReader(fileInputStream));
        script = true;
        readCommands();
        script = false;
        commandManager.clearUsedScripts();
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    public String askValue(String msg){
        String value = null;
        try {
            if (!script) {
                printStream.print(msg + ": ");
            }
            if (!script | reader.ready()) {
                value = reader.readLine().trim();
            }
        } catch (IOException e) {
            logger.log(Level.WARNING, e.toString(), e);
            System.out.println(e.getMessage());
        }
        return value;
    }

    public void exit(){
        exit = true;
    }

    public boolean isScript(){
        return script;
    }
    */
}
