package input;

import exceptions.InvalidFieldException;
import exceptions.ScriptException;
import inputManager.PersonReader;
import person.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalDate;

public class ScriptPersonReader implements PersonReader {
    private BufferedReader reader;

    public ScriptPersonReader(BufferedReader reader) {
        this.reader = reader;
    }

    @Override
    public RawPerson readPerson(){
        RawPersonBuilder personBuilder = new RawPersonBuilderImpl();
        try {
            readFields(personBuilder);
        } catch(IOException | InvalidFieldException | NumberFormatException e){
            throw new ScriptException();
        }
        return personBuilder.getRawPerson();
    }

    private void readFields(RawPersonBuilder personBuilder) throws IOException, InvalidFieldException {
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
