package lab2;

import ru.ifmo.se.pokemon.*;

public class Mankey extends Pokemon {
    Mankey(String name, int level){
        super(name, level);
        addType(Type.FIGHTING);
        setStats(40, 80, 35, 35, 45, 70);
        setMove(new Thunderbolt(), new DoubleTeam(), new LowSweep());
    }
}

class Thunderbolt extends SpecialMove{
    Thunderbolt(){
        super(Type.ELECTRIC, 90, 1.0);
    }
    protected void applyOppEffects(Pokemon p){
        if(Math.random() < 0.1) Effect.paralyze(p);
    }
    protected String describe(){
        return "использует Thunderbolt";
    }
}

class DoubleTeam extends StatusMove{
    DoubleTeam(){
        super(Type.NORMAL, 0, 0);
    }
    protected void applySelfEffects(Pokemon p){
        p.setMod(Stat.EVASION, 1);
    }
    protected boolean checkAccuracy(Pokemon att, Pokemon def) {
        return true;
    }
    protected String describe(){
        return "использует Double Team";
    }
}

class LowSweep extends PhysicalMove{
    LowSweep(){
        super(Type.FIGHTING, 65, 1.0);
    }
    protected void applyOppEffects(Pokemon p){
        p.setMod(Stat.SPEED, -1);
    }
    protected String describe(){
        return "использует Low Sweep";
    }
}