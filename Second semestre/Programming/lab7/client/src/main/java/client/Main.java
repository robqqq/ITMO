package client;

import application.Application;

public class Main {

    public static void main(String[] args){
        Application app;
        if (args.length < 2) {
            app = new ClientApplication(null, null);
        } else {
            app = new ClientApplication(args[0], args[1]);
        }
        app.start();
    }
}
