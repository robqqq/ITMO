package lab2;

import ru.ifmo.se.pokemon.*;

public class Primeape extends Mankey{
    Primeape(String name, int level) {
        super(name, level);
        setStats(65, 105, 60, 60, 70, 95);
        addMove(new StoneEdge());
    }
}

class StoneEdge extends PhysicalMove{
    StoneEdge(){
        super(Type.ROCK, 100, 0.8);
    }
    protected double calcCriticalHit(Pokemon att, Pokemon def){
        if (Math.random() < 1.0/8.0){
            return 2;
        }
        else{
            return 1;
        }
    }
    protected String describe(){
        return "использует Stone Edge";
    }
}
