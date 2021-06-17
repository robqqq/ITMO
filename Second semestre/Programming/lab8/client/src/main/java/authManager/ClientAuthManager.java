package authManager;

import auth.Auth;
import auth.SHA512Generator;
import exceptions.AuthException;
import exceptions.PasswordPatternException;
import exceptions.UsernamePatternException;
import networkMessages.Response;
import networkMessages.ResponseType;
import requests.ClientRequestFactory;
import requests.ClientRequestSender;
import requests.RequestFactory;
import requests.RequestSender;
import responses.ClientResponseReceiver;
import responses.ResponseReceiver;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.SocketAddress;
import java.util.regex.Pattern;

public class ClientAuthManager {
    private Auth auth;
    private final Pattern usernamePattern = Pattern.compile("^[a-zA-Z][a-zA-Z0-9-_.]{1,19}$");
    private final Pattern passwordPattern = Pattern.compile("^[a-zA-Z0-9]{6,}$");
    private final String salt;
    private final SocketAddress address;
    private final DatagramSocket socket;

    public ClientAuthManager(String salt, SocketAddress address, DatagramSocket socket){
        this.salt = salt;
        this.address = address;
        this.socket = socket;
    }

    public void auth(String login, String password) throws UsernamePatternException, PasswordPatternException, IOException {
        if (!usernamePattern.matcher(login).matches()){
            throw new UsernamePatternException();
        }
        if (!passwordPattern.matcher(password).matches()){
            throw new PasswordPatternException();
        }
        Auth oldAuth = auth;
        auth = new Auth(login, SHA512Generator.getHash(password + salt));
        RequestSender requestSender = new ClientRequestSender(address, socket);
        RequestFactory requestFactory = new ClientRequestFactory();
            requestSender.sendRequest(requestFactory.createAuthRegRequest("auth", auth, oldAuth));
    }

    public void reg(String login, String password) throws UsernamePatternException, PasswordPatternException, IOException {
        if (!usernamePattern.matcher(login).matches()){
            throw new UsernamePatternException();
        }
        if (!passwordPattern.matcher(password).matches()){
            throw new PasswordPatternException();
        }
        Auth oldAuth = auth;
        auth = new Auth(login, SHA512Generator.getHash(password + salt));
        RequestSender requestSender = new ClientRequestSender(address, socket);
        RequestFactory requestFactory = new ClientRequestFactory();
        requestSender.sendRequest(requestFactory.createAuthRegRequest("reg", auth, oldAuth));
    }

    public void disconnect(){
        auth = null;
    }

    public Auth getAuth(){
        return auth;
    }
}
