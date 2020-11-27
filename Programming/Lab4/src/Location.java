public enum  Location{
    SPACESHIP("spaceship"),
    MOONCAVE("mooncave"),
    UNKNOWN("unknown"),
    OUTSIDE("outside");

    private String name;

    Location(String name){
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
