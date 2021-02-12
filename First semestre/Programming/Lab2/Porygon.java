package lab2;

import ru.ifmo.se.pokemon.*;

public class Porygon extends Pokemon {
    Porygon(String name, int level){
        super(name, level);
        addType(Type.NORMAL);
        setStats(65, 60, 70, 85, 75, 40);
        setMove(new DoubleTeam(), new Swagger());
    }
}

class Swagger extends StatusMove{
    Swagger(){
        super(Type.NORMAL, 0, 85);
    }
    protected void applyOppEffects(Pokemon p){
        Effect.confuse(p);
        p.setMod(Stat.ATTACK, 2);
    }
    protected String describe(){
        return "использует Swagger";
    }
}