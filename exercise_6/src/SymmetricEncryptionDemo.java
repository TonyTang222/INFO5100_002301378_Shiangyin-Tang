import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import java.security.SecureRandom;
import java.util.Base64;

public class SymmetricEncryptionDemo {
    public static void main(String[] args) throws Exception {
        // Generate AES-256 Key
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(256);
        SecretKey aesKey = keyGenerator.generateKey();

        // Alice's Message
        String plainText = "Hello Bob! This is Alice.";
        System.out.println("Original Message: " + plainText);

        // Encrypt Message
        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
        byte[] iv = new byte[12]; // GCM IV is 12 bytes
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextBytes(iv);
        GCMParameterSpec gcmSpec = new GCMParameterSpec(128, iv); // 128-bit Authentication Tag

        cipher.init(Cipher.ENCRYPT_MODE, aesKey, gcmSpec);
        byte[] encryptedMessage = cipher.doFinal(plainText.getBytes());
        System.out.println("Encrypted Message: " + Base64.getEncoder().encodeToString(encryptedMessage));

        // Decrypt Message
        cipher.init(Cipher.DECRYPT_MODE, aesKey, gcmSpec);
        byte[] decryptedMessage = cipher.doFinal(encryptedMessage);
        System.out.println("Decrypted Message: " + new String(decryptedMessage));
    }
}