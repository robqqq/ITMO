public enum  Location{
    SPACESHIP("spaceship", false),
    MOON_CAVE("moon cave", false),
    UNKNOWN("unknown", false),
    OUTSIDE("outside", false),
    FOOD_COMPARTMENT("food compartment", false),
    TAIL_SECTION("tail section of the rocket", false),
    ICICLE_GROTTO("icicle grotto", false),
    ICE_TUNNEL("tunnel with the ice bottom", false),
    SUBLUNAR_WELL("sublunar well", false),
    LOS_SVINOS_AIRSPACE("over the Los Svinos", true),
    LOS_KABANOS_AIRSPACE("over the Los Kabanos", true),
    LOS_PAGANOS_AIRSPACE("over the Los Paganos", true),
    SEA_AIRSPACE("over the sea", true);

    private String name;
    private boolean airspace;

    Location(String name, boolean airspace){
        this.name = name;
        this.airspace = airspace;
    }

    public boolean airspace(){
        return airspace;
    }

    @Override
    public String toString() {
        return name;
    }
}
