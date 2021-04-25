package authManager;

import auth.Auth;

import java.util.Map;
import java.util.Set;

public interface AuthManager {

    void setUsers(Set<Auth> users);

    boolean checkAuth(Auth auth);

    void addUser(Auth auth);
}
