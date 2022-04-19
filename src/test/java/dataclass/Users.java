package dataclass;

public class Users {
    private String login;
    private String password;
    private String status;

    public Users(String login, String password, String status) {
        this.login = login;
        this.password = password;
        this.status = status;
    }

    public Users() {
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getStatus() {
        return status;
    }
}
