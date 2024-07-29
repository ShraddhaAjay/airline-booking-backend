package com.booking.airline.util;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class EncryptionUtil {

    private static final String ALGORITHM = "AES";
    private static final String key = "SECRET";
    private static SecretKey secretKey;

    public EncryptionUtil(){
        byte[] secretKeyBytes = key.getBytes();
        byte[] keyBytes = new byte[16];
        int length = Math.min(secretKeyBytes.length, keyBytes.length);
        for(int i=0;i<length;i++) {
            keyBytes[i] = secretKeyBytes[i];
        }
        secretKey = new SecretKeySpec(keyBytes, ALGORITHM);
    }

    public String encrypt(String data) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedBytes = cipher.doFinal(data.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    public String decrypt(String encryptedData) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decodedBytes = Base64.getDecoder().decode(encryptedData);
        byte[] decryptedBytes = cipher.doFinal(decodedBytes);
        return new String(decryptedBytes);
    }

}
