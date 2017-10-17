package teymi15.kassistant.Hashing;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

public class BcryptHashing {

    public static final String SALT = "abasdfasdvs";

    public static String signup(String password) {
        String saltedPassword = SALT + password;
        String hashedPassword = generateHash(saltedPassword);
        return hashedPassword;
    }

    public static Boolean mach(String password,String dbPassword) {
        Boolean isAuthenticated;

        String hashedPassword = generateHash(SALT + password);

        if(hashedPassword.equals(dbPassword)){
            isAuthenticated = true;
            System.out.print("satt");
        }else{
            isAuthenticated = false;
            System.out.print("false");
        }
        return isAuthenticated;
    }

    public static String generateHash(String input) {
        StringBuilder hash = new StringBuilder();

        try {
            MessageDigest sha = MessageDigest.getInstance("SHA-1");
            byte[] hashedBytes = sha.digest(input.getBytes());
            char[] digits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                    'a', 'b', 'c', 'd', 'e', 'f' };
            for (int idx = 0; idx < hashedBytes.length; ++idx) {
                byte b = hashedBytes[idx];
                hash.append(digits[(b & 0xf0) >> 4]);
                hash.append(digits[b & 0x0f]);
            }
        } catch (NoSuchAlgorithmException e) {
            // handle error here.
            System.out.print("error in generateHash: " + e);
        }

        return hash.toString();
    }
}
