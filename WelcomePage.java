import java.awt.Component;
import java.awt.Font;
import java.awt.LayoutManager;
import javax.swing.JFrame;
import javax.swing.JLabel;

// WelcomePage class creates a simple welcome screen for logged-in users.
public class WelcomePage extends JFrame {
   JLabel welcomeLabel = new JLabel(); // Welcome Page label

   WelcomePage(String Name, boolean isNewUser) {
      // checks user is new or not
      if (isNewUser) {
         welcomeLabel.setText("Hello " + Name + "! Thanks for registering");
      } else {
         welcomeLabel.setText("Hello " + Name + "! Welcome back ");
      }

      // setup components parameters
      welcomeLabel.setBounds(5, 5, 400, 30);
      welcomeLabel.setFont(new Font("Serif", 0, 22));

      // configure Welcome Page Frame
      add(welcomeLabel);
      setDefaultCloseOperation(3);
      setSize(400, 400);
      setLayout((LayoutManager) null);
      setLocationRelativeTo((Component) null);
      setResizable(false);
      setVisible(true);
   }
}