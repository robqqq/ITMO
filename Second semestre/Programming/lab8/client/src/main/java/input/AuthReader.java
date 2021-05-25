package input;

import auth.Auth;

import java.io.IOException;

public interface AuthReader {

    Auth readAuth() throws IOException;
}
