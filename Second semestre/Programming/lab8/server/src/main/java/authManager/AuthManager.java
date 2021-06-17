package authManager;

import auth.Auth;

import java.util.Collection;

public interface AuthManager {

    void setUsers(Collection<Auth> users);

    boolean checkAuth(Auth auth);

    void addUser(Auth auth);
}
