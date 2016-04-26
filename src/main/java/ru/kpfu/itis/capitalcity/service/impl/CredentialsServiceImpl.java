package ru.kpfu.itis.capitalcity.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.capitalcity.model.Credentials;
import ru.kpfu.itis.capitalcity.repository.CredentialsRepository;
import ru.kpfu.itis.capitalcity.service.CredentialsService;
import ru.kpfu.itis.capitalcity.util.PasswordHelper;

/**
 * Created by a.gilmullin on 05.04.2016.
 */
@Service
public class CredentialsServiceImpl implements CredentialsService {

    @Autowired
    private CredentialsRepository credentialsRepository;

    @Override
    public Credentials findByLogin(String login) {
        return credentialsRepository.findOneByLogin(login);
    }

    @Override
    public void save(Credentials credentials) {
        String salt = PasswordHelper.generateSalt();
        credentials.setSalt(salt);
        String encryptedPassword = PasswordHelper.encrypt(credentials.getPassword(), salt);
        credentials.setPassword(encryptedPassword);
        credentialsRepository.save(credentials);
    }
}
