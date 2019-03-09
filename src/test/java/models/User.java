package models;

public class User {
    private String name;
    private String lastname;
    private String username;
    private String email;
    private String password;
    private String passwordConfirmation;

    public User(String name, String lastname, String username, String email, String password, String passwordConfirmation) {
        this.name = name;
        this.lastname = lastname;
        this.username = username;
        this.email = email;
        this.password = password;
        this.passwordConfirmation = passwordConfirmation;
    }
}
