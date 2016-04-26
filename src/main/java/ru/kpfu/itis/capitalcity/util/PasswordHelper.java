package ru.kpfu.itis.capitalcity.util;

import org.apache.commons.codec.digest.DigestUtils;

import java.util.UUID;

/**
 * Created by a.gilmullin on 11.04.2016.
 */
public class PasswordHelper {
    public static String generateSalt() {
        return UUID.randomUUID().toString();
    }

    public static String encrypt(String unencrypted, String salt) {
        return DigestUtils.md5Hex(getSaltedPass(unencrypted, salt));
    }

    public static String getSaltedPass(String password, String salt) {
        return password + salt;
    }
}
