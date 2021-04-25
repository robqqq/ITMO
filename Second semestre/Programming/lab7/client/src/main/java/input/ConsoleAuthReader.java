package input;

import auth.Auth;
import auth.SHA512Generator;
import messages.Messenger;
import output.OutputManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.regex.Pattern;

public class ConsoleAuthReader implements AuthReader{
    private final BufferedReader reader;
    private final Messenger messenger;
    private final OutputManager outputManager;
    private final Pattern usernamePattern = Pattern.compile("^[a-zA-Z][a-zA-Z0-9-_.]{1,19}$");
    private final Pattern passwordPattern = Pattern.compile("^[a-zA-Z0-9]{6,}$");
    private final String salt;

    public ConsoleAuthReader(BufferedReader reader, Messenger messenger, OutputManager outputManager, String salt) {
        this.reader = reader;
        this.messenger = messenger;
        this.outputManager = outputManager;
        this.salt = salt;
    }

    @Override
    public Auth readAuth() throws IOException {
        outputManager.printMsg("login: "); //TODO: Добавить в мессенджер все в этом классе
        String login = reader.readLine();
        while (!usernamePattern.matcher(login).matches()){
            outputManager.printErrorMsg("username can only contain numbers, letters, dots, underscores, and dashes,\n" +
                    "must start with a letter, and must be between 2 and 20 characters long\n");
            outputManager.printMsg("login: ");
            login = reader.readLine();
        }
        String password;
        outputManager.printMsg("password: ");
        if (System.console() != null) {
            password = String.valueOf(System.console().readPassword());
            while (!passwordPattern.matcher(password).matches()){
                outputManager.printErrorMsg("password can contain only letters and numbers,\n" +
                        "the length must be at least 6 characters\n");
                outputManager.printMsg("password: ");
                password = String.valueOf(System.console().readPassword());
            }
        } else {
            password = reader.readLine();
            while (!passwordPattern.matcher(password).matches()){
                outputManager.printErrorMsg("password length must be more or equals 6\n");
                outputManager.printMsg("password: ");
                password = reader.readLine();
            }
        }
        password = SHA512Generator.getHash(password + salt);
        return new Auth(login, password);
    }
}
