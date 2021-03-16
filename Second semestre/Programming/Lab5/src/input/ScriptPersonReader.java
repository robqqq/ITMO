package input;

import exceptions.InvalidFieldException;
import exceptions.ScriptException;
import messages.Messenger;
import output.OutputManager;
import person.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Реализация интерфейса PersonReader для чтения из скрипта
 */
public class ScriptPersonReader implements PersonReader {
    private BufferedReader reader;
    private Messenger messenger;

    /**
     * @param reader объект BufferedReader
     * @param messenger messenger
     */
    public ScriptPersonReader(BufferedReader reader, Messenger messenger) {
        this.reader = reader;
        this.messenger = messenger;
    }

    @Override
    public Person readPerson(){
        PersonBuilder personBuilder = new PersonBuilderImpl(messenger);
        personBuilder.setId();
        try {
            readFields(personBuilder);
        } catch(IOException | InvalidFieldException | NumberFormatException e){
            throw new ScriptException(messenger.getExceptionMsg("script") + "\n");
        }
        return personBuilder.getPerson();
    }

    @Override
    public Person readPerson(int id, LocalDateTime creationDate){
        PersonBuilder personBuilder = new PersonBuilderImpl(messenger);
        try {
            personBuilder.setId(id);
            personBuilder.setCreationDate(creationDate);
            readFields(personBuilder);
        } catch (IOException | InvalidFieldException | NumberFormatException e) {
            throw new ScriptException(messenger.getExceptionMsg("script") + "\n");
        }
        return personBuilder.getPerson();
    }

    private void readFields(PersonBuilder personBuilder) throws IOException, InvalidFieldException {
        personBuilder.setName(reader.readLine().trim());
        personBuilder.setCoordinatesX(Double.parseDouble(reader.readLine().trim()));
        personBuilder.setCoordinatesY(Long.parseLong(reader.readLine().trim()));
        personBuilder.setHeight(Long.parseLong(reader.readLine().trim()));
        personBuilder.setBirthday(LocalDate.parse(reader.readLine().trim()).atStartOfDay());
        personBuilder.setEyeColor(EyeColor.valueOf(reader.readLine().trim().toUpperCase()));
        personBuilder.setHairColor(HairColor.valueOf(reader.readLine().trim().toUpperCase()));
        personBuilder.setLocationX(Float.parseFloat(reader.readLine().trim()));
        personBuilder.setLocationY(Long.parseLong(reader.readLine().trim()));
        personBuilder.setLocationName(reader.readLine().trim());
    }
}
