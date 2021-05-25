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
        outputManager.printMsg(messenger.getMsg("loginInput") + ": ");
        String login = reader.readLine();
        while (!usernamePattern.matcher(login).matches()){
            outputManager.printErrorMsg(messenger.getMsg("wrongLoginPattern") + "\n");
            outputManager.printMsg(messenger.getMsg("loginInput") + ": ");
            login = reader.readLine();
        }
        String password;
        outputManager.printMsg(messenger.getMsg("passwordInput") + ": ");
        if (System.console() != null) {
            password = String.valueOf(System.console().readPassword());
            while (!passwordPattern.matcher(password).matches()){
                outputManager.printErrorMsg(messenger.getMsg("wrongPasswordPattern") + "\n");
                outputManager.printMsg(messenger.getMsg("passwordInput") + ": ");
                password = String.valueOf(System.console().readPassword());
            }
        } else {
            password = reader.readLine();
            while (!passwordPattern.matcher(password).matches()){
                outputManager.printErrorMsg(messenger.getMsg("wrongPasswordPattern") + "\n");
                outputManager.printMsg(messenger.getMsg("passwordInput") + ": ");
                password = reader.readLine();
            }
        }
        password = SHA512Generator.getHash(password + salt);
        return new Auth(login, password);
    }
}
