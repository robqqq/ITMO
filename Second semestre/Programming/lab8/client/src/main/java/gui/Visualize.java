package gui;

import authManager.ClientAuthManager;
import collectionManager.ClientCollectionManager;
import exceptions.ScriptException;
import person.Coordinates;
import person.Person;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class Visualize extends JComponent{
    private ClientCollectionManager collectionManager;
    private ClientAuthManager authManager;
    public final static int side = 10;
    public final static int gap = 3;
    private final int fps = 10;
    private static volatile Map<String, Color> owners = new HashMap<>();
    private volatile Map<Person, PersonRectangle> persons = new HashMap<>();
    private int minX;
    private int minY;

    public Visualize(ClientCollectionManager collectionManager, ClientAuthManager authManager){
        this.collectionManager = collectionManager;
        this.authManager = authManager;
        update();
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e){
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
                                            - (long) (minY - 1) * (side + gap) &&
                                    y <= (p.getCoordinates().getY() - 1) * side + p.getCoordinates().getY() * gap
                                            - (long) (minY - 1) * (side + gap) + 10){
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
                                if (authManager.getAuth().getLogin().equals(p.getOwner())){
                                    int option = JOptionPane.showConfirmDialog(Visualize.this, personString,
                                            ResourceBundle.getBundle("messages").getString("title.update"),
                                            JOptionPane.YES_NO_OPTION);
                                    if (option == 0) {
                                        GUIController.getCreatePersonPanel().setCommand("update");
                                        GUIController.getCreatePersonPanel().setArg(String.valueOf(p.getId()));
                                        GUIController.getCreatePersonPanel().getNameTF().setText(p.getName());
                                        GUIController.getCreatePersonPanel().getHeightTF().setText(String.valueOf(p.getHeight()));
                                        GUIController.getCreatePersonPanel().getBirthdayPicker().setDate(Timestamp.valueOf(p.getBirthday()));
                                        GUIController.getCreatePersonPanel().getEyeColorComboBox().setSelectedItem(p.getEyeColor());
                                        GUIController.getCreatePersonPanel().getHairColorComboBox().setSelectedItem(p.getHairColor());
                                        GUIController.getCreatePersonPanel().getCoordinatesXTF().setText(String.valueOf(p.getCoordinates().getX()));
                                        GUIController.getCreatePersonPanel().getCoordinatesYTF().setText(String.valueOf(p.getCoordinates().getY()));
                                        GUIController.getCreatePersonPanel().getLocationXTF().setText(String.valueOf(p.getLocation().getX()));
                                        GUIController.getCreatePersonPanel().getLocationYTF().setText(String.valueOf(p.getLocation().getY()));
                                        GUIController.getCreatePersonPanel().getLocationNameTF().setText(p.getLocation().getName());
                                        GUIController.createPersonDialog(ResourceBundle.getBundle("messages").getString("title.update"));
                                    }
                                } else {
                                    JOptionPane.showMessageDialog(Visualize.this, personString,
                                            ResourceBundle.getBundle("messages").getString("title.person"),
                                            JOptionPane.INFORMATION_MESSAGE);
                                }
                            }
                        });
            }
        });
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        collectionManager.getPersonStream().forEach(p -> {
            if(persons.containsKey(p)){
                g2.setColor(owners.get(p.getOwner()));
                g2.fill(persons.get(p));
            } else {
                if (!owners.containsKey(p.getOwner())){
                    Color color;
                    do {
                        color = RandomColor.getRandomColor();
                    } while (owners.containsValue(color));
                    owners.put(p.getOwner(), color);
                }
                PersonRectangle rectangle = new PersonRectangle(p, minX, minY);
                g2.setColor(owners.get(p.getOwner()));
                g2.fill(rectangle);
                persons.put(p, rectangle);
            }
        });
        tick();
    }

    private void tick() {
        new Thread(() -> {
            try {
                Thread.sleep(1000/fps);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (PersonRectangle rectangle: persons.values()) {
                rectangle.onTick(minX, minY);
                repaint();
            }
        }).start();
    }

    public void forceUpdate(){
        persons.clear();
    }

    public void update(){
        if (collectionManager.size() == 0) return;
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
}
