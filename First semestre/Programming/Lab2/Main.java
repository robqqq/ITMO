package lab2;

import ru.ifmo.se.pokemon.*;

public class Main {
    public static void main(String[] args) {
	Battle b = new Battle();
	Plusle a1 = new Plusle("Java", 100);
	Pokemon a2 = new Mankey("C", 100);
	Pokemon a3 = new Primeape("C++", 100);
	Pokemon f1 = new Porygon("Python", 100);
	Pokemon f2 = new Porygon2("Python 2", 100);
	PorygonZ f3 = new PorygonZ("Python 3", 100);
	b.addAlly(a1);
	b.addAlly(a2);
	b.addAlly(a3);
	b.addFoe(f1);
	b.addFoe(f2);
	b.addFoe(f3);
	b.go();
	Pokemon[] plusleee = new Pokemon[3];
	plusleee[1] = f3;
    }
}