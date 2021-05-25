package gui;

import authManager.ClientAuthManager;
import clientCommands.ClientCommandManager;
import collectionManager.ClientCollectionManager;
import collectionManager.CollectionSynchronizer;
import exceptions.*;
import networkMessages.Response;
import org.jdesktop.swingx.renderer.IconValue;
import org.jdesktop.swingx.sort.RowFilters;
import person.*;

import javax.swing.*;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.regex.PatternSyntaxException;

public class GUIController {
    private volatile boolean updatingCollection;
    private JFrame frame;
    private JDialog dialog;
    private AuthPanel authPanel;
    private MainPanel mainPanel;
    private TableRowSorter<TableModel> sorter;
    private CreatePersonPanel createPersonPanel;
    private PersonTableModel tableModel;
    private CollectionSynchronizer collectionSynchronizer;
    private ClientAuthManager authManager;
    private ClientCollectionManager collectionManager;
    private ClientCommandManager commandManager;
    private Map<String, Locale> locales;
    private Thread updatingCollectionThread;
    private Toolkit toolkit = Toolkit.getDefaultToolkit();
    private final EyeColor[] eyeColorConstants;
    private final HairColor[] hairColorConstants;

    public GUIController(ClientCollectionManager collectionManager, ClientAuthManager authManager,
                         ClientCommandManager commandManager, CollectionSynchronizer collectionSynchronizer) {
        this.collectionSynchronizer = collectionSynchronizer;
        this.authManager = authManager;
        this.collectionManager = collectionManager;
        this.commandManager = commandManager;
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
        locales = new HashMap<>(4);
        locales.put("ru", new Locale("ru"));
        locales.put("sr", new Locale("sr"));
        locales.put("hu", new Locale("hu"));
        locales.put("es_EC", new Locale("es", "EC"));
        tableModel = new PersonTableModel(collectionManager);
        frame = new JFrame("Lab8");
        frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        frame.setResizable(false);
        mainPanel = new MainPanel(tableModel, collectionManager);
        sorter = new TableRowSorter<>(tableModel);
        mainPanel.getTable().setRowSorter(sorter);
        authPanel = new AuthPanel();
        createPersonPanel = new CreatePersonPanel();
        addActionListeners();
    }

    private void authPanel(){
        updatingCollection = false;
        frame.remove(mainPanel);
        frame.setJMenuBar(null);
        frame.add(authPanel);
        frame.setBounds(toolkit.getScreenSize().width/2 - 160, toolkit.getScreenSize().height/2 - 120,
                320, 240);
    }

    private void mainPanel(){
        updatingCollection = true;
        frame.remove(authPanel);
        frame.add(mainPanel);
        frame.setJMenuBar(mainPanel.getMenuBar());
        mainPanel.getUserMenu().setText(authManager.getAuth().getLogin());
        frame.setBounds(toolkit.getScreenSize().width/2 - 640, toolkit.getScreenSize().height/2 - 360,
                1280, 720);
    }

    private void createPersonDialog(String title){
        updatingCollection = false;
        dialog = new JDialog(frame, title);
        dialog.setResizable(false);
        dialog.add(createPersonPanel);
        dialog.pack();
        dialog.setLocationRelativeTo(frame);
        dialog.setVisible(true);
    }

    private void addActionListeners(){
        authPanel.getCancelButton().addActionListener(e -> {
            try {
                if (authManager.getAuth() != null) authManager.disconnect();
            } catch (IOException ignored) {

            }
            System.exit(0);
        });

        authPanel.getCancelRegisterButton().addActionListener(e -> {
            try {
                if (authManager.getAuth() != null) authManager.disconnect();
            } catch (IOException ignored) {

            }
            System.exit(0);
        });

        authPanel.getLoginButton().addActionListener(e -> {
            try {
                authManager.auth(authPanel.getLoginTF().getText(), new String(authPanel.getPwdTF().getPassword()));
                mainPanel();
                authPanel.getLoginTF().setText("");
                authPanel.getPwdTF().setText("");
                authPanel.getLoginRegisterTF().setText("");
                authPanel.getPwdRegisterTF().setText("");
                authPanel.getRepeatPwdTF().setText("");
            } catch (UsernamePatternException usernamePatternException) {
                authPanel.getLoginTF().setText("");
                authPanel.getPwdTF().setText("");
                JOptionPane.showMessageDialog(frame, ResourceBundle.getBundle("messages").getString("err.username_pattern"),
                        ResourceBundle.getBundle("messages").getString("title.error"), JOptionPane.ERROR_MESSAGE);
            } catch (PasswordPatternException passwordPatternException) {
                authPanel.getPwdTF().setText("");
                JOptionPane.showMessageDialog(frame, ResourceBundle.getBundle("messages").getString("err.password_pattern"),
                        ResourceBundle.getBundle("messages").getString("title.error"), JOptionPane.ERROR_MESSAGE);
            } catch (AuthException authException) {
                authPanel.getLoginTF().setText("");
                authPanel.getPwdTF().setText("");
                JOptionPane.showMessageDialog(frame, ResourceBundle.getBundle("messages").getString("err.wrong_auth"),
                        ResourceBundle.getBundle("messages").getString("title.error"), JOptionPane.ERROR_MESSAGE);
            } catch (IOException ioException) {
                JOptionPane.showMessageDialog(frame, ResourceBundle.getBundle("messages").getString("err.no_connection"),
                        ResourceBundle.getBundle("messages").getString("title.error"), JOptionPane.ERROR_MESSAGE);
            }
        });

        authPanel.getRegisterButton().addActionListener(e -> {
            try {
                if (Arrays.equals(authPanel.getPwdRegisterTF().getPassword(), authPanel.getRepeatPwdTF().getPassword())) {
                    authManager.reg(authPanel.getLoginRegisterTF().getText(), new String(authPanel.getPwdRegisterTF().getPassword()));
                    mainPanel();
                    authPanel.getLoginTF().setText("");
                    authPanel.getPwdTF().setText("");
                    authPanel.getLoginRegisterTF().setText("");
                    authPanel.getPwdRegisterTF().setText("");
                    authPanel.getRepeatPwdTF().setText("");
                } else {
                    authPanel.getPwdRegisterTF().setText("");
                    authPanel.getRepeatPwdTF().setText("");
                    JOptionPane.showMessageDialog(frame, ResourceBundle.getBundle("messages").getString("err.pwd_not_match"),
                            ResourceBundle.getBundle("messages").getString("title.error"), JOptionPane.ERROR_MESSAGE);
                }
            } catch (UsernamePatternException usernamePatternException) {
                authPanel.getLoginRegisterTF().setText("");
                authPanel.getPwdRegisterTF().setText("");
                authPanel.getRepeatPwdTF().setText("");
                JOptionPane.showMessageDialog(frame, ResourceBundle.getBundle("messages").getString("err.username_pattern"),
                        ResourceBundle.getBundle("messages").getString("title.error"), JOptionPane.ERROR_MESSAGE);
            } catch (PasswordPatternException passwordPatternException) {
                authPanel.getPwdRegisterTF().setText("");
                authPanel.getRepeatPwdTF().setText("");
                JOptionPane.showMessageDialog(frame, ResourceBundle.getBundle("messages").getString("err.password_pattern"),
                        ResourceBundle.getBundle("messages").getString("title.error"), JOptionPane.ERROR_MESSAGE);
            } catch (AuthException authException) {
                authPanel.getLoginRegisterTF().setText("");
                authPanel.getPwdRegisterTF().setText("");
                authPanel.getRepeatPwdTF().setText("");
                JOptionPane.showMessageDialog(frame, ResourceBundle.getBundle("messages").getString("err.wrong_auth"),
                        ResourceBundle.getBundle("messages").getString("title.error"), JOptionPane.ERROR_MESSAGE);
            } catch (IOException ioException) {
                JOptionPane.showMessageDialog(frame, ResourceBundle.getBundle("messages").getString("err.no_connection"),
                        ResourceBundle.getBundle("messages").getString("title.error"), JOptionPane.ERROR_MESSAGE);
            }
        });

        mainPanel.getExitTableButton().addActionListener(e -> {
            try {
                if (authManager.getAuth() != null) authManager.disconnect();
            } catch (IOException ignored) {

            }
            System.exit(0);
        });

        mainPanel.getExitVisualizeButton().addActionListener(e -> {
            try {
                if (authManager.getAuth() != null) authManager.disconnect();
            } catch (IOException ignored) {

            }
            System.exit(0);
        });

        mainPanel.getChangeColor().addActionListener(e -> {
            mainPanel.getVisualize().changeColor(authManager.getAuth().getLogin());
        });

        mainPanel.getChangeUser().addActionListener(e -> {
            try {
                authPanel();
                authManager.disconnect();
            } catch (IOException ignored) {
            }
        });

        mainPanel.getRussianLocale().addActionListener(e -> setLocale(locales.get("ru")));

        mainPanel.getHungarianLocale().addActionListener(e -> setLocale(locales.get("hu")));

        mainPanel.getSerbianLocale().addActionListener(e -> setLocale(locales.get("sr")));

        mainPanel.getSpanishLocale().addActionListener(e -> setLocale(locales.get("es_EC")));

        mainPanel.getInfoCommand().addActionListener(e -> {
            try {
                updatingCollection = false;
                Response response = commandManager.executeCommand("info", null, null);
                collectionManager.setPersons(response.getPersonCollection());
                tableModel.fireTableDataChanged();
                mainPanel.getVisualize().update();
                JOptionPane.showMessageDialog(frame, response.getContent(),
                        ResourceBundle.getBundle("messages").getString("title.info"), JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException ioException) {
                JOptionPane.showMessageDialog(frame, ResourceBundle.getBundle("messages").getString("err.no_connection"),
                        ResourceBundle.getBundle("messages").getString("title.error"), JOptionPane.ERROR_MESSAGE);
            } finally {
                updatingCollection = true;
            }
        });

        mainPanel.getHelpCommand().addActionListener(e -> {
            try {
                updatingCollection = false;
                Response response = commandManager.executeCommand("help", null, null);
                collectionManager.setPersons(response.getPersonCollection());
                tableModel.fireTableDataChanged();
                mainPanel.getVisualize().update();
                JOptionPane.showMessageDialog(frame, response.getContent(),
                        ResourceBundle.getBundle("messages").getString("title.help"), JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException ioException) {
                JOptionPane.showMessageDialog(frame, ResourceBundle.getBundle("messages").getString("err.no_connection"),
                        ResourceBundle.getBundle("messages").getString("title.error"), JOptionPane.ERROR_MESSAGE);
            } finally {
                updatingCollection = true;
            }
        });

        mainPanel.getHistoryCommand().addActionListener(e -> {
            try {
                updatingCollection = false;
                Response response = commandManager.executeCommand("history", null, null);
                collectionManager.setPersons(response.getPersonCollection());
                tableModel.fireTableDataChanged();
                mainPanel.getVisualize().update();
                JOptionPane.showMessageDialog(frame, response.getContent(),
                        ResourceBundle.getBundle("messages").getString("title.history"), JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException ioException) {
                JOptionPane.showMessageDialog(frame, ResourceBundle.getBundle("messages").getString("err.no_connection"),
                        ResourceBundle.getBundle("messages").getString("title.error"), JOptionPane.ERROR_MESSAGE);
            } finally {
                updatingCollection = true;
            }
        });

        mainPanel.getMaxByEyeColorCommand().addActionListener(e -> {
            try {
                updatingCollection = false;
                Response response = commandManager.executeCommand("max_by_eye_color", null, null);
                collectionManager.setPersons(response.getPersonCollection());
                tableModel.fireTableDataChanged();
                mainPanel.getVisualize().update();
                JOptionPane.showMessageDialog(frame, response.getContent(),
                        ResourceBundle.getBundle("messages").getString("title.max_by_eye_color"), JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException ioException) {
                JOptionPane.showMessageDialog(frame, ResourceBundle.getBundle("messages").getString("err.no_connection"),
                        ResourceBundle.getBundle("messages").getString("title.error"), JOptionPane.ERROR_MESSAGE);
            } finally {
                updatingCollection = true;
            }
        });

        mainPanel.getClearCommand().addActionListener(e -> {
            try {
                updatingCollection = false;
                Response response = commandManager.executeCommand("clear", null, null);
                collectionManager.setPersons(response.getPersonCollection());
                tableModel.fireTableDataChanged();
                mainPanel.getVisualize().update();
                JOptionPane.showMessageDialog(frame, response.getContent(),
                        ResourceBundle.getBundle("messages").getString("title.command_executed"), JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException ioException) {
                JOptionPane.showMessageDialog(frame, ResourceBundle.getBundle("messages").getString("err.no_connection"),
                        ResourceBundle.getBundle("messages").getString("title.error"), JOptionPane.ERROR_MESSAGE);
            } finally {
                updatingCollection = true;
            }
        });

        mainPanel.getRemoveCommand().addActionListener(e -> {
            try {
                Integer id = (Integer) JOptionPane.showInputDialog(frame, ResourceBundle.getBundle("messages").getString("input.id"),
                        ResourceBundle.getBundle("messages").getString("title.remove"),
                        JOptionPane.QUESTION_MESSAGE,
                        IconValue.NULL_ICON,
                        collectionManager.getPersonStream()
                                .filter(p -> p.getOwner().equals(authManager.getAuth().getLogin()))
                                .map(Person::getId)
                                .toArray(),
                        null);
                if (id == null) return;
                updatingCollection = false;
                Response response = commandManager.executeCommand("remove_by_id", String.valueOf(id), null);
                collectionManager.setPersons(response.getPersonCollection());
                tableModel.fireTableDataChanged();
                mainPanel.getVisualize().update();
                JOptionPane.showMessageDialog(frame, response.getContent(),
                        ResourceBundle.getBundle("messages").getString("title.command_executed"), JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException ioException) {
                JOptionPane.showMessageDialog(frame, ResourceBundle.getBundle("messages").getString("err.no_connection"),
                        ResourceBundle.getBundle("messages").getString("title.error"), JOptionPane.ERROR_MESSAGE);
            }finally {
                updatingCollection = true;
            }
        });

        createPersonPanel.getCancelButton().addActionListener(e -> {
            dialog.dispose();
            createPersonPanel.clearInputs();
        });

        createPersonPanel.getOkButton().addActionListener(e -> {
            String errMsg = validatePerson();
            if (errMsg.equals("")) {
                try {
                    Response response = commandManager.executeCommand(
                            createPersonPanel.getCommand(), null, createPerson());
                    collectionManager.setPersons(response.getPersonCollection());
                    tableModel.fireTableDataChanged();
                    mainPanel.getVisualize().update();
                    dialog.dispose();
                    createPersonPanel.clearInputs();
                    JOptionPane.showMessageDialog(frame, response.getContent(),
                            ResourceBundle.getBundle("messages").getString("title.command_executed"), JOptionPane.INFORMATION_MESSAGE);
                } catch (IOException ioException) {
                    JOptionPane.showMessageDialog(frame, ResourceBundle.getBundle("messages").getString("err.no_connection"),
                            ResourceBundle.getBundle("messages").getString("title.error"), JOptionPane.ERROR_MESSAGE);
                } finally {
                    updatingCollection = true;
                    createPersonPanel.clearInputs();
                }
            }
        });

        mainPanel.getAddCommand().addActionListener(e -> {
            createPersonPanel.setCommand("add");
            createPersonPanel.setArg(null);
            createPersonDialog(ResourceBundle.getBundle("messages").getString("menu_item.add"));
        });

        mainPanel.getAddIfMaxCommand().addActionListener(e -> {
            createPersonPanel.setCommand("add_if_max");
            createPersonPanel.setArg(null);
            createPersonDialog(ResourceBundle.getBundle("messages").getString("menu_item.add_if_max"));
        });

        mainPanel.getAddIfMinCommand().addActionListener(e -> {
            createPersonPanel.setCommand("add_if_min");
            createPersonPanel.setArg(null);
            createPersonDialog(ResourceBundle.getBundle("messages").getString("menu_item.add_if_min"));
        });

        mainPanel.getUpdateCommand().addActionListener(e -> {
            createPersonPanel.setCommand("update");
            Integer id = (Integer) JOptionPane.showInputDialog(frame, ResourceBundle.getBundle("messages").getString("input.id"),
                    ResourceBundle.getBundle("messages").getString("title.update"),
                    JOptionPane.QUESTION_MESSAGE,
                    IconValue.NULL_ICON,
                    collectionManager.getPersonStream()
                            .filter(p -> p.getOwner().equals(authManager.getAuth().getLogin()))
                            .map(Person::getId)
                            .toArray(),
                    null);
            if (id == null) return;
            createPersonPanel.setArg(String.valueOf(id));
            Person oldPerson = collectionManager.getPersonStream().filter(p -> p.getId() == id).findAny().get();
            createPersonPanel.getNameTF().setText(oldPerson.getName());
            createPersonPanel.getHeightTF().setText(String.valueOf(oldPerson.getHeight()));
            createPersonPanel.getBirthdayPicker().setDate(Timestamp.valueOf(oldPerson.getBirthday()));
            createPersonPanel.getEyeColorComboBox().setSelectedItem(oldPerson.getEyeColor());
            createPersonPanel.getHairColorComboBox().setSelectedItem(oldPerson.getHairColor());
            createPersonPanel.getCoordinatesXTF().setText(String.valueOf(oldPerson.getCoordinates().getX()));
            createPersonPanel.getCoordinatesYTF().setText(String.valueOf(oldPerson.getCoordinates().getY()));
            createPersonPanel.getLocationXTF().setText(String.valueOf(oldPerson.getLocation().getX()));
            createPersonPanel.getLocationYTF().setText(String.valueOf(oldPerson.getLocation().getY()));
            createPersonPanel.getLocationNameTF().setText(oldPerson.getLocation().getName());
            createPersonDialog(ResourceBundle.getBundle("messages").getString("menu_item.update"));
        });

        mainPanel.getIdFilter().addActionListener(e -> {
            Integer id = (Integer) JOptionPane.showInputDialog(frame, ResourceBundle.getBundle("messages").getString("input.id"),
                    ResourceBundle.getBundle("messages").getString("title.filter"),
                    JOptionPane.QUESTION_MESSAGE,
                    IconValue.NULL_ICON,
                    collectionManager.getPersonStream()
                            .map(Person::getId)
                            .toArray(),
                    null);
            if (id == null) return;
            sorter.setRowFilter(RowFilter.numberFilter(RowFilter.ComparisonType.EQUAL, id, 0));
        });

        mainPanel.getNameFilter().addActionListener(e -> {
            String nameRegEx = JOptionPane.showInputDialog(frame,
                    ResourceBundle.getBundle("messages").getString("input.regex_name"),
                    ResourceBundle.getBundle("messages").getString("title.filter"));
            if (nameRegEx.length() == 0) {
                sorter.setRowFilter(null);
            } else {
                try {
                    sorter.setRowFilter(RowFilter.regexFilter(nameRegEx, 1));
                } catch (PatternSyntaxException pse) {
                    JOptionPane.showMessageDialog(frame,
                            ResourceBundle.getBundle("messages").getString("err.bad_regex"),
                            ResourceBundle.getBundle("messages").getString("title.error"), JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        mainPanel.getCoordinatesXFilter().addActionListener(e -> {
            try {
                Double coordinatesX = Double.parseDouble(JOptionPane.showInputDialog(frame,
                        ResourceBundle.getBundle("messages").getString("input.coordinates_x"),
                        ResourceBundle.getBundle("messages").getString("title.filter")));
                sorter.setRowFilter(RowFilter.numberFilter(RowFilter.ComparisonType.EQUAL, coordinatesX, 2));
            } catch (NumberFormatException numberFormatException){
                JOptionPane.showMessageDialog(frame,
                        ResourceBundle.getBundle("messages").getString("err.no_float"),
                        ResourceBundle.getBundle("messages").getString("title.error"), JOptionPane.ERROR_MESSAGE);
            }
        });

        mainPanel.getCoordinatesYFilter().addActionListener(e -> {
            try {
                Long coordinatesY = Long.parseLong(JOptionPane.showInputDialog(frame,
                        ResourceBundle.getBundle("messages").getString("input.coordinates_y"),
                        ResourceBundle.getBundle("messages").getString("title.filter")));
                sorter.setRowFilter(RowFilter.numberFilter(RowFilter.ComparisonType.EQUAL, coordinatesY, 3));
            } catch (NumberFormatException numberFormatException){
                JOptionPane.showMessageDialog(frame,
                        ResourceBundle.getBundle("messages").getString("err.no_integer"),
                        ResourceBundle.getBundle("messages").getString("title.error"), JOptionPane.ERROR_MESSAGE);
            }
        });

        mainPanel.getCreationDateFilter().addActionListener(e -> {
            try {
                LocalDate creationDate = LocalDate.parse(JOptionPane.showInputDialog(frame,
                        ResourceBundle.getBundle("messages").getString("input.creation_date"),
                        ResourceBundle.getBundle("messages").getString("title.filter")));
                sorter.setRowFilter(RowFilter.regexFilter(creationDate.toString(), 4));
            } catch (DateTimeParseException dateTimeParseException){
                JOptionPane.showMessageDialog(frame,
                        ResourceBundle.getBundle("messages").getString("err.no_date"),
                        ResourceBundle.getBundle("messages").getString("title.error"), JOptionPane.ERROR_MESSAGE);
            }
        });

        mainPanel.getHeightFilter().addActionListener(e -> {
            try {
                Long height = Long.parseLong(JOptionPane.showInputDialog(frame,
                        ResourceBundle.getBundle("messages").getString("input.height"),
                        ResourceBundle.getBundle("messages").getString("title.filter")));
                sorter.setRowFilter(RowFilter.numberFilter(RowFilter.ComparisonType.EQUAL, height, 5));
            } catch (NumberFormatException numberFormatException){
                JOptionPane.showMessageDialog(frame,
                        ResourceBundle.getBundle("messages").getString("err.no_integer"),
                        ResourceBundle.getBundle("messages").getString("title.error"), JOptionPane.ERROR_MESSAGE);
            }
        });

        mainPanel.getBirthdayFilter().addActionListener(e -> {
            try {
                LocalDate birthday = LocalDate.parse(JOptionPane.showInputDialog(frame,
                        ResourceBundle.getBundle("messages").getString("input.birthday"),
                        ResourceBundle.getBundle("messages").getString("title.filter")));
                sorter.setRowFilter(RowFilter.regexFilter(birthday.toString(), 6));
            } catch (DateTimeParseException dateTimeParseException){
                JOptionPane.showMessageDialog(frame,
                        ResourceBundle.getBundle("messages").getString("err.no_date"),
                        ResourceBundle.getBundle("messages").getString("title.error"), JOptionPane.ERROR_MESSAGE);
            }
        });

        mainPanel.getEyeColorFilter().addActionListener(e -> {
            EyeColor eyeColor = (EyeColor) JOptionPane.showInputDialog(frame,
                    ResourceBundle.getBundle("messages").getString("input.eye_color"),
                    ResourceBundle.getBundle("messages").getString("title.filter"),
                    JOptionPane.QUESTION_MESSAGE,
                    IconValue.NULL_ICON,
                    eyeColorConstants, EyeColor.BLACK);
            sorter.setRowFilter(RowFilter.regexFilter(eyeColor.toString(), 7));
        });

        mainPanel.getHairColorFilter().addActionListener(e -> {
            HairColor hairColor = (HairColor) JOptionPane.showInputDialog(frame,
                    ResourceBundle.getBundle("messages").getString("input.hair_color"),
                    ResourceBundle.getBundle("messages").getString("title.filter"),
                    JOptionPane.QUESTION_MESSAGE,
                    IconValue.NULL_ICON,
                    hairColorConstants, EyeColor.BLACK);
            sorter.setRowFilter(RowFilter.regexFilter(hairColor.toString(), 8));
        });

        mainPanel.getLocationXFilter().addActionListener(e -> {
            try {
                Float locationX = Float.parseFloat(JOptionPane.showInputDialog(frame,
                        ResourceBundle.getBundle("messages").getString("input.location_x"),
                        ResourceBundle.getBundle("messages").getString("title.filter")));
                sorter.setRowFilter(RowFilter.numberFilter(RowFilter.ComparisonType.EQUAL, locationX, 9));
            } catch (NumberFormatException numberFormatException){
                JOptionPane.showMessageDialog(frame,
                        ResourceBundle.getBundle("messages").getString("err.no_float"),
                        ResourceBundle.getBundle("messages").getString("title.error"), JOptionPane.ERROR_MESSAGE);
            }
        });

        mainPanel.getLocationYFilter().addActionListener(e -> {
            try {
                Long locationY = Long.parseLong(JOptionPane.showInputDialog(frame,
                        ResourceBundle.getBundle("messages").getString("input.location_y"),
                        ResourceBundle.getBundle("messages").getString("title.filter")));
                sorter.setRowFilter(RowFilter.numberFilter(RowFilter.ComparisonType.EQUAL, locationY, 10));
            } catch (NumberFormatException numberFormatException){
                JOptionPane.showMessageDialog(frame,
                        ResourceBundle.getBundle("messages").getString("err.no_integer"),
                        ResourceBundle.getBundle("messages").getString("title.error"), JOptionPane.ERROR_MESSAGE);
            }
        });

        mainPanel.getLocationNameFilter().addActionListener(e -> {
            String locationName = JOptionPane.showInputDialog(frame,
                    ResourceBundle.getBundle("messages").getString("input.location_name"),
                    ResourceBundle.getBundle("messages").getString("title.filter"));
            if (locationName.length() == 0) {
                sorter.setRowFilter(null);
            } else {
                try {
                    sorter.setRowFilter(RowFilter.regexFilter(locationName, 11));
                } catch (PatternSyntaxException pse) {
                    JOptionPane.showMessageDialog(frame,
                            ResourceBundle.getBundle("messages").getString("err.bad_regex"),
                            ResourceBundle.getBundle("messages").getString("title.error"), JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        mainPanel.getOwnerFilter().addActionListener(e -> {
            String owner = JOptionPane.showInputDialog(frame,
                    ResourceBundle.getBundle("messages").getString("input.owner"),
                    ResourceBundle.getBundle("messages").getString("title.filter"));
            if (owner.length() == 0) {
                sorter.setRowFilter(null);
            } else {
                try {
                    sorter.setRowFilter(RowFilter.regexFilter(owner, 12));
                } catch (PatternSyntaxException pse) {
                    JOptionPane.showMessageDialog(frame,
                            ResourceBundle.getBundle("messages").getString("err.bad_regex"),
                            ResourceBundle.getBundle("messages").getString("title.error"), JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private String validatePerson(){
        PersonValidator personValidator = new PersonValidatorImpl();
        CoordinatesValidator coordinatesValidator = new CoordinatesValidatorImpl();
        LocationValidator locationValidator = new LocationValidatorImpl();
        StringBuilder errorMsg = new StringBuilder();
        if (!personValidator.validateName(createPersonPanel.getNameTF().getText())){
            errorMsg.append(ResourceBundle.getBundle("messages").getString("err.invalid_name"))
                    .append("\n");
            createPersonPanel.getNameTF().setText("");
        }
        try {
            if (!coordinatesValidator.validateCoordinatesX(Double.parseDouble(createPersonPanel.getCoordinatesXTF().getText()))) {
                errorMsg.append(ResourceBundle.getBundle("messages").getString("err.invalid_coordinates_x"))
                        .append("\n");
                createPersonPanel.getCoordinatesXTF().setText("");
            }
        } catch(NumberFormatException e){
            errorMsg.append(ResourceBundle.getBundle("messages").getString("err.invalid_coordinates_x"))
                    .append("\n");
            createPersonPanel.getCoordinatesXTF().setText("");
        }
        try {
            if (!coordinatesValidator.validateCoordinatesY(Long.parseLong(createPersonPanel.getCoordinatesYTF().getText()))) {
                errorMsg.append(ResourceBundle.getBundle("messages").getString("err.invalid_coordinates_y"))
                        .append("\n");
                createPersonPanel.getCoordinatesYTF().setText("");
            }
        } catch(NumberFormatException e){
            errorMsg.append(ResourceBundle.getBundle("messages").getString("err.invalid_coordinates_y"))
                    .append("\n");
            createPersonPanel.getCoordinatesYTF().setText("");
        }
        try {
            if (!personValidator.validateHeight(Long.parseLong(createPersonPanel.getHeightTF().getText()))) {
                errorMsg.append(ResourceBundle.getBundle("messages").getString("err.invalid_height"))
                        .append("\n");
                createPersonPanel.getHeightTF().setText("");
            }
        } catch(NumberFormatException e){
            errorMsg.append(ResourceBundle.getBundle("messages").getString("err.invalid_height"))
                    .append("\n");
            createPersonPanel.getHeightTF().setText("");
        }
        if (!personValidator.validateBirthday(new Timestamp(
                createPersonPanel.getBirthdayPicker().getDate().getTime()).toLocalDateTime())) {
            errorMsg.append(ResourceBundle.getBundle("messages").getString("err.invalid_birthday"))
                    .append("\n");
            createPersonPanel.getBirthdayPicker().setDate(null);
        }
        try {
            if (!locationValidator.validateLocationX(Float.parseFloat(createPersonPanel.getLocationXTF().getText()))) {
                errorMsg.append(ResourceBundle.getBundle("messages").getString("err.invalid_location_x"))
                        .append("\n");
                createPersonPanel.getLocationXTF().setText("");
            }
        } catch (NumberFormatException e){
            errorMsg.append(ResourceBundle.getBundle("messages").getString("err.invalid_location_x"))
                    .append("\n");
            createPersonPanel.getLocationXTF().setText("");
        }
        try {
            if (!locationValidator.validateLocationY(Long.parseLong(createPersonPanel.getLocationYTF().getText()))) {
                errorMsg.append(ResourceBundle.getBundle("messages").getString("err.invalid_location_y"))
                        .append("\n");
                createPersonPanel.getLocationYTF().setText("");
            }
        } catch (NumberFormatException e){
            errorMsg.append(ResourceBundle.getBundle("messages").getString("err.invalid_location_y"))
                    .append("\n");
            createPersonPanel.getLocationYTF().setText("");
        }
        if (!locationValidator.validateLocationName(createPersonPanel.getLocationNameTF().getText())) {
            errorMsg.append(ResourceBundle.getBundle("messages").getString("err.invalid_location_name"))
                    .append("\n");
            createPersonPanel.getLocationNameTF().setText("");
        }
        return errorMsg.toString();
    }

    private RawPerson createPerson(){
        RawPersonBuilder builder = new RawPersonBuilderImpl();
        builder.setName(createPersonPanel.getNameTF().getText());
        builder.setCoordinatesX(Double.parseDouble(createPersonPanel.getCoordinatesXTF().getText()));
        builder.setCoordinatesY(Long.parseLong(createPersonPanel.getCoordinatesYTF().getText()));
        builder.setHeight(Long.parseLong(createPersonPanel.getHeightTF().getText()));
        builder.setBirthday(new Timestamp(createPersonPanel.getBirthdayPicker().getDate().getTime()).toLocalDateTime());
        builder.setEyeColor((EyeColor) createPersonPanel.getEyeColorComboBox().getSelectedItem());
        builder.setHairColor((HairColor) createPersonPanel.getHairColorComboBox().getSelectedItem());
        builder.setLocationX(Float.parseFloat(createPersonPanel.getLocationXTF().getText()));
        builder.setLocationY(Long.parseLong(createPersonPanel.getLocationYTF().getText()));
        builder.setLocationName(createPersonPanel.getLocationNameTF().getText());
        return builder.getRawPerson();
    }

    public void start(){
        updatingCollectionThread = new Thread(() -> {
            while(true) {
                try {
                    if (authManager.getAuth() != null && updatingCollection) {
                        collectionSynchronizer.synchronizeCollection(collectionManager);
                        tableModel.fireTableDataChanged();
                        mainPanel.getVisualize().update();
                    }
                    Thread.sleep(2000);
                } catch (InterruptedException | IOException ignored) {

                }
            }
        });
        updatingCollectionThread.start();
        authPanel();
        frame.setVisible(true);
    }

    private void setLocale(Locale locale){
        Locale.setDefault(locale);
        ResourceBundle.clearCache();
        mainPanel.setTexts();
        authPanel.setTexts();
        createPersonPanel.setTexts();
    }
}
