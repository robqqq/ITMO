package gui;

import person.Person;

import javax.swing.*;
import java.awt.*;

public class PersonComponent extends JComponent {
    private final Person person;
    private final Color color;
    private final int x;
    private final int y;
    private final static int size = 10;
    Timer timer;

    public PersonComponent(Person person, Color color, int x, int y) {
        this.person = person;
        this.color = color;
        this.x = x;
        this.y = y;
        timer = new Timer(0, null);
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(color);
        for (int tempSize = 0; tempSize < size; tempSize += 2){
            g2.fillRect(x + size - tempSize / 2, y + size - tempSize / 2, tempSize, tempSize);
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
