package log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Класс, который осуществляет логирование
 */
public class Log {
    private static final Logger logger = LoggerFactory.getLogger(Log.class);

    public static Logger log(){
        return logger;
    }
}