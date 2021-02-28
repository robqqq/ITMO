package cleint;

import main.ObjectManager;

import java.io.FileInputStream;

public interface ClientManagerInterface {
    void readCommands();
    void start(ObjectManager objectManager);
    String askValue(String msg);
    void exit();
    void readScript(FileInputStream fileInputStream);
}
