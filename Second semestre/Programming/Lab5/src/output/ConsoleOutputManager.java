package output;

public class ConsoleOutputManager implements OutputManager{

    @Override
    public void printMsg(String msg) {
        System.out.print(msg);
    }

    @Override
    public void printErrorMsg(String msg) {
        System.err.print(msg);
    }
}
