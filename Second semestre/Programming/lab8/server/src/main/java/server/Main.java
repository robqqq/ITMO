package server;

import application.Application;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {

    public static void main(String[] args){
        Logger logger = LoggerFactory.getLogger(Main.class);
        try {
            if (args.length < 3) {
                logger.error("requires port, db username and password in the arguments");
            } else {
                Application app = new ServerApplication(Integer.parseInt(args[0]), args[1], args[2]);
                app.start();
            }
        } catch (NumberFormatException e){
            logger.error("port must be an integer number");
        }
    }
}
