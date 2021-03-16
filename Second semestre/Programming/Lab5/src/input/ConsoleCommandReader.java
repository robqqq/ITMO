package input;

import java.util.Scanner;

/**
 * Реализация интерфейса CommandReader для чтения из консоли
 */
public class ConsoleCommandReader implements CommandReader{
    private Scanner scanner;

    /**
     * @param scanner сканер
     */
    public ConsoleCommandReader(Scanner scanner){
        this.scanner = scanner;
    }

    @Override
    public String readCommand() {
        String input = scanner.nextLine();
        return input.trim().toLowerCase();
    }
}
