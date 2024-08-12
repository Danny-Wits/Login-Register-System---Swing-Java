import java.io.Serializable;

public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    private String Name;
    private String PasswordHash;

    public User(String name, String passwordHash) {
        this.Name = name;
        this.PasswordHash = passwordHash;
    }

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
