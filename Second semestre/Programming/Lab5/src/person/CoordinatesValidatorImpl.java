package person;

public class CoordinatesValidatorImpl implements CoordinatesValidator{
    @Override
    public boolean validateCoordinatesX(Double x) {
        return x != null && x <= 882;
    }

    @Override
    public boolean validateCoordinatesY(long y) {
        return y > -266;
    }
}
