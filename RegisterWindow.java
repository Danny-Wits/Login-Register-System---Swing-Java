import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

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
   private JButton LoginButton;

   /*
    * RegisterUI class handles the user interface for the registration screen.
    * It creates and manages the registration form, including input fields and
    * buttons.
    */
   public RegisterWindow() {
      this.initializeComponents();
      this.setupComponents();
      this.setupLayout();
      this.configureFrame();
   }

   // Initializes all UI components with their properties.
   private void initializeComponents() {

      this.RegisterPanel = new JPanel();
      this.RegisterLabel = new JLabel("Register");
      this.nameLabel = new JLabel("Name");
      this.passwordLabel = new JLabel("Password");

      this.nameField = new JTextField();
      this.passwordField = new JPasswordField();

      this.CreateAccButton = new JButton("Create Account");
      this.LoginButton = new JButton("Already a User ! Login Now ");
   }

   // set component parameters
   private void setupComponents() {
      this.RegisterPanel.setBackground(Color.WHITE);
      this.RegisterPanel.setPreferredSize(new Dimension(300, 400));
      this.RegisterPanel.setLayout((LayoutManager) null);
      this.RegisterLabel.setFont(new Font("Arial", 1, 37));
      this.RegisterLabel.setBounds(0, 30, 300, 60);
      this.RegisterLabel.setHorizontalAlignment(0);
      this.nameLabel.setBounds(50, 100, 200, 20);
      this.passwordLabel.setBounds(50, 160, 200, 20);

      this.nameField.setBounds(50, 124, 200, 30);
      this.passwordField.setBounds(50, 184, 200, 30);

      this.CreateAccButton.setBounds(70, 244, 160, 30);
      this.LoginButton.setBounds(30, 340, 250, 30);
      this.CreateAccButton.setBackground(new Color(135, 206, 250));
      this.CreateAccButton.setFocusPainted(false);
      this.CreateAccButton.setFont(new Font("Arial", 1, 14));
      this.CreateAccButton.addActionListener(this);
      this.LoginButton.setForeground(new Color(95, 206, 250));
      this.LoginButton.setBorderPainted(false);
      this.LoginButton.setContentAreaFilled(false);
      this.LoginButton.setFocusPainted(false);
      this.LoginButton.setFont(new Font("Arial", 1, 14));
      this.LoginButton.addActionListener(this);
   }

   // Sets up the layout of the Registration panel.
   private void setupLayout() {
      this.RegisterPanel.add(this.RegisterLabel);
      this.RegisterPanel.add(this.nameLabel);
      this.RegisterPanel.add(this.nameField);
      this.RegisterPanel.add(this.passwordLabel);
      this.RegisterPanel.add(this.passwordField);
      this.RegisterPanel.add(this.CreateAccButton);
      this.RegisterPanel.add(this.LoginButton);
   }

   // Configures the frame of the Register Window
   private void configureFrame() {
      this.setTitle("Java Register");
      this.setSize(550, 550);
      this.setDefaultCloseOperation(3);
      this.getContentPane().setBackground(new Color(135, 206, 250));
      this.setLayout(new GridBagLayout());
      this.add(this.RegisterPanel);
      this.setLocationRelativeTo((Component) null);
      this.setVisible(true);
      this.setResizable(false);
   }

   // Handle Button Clicks
   public void actionPerformed(ActionEvent e) {
      // Handles the Login Process
      if (e.getSource() == this.CreateAccButton) {
         String Name = this.nameField.getText();
         String Password = new String(this.passwordField.getPassword());
         try {
            FileWriter myWriter = new FileWriter("userData.txt", true);
            myWriter.write(Name + "&" + UserAuthentication.hashPassword(Password) + "\n");
            myWriter.close();
            this.dispose();// Remove Register Window frame
            new WelcomePage(Name);// Open Welcome Page
         } catch (IOException var5) {
            var5.printStackTrace();
         }
      }
      // Opens login window after removing registration window
      if (e.getSource() == this.LoginButton) {
         this.dispose();
         new LoginWindow();
      }

      throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
   }
}
