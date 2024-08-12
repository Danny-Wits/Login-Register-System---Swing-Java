import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class UserAuthentication {

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
