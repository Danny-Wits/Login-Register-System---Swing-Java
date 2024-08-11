import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginWindow extends JFrame implements ActionListener {

    // Panels & Labels
    private JPanel loginPanel;
    private JLabel LoginTitle;
    private JLabel nameLabel;
    private JLabel passwordLabel;

    // Input Fields
    private JTextField nameField;
    private JPasswordField passwordField;

    // Buttons
    private JButton loginButton;
    private JButton resetButton;
    private JButton RegisterButton;

    /*
     * LoginUI class handles the user interface for the login screen.
     * It creates and manages the login form, including input fields and buttons.
     */
    public LoginWindow() {
        this.initializeComponents();
        this.setupComponents();
        this.setupLayout();
        this.configureFrame();
    }

    // Initializes all UI components with their properties.
    private void initializeComponents() {
        this.loginPanel = new JPanel();
        this.LoginTitle = new JLabel("Login");
        this.nameLabel = new JLabel("Name");
        this.passwordLabel = new JLabel("Password");

        this.nameField = new JTextField();
        this.passwordField = new JPasswordField();

        this.loginButton = new JButton("Login");
        this.resetButton = new JButton("Reset");
        this.RegisterButton = new JButton("Don't have an account ! Register Now ");
    }

    // set component parameters
    private void setupComponents() {
        this.loginPanel.setBackground(Color.WHITE);
        this.loginPanel.setPreferredSize(new Dimension(300, 400));
        this.loginPanel.setLayout((LayoutManager) null);

        this.LoginTitle.setFont(new Font("Arial", 1, 37));
        this.LoginTitle.setBounds(0, 30, 300, 60);
        this.LoginTitle.setHorizontalAlignment(0);

        this.nameLabel.setBounds(50, 100, 200, 20);
        this.passwordLabel.setBounds(50, 160, 200, 20);

        this.nameField.setBounds(50, 124, 200, 30);
        this.passwordField.setBounds(50, 184, 200, 30);

        this.loginButton.setBounds(50, 244, 95, 30);
        this.loginButton.setBackground(new Color(135, 206, 250));
        this.loginButton.setFocusPainted(false);
        this.loginButton.addActionListener(this);

        this.resetButton.setBounds(155, 244, 95, 30);
        this.resetButton.setBackground(new Color(135, 206, 250));
        this.resetButton.setFocusPainted(false);
        this.resetButton.addActionListener(this);

        this.RegisterButton.setBounds(30, 340, 250, 30);
        this.RegisterButton.setForeground(new Color(95, 206, 250));
        this.RegisterButton.setBorderPainted(false);
        this.RegisterButton.setContentAreaFilled(false);
        this.RegisterButton.setFocusPainted(false);
        this.RegisterButton.setFont(new Font("Arial", 1, 12));
        this.RegisterButton.addActionListener(this);
    }

    // Sets up the layout of the login panel.
    private void setupLayout() {
        this.loginPanel.add(this.LoginTitle);
        this.loginPanel.add(this.nameLabel);
        this.loginPanel.add(this.nameField);
        this.loginPanel.add(this.passwordLabel);
        this.loginPanel.add(this.passwordField);
        this.loginPanel.add(this.loginButton);
        this.loginPanel.add(this.resetButton);
        this.loginPanel.add(this.RegisterButton);
    }

    // Configures the frame of the login Window.
    private void configureFrame() {
        this.setTitle("Java Login");
        this.setSize(550, 550);
        this.setDefaultCloseOperation(3);
        this.getContentPane().setBackground(new Color(135, 206, 250));
        this.setLayout(new GridBagLayout());
        this.add(this.loginPanel);
        this.setLocationRelativeTo((Component) null);
        this.setVisible(true);
        this.setResizable(false);
    }

    // Handle Button Clicks
    public void actionPerformed(ActionEvent e) {
        // Handles the Login Process
        if (e.getSource() == this.loginButton) {
            String Name = this.nameField.getText();
            String Password = new String(this.passwordField.getPassword());
            if (!Name.isEmpty() && Password.length() > 0) {
                if (UserAuthentication.authenticate(Name, Password)) {
                    this.dispose();// Remove Register Window frame
                    new WelcomePage(Name);// Open Welcome Page
                } else {
                    JOptionPane.showMessageDialog((Component) null, "Invalid Name or Password!\nEnter Correct Details",
                            "Login Error", 0);
                }
            } else {
                JOptionPane.showMessageDialog((Component) null, "Name & Password can't be empty", "Login Error", 0);
            }
        }
        // Resets input fields
        if (e.getSource() == this.resetButton) {
            this.nameField.setText("");
            this.passwordField.setText("");
        }
        // Opens registration Window after removing Login window
        if (e.getSource() == this.RegisterButton) {
            this.dispose();
            new RegisterWindow();
        }

        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }
}
