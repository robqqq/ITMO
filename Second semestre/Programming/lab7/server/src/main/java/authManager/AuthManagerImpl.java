package authManager;

import auth.Auth;

import java.util.Set;

public class AuthManagerImpl implements AuthManager{
    private Set<Auth> users;

    @Override
    public void setUsers(Set<Auth> users) {
        this.users = users;
    }

    @Override
    public boolean checkAuth(Auth auth) {
        return users.contains(auth);
    }

    @Override
    public void addUser(Auth auth) {
        users.add(auth);
    }
}
