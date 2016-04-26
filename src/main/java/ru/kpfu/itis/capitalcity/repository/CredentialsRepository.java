package ru.kpfu.itis.capitalcity.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.kpfu.itis.capitalcity.model.Credentials;

/**
 * Created by a.gilmullin on 05.04.2016.
 */
public interface CredentialsRepository extends MongoRepository<Credentials, String> {
    Credentials findOneByLogin(String login);
}
