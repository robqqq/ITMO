package input;

import exceptions.InvalidFieldException;
import messages.Messenger;
import output.OutputManager;
import person.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class ConsolePersonReader implements PersonReader {
    private Scanner scanner;
    private Messenger messenger;
    private OutputManager outputManager;

    public ConsolePersonReader(Scanner scanner, Messenger messenger, OutputManager outputManager) {
        this.scanner = scanner;
        this.messenger = messenger;
        this.outputManager = outputManager;
    }

    @Override
    public Person readPerson(){
        PersonBuilder personBuilder = new PersonBuilderImpl(messenger);
        personBuilder.setId();
        readFields(personBuilder);
        return personBuilder.getPerson();
    }

    @Override
    public Person readPerson(int id, LocalDateTime creationDate) {
        PersonBuilder personBuilder = new PersonBuilderImpl(messenger);
        try {
            personBuilder.setId(id);
            personBuilder.setCreationDate(creationDate);
        } catch (InvalidFieldException e) {
            outputManager.printErrorMsg(e.getMessage() + "\n");
        }
        readFields(personBuilder);
        return personBuilder.getPerson();
    }

    private void readName(PersonBuilder personBuilder){
        outputManager.printMsg(messenger.getFieldInputMsg("name") + ": ");
        try{
            personBuilder.setName(scanner.nextLine().trim());
        } catch (InvalidFieldException e) {
            outputManager.printErrorMsg(e.getMessage() + "\n");
            readName(personBuilder);
        }
    }

    private void readCoordinatesX(PersonBuilder personBuilder){
        outputManager.printMsg(messenger.getFieldInputMsg("coordinatesX") + ": ");
        try{
            personBuilder.setCoordinatesX(Double.parseDouble(scanner.nextLine().trim()));
        } catch (NumberFormatException e) {
            outputManager.printErrorMsg(messenger.getExceptionMsg("noFloat") + "\n");
            readCoordinatesX(personBuilder);
        } catch (InvalidFieldException e) {
            outputManager.printErrorMsg(e.getMessage() + "\n");
            readCoordinatesX(personBuilder);
        }
    }

    private void readCoordinatesY(PersonBuilder personBuilder){
        outputManager.printMsg(messenger.getFieldInputMsg("coordinatesY") + ": ");
        try{
            personBuilder.setCoordinatesY(Long.parseLong(scanner.nextLine().trim()));
        } catch (NumberFormatException e) {
            outputManager.printErrorMsg(messenger.getExceptionMsg("noInteger") + "\n");
            readCoordinatesY(personBuilder);
        } catch (InvalidFieldException e) {
            outputManager.printErrorMsg(e.getMessage() + "\n");
            readCoordinatesY(personBuilder);
        }
    }

    private void readHeight(PersonBuilder personBuilder){
        outputManager.printMsg(messenger.getFieldInputMsg("height") + ": ");
        try{
            personBuilder.setHeight(Long.parseLong(scanner.nextLine().trim()));
        } catch (NumberFormatException e) {
            outputManager.printErrorMsg(messenger.getExceptionMsg("noInteger") + "\n");
            readHeight(personBuilder);
        } catch (InvalidFieldException e) {
            outputManager.printErrorMsg(e.getMessage() + "\n");
            readHeight(personBuilder);
        }
    }

    private void readBirthday(PersonBuilder personBuilder){
        outputManager.printMsg(messenger.getFieldInputMsg("birthday") + ": ");
        try{
            personBuilder.setBirthday(LocalDate.parse(scanner.nextLine().trim()).atStartOfDay());
        } catch (DateTimeParseException e) {
            outputManager.printErrorMsg(messenger.getExceptionMsg("noDate") + "\n");
            readBirthday(personBuilder);
        } catch (InvalidFieldException e) {
            outputManager.printErrorMsg(e.getMessage() + "\n");
            readBirthday(personBuilder);
        }
    }

    private void readEyeColor(PersonBuilder personBuilder){
        outputManager.printMsg(messenger.getFieldInputMsg("eyeColor") + ": ");
        try{
            personBuilder.setEyeColor(EyeColor.valueOf(scanner.nextLine().trim().toUpperCase()));
        } catch (IllegalArgumentException e) {
            outputManager.printErrorMsg(messenger.getExceptionMsg("noEnum") + "\n");
            readEyeColor(personBuilder);
        } catch (InvalidFieldException e) {
            outputManager.printErrorMsg(e.getMessage() + "\n");
            readEyeColor(personBuilder);
        }
    }

    private void readHairColor(PersonBuilder personBuilder){
        outputManager.printMsg(messenger.getFieldInputMsg("hairColor") + ": ");
        try{
            personBuilder.setHairColor(HairColor.valueOf(scanner.nextLine().trim().toUpperCase()));
        } catch (IllegalArgumentException e) {
            outputManager.printErrorMsg(messenger.getExceptionMsg("noEnum") + "\n");
            readHairColor(personBuilder);
        } catch (InvalidFieldException e) {
            outputManager.printErrorMsg(e.getMessage() + "\n");
            readHairColor(personBuilder);
        }
    }

    private void readLocationX(PersonBuilder personBuilder){
        outputManager.printMsg(messenger.getFieldInputMsg("locationX") + ": ");
        try{
            personBuilder.setLocationX(Float.parseFloat(scanner.nextLine().trim()));
        } catch (NumberFormatException e) {
            outputManager.printErrorMsg(messenger.getExceptionMsg("noFloat") + "\n");
            readLocationX(personBuilder);
        }
    }

    private void readLocationY(PersonBuilder personBuilder){
        outputManager.printMsg(messenger.getFieldInputMsg("locationY") + ": ");
        try{
            personBuilder.setLocationY(Long.parseLong(scanner.nextLine().trim()));
        } catch (NumberFormatException e) {
            outputManager.printErrorMsg(messenger.getExceptionMsg("noInteger") + "\n");
            readLocationY(personBuilder);
        }
    }

    private void readLocationName(PersonBuilder personBuilder){
        outputManager.printMsg(messenger.getFieldInputMsg("locationName") + ": ");
        try{
            personBuilder.setLocationName(scanner.nextLine().trim());
        } catch (InvalidFieldException e) {
            outputManager.printErrorMsg(e.getMessage() + "\n");
            readLocationName(personBuilder);
        }
    }

    private void readFields(PersonBuilder personBuilder){
        readName(personBuilder);
        readCoordinatesX(personBuilder);
        readCoordinatesY(personBuilder);
        readHeight(personBuilder);
        readBirthday(personBuilder);
        readEyeColor(personBuilder);
        readHairColor(personBuilder);
        readLocationX(personBuilder);
        readLocationY(personBuilder);
        readLocationName(personBuilder);
    }
}
