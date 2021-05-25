package gui;

import collectionManager.ClientCollectionManager;
import person.Coordinates;
import person.Person;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class Visualize extends JComponent {
    private ClientCollectionManager collectionManager;
    private int side;
    private int gap;
    private static Map<String, Color> owners = new HashMap<>();
    private int maxX;
    private int maxY;
    private int minX;
    private int minY;


    public Visualize(ClientCollectionManager collectionManager){
        this.collectionManager = collectionManager;
        side = 10;
        gap = 3;
        update();
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e){
                if (e.getButton() != MouseEvent.BUTTON1) return;
                int x = e.getX();
                int y = e.getY();
                collectionManager.getPersonStream()
                        .forEach(p -> {
                            if(x >= (p.getCoordinates().getX() - 1) * side + p.getCoordinates().getX() * gap
                                    - (minX - 1) * (side + gap) &&
                                    x <= (p.getCoordinates().getX() - 1) * side + p.getCoordinates().getX() * gap
                                            - (minX - 1) * (side + gap) + 10 &&
                                    y >= (p.getCoordinates().getY() - 1) * side + p.getCoordinates().getY() * gap
                                            - (long) (minX - 1) * (side + gap) &&
                                    y <= (p.getCoordinates().getY() - 1) * side + p.getCoordinates().getY() * gap
                                            - (long) (minX - 1) * (side + gap) + 10){
                                String personString = String.format("""
                        %s: %d;
                        %s: %s;
                        %s: %f;
                        %s: %d;
                        %s: %s;
                        %s: %d;
                        %s: %s;
                        %s: %s;
                        %s: %s;
                        %s: %f;
                        %s: %d;
                        %s: %s;
                        %s: %s
                        """,
                                        ResourceBundle.getBundle("messages").getString("column.id"), p.getId(),
                                        ResourceBundle.getBundle("messages").getString("column.name"), p.getName(),
                                        ResourceBundle.getBundle("messages").getString("column.coordinates_x"),
                                        p.getCoordinates().getX(),
                                        ResourceBundle.getBundle("messages").getString("column.coordinates_y"),
                                        p.getCoordinates().getY(),
                                        ResourceBundle.getBundle("messages").getString("column.creation_date"),
                                        p.getCreationDate().toLocalDate(),
                                        ResourceBundle.getBundle("messages").getString("column.height"),
                                        p.getHeight(),
                                        ResourceBundle.getBundle("messages").getString("column.birthday"),
                                        p.getBirthday().toLocalDate(),
                                        ResourceBundle.getBundle("messages").getString("column.eye_color"),
                                        p.getEyeColor(),
                                        ResourceBundle.getBundle("messages").getString("column.hair_color"),
                                        p.getHairColor(),
                                        ResourceBundle.getBundle("messages").getString("column.location_x"),
                                        p.getLocation().getX(),
                                        ResourceBundle.getBundle("messages").getString("column.location_y"),
                                        p.getLocation().getY(),
                                        ResourceBundle.getBundle("messages").getString("column.location_name"),
                                        p.getLocation().getName(),
                                        ResourceBundle.getBundle("messages").getString("column.owner"),
                                        p.getOwner());
                                JOptionPane.showMessageDialog(Visualize.this, personString,
                                        ResourceBundle.getBundle("messages").getString("title.person"),
                                        JOptionPane.INFORMATION_MESSAGE);
                            }
                        });
            }
        });
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        collectionManager.getPersonStream().forEach(p -> {
            int x = (int) ((p.getCoordinates().getX() - 1) * side + p.getCoordinates().getX() * gap
                    - (minX - 1) * (side + gap));
            int y = (int) ((p.getCoordinates().getY() - 1) * side + p.getCoordinates().getY() * gap
                    - (minY - 1) * (side + gap));
            if (!owners.containsKey(p.getOwner())) {
                Color color;
                do {
                    color = RandomColor.getRandomColor();
                } while (owners.containsValue(color));
                owners.put(p.getOwner(), color);
            } else {
                g2.setColor(owners.get(p.getOwner()));
                g2.fillRect(x, y, side, side);
            }
        });
    }

    public void update(){
        if (collectionManager.size() == 0) return;
        maxX = collectionManager.getPersonStream()
                .map(Person::getCoordinates)
                .map(Coordinates::getX)
                .max(Double::compareTo)
                .get()
                .intValue();
        maxX = collectionManager.getPersonStream()
                .map(Person::getCoordinates)
                .map(Coordinates::getY)
                .max(Long::compareTo)
                .get()
                .intValue();
        minX = collectionManager.getPersonStream()
                .map(Person::getCoordinates)
                .map(Coordinates::getX)
                .min(Double::compareTo)
                .get()
                .intValue();
        minY = collectionManager.getPersonStream()
                .map(Person::getCoordinates)
                .map(Coordinates::getY)
                .min(Long::compareTo)
                .get()
                .intValue();
        repaint();
    }

    public void changeColor(String owner){
        owners.remove(owner);
    }
}
