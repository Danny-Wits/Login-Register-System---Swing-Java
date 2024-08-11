import java.awt.Component;
import java.awt.Font;
import java.awt.LayoutManager;
import javax.swing.JFrame;
import javax.swing.JLabel;

// WelcomePage class creates a simple welcome screen for logged-in users.
public class WelcomePage extends JFrame {
   JLabel welcomeLabel = new JLabel(); // Welcome Page label

   WelcomePage(String Name) {

      // setup components parameters
      this.welcomeLabel.setText("Hello " + Name);
      this.welcomeLabel.setBounds(5, 5, 200, 30);
      this.welcomeLabel.setFont(new Font("Serif", 0, 22));

      // configure Welcome Page Frame
      this.add(this.welcomeLabel);
      this.setDefaultCloseOperation(3);
      this.setSize(400, 400);
      this.setLayout((LayoutManager) null);
      this.setLocationRelativeTo((Component) null);
      this.setResizable(false);
      this.setVisible(true);
   }
}