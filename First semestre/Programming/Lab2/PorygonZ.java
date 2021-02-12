package lab2;

public class PorygonZ extends Porygon2{
    PorygonZ(String name, int level) {
        super(name, level);
        setStats(85, 80, 70, 135, 75, 90);
        addMove(new ThunderWave());
    }
}