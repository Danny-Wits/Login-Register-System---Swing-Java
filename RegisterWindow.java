import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class RegisterWindow extends JFrame implements ActionListener {

    // Panels & Labels
    private JPanel RegisterPanel;
    private JLabel RegisterLabel;
    private JLabel nameLabel;
    private JLabel passwordLabel;

    // Input Fields
    private JTextField nameField;
    private JPasswordField passwordField;

    // Buttons
    private JButton CreateAccButton;
    private JButton LoginLink;

    /*
     * RegisterUI class handles the user interface for the registration screen.
     * It creates and manages the registration form, including input fields and
     * buttons.
     */
    public RegisterWindow() {
        initializeComponents();
        setupComponents();
        setupLayout();
        configureFrame();
    }

    // Initializes all UI components with their properties.
    private void initializeComponents() {

        RegisterPanel = new JPanel();
        RegisterLabel = new JLabel("Register");
        nameLabel = new JLabel("Name");
        passwordLabel = new JLabel("Password");

        nameField = new JTextField();
        passwordField = new JPasswordField();

        CreateAccButton = new JButton("Create Account");
        LoginLink = new JButton("Already a User ! Login Now ");
    }

    // set component parameters
    private void setupComponents() {
        RegisterPanel.setBackground(Color.WHITE);
        RegisterPanel.setPreferredSize(new Dimension(300, 400));
        RegisterPanel.setLayout((LayoutManager) null);
        RegisterLabel.setFont(new Font("Arial", 1, 37));
        RegisterLabel.setBounds(0, 30, 300, 60);
        RegisterLabel.setHorizontalAlignment(0);
        nameLabel.setBounds(50, 100, 200, 20);
        passwordLabel.setBounds(50, 160, 200, 20);

        nameField.setBounds(50, 124, 200, 30);
        passwordField.setBounds(50, 184, 200, 30);

        CreateAccButton.setBounds(70, 244, 160, 30);
        LoginLink.setBounds(30, 330, 250, 30);
        CreateAccButton.setBackground(new Color(135, 206, 250));
        CreateAccButton.setFocusPainted(false);
        CreateAccButton.setFont(new Font("Arial", 1, 14));
        CreateAccButton.addActionListener(this);
        LoginLink.setForeground(new Color(95, 206, 250));
        LoginLink.setBorderPainted(false);
        LoginLink.setContentAreaFilled(false);
        LoginLink.setFocusPainted(false);
        LoginLink.setFont(new Font("Arial", 1, 14));
        LoginLink.addActionListener(this);
    }

    // Sets up the layout of the Registration panel.
    private void setupLayout() {
        RegisterPanel.add(RegisterLabel);
        RegisterPanel.add(nameLabel);
        RegisterPanel.add(nameField);
        RegisterPanel.add(passwordLabel);
        RegisterPanel.add(passwordField);
        RegisterPanel.add(CreateAccButton);
        RegisterPanel.add(LoginLink);
    }

    // Configures the frame of the Register Window
    private void configureFrame() {
        setTitle("Java Register");
        setSize(550, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(135, 206, 250));
        setLayout(new GridBagLayout());
        add(RegisterPanel);
        setLocationRelativeTo((Component) null);
        setVisible(true);
        setResizable(false);
    }

    // Handle Button Clicks
    public void actionPerformed(ActionEvent e) {
        // Handles the Registration Process
        if (e.getSource() == this.CreateAccButton) {
            String Name = this.nameField.getText();
            String Password = new String(this.passwordField.getPassword());
            String PasswordHash = UserAuthentication.hashPassword(new String(Password));

            if (!Name.isEmpty() && Password.length() > 0) {
                User newUser = new User(Name, PasswordHash); // creates object with the given credentials
                // storing user data to the file
                try {
                    ArrayList<User> users = oldUserData(); // gets old data
                    users.add(newUser); // adding new user data to old data
                    saveUsers(users); // storing user data

                    SwingUtilities.invokeLater(() -> {
                        new WelcomePage(Name, true);// opens Welcome Page
                        this.dispose(); // closes Register page
                    });

                } catch (IOException | ClassNotFoundException var5) {
                    System.out.println("Registration Error");
                    var5.printStackTrace();
                }

            } else {
                JOptionPane.showMessageDialog((Component) null, "Name & Password can't be empty", "Registration Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
        // Opens login window and closes registration window
        if (e.getSource() == this.LoginLink)

        {
            SwingUtilities.invokeLater(() -> {
                new LoginWindow();
                this.dispose();
            });
        }
    }

    @SuppressWarnings("unchecked") // ignore this

    // Loading Users : De-serialization
    // Reading previous data and storing in arraylist
    private ArrayList<User> oldUserData() throws IOException, ClassNotFoundException {
        File file = new File("userData.ser");
        if (file.exists()) {
            try (ObjectInputStream myReader = new ObjectInputStream(new FileInputStream(file))) {
                return (ArrayList<User>) myReader.readObject();
            }
        }
        return new ArrayList<>();
    }

    // Saving users : Serialization
    // Saving the user data back to the file
    private void saveUsers(ArrayList<User> users) throws IOException {
        try (ObjectOutputStream myWriter = new ObjectOutputStream(new FileOutputStream("userData.ser"))) {
            myWriter.writeObject(users);
        }
    }
}