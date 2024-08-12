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

### Login Page

<img src="Images/Login.png" width="310" height="300" />

### Registration Page

<img src="Images/Register.png" width="310" height="300" />

### Welcome Page for New User

<img src="Images/WelcomeNewUser.png" width="310" height="300" />

### Welcome Page for Existing User

<img src="Images/WelcomeUser.png" width="310" height="300" />

## Dependencies

This project uses standard Java libraries and Swing for the GUI. No additional dependencies are required.

## Security Considerations

- Passwords are hashed using SHA-256 before storage.
- User data is serialized and stored locally. For a production environment, consider using a more secure database solution.

## Contributing

Contributions to improve the system are welcome. Please fork the repository and submit a pull request with your changes.
