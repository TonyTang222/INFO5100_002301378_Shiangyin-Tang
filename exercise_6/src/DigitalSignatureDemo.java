import java.security.*;
import java.util.Base64;

public class DigitalSignatureDemo {
    public static void main(String[] args) throws Exception {
        // Generate RSA-2048 Key Pair
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
        keyPairGen.initialize(2048);
        KeyPair keyPair = keyPairGen.generateKeyPair();
        PrivateKey privateKey = keyPair.getPrivate();
        PublicKey publicKey = keyPair.getPublic();

        // Alice's Message
        String message = "Hello Bob! This is Alice.";
        System.out.println("Original Message: " + message);

        // Alice Signs the Message
        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initSign(privateKey);
        signature.update(message.getBytes());
        byte[] digitalSignature = signature.sign();
        System.out.println("Digital Signature: " + Base64.getEncoder().encodeToString(digitalSignature));

        // Bob Validates the Signature
        signature.initVerify(publicKey);
        signature.update(message.getBytes());
        boolean isValid = signature.verify(digitalSignature);
        System.out.println("Is the signature valid? " + isValid);
    }
}
