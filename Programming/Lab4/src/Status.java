public enum Status {
    UNKNOWN("unknown"),
    TIRED("tired"),
    SLEEPING("sleeping"),
    FULLOFENERGY("full of energy"),
    SCARED("scared"),
    CALM("calm");

    private String name;

    Status(String name){
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
