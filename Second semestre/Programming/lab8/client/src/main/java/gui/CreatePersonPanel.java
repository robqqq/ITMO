package gui;

import java.awt.*;
import java.util.ResourceBundle;
import javax.swing.*;
import javax.swing.border.*;

import org.jdesktop.swingx.*;
import person.*;

public class CreatePersonPanel extends JPanel {
    private JPanel buttonPanel;
    private JButton okButton;
    private JButton cancelButton;
    private JPanel inputPanel;
    private JPanel namePanel;
    private JLabel nameLabel;
    private JTextField nameTF;
    private JPanel heightPanel;
    private JLabel heightLabel;
    private JTextField heightTF;
    private JPanel birthdayPanel;
    private JLabel birthdayLabel;
    private JXDatePicker birthdayPicker;
    private JPanel colorsPanel;
    private JLabel eyeColorLabel;
    private JComboBox eyeColorComboBox;
    private JLabel hairColorLabel;
    private JComboBox hairColorComboBox;
    private JLabel coordinatesLabel;
    private JComponent coordinatesSeparator;
    private JPanel coordinatesPanel;
    private JLabel coordinatesXLabel;
    private JTextField coordinatesXTF;
    private JLabel coordinatesYLabel;
    private JTextField coordinatesYTF;
    private JLabel locationLabel;
    private JComponent locationSeparator;
    private JPanel locationPanel;
    private JLabel locationXLabel;
    private JTextField locationXTF;
    private JLabel locationYLabel;
    private JTextField locationYTF;
    private JLabel locationNameLabel;
    private JTextField locationNameTF;
    private final EyeColor[] eyeColorConstants;
    private final HairColor[] hairColorConstants;
    private String command;
    private String arg;

    public CreatePersonPanel() {
        super();
        eyeColorConstants = new EyeColor[3];
        eyeColorConstants[0] = EyeColor.BLACK;
        eyeColorConstants[1] = EyeColor.ORANGE;
        eyeColorConstants[2] = EyeColor.WHITE;
        hairColorConstants = new HairColor[5];
        hairColorConstants[0] = HairColor.GREEN;
        hairColorConstants[1] = HairColor.BLACK;
        hairColorConstants[2] = HairColor.BLUE;
        hairColorConstants[3] = HairColor.YELLOW;
        hairColorConstants[4] = HairColor.WHITE;
        initComponents();
    }

    private void initComponents() {
        buttonPanel = new JPanel();
        okButton = new JButton();
        cancelButton = new JButton();
        inputPanel = new JPanel();
        namePanel = new JPanel();
        nameLabel = new JLabel();
        nameTF = new JTextField();
        heightPanel = new JPanel();
        heightLabel = new JLabel();
        heightTF = new JTextField();
        birthdayPanel = new JPanel();
        birthdayLabel = new JLabel();
        birthdayPicker = new JXDatePicker();
        colorsPanel = new JPanel();
        eyeColorLabel = new JLabel();
        eyeColorComboBox = new JComboBox(eyeColorConstants);
        hairColorLabel = new JLabel();
        hairColorComboBox = new JComboBox(hairColorConstants);
        coordinatesLabel = new JLabel();
        coordinatesSeparator = new JSeparator();
        coordinatesPanel = new JPanel();
        coordinatesXLabel = new JLabel();
        coordinatesXTF = new JTextField();
        coordinatesYLabel = new JLabel();
        coordinatesYTF = new JTextField();
        locationLabel = new JLabel();
        locationSeparator = new JSeparator();
        locationPanel = new JPanel();
        locationXLabel = new JLabel();
        locationXTF = new JTextField();
        locationYLabel = new JLabel();
        locationYTF = new JTextField();
        locationNameLabel = new JLabel();
        locationNameTF = new JTextField();

        //======== this ========
        {
            setBorder(new EmptyBorder(12, 12, 12, 12));
            setLayout(new BorderLayout());

            //======== buttonPanel ========
            {
                buttonPanel.setLayout(new FlowLayout());

                //---- okButton ----
                okButton.setText(ResourceBundle.getBundle("messages").getString("button.ok"));
                buttonPanel.add(okButton);

                //---- cancelButton ----
                cancelButton.setText(ResourceBundle.getBundle("messages").getString("button.cancel"));
                buttonPanel.add(cancelButton);
            }
            add(buttonPanel, BorderLayout.SOUTH);

            //======== inputPanel ========
            {
                inputPanel.setLayout(new VerticalLayout(6));

                //======== namePanel ========
                {
                    namePanel.setLayout(new HorizontalLayout(6));

                    //---- nameLabel ----
                    nameLabel.setText(ResourceBundle.getBundle("messages").getString("label.name"));
                    nameLabel.setPreferredSize(new Dimension(50, 25));
                    namePanel.add(nameLabel);

                    //---- nameTF ----
                    nameTF.setPreferredSize(new Dimension(230, 25));
                    namePanel.add(nameTF);
                }
                inputPanel.add(namePanel);

                //======== heightPanel ========
                {
                    heightPanel.setLayout(new HorizontalLayout(6));

                    //---- heightLabel ----
                    heightLabel.setText(ResourceBundle.getBundle("messages").getString("label.height"));
                    heightLabel.setPreferredSize(new Dimension(50, 25));
                    heightPanel.add(heightLabel);

                    //---- heightTF ----
                    heightTF.setPreferredSize(new Dimension(230, 25));
                    heightPanel.add(heightTF);
                }
                inputPanel.add(heightPanel);

                //======== birthdayPanel ========
                {
                    birthdayPanel.setLayout(new HorizontalLayout(6));

                    //---- birthdayLabel ----
                    birthdayLabel.setText(ResourceBundle.getBundle("messages").getString("label.birthday"));
                    birthdayPanel.add(birthdayLabel);
                    birthdayPanel.add(birthdayPicker);
                }
                inputPanel.add(birthdayPanel);

                //======== colorsPanel ========
                {
                    colorsPanel.setLayout(new HorizontalLayout(6));

                    //---- eyeColorLabel ----
                    eyeColorLabel.setText(ResourceBundle.getBundle("messages").getString("label.eye_color"));
                    colorsPanel.add(eyeColorLabel);

                    //---- eyeColorComboBox ----
                    colorsPanel.add(eyeColorComboBox);

                    //---- hairColorLabel ----
                    hairColorLabel.setText(ResourceBundle.getBundle("messages").getString("label.hair_color"));
                    colorsPanel.add(hairColorLabel);

                    //---- hairColorComboBox ----
                    colorsPanel.add(hairColorComboBox);
                }
                inputPanel.add(colorsPanel);
                inputPanel.add(coordinatesSeparator);

                //---- coordinatesLabel ----
                coordinatesLabel.setText(ResourceBundle.getBundle("messages").getString("label.coordinates"));
                inputPanel.add(coordinatesLabel);

                //======== coordinatesPanel ========
                {
                    coordinatesPanel.setLayout(new HorizontalLayout(6));

                    //---- coordinatesXLabel ----
                    coordinatesXLabel.setText(ResourceBundle.getBundle("messages").getString("label.x"));
                    coordinatesPanel.add(coordinatesXLabel);

                    //---- coordinatesXTF ----
                    coordinatesXTF.setPreferredSize(new Dimension(60,25));
                    coordinatesPanel.add(coordinatesXTF);

                    //---- coordinatesYLabel ----
                    coordinatesYLabel.setText(ResourceBundle.getBundle("messages").getString("label.y"));
                    coordinatesPanel.add(coordinatesYLabel);

                    //---- coordinatesYTF ----
                    coordinatesYTF.setPreferredSize(new Dimension(60,25));
                    coordinatesPanel.add(coordinatesYTF);
                }
                inputPanel.add(coordinatesPanel);

                inputPanel.add(locationSeparator);

                //---- locationLabel ----//
                locationLabel.setText(ResourceBundle.getBundle("messages").getString("label.location"));
                inputPanel.add(locationLabel);

                //======== locationPanel ========
                {
                    locationPanel.setLayout(new HorizontalLayout(6));

                    //---- locationXLabel ----
                    locationXLabel.setText(ResourceBundle.getBundle("messages").getString("label.x"));
                    locationPanel.add(locationXLabel);

                    //---- locationXTF ----
                    locationXTF.setPreferredSize(new Dimension(60,25));
                    locationPanel.add(locationXTF);

                    //---- locationYLabel ----
                    locationYLabel.setText(ResourceBundle.getBundle("messages").getString("label.y"));
                    locationPanel.add(locationYLabel);

                    //---- locationYTF ----
                    locationYTF.setPreferredSize(new Dimension(60,25));
                    locationPanel.add(locationYTF);

                    //---- locationNameLabel ----
                    locationNameLabel.setText(ResourceBundle.getBundle("messages").getString("label.name"));
                    locationPanel.add(locationNameLabel);

                    //---- locationNameTF ----
                    locationNameTF.setPreferredSize(new Dimension(80,25));
                    locationPanel.add(locationNameTF);
                }
                inputPanel.add(locationPanel);
            }
            add(inputPanel, BorderLayout.CENTER);
        }
    }

    public JButton getOkButton() {
        return okButton;
    }

    public JButton getCancelButton() {
        return cancelButton;
    }

    public JTextField getNameTF() {
        return nameTF;
    }

    public JTextField getHeightTF() {
        return heightTF;
    }

    public JXDatePicker getBirthdayPicker() {
        return birthdayPicker;
    }

    public JComboBox getEyeColorComboBox() {
        return eyeColorComboBox;
    }

    public JComboBox getHairColorComboBox() {
        return hairColorComboBox;
    }

    public JTextField getCoordinatesXTF() {
        return coordinatesXTF;
    }

    public JTextField getCoordinatesYTF() {
        return coordinatesYTF;
    }

    public JTextField getLocationXTF() {
        return locationXTF;
    }

    public JTextField getLocationYTF() {
        return locationYTF;
    }

    public JTextField getLocationNameTF() {
        return locationNameTF;
    }

    public void setTexts(){
        okButton.setText(ResourceBundle.getBundle("messages").getString("button.ok"));
        cancelButton.setText(ResourceBundle.getBundle("messages").getString("button.cancel"));
        nameLabel.setText(ResourceBundle.getBundle("messages").getString("label.name"));
        heightLabel.setText(ResourceBundle.getBundle("messages").getString("label.height"));
        birthdayLabel.setText(ResourceBundle.getBundle("messages").getString("label.birthday"));
        eyeColorLabel.setText(ResourceBundle.getBundle("messages").getString("label.eye_color"));
        hairColorLabel.setText(ResourceBundle.getBundle("messages").getString("label.hair_color"));
        coordinatesLabel.setText(ResourceBundle.getBundle("messages").getString("label.coordinates"));
        coordinatesXLabel.setText(ResourceBundle.getBundle("messages").getString("label.x"));
        coordinatesYLabel.setText(ResourceBundle.getBundle("messages").getString("label.y"));
        locationLabel.setText(ResourceBundle.getBundle("messages").getString("label.location"));
        locationXLabel.setText(ResourceBundle.getBundle("messages").getString("label.x"));
        locationYLabel.setText(ResourceBundle.getBundle("messages").getString("label.y"));
        locationNameLabel.setText(ResourceBundle.getBundle("messages").getString("label.name"));
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public void clearInputs(){
        nameTF.setText("");
        heightTF.setText("");
        birthdayPicker.setDate(null);
        coordinatesXTF.setText("");
        coordinatesYTF.setText("");
        locationXTF.setText("");
        locationYTF.setText("");
        locationNameTF.setText("");
    }

    public String getArg() {
        return arg;
    }

    public void setArg(String arg) {
        this.arg = arg;
    }
}
