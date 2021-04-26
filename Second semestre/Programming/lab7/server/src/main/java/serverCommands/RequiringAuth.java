package serverCommands;

import auth.Auth;

public interface RequiringAuth {

    void setAuth(Auth auth);
}
