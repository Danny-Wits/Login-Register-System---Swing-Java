import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.stream.Collectors;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

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
        initializeComponents();
        setupComponents();
        setupLayout();
        configureFrame();
    }

    // Initializes all UI components with their properties.
    private void initializeComponents() {
        loginPanel = new JPanel();
        LoginTitle = new JLabel("Login");
        nameLabel = new JLabel("Name");
        passwordLabel = new JLabel("Password");

        nameField = new JTextField();
        passwordField = new JPasswordField();

        loginButton = new JButton("Login");
        resetButton = new JButton("Reset");
        RegisterButton = new JButton("Don't have an account ! Register Now ");
    }

    // set component parameters
    private void setupComponents() {
        loginPanel.setBackground(Color.WHITE);
        loginPanel.setPreferredSize(new Dimension(300, 400));
        loginPanel.setLayout((LayoutManager) null);

        LoginTitle.setFont(new Font("Arial", 1, 37));
        LoginTitle.setBounds(0, 30, 300, 60);
        LoginTitle.setHorizontalAlignment(0);

        nameLabel.setBounds(50, 100, 200, 20);
        passwordLabel.setBounds(50, 160, 200, 20);

        nameField.setBounds(50, 124, 200, 30);
        passwordField.setBounds(50, 184, 200, 30);

        loginButton.setBounds(50, 244, 95, 30);
        loginButton.setBackground(new Color(135, 206, 250));
        loginButton.setFocusPainted(false);
        loginButton.addActionListener(this);

        resetButton.setBounds(155, 244, 95, 30);
        resetButton.setBackground(new Color(135, 206, 250));
        resetButton.setFocusPainted(false);
        resetButton.addActionListener(this);

        RegisterButton.setBounds(5, 330, 300, 30);
        RegisterButton.setForeground(new Color(95, 206, 250));
        RegisterButton.setBorderPainted(false);
        RegisterButton.setContentAreaFilled(false);
        RegisterButton.setFocusPainted(false);
        RegisterButton.setFont(new Font("Arial", 1, 13));
        RegisterButton.addActionListener(this);
    }

    // Sets up the layout of the login panel.
    private void setupLayout() {
        loginPanel.add(LoginTitle);
        loginPanel.add(nameLabel);
        loginPanel.add(nameField);
        loginPanel.add(passwordLabel);
        loginPanel.add(passwordField);
        loginPanel.add(loginButton);
        loginPanel.add(resetButton);
        loginPanel.add(RegisterButton);
    }

    // Configures the frame of the login Window.
    private void configureFrame() {
        setTitle("Java Login");
        setSize(550, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(135, 206, 250));
        setLayout(new GridBagLayout());
        add(loginPanel);
        setLocationRelativeTo(this);
        setVisible(true);
        setResizable(false);
    }

    // Handle Button Clicks
    public void actionPerformed(ActionEvent e) {
        // Handles the Login Process
        if (e.getSource() == this.loginButton) {
            String Name = this.nameField.getText();
            String Password = new String(this.passwordField.getPassword());
            if (Name == null || Password == null) {
                errorMessage("Please Enter Name and Password!");
                return;
            }
            String PasswordHash = UserAuthentication.hashPassword(new String(Password));
            if (!Name.trim().isEmpty() && Password.trim().length() > 0) {

                User newUser = new User(Name, PasswordHash); // creates user object with given credentials

                // Storing user data in an arraylist then authenticating whether user exists/not
                try {
                    ArrayList<User> Users = UsersData();
                    boolean authenticated = false;
                    // boolean authenticated = Users.contains(newUser);
                    for (User UserData : Users) {
                        if (UserData.getName().equals(Name) &&
                                UserData.getPasswordHash().equals(PasswordHash)) {
                            authenticated = true;
                            break;
                        }
                    }
                    if (authenticated) {
                        SwingUtilities.invokeLater(() -> {
                            new WelcomePage(Name, false); // opens welcome page
                            this.dispose(); // closing Login page
                        });
                    } else {
                        errorMessage("Invalid Name or Password!\nEnter Correct Details");
                    }
                } catch (IOException | ClassNotFoundException var6) {
                    System.out.println("Login Error");
                    var6.printStackTrace();
                }
            } else {
                errorMessage("Name & Password can't be empty");
            }
        }

        // Resets input fields
        if (e.getSource() == this.resetButton) {
            this.nameField.setText("");
            this.passwordField.setText("");
        }
        // Opens registration Window and closes Login window
        if (e.getSource() == this.RegisterButton) {
            SwingUtilities.invokeLater(() -> {
                new RegisterWindow();
                this.dispose();
            });
        }
    }

    // returns error message
    private void errorMessage(String message) {
        JOptionPane.showMessageDialog(this,
                message,
                "Login Error", JOptionPane.ERROR_MESSAGE);
    }

    // Reading user data and storing in Array list
    private ArrayList<User> UsersData() throws IOException, ClassNotFoundException {
        File file = new File("userData.ser");
        if (file.exists()) {
            try (ObjectInputStream myReader = new ObjectInputStream(new FileInputStream(file))) {
                return ((ArrayList<?>) myReader.readObject()).stream().map(this::objToUser)
                        .collect(Collectors.toCollection(ArrayList::new));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return new ArrayList<>();
    }

    User objToUser(Object obj) {
        return (User) obj;
    }
}
