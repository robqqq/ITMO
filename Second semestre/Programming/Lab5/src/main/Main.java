package main;

import application.Application;
import application.ApplicationImpl;

public class Main {
    public static void main(String[] args){
        Application app = new ApplicationImpl();
        app.start();
    }
}
