package inputManager;

import java.io.IOException;

public interface CommandReader {

    String readCommand() throws IOException;
}
