package lab2;

import ru.ifmo.se.pokemon.*;

public class Plusle extends Pokemon {
    Plusle(String name, int level){
        super(name, level);
        addType(Type.ELECTRIC);
        setStats(60, 50, 40, 85, 75, 95);
        setMove(new QuickAttack(), new ThunderWave(), new SweetKiss(), new Spark());
    }
}

class QuickAttack extends PhysicalMove{
    QuickAttack(){
        super(Type.NORMAL, 40, 1.0);
    }
    protected String describe(){
        return "использует Quick Attack";
    }
}

class ThunderWave extends StatusMove{
    ThunderWave(){
        super(Type.ELECTRIC, 0, 0.9);
    }
    protected void applyOppEffects(Pokemon p){
        Effect.paralyze(p);
    }
    protected String describe(){
        return "использует Thunder Wave";
    }
}

class SweetKiss extends StatusMove{
    SweetKiss(){
        super(Type.FAIRY, 0, 0.75);
    }
    protected void applyOppEffects(Pokemon p){
        Effect.confuse(p);
    }
    protected String describe(){
        return "использует Sweet Kiss";
    }
}

class Spark extends PhysicalMove{
    Spark(){
        super(Type.ELECTRIC, 65, 1.0);
    }
    protected void applyOppEffects(Pokemon p){
        if(Math.random() < 0.3) Effect.paralyze(p);
    }
    protected String describe(){
        return "использует Spark";
    }
}