package models;

public class CreateUserRequest {
    private String email;
    private String password;
    private String name;

    public CreateUserRequest(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

    public CreateUserRequest() {
    }

    public String getEmail() {
        return email;
    }

    public CreateUserRequest withEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public CreateUserRequest withPassword(String password) {
        this.password = password;
        return this;
    }

    public String getName() {
        return name;
    }

    public CreateUserRequest withName(String name) {
        this.name = name;
        return this;
    }
}
