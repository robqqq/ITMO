package server;

import application.Application;

public class Main {

    public static void main(String[] args){
        Application app = new ServerApplication(61231);
        app.start();
    }
}
