package teymi15.kassistant.Hashing;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Class that handle password hashing
 */
public class BcryptHashing {

    public static final String SALT = "abasdfasdvs";

    /**
     * Hashing password upon sign up
     * @param password password
     * @return hashed string
     */
    public static String signup(String password) {
        String saltedPassword = SALT + password;
        String hashedPassword = generateHash(saltedPassword);
        return hashedPassword;
    }

    /**
     * Check whether the password is matched
     * @param password password
     * @param dbPassword password stores in the database
     * @return true if matched
     */
    public static Boolean match(String password, String dbPassword) {
        Boolean isAuthenticated;

        String hashedPassword = generateHash(SALT + password);

        if(hashedPassword.equals(dbPassword)){
            isAuthenticated = true;
        }else{
            isAuthenticated = false;
        }
        return isAuthenticated;
    }

    /**
     * This function generate the hashing
     * @param input input
     * @return hashed string
     */
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
