package client;

import application.Application;

public class Main {

    public static void main(String[] args){
        if (args.length < 2){
            System.err.println("Requires address and port in args");//TODO: Перенести проверку и вывод в Application
        } else {
            try {
                Application app = new ClientApplication(args[0], Integer.parseInt(args[1]));
                app.start();
            } catch (NumberFormatException e){
                System.err.println("port must be an integer number");
            }
        }
    }
}
