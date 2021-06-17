package gui;

import person.Person;

import java.awt.*;
import java.util.Objects;

public class PersonRectangle extends Rectangle{
    private Person person;

    public PersonRectangle(Person person, int minX, int minY){
        this.person = person;
        reset(minX, minY);
    }

    public Person getPerson(){
        return person;
    }

    public void onTick(int minX, int minY){
        if (isAnimating()) {
            setVelocitySide(getVelocitySide() + 1);
            recalculate(minX, minY);
        }
    }

    public boolean isAnimating(){
        return getVelocitySide() < Visualize.side;
    }

    public void reset(int minX, int minY){
        setVelocitySide(0);
        recalculate(minX, minY);
    }

    private void recalculate(int minX, int minY){
        x = (int) ((person.getCoordinates().getX() - 1) * Visualize.side + person.getCoordinates().getX() *
                Visualize.gap - (minX - 1) * (Visualize.side + Visualize.gap) + (Visualize.side - getVelocitySide()) / 2);
        y = (int) ((person.getCoordinates().getY() - 1) * Visualize.side + person.getCoordinates().getY() *
                Visualize.gap - (minY - 1) * (Visualize.side + Visualize.gap) + (Visualize.side - getVelocitySide()) / 2);
    }

    private void setVelocitySide(int side){
        width = side;
        height = side;
    }

    private int getVelocitySide(){
        return width;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        PersonRectangle rectangle = (PersonRectangle) o;
        return Objects.equals(person, rectangle.person);
    }

    @Override
    public int hashCode() {
        return Objects.hash(person);
    }
}
