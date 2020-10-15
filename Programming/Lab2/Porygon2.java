package lab2;

import ru.ifmo.se.pokemon.*;

public class Porygon2 extends Porygon{
    Porygon2(String name, int level) {
        super(name, level);
        setStats(85, 80, 90, 105, 95, 60);
        addMove(new DefenseCurl());
    }
}

class DefenseCurl extends StatusMove{
    DefenseCurl(){
        super(Type.NORMAL, 0, 0);
    }
    protected void applySelfEffects(Pokemon p){
        p.setMod(Stat.DEFENSE, 1);
    }
    protected boolean checkAccuracy(Pokemon att, Pokemon def) {
        return true;
    }
    protected String describe(){
        return "использует Defense Curl";
    }
}