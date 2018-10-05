package classes;
/**
 * Codes originaly from codejava.net
 * Edited by Derrick Bush
 */
//Imports
//IO
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
//Security
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
 //Crypto
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

/**
 * A utility class that encrypts or decrypts a file.
 * @author www.codejava.net
 *
 */
public class Cryption {
	//Instance Variables
	//String
    private static final String ALGORITHM = "AES";
    private static final String TRANSFORMATION = "AES";
    
    //Methods
    //Public
    public static void encrypt(String key, File inputFile) throws CryptoException {
        try {
        	//Creates encryption key
            Key secretKey = new SecretKeySpec(key.getBytes(), ALGORITHM);
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            //Reads in the file that needs to be encrypted and encryptes it
            FileInputStream inputStream = new FileInputStream(inputFile);
            byte[] inputBytes = new byte[(int) inputFile.length()];
            inputStream.read(inputBytes);
            byte[] outputBytes = cipher.doFinal(inputBytes);
            inputStream.close();
            //Writes over the file to encrypt it
            FileOutputStream outputStream = new FileOutputStream(inputFile);
            outputStream.write(outputBytes);
            outputStream.close();
        } catch (NoSuchPaddingException | NoSuchAlgorithmException
                | InvalidKeyException | BadPaddingException
                | IllegalBlockSizeException | IOException ex) {
        	debug.debug("CryptionEncrypt:" + "There was an error encrypting  the file.");
        	debug.debug(ex.getStackTrace());
            throw new CryptoException("Error encrypting/decrypting file", ex);
        }
    }
 
    public static File decrypt(String key, File inputFile) throws CryptoException, IOException {
    	File file = null;
    	try {
        	//Creates encryption key
            Key secretKey = new SecretKeySpec(key.getBytes(), ALGORITHM);
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            //Reads in the file that needs to be decrypted and decryptes it
            FileInputStream inputStream = new FileInputStream(inputFile);
            byte[] inputBytes = new byte[(int) inputFile.length()];
            inputStream.read(inputBytes);
            byte[] outputBytes = cipher.doFinal(inputBytes);
            inputStream.close();
            //Writes the decrypted file to a temp file to be deleted after use
            file = File.createTempFile("decryptedFile", ".txt");
            FileOutputStream outputStream = new FileOutputStream(file);
            outputStream.write(outputBytes);
            outputStream.close();
        } catch (NoSuchPaddingException | NoSuchAlgorithmException
                | InvalidKeyException | BadPaddingException
                | IllegalBlockSizeException | IOException ex) {
        	debug.debug("CryptionEncrypt:" + "There was an error decrypting the file.");
        	debug.debug(ex.getStackTrace());
            throw new CryptoException("Error encrypting/decrypting file", ex);
        }
    	return file;
    }
}