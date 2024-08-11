import java.io.FileReader;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Scanner;

//Handles user authentication and password security.
public class UserAuthentication {
   public UserAuthentication() {
   }

   // Checks whether user exists or not
   public static boolean authenticate(String Name, String Password) {
      try {
         // reading and checking user data
         FileReader myReader = new FileReader("userData.txt");
         Scanner scan = new Scanner(myReader);
         while (scan.hasNextLine()) {
            String userData = scan.nextLine();
            if (userData.equals(Name + "&" + hashPassword(Password))) {
               scan.close();
               return true;
            }
         }
         scan.close();
         myReader.close();
      } catch (IOException var5) {
         System.out.println("An error occurred.");
         var5.printStackTrace();
      }

      return false;
   }

   // Encrypts or Hashes a password using SHA-256 Algorithm.
   public static String hashPassword(String Password) {
      try {
         MessageDigest digest = MessageDigest.getInstance("SHA-256");
         byte[] hash = digest.digest(Password.getBytes());
         return Base64.getEncoder().encodeToString(hash);
      } catch (NoSuchAlgorithmException var3) {
         throw new RuntimeException("SHA-256 algorithm not found.", var3);
      }
   }
}
