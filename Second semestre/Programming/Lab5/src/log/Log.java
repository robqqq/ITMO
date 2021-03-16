package log;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.LogManager;
import java.util.logging.Logger;

/**
 * Класс, который осуществляет логирование
 */
public class Log {

    public static Logger logger;

    static {
        try(FileInputStream ins = new FileInputStream("log.config")) {
            LogManager.getLogManager().readConfiguration(ins);
            logger = Logger.getLogger(Log.class.getName());
        }catch (IOException e){
            System.out.println(e.toString());
        }
    }
}