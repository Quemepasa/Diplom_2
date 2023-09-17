package models;

public class ChangeUserDataRequest {
    private String email;
    private String password;
    private String name;

    public ChangeUserDataRequest(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

    public ChangeUserDataRequest() {
    }

    public String getEmail() {
        return email;
    }

    public ChangeUserDataRequest setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public ChangeUserDataRequest setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getName() {
        return name;
    }

    public ChangeUserDataRequest setName(String name) {
        this.name = name;
        return this;
    }
}
