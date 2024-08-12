import java.io.Serializable;

/*
 * The User class represents a user entity in the system.
 * It implements Serializable for object serialization.
 */
public class User implements Serializable {
    // The class uses a serialVersionUID for version control during serialization
    private static final long serialVersionUID = 1L;
    private String Name;
    private String PasswordHash;

    // Constructor creates a new User object with the specified name and password
    public User(String name, String passwordHash) {
        this.Name = name;
        this.PasswordHash = passwordHash;
    }

    // getter methods for accessing user information
    public String getName() {
        return Name;
    }

    public String getPasswordHash() {
        return PasswordHash;
    }

    // @Override
    // public boolean equals(Object obj) {
    // User other = (User) obj;
    // return this.Name == other.Name && this.PasswordHash == other.PasswordHash;
    // }
}
