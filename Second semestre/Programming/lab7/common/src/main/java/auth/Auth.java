package auth;

import java.io.Serializable;
import java.util.Objects;

public class Auth implements Serializable {
    //TODO: serialversionUID
    private final String login;
    private final String password;

    public Auth(String login, String password){
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Auth auth = (Auth) o;
        return login.equals(auth.login) && password.equals(auth.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, password);
    }

    @Override
    public String toString() {
        return "Auth{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
