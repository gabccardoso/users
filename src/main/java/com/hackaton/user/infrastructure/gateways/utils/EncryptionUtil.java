package com.hackaton.user.infrastructure.gateways.utils;

import org.jasypt.util.text.AES256TextEncryptor;
import org.springframework.beans.factory.annotation.Value;

public class EncryptionUtil {

    @Value("${jwt.secretKey}")
    private static String secretKey;

    public static String encrypt(String data) {
        AES256TextEncryptor encryptor = new AES256TextEncryptor();
        encryptor.setPassword(secretKey);
        return encryptor.encrypt(data);
    }

    public static String decrypt(String encryptedData) {
        AES256TextEncryptor encryptor = new AES256TextEncryptor();
        encryptor.setPassword(secretKey);
        return encryptor.decrypt(encryptedData);
    }
}
