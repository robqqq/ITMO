package input;

import exceptions.InvalidFieldException;
import inputManager.PersonReader;
import messages.Messenger;
import output.OutputManager;
import person.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class
ConsolePersonReader implements PersonReader {
    private BufferedReader reader;;
    private Messenger messenger;
    private OutputManager outputManager;

    public ConsolePersonReader(BufferedReader reader, Messenger messenger, OutputManager outputManager) {
        this.reader = reader;
        this.messenger = messenger;
        this.outputManager = outputManager;
    }

    @Override
    public RawPerson readPerson() throws IOException {
        RawPersonBuilder personBuilder = new RawPersonBuilderImpl();
        readFields(personBuilder);
        return personBuilder.getRawPerson();
    }

    private void readName(RawPersonBuilder personBuilder) throws IOException {
        outputManager.printMsg(messenger.getMsg("inputName") + ": ");
        try{
            personBuilder.setName(reader.readLine().trim());
        } catch (InvalidFieldException e) {
            outputManager.printErrorMsg(messenger.getMsg("invalidName") + "\n");
            readName(personBuilder);
        }
    }

    private void readCoordinatesX(RawPersonBuilder personBuilder) throws IOException {
        outputManager.printMsg(messenger.getMsg("inputCoordinatesX") + ": ");
        try{
            personBuilder.setCoordinatesX(Double.parseDouble(reader.readLine().trim()));
        } catch (NumberFormatException e) {
            outputManager.printErrorMsg(messenger.getMsg("noFloat") + "\n");
            readCoordinatesX(personBuilder);
        } catch (InvalidFieldException e) {
            outputManager.printErrorMsg(messenger.getMsg("invalidCoordinatesX") + "\n");
            readCoordinatesX(personBuilder);
        }
    }

    private void readCoordinatesY(RawPersonBuilder personBuilder) throws IOException {
        outputManager.printMsg(messenger.getMsg("inputCoordinatesY") + ": ");
        try{
            personBuilder.setCoordinatesY(Long.parseLong(reader.readLine().trim()));
        } catch (NumberFormatException e) {
            outputManager.printErrorMsg(messenger.getMsg("noInteger") + "\n");
            readCoordinatesY(personBuilder);
        } catch (InvalidFieldException e) {
            outputManager.printErrorMsg(messenger.getMsg("invalidCoordinatesY") + "\n");
            readCoordinatesY(personBuilder);
        }
    }

    private void readHeight(RawPersonBuilder personBuilder) throws IOException {
        outputManager.printMsg(messenger.getMsg("inputHeight") + ": ");
        try{
            personBuilder.setHeight(Long.parseLong(reader.readLine().trim()));
        } catch (NumberFormatException e) {
            outputManager.printErrorMsg(messenger.getMsg("noInteger") + "\n");
            readHeight(personBuilder);
        } catch (InvalidFieldException e) {
            outputManager.printErrorMsg(messenger.getMsg("invalidHeight") + "\n");
            readHeight(personBuilder);
        }
    }

    private void readBirthday(RawPersonBuilder personBuilder) throws IOException {
        outputManager.printMsg(messenger.getMsg("inputBirthday") + ": ");
        try{
            personBuilder.setBirthday(LocalDate.parse(reader.readLine().trim()).atStartOfDay());
        } catch (DateTimeParseException e) {
            outputManager.printErrorMsg(messenger.getMsg("noDate") + "\n");
            readBirthday(personBuilder);
        } catch (InvalidFieldException e) {
            outputManager.printErrorMsg(messenger.getMsg("invalidBirthday") + "\n");
            readBirthday(personBuilder);
        }
    }

    private void readEyeColor(RawPersonBuilder personBuilder) throws IOException {
        outputManager.printMsg(messenger.getMsg("inputEyeColor") + ": ");
        try{
            personBuilder.setEyeColor(EyeColor.valueOf(reader.readLine().trim().toUpperCase()));
        } catch (IllegalArgumentException e) {
            outputManager.printErrorMsg(messenger.getMsg("noEnum") + "\n");
            readEyeColor(personBuilder);
        } catch (InvalidFieldException e) {
            outputManager.printErrorMsg(messenger.getMsg("invalidEyeColor") + "\n");
            readEyeColor(personBuilder);
        }
    }

    private void readHairColor(RawPersonBuilder personBuilder) throws IOException {
        outputManager.printMsg(messenger.getMsg("inputHairColor") + ": ");
        try{
            personBuilder.setHairColor(HairColor.valueOf(reader.readLine().trim().toUpperCase()));
        } catch (IllegalArgumentException e) {
            outputManager.printErrorMsg(messenger.getMsg("noEnum") + "\n");
            readHairColor(personBuilder);
        } catch (InvalidFieldException e) {
            outputManager.printErrorMsg(messenger.getMsg("invalidHairColor") + "\n");
            readHairColor(personBuilder);
        }
    }

    private void readLocationX(RawPersonBuilder personBuilder) throws IOException {
        outputManager.printMsg(messenger.getMsg("inputLocationX") + ": ");
        try{
            personBuilder.setLocationX(Float.parseFloat(reader.readLine().trim()));
        } catch (NumberFormatException e) {
            outputManager.printErrorMsg(messenger.getMsg("noFloat") + "\n");
            readLocationX(personBuilder);
        }
    }

    private void readLocationY(RawPersonBuilder personBuilder) throws IOException {
        outputManager.printMsg(messenger.getMsg("inputLocationY") + ": ");
        try{
            personBuilder.setLocationY(Long.parseLong(reader.readLine().trim()));
        } catch (NumberFormatException e) {
            outputManager.printErrorMsg(messenger.getMsg("noInteger") + "\n");
            readLocationY(personBuilder);
        }
    }

    private void readLocationName(RawPersonBuilder personBuilder) throws IOException {
        outputManager.printMsg(messenger.getMsg("inputLocationName") + ": ");
        try{
            personBuilder.setLocationName(reader.readLine().trim());
        } catch (InvalidFieldException e) {
            outputManager.printErrorMsg(messenger.getMsg("invalidLocationName") + "\n");
            readLocationName(personBuilder);
        }
    }

    private void readFields(RawPersonBuilder personBuilder) throws IOException {
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
