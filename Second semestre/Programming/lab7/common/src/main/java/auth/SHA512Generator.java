package auth;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA512Generator {

    public static String getHash(String str){
        try{
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            byte[] messageDigest = md.digest((str).getBytes());
            BigInteger no = new BigInteger(1, messageDigest);
            StringBuilder hash = new StringBuilder(no.toString(16));
            while (hash.length() < 32){
                hash.insert(0, "0");
            }
            return hash.toString();
        } catch (NoSuchAlgorithmException ignored) {
            throw new RuntimeException();
        }
    }
}
