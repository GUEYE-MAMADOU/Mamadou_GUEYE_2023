package entity;

public class User {
    private int id;
    private String email;
    private String passwordHashed;

    //private Role role;
    private String password;
    private Role role;

    public User() {
    }

    public User(int id, String email, String passwordHashed, String password) {
        this.id = id;
        this.email = email;
        this.passwordHashed = passwordHashed;
        this.password = password;
    }

    public User(String email, String password, String hashedPassword) {
    }

    public User(int userId, String email, String password, String hashedPassword, Role role) {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHashed() {
        return passwordHashed;
    }

    public void setPasswordHashed(String passwordHashed) {
        this.passwordHashed = passwordHashed;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
