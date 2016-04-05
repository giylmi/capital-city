package ru.kpfu.itis.capitalcity.service.impl;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.capitalcity.model.Credentials;
import ru.kpfu.itis.capitalcity.repository.CredentialsRepository;
import ru.kpfu.itis.capitalcity.service.CredentialsService;

import java.util.UUID;

/**
 * Created by a.gilmullin on 05.04.2016.
 */
@Service
public class CredentialsServiceImpl implements CredentialsService {

    @Autowired
    private CredentialsRepository credentialsRepository;

    @Override
    public void save(Credentials credentials) {
        String salt = generateSalt();
        credentials.setSalt(salt);
        String encryptedPassword = encrypt(credentials.getPassword(), salt);
        credentials.setPassword(encryptedPassword);
        credentialsRepository.save(credentials);
    }

    private static String generateSalt() {
        return UUID.randomUUID().toString();
    }

    private static String encrypt(String unencrypted, String salt) {
        return DigestUtils.md5Hex(getSaltedPass(unencrypted, salt));
    }

    private static String getSaltedPass(String password, String salt) {
        return password + salt;
    }
}
