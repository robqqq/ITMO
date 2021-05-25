package gui;

import org.jdesktop.swingx.HorizontalLayout;
import org.jdesktop.swingx.VerticalLayout;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ResourceBundle;

public class AuthPanel extends JPanel {
    private JTabbedPane tabbedPane;
    private JLabel loginLabel;
    private JTextField loginTF;
    private JLabel pwdLabel;
    private JPasswordField pwdTF;
    private JButton loginButton;
    private JButton cancelButton;
    private JButton registerButton;
    private JButton cancelRegisterButton;
    private JLabel loginRegisterLabel;
    private JTextField loginRegisterTF;
    private JLabel pwdRegisterLabel;
    private JPasswordField pwdRegisterTF;
    private JLabel repeatPwdLabel;
    private JPasswordField repeatPwdTF;

    public AuthPanel() {
        super();
        initComponents();
    }

    private void initComponents() {
        tabbedPane = new JTabbedPane();
        JPanel loginTab = new JPanel();
        JPanel inputPanel = new JPanel();
        JPanel loginPanel = new JPanel();
        loginLabel = new JLabel();
        loginTF = new JTextField();
        JPanel pwdPanel = new JPanel();
        pwdLabel = new JLabel();
        pwdTF = new JPasswordField();
        JPanel buttonPanel = new JPanel();
        loginButton = new JButton();
        cancelButton = new JButton();
        JPanel registerTab = new JPanel();
        JPanel buttonRegisterPanel = new JPanel();
        registerButton = new JButton();
        cancelRegisterButton = new JButton();
        JPanel inputRegisterPanel = new JPanel();
        JPanel loginRegisterPanel = new JPanel();
        loginRegisterLabel = new JLabel();
        loginRegisterTF = new JTextField();
        JPanel pwdRegisterPanel = new JPanel();
        pwdRegisterLabel = new JLabel();
        pwdRegisterTF = new JPasswordField();
        JPanel repeatPwdPanel = new JPanel();
        repeatPwdLabel = new JLabel();
        repeatPwdTF = new JPasswordField();
        {
            setMinimumSize(new Dimension(320, 240));
            setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

            //======== tabbedPane ========
            {

                //======== loginTab ========
                {
                    loginTab.setBorder(new EmptyBorder(6, 6, 6, 6));
                    loginTab.setMinimumSize(new Dimension(0, 0));
                    loginTab.setLayout(new BorderLayout());

                    //======== inputPanel ========
                    {
                        inputPanel.setLayout(new VerticalLayout(6));

                        //======== loginPanel ========
                        {
                            loginPanel.setLayout(new HorizontalLayout(12));

                            //---- loginLabel ----
                            loginLabel.setText(ResourceBundle.getBundle("messages").getString("label.login"));
                            loginLabel.setLabelFor(loginTF);
                            loginLabel.setMaximumSize(new Dimension(60, 16));
                            loginLabel.setMinimumSize(new Dimension(60, 16));
                            loginLabel.setPreferredSize(new Dimension(60, 16));
                            loginPanel.add(loginLabel);

                            //---- loginTF ----
                            loginTF.setMinimumSize(new Dimension(210, 30));
                            loginTF.setPreferredSize(new Dimension(210, 30));
                            loginPanel.add(loginTF);
                        }
                        inputPanel.add(loginPanel);

                        //======== pwdPanel ========
                        {
                            pwdPanel.setLayout(new HorizontalLayout(12));

                            //---- pwdLabel ----
                            pwdLabel.setText(ResourceBundle.getBundle("messages").getString("label.password"));
                            pwdLabel.setLabelFor(pwdTF);
                            pwdLabel.setMaximumSize(new Dimension(60, 16));
                            pwdLabel.setMinimumSize(new Dimension(60, 16));
                            pwdLabel.setPreferredSize(new Dimension(60, 16));
                            pwdPanel.add(pwdLabel);

                            //---- pwdTF ----
                            pwdTF.setMinimumSize(new Dimension(210, 30));
                            pwdTF.setPreferredSize(new Dimension(210, 30));
                            pwdPanel.add(pwdTF);
                        }
                        inputPanel.add(pwdPanel);
                    }
                    loginTab.add(inputPanel, BorderLayout.CENTER);

                    //======== buttonPanel ========
                    {
                        buttonPanel.setLayout(new FlowLayout());

                        //---- loginButton ----
                        loginButton.setText(ResourceBundle.getBundle("messages").getString("button.login"));
                        buttonPanel.add(loginButton);
//

                        //---- cancelButton ----
                        cancelButton.setText(ResourceBundle.getBundle("messages").getString("button.cancel"));
                        buttonPanel.add(cancelButton);
                    }
                    loginTab.add(buttonPanel, BorderLayout.SOUTH);
                }
                tabbedPane.addTab(ResourceBundle.getBundle("messages").getString("tab.login"), loginTab);

                //======== registerTab ========
                {
                    registerTab.setBorder(new EmptyBorder(6, 6, 6, 6));
                    registerTab.setMinimumSize(new Dimension(0, 0));
                    registerTab.setLayout(new BorderLayout());

                    //======== buttonRegisterPanel ========
                    {
                        buttonRegisterPanel.setLayout(new FlowLayout());

                        //---- registerButton ----
                        registerButton.setText(ResourceBundle.getBundle("messages").getString("button.register"));
                        buttonRegisterPanel.add(registerButton);

                        //---- cancelRegisterButton ----
                        cancelRegisterButton.setText(ResourceBundle.getBundle("messages").getString("button.exit"));
                        buttonRegisterPanel.add(cancelRegisterButton);
                    }
                    registerTab.add(buttonRegisterPanel, BorderLayout.SOUTH);

                    //======== inputRegisterPanel ========
                    {
                        inputRegisterPanel.setLayout(new VerticalLayout(6));

                        //======== loginRegisterPanel ========
                        {
                            loginRegisterPanel.setLayout(new HorizontalLayout(12));

                            //---- loginRegisterLabel ----
                            loginRegisterLabel.setText(ResourceBundle.getBundle("messages").getString("label.login"));
                            loginRegisterLabel.setLabelFor(loginTF);
                            loginRegisterLabel.setMaximumSize(new Dimension(100, 16));
                            loginRegisterLabel.setMinimumSize(new Dimension(100, 16));
                            loginRegisterLabel.setPreferredSize(new Dimension(100, 16));
                            loginRegisterPanel.add(loginRegisterLabel);

                            //---- loginRegisterTF ----
                            loginRegisterTF.setMinimumSize(new Dimension(170, 30));
                            loginRegisterTF.setPreferredSize(new Dimension(170, 30));
                            loginRegisterPanel.add(loginRegisterTF);
                        }
                        inputRegisterPanel.add(loginRegisterPanel);

                        //======== pwdRegisterPanel ========
                        {
                            pwdRegisterPanel.setLayout(new HorizontalLayout(12));

                            //---- pwdRegisterLabel ----
                            pwdRegisterLabel.setText(ResourceBundle.getBundle("messages").getString("label.password"));
                            pwdRegisterLabel.setLabelFor(pwdRegisterTF);
                            pwdRegisterLabel.setMinimumSize(new Dimension(100, 16));
                            pwdRegisterLabel.setMaximumSize(new Dimension(100, 16));
                            pwdRegisterLabel.setPreferredSize(new Dimension(100, 16));
                            pwdRegisterPanel.add(pwdRegisterLabel);

                            //---- pwdRegisterTF ----
                            pwdRegisterTF.setPreferredSize(new Dimension(170, 30));
                            pwdRegisterTF.setMinimumSize(new Dimension(170, 30));
                            pwdRegisterPanel.add(pwdRegisterTF);
                        }
                        inputRegisterPanel.add(pwdRegisterPanel);

                        //======== repeatPwdPanel ========
                        {
                            repeatPwdPanel.setLayout(new HorizontalLayout(12));

                            //---- repeatPwdLabel ----
                            repeatPwdLabel.setText(ResourceBundle.getBundle("messages").getString("label.repeat_password"));
                            repeatPwdLabel.setLabelFor(repeatPwdTF);
                            repeatPwdLabel.setMinimumSize(new Dimension(100, 16));
                            repeatPwdLabel.setMaximumSize(new Dimension(100, 16));
                            repeatPwdLabel.setPreferredSize(new Dimension(100, 16));
                            repeatPwdPanel.add(repeatPwdLabel);

                            //---- repeatPwdTF ----
                            repeatPwdTF.setPreferredSize(new Dimension(170, 30));
                            repeatPwdTF.setMinimumSize(new Dimension(170, 30));
                            repeatPwdPanel.add(repeatPwdTF);
                        }
                        inputRegisterPanel.add(repeatPwdPanel);
                    }
                    registerTab.add(inputRegisterPanel, BorderLayout.CENTER);
                }
                tabbedPane.addTab(ResourceBundle.getBundle("messages").getString("tab.register"), registerTab);
            }
            add(tabbedPane);
        }
    }

    public JTextField getLoginTF() {
        return loginTF;
    }

    public JPasswordField getPwdTF() {
        return pwdTF;
    }

    public JButton getLoginButton() {
        return loginButton;
    }

    public JButton getCancelButton() {
        return cancelButton;
    }

    public JButton getRegisterButton() {
        return registerButton;
    }

    public JButton getCancelRegisterButton() {
        return cancelRegisterButton;
    }

    public JTextField getLoginRegisterTF() {
        return loginRegisterTF;
    }

    public JPasswordField getPwdRegisterTF() {
        return pwdRegisterTF;
    }

    public JPasswordField getRepeatPwdTF() {
        return repeatPwdTF;
    }

    public void setTexts(){
        tabbedPane.setTitleAt(0, ResourceBundle.getBundle("messages").getString("tab.login"));
        tabbedPane.setTitleAt(1, ResourceBundle.getBundle("messages").getString("tab.register"));
        loginLabel.setText(ResourceBundle.getBundle("messages").getString("label.login"));
        pwdLabel.setText(ResourceBundle.getBundle("messages").getString("label.password"));
        loginButton.setText(ResourceBundle.getBundle("messages").getString("button.login"));
        cancelButton.setText(ResourceBundle.getBundle("messages").getString("button.cancel"));
        loginRegisterLabel.setText(ResourceBundle.getBundle("messages").getString("label.login"));
        pwdRegisterLabel.setText(ResourceBundle.getBundle("messages").getString("label.password"));
        repeatPwdLabel.setText(ResourceBundle.getBundle("messages").getString("label.repeat_password"));
        registerButton.setText(ResourceBundle.getBundle("messages").getString("button.register"));
        cancelRegisterButton.setText(ResourceBundle.getBundle("messages").getString("button.cancel"));
    }
}
