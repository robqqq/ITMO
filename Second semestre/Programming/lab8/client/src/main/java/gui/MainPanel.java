package gui;

import authManager.ClientAuthManager;
import collectionManager.ClientCollectionManager;

import javax.swing.*;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.util.ResourceBundle;

public class MainPanel extends JPanel{
    private JMenuBar menuBar;
    private JMenu userMenu;
    private JMenuItem changeUser;
    private JMenu localeMenu;
    private JMenuItem russianLocale;
    private JMenuItem serbianLocale;
    private JMenuItem hungarianLocale;
    private JMenuItem spanishLocale;
    private JMenu commandsMenu;
    private JMenuItem helpCommand;
    private JMenuItem infoCommand;
    private JMenu addCommandsMenu;
    private JMenuItem addCommand;
    private JMenuItem addIfMaxCommand;
    private JMenuItem addIfMinCommand;
    private JMenuItem updateCommand;
    private JMenuItem removeCommand;
    private JMenuItem clearCommand;
    private JMenuItem historyCommand;
    private JMenuItem maxByEyeColorCommand;
    private JMenuItem executeScriptCommand;
    private JMenu filter;
    private JMenuItem idFilter;
    private JMenuItem nameFilter;
    private JMenuItem coordinatesXFilter;
    private JMenuItem coordinatesYFilter;
    private JMenuItem creationDateFilter;
    private JMenuItem heightFilter;
    private JMenuItem birthdayFilter;
    private JMenuItem eyeColorFilter;
    private JMenuItem hairColorFilter;
    private JMenuItem locationXFilter;
    private JMenuItem locationYFilter;
    private JMenuItem locationNameFilter;
    private JMenuItem ownerFilter;
    private JTabbedPane tabbedPane;
    private JButton exitTableButton;
    private JButton exitVisualizeButton;
    private JTable table;
    private Visualize visualize;
    private PersonTableModel tableModel;
    private ClientCollectionManager collectionManager;
    private ClientAuthManager authManager;

    public MainPanel(PersonTableModel tableModel, ClientCollectionManager collectionManager, ClientAuthManager authManager) {
        super();
        this.authManager = authManager;
        this.tableModel = tableModel;
        this.collectionManager = collectionManager;
        initComponents();
    }

    private void initComponents() {
        menuBar = new JMenuBar();
        userMenu = new JMenu();
        changeUser = new JMenuItem();
        localeMenu = new JMenu();
        russianLocale = new JMenuItem();
        serbianLocale = new JMenuItem();
        hungarianLocale = new JMenuItem();
        spanishLocale = new JMenuItem();
        commandsMenu = new JMenu();
        helpCommand = new JMenuItem();
        infoCommand = new JMenuItem();
        addCommandsMenu = new JMenu();
        addCommand = new JMenuItem();
        addIfMaxCommand = new JMenuItem();
        addIfMinCommand = new JMenuItem();
        updateCommand = new JMenuItem();
        removeCommand = new JMenuItem();
        clearCommand = new JMenuItem();
        historyCommand = new JMenuItem();
        maxByEyeColorCommand = new JMenuItem();
        executeScriptCommand = new JMenuItem();
        filter = new JMenu();
        idFilter = new JMenuItem();
        nameFilter = new JMenuItem();
        coordinatesXFilter = new JMenuItem();
        coordinatesYFilter = new JMenuItem();
        creationDateFilter = new JMenuItem();
        heightFilter = new JMenuItem();
        birthdayFilter = new JMenuItem();
        eyeColorFilter = new JMenuItem();
        hairColorFilter = new JMenuItem();
        locationXFilter = new JMenuItem();
        locationYFilter = new JMenuItem();
        locationNameFilter = new JMenuItem();
        ownerFilter = new JMenuItem();
        tabbedPane = new JTabbedPane();
        JPanel tableTab = new JPanel();
        table = new JTable(tableModel);
        visualize = new Visualize(collectionManager, authManager);
        JPanel exitTablePanel = new JPanel();
        exitTableButton = new JButton();
        JPanel exitVisualizePanel = new JPanel();
        exitVisualizeButton = new JButton();
        JScrollPane tableScrollPane = new JScrollPane();
        JPanel visualizeTab = new JPanel();

        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        //======== menuBar ========
        {

            //======== userMenu ========
            {
                userMenu.setText(ResourceBundle.getBundle("messages").getString("menu.login"));

                //---- changeUser ----
                changeUser.setText(ResourceBundle.getBundle("messages").getString("menu_item.change_user"));
                userMenu.add(changeUser);
            }
            menuBar.add(userMenu);

            //======== localeMenu ========
            {
                localeMenu.setText(ResourceBundle.getBundle("messages").getString("menu.locale"));

                //---- russianLocale ----
                russianLocale.setText(ResourceBundle.getBundle("messages").getString("menu_item.russian"));
                localeMenu.add(russianLocale);

                //---- serbianLocale ----
                serbianLocale.setText(ResourceBundle.getBundle("messages").getString("menu_item.serbian"));
                localeMenu.add(serbianLocale);

                //---- hungarianLocale ----
                hungarianLocale.setText(ResourceBundle.getBundle("messages").getString("menu_item.hungarian"));
                localeMenu.add(hungarianLocale);

                //---- spanishLocale ----
                spanishLocale.setText(ResourceBundle.getBundle("messages").getString("menu_item.spanish"));
                localeMenu.add(spanishLocale);
            }
            menuBar.add(localeMenu);

            //======== commandsMenu ========
            {
                commandsMenu.setText(ResourceBundle.getBundle("messages").getString("menu.commands"));

                //---- helpCommand ----
                helpCommand.setText(ResourceBundle.getBundle("messages").getString("menu_item.help"));
                commandsMenu.add(helpCommand);

                //---- infoCommand ----
                infoCommand.setText(ResourceBundle.getBundle("messages").getString("menu_item.info"));
                commandsMenu.add(infoCommand);

                //======== addCommandsMenu ========
                {
                    addCommandsMenu.setText(ResourceBundle.getBundle("messages").getString("menu.add"));

                    //---- addCommand ----
                    addCommand.setText(ResourceBundle.getBundle("messages").getString("menu_item.add"));
                    addCommandsMenu.add(addCommand);

                    //---- addIfMaxCommand ----
                    addIfMaxCommand.setText(ResourceBundle.getBundle("messages").getString("menu_item.add_if_max"));
                    addCommandsMenu.add(addIfMaxCommand);

                    //---- addIfMinCommand ----
                    addIfMinCommand.setText(ResourceBundle.getBundle("messages").getString("menu_item.add_if_min"));
                    addCommandsMenu.add(addIfMinCommand);
                }
                commandsMenu.add(addCommandsMenu);

                //---- updateCommand ----
                updateCommand.setText(ResourceBundle.getBundle("messages").getString("menu_item.update"));
                commandsMenu.add(updateCommand);

                //---- removeCommand ----
                removeCommand.setText(ResourceBundle.getBundle("messages").getString("menu_item.remove"));
                commandsMenu.add(removeCommand);

                //---- clearCommand ----
                clearCommand.setText(ResourceBundle.getBundle("messages").getString("menu_item.clear"));
                commandsMenu.add(clearCommand);

                //---- historyCommand ----
                historyCommand.setText(ResourceBundle.getBundle("messages").getString("menu_item.history"));
                commandsMenu.add(historyCommand);

                //---- maxByEyeColorCommand ----
                maxByEyeColorCommand.setText(ResourceBundle.getBundle("messages").getString("menu_item.max_by_eye_color"));
                commandsMenu.add(maxByEyeColorCommand);

                //---- executeScriptCommand ----
                executeScriptCommand.setText(ResourceBundle.getBundle("messages").getString("menu_item.execute_script"));
                commandsMenu.add(executeScriptCommand);
            }
            menuBar.add(commandsMenu);

            //======== filter ========
            {
                filter.setText(ResourceBundle.getBundle("messages").getString("menu.filter"));

                //---- idFilter ----
                idFilter.setText(ResourceBundle.getBundle("messages").getString("menu_item.id_filter"));
                filter.add(idFilter);

                //---- nameFilter ----
                nameFilter.setText(ResourceBundle.getBundle("messages").getString("menu_item.name_filter"));
                filter.add(nameFilter);

                //---- coordinatesXFilter ----
                coordinatesXFilter.setText(ResourceBundle.getBundle("messages").getString("menu_item.coordinates_x_filter"));
                filter.add(coordinatesXFilter);

                //---- coordinatesYFilter ----
                coordinatesYFilter.setText(ResourceBundle.getBundle("messages").getString("menu_item.coordinates_y_filter"));
                filter.add(coordinatesYFilter);

                //---- creationDateFilter ----
                creationDateFilter.setText(ResourceBundle.getBundle("messages").getString("menu_item.creation_date_filter"));
                filter.add(creationDateFilter);

                //---- heightFilter ----
                heightFilter.setText(ResourceBundle.getBundle("messages").getString("menu_item.height_filter"));
                filter.add(heightFilter);

                //---- birthdayFilter ----
                birthdayFilter.setText(ResourceBundle.getBundle("messages").getString("menu_item.birthday_filter"));
                filter.add(birthdayFilter);

                //---- eyeColorFilter ----
                eyeColorFilter.setText(ResourceBundle.getBundle("messages").getString("menu_item.eye_color_filter"));
                filter.add(eyeColorFilter);

                //---- hairColorFilter ----
                hairColorFilter.setText(ResourceBundle.getBundle("messages").getString("menu_item.hair_color_filter"));
                filter.add(hairColorFilter);

                //---- locationXFilter ----
                locationXFilter.setText(ResourceBundle.getBundle("messages").getString("menu_item.location_x_filter"));
                filter.add(locationXFilter);

                //---- locationYFilter ----
                locationYFilter.setText(ResourceBundle.getBundle("messages").getString("menu_item.location_y_filter"));
                filter.add(locationYFilter);

                //---- locationNameFilter ----
                locationNameFilter.setText(ResourceBundle.getBundle("messages").getString("menu_item.location_name_filter"));
                filter.add(locationNameFilter);

                //---- ownerFilter ----
                ownerFilter.setText(ResourceBundle.getBundle("messages").getString("menu_item.owner_filter"));
                filter.add(ownerFilter);
            }
            menuBar.add(filter);
        }

        //======== tabbedPane ========
        {

            //======== tableTab ========
            {
                tableTab.setBorder(new javax.swing.border.EmptyBorder(6, 6, 6, 6));
                tableTab.setLayout(new BorderLayout(6, 6));

                //======== exitTablePanel ========
                {
                    exitTablePanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

                    //---- exitTableButton ----
                    exitTableButton.setText(ResourceBundle.getBundle("messages").getString("button.exit"));
                    exitTablePanel.add(exitTableButton);
                }
                tableTab.add(exitTablePanel, BorderLayout.SOUTH);

                //======== tableScrollPane ========
                {
                    tableScrollPane.setViewportView(table);
                }
                tableTab.add(tableScrollPane, BorderLayout.CENTER);
            }
            tabbedPane.addTab(ResourceBundle.getBundle("messages").getString("tab.table"), tableTab);

            //======== visualizeTab ========
            {
                visualizeTab.setLayout(new BorderLayout(6, 6));
                visualizeTab.setBorder(new javax.swing.border.EmptyBorder(6, 6, 6, 6));

                //======== exitVisualizePanel ========
                {
                    exitVisualizePanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

                    //---- exitTableButton ----
                    exitVisualizeButton.setText(ResourceBundle.getBundle("messages").getString("button.exit"));
                    exitVisualizePanel.add(exitVisualizeButton);
                }
                visualizeTab.add(exitVisualizePanel, BorderLayout.SOUTH);

                //======== visualizeScrollPane ========
//                {
//                    visualizeScrollPane = new JScrollPane(visualize);
//                }
                visualizeTab.add(visualize, BorderLayout.CENTER);

            }
            tabbedPane.addTab(ResourceBundle.getBundle("messages").getString("tab.visualize"), visualizeTab);
        }
        add(tabbedPane);
    }

    public JMenuBar getMenuBar() {
        return menuBar;
    }

    public JMenu getUserMenu() {
        return userMenu;
    }

    public JMenuItem getChangeUser() {
        return changeUser;
    }

    public JMenuItem getRussianLocale() {
        return russianLocale;
    }

    public JMenuItem getSerbianLocale() {
        return serbianLocale;
    }

    public JMenuItem getHungarianLocale() {
        return hungarianLocale;
    }

    public JMenuItem getSpanishLocale() {
        return spanishLocale;
    }

    public JMenuItem getHelpCommand() {
        return helpCommand;
    }

    public JMenuItem getInfoCommand() {
        return infoCommand;
    }

    public JMenuItem getAddCommand() {
        return addCommand;
    }

    public JMenuItem getAddIfMaxCommand() {
        return addIfMaxCommand;
    }

    public JMenuItem getAddIfMinCommand() {
        return addIfMinCommand;
    }

    public JMenuItem getUpdateCommand() {
        return updateCommand;
    }

    public JMenuItem getRemoveCommand() {
        return removeCommand;
    }

    public JMenuItem getClearCommand() {
        return clearCommand;
    }

    public JMenuItem getHistoryCommand() {
        return historyCommand;
    }

    public JMenuItem getMaxByEyeColorCommand() {
        return maxByEyeColorCommand;
    }

    public JMenuItem getExecuteScriptCommand() {
        return executeScriptCommand;
    }

    public JMenuItem getIdFilter() {
        return idFilter;
    }

    public JMenuItem getCoordinatesXFilter() {
        return coordinatesXFilter;
    }

    public JMenuItem getCoordinatesYFilter() {
        return coordinatesYFilter;
    }

    public JMenuItem getCreationDateFilter() {
        return creationDateFilter;
    }

    public JMenuItem getHeightFilter() {
        return heightFilter;
    }

    public JMenuItem getBirthdayFilter() {
        return birthdayFilter;
    }

    public JMenuItem getEyeColorFilter() {
        return eyeColorFilter;
    }

    public JMenuItem getHairColorFilter() {
        return hairColorFilter;
    }

    public JMenuItem getNameFilter() {
        return nameFilter;
    }

    public JMenuItem getLocationXFilter() {
        return locationXFilter;
    }

    public JMenuItem getLocationYFilter() {
        return locationYFilter;
    }

    public JMenuItem getLocationNameFilter() {
        return locationNameFilter;
    }

    public JMenuItem getOwnerFilter() {
        return ownerFilter;
    }

    public JButton getExitTableButton() {
        return exitTableButton;
    }

    public JButton getExitVisualizeButton() {
        return exitVisualizeButton;
    }

    public JTable getTable() {
        return table;
    }

    public JTabbedPane getTabbedPane() {
        return tabbedPane;
    }

    public Visualize getVisualize() {
        return visualize;
    }

    public void setTexts(){
        localeMenu.setText(ResourceBundle.getBundle("messages").getString("menu.locale"));
        commandsMenu.setText(ResourceBundle.getBundle("messages").getString("menu.commands"));
        addCommandsMenu.setText(ResourceBundle.getBundle("messages").getString("menu.add"));
        filter.setText(ResourceBundle.getBundle("messages").getString("menu.filter"));
        changeUser.setText(ResourceBundle.getBundle("messages").getString("menu_item.change_user"));
        russianLocale.setText(ResourceBundle.getBundle("messages").getString("menu_item.russian"));
        hungarianLocale.setText(ResourceBundle.getBundle("messages").getString("menu_item.hungarian"));
        serbianLocale.setText(ResourceBundle.getBundle("messages").getString("menu_item.serbian"));
        spanishLocale.setText(ResourceBundle.getBundle("messages").getString("menu_item.spanish"));
        helpCommand.setText(ResourceBundle.getBundle("messages").getString("menu_item.help"));
        infoCommand.setText(ResourceBundle.getBundle("messages").getString("menu_item.info"));
        addCommand.setText(ResourceBundle.getBundle("messages").getString("menu_item.add"));
        addIfMaxCommand.setText(ResourceBundle.getBundle("messages").getString("menu_item.add_if_max"));
        addIfMinCommand.setText(ResourceBundle.getBundle("messages").getString("menu_item.add_if_min"));
        updateCommand.setText(ResourceBundle.getBundle("messages").getString("menu_item.update"));
        removeCommand.setText(ResourceBundle.getBundle("messages").getString("menu_item.remove"));
        clearCommand.setText(ResourceBundle.getBundle("messages").getString("menu_item.clear"));
        historyCommand.setText(ResourceBundle.getBundle("messages").getString("menu_item.history"));
        maxByEyeColorCommand.setText(ResourceBundle.getBundle("messages").getString("menu_item.max_by_eye_color"));
        executeScriptCommand.setText(ResourceBundle.getBundle("messages").getString("menu_item.execute_script"));
        idFilter.setText(ResourceBundle.getBundle("messages").getString("menu_item.id_filter"));
        nameFilter.setText(ResourceBundle.getBundle("messages").getString("menu_item.name_filter"));
        coordinatesXFilter.setText(ResourceBundle.getBundle("messages").getString("menu_item.coordinates_x_filter"));
        coordinatesYFilter.setText(ResourceBundle.getBundle("messages").getString("menu_item.coordinates_y_filter"));
        creationDateFilter.setText(ResourceBundle.getBundle("messages").getString("menu_item.creation_date_filter"));
        heightFilter.setText(ResourceBundle.getBundle("messages").getString("menu_item.height_filter"));
        birthdayFilter.setText(ResourceBundle.getBundle("messages").getString("menu_item.birthday_filter"));
        eyeColorFilter.setText(ResourceBundle.getBundle("messages").getString("menu_item.eye_color_filter"));
        hairColorFilter.setText(ResourceBundle.getBundle("messages").getString("menu_item.hair_color_filter"));
        locationXFilter.setText(ResourceBundle.getBundle("messages").getString("menu_item.location_x_filter"));
        locationYFilter.setText(ResourceBundle.getBundle("messages").getString("menu_item.location_y_filter"));
        locationNameFilter.setText(ResourceBundle.getBundle("messages").getString("menu_item.location_name_filter"));
        ownerFilter.setText(ResourceBundle.getBundle("messages").getString("menu_item.owner_filter"));
        tabbedPane.setTitleAt(0, ResourceBundle.getBundle("messages").getString("tab.table"));
        tabbedPane.setTitleAt(1, ResourceBundle.getBundle("messages").getString("tab.visualize"));
        exitTableButton.setText(ResourceBundle.getBundle("messages").getString("button.exit"));
        exitVisualizeButton.setText(ResourceBundle.getBundle("messages").getString("button.exit"));
        tableModel.updateColumnHeaders();
        tableModel.fireTableStructureChanged();
    }
}
