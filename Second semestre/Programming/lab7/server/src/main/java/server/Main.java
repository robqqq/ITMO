package server;

import application.Application;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {

    public static void main(String[] args){
        Logger logger = LoggerFactory.getLogger(Main.class);
        if (args.length < 2){
            logger.error("requires db username and password in the arguments");
        } else {
            Application app = new ServerApplication(61231, args[0], args[1]);
            app.start();
        }
    }
}
