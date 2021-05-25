package person;

public class LocationValidatorImpl implements LocationValidator{

    @Override
    public boolean validateLocationX(float x) {
        return true;
    }

    @Override
    public boolean validateLocationY(long y) {
        return true;
    }

    @Override
    public boolean validateLocationName(String name) {
        if (name == null){
            return true;
        }
        return name.length() <= 480;
    }
}
