## Java Swing Login/Register System

This project implements a simple login and registration system using Java Swing and also implements Serialization. It provides a graphical user interface for users to create new accounts, log in to existing accounts, and view a welcome page upon successful authentication.

### Features

- User Registration: Allow new users to create accounts
- User Login: Authenticate existing users
- Welcome Page: Display a personalized welcome message after successful login or registration
- Data Persistence: Save user data using Java serialization
- Password Hashing: Securely store passwords using SHA-256 hashing
- Error Handling: Provide user-friendly error messages for invalid inputs or failed authentication

## Screenshots

<table>
  <tr>
    <td><strong>Login Page</strong></td>
    <td><strong>Registration Page</strong></td>
  </tr>
  <tr>
    <td><img src="Images/Login.png" width="400"/></td>
    <td><img src="Images/Register.png" width="400"/></td>
  </tr>
</table>
<table>
  <tr>
    <td><strong>Welcome Page for New User</strong></td>
    <td><strong>For Existing User</strong></td>
  </tr>
  <tr>
    <td><img src="Images/WelcomeNewUser.png" width="400"/></td>
    <td><img src="Images/WelcomeUser.png" width="400"/></td>
  </tr>
</table>

## Dependencies

This project uses standard Java libraries and Swing for the GUI. No additional dependencies are required.

## Security Considerations

- Passwords are hashed using SHA-256 before storage.
- User data is serialized and stored locally. For a production environment, consider using a more secure database solution.

## Contributing

Contributions to improve the system are welcome. Please fork the repository and submit a pull request with your changes.
