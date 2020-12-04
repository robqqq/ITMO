public enum Status {
    UNKNOWN("unknown"),
    TIRED("tired"),
    SLEEPING("sleeping"),
    FULL_OF_ENERGY("full of energy"),
    SCARED("scared"),
    CALM("calm"),
    NOT_PLEASED("not pleased"),
    PLEASED("pleased");

    private String name;

    Status(String name){
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
