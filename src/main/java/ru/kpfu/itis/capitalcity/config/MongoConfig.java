package ru.kpfu.itis.capitalcity.config;

import com.mongodb.MongoClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.authentication.UserCredentials;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * Created by a.gilmullin on 05.04.2016.
 */
@Configuration
@EnableMongoRepositories(basePackages = {"ru.kpfu.itis.capitalcity.repository"})
@PropertySource(value = {
        "classpath:properties/mongo.properties",
})
public class MongoConfig {

    @Autowired
    private Environment env;

    public @Bean
    MongoDbFactory mongoDbFactory() throws Exception {
        return new SimpleMongoDbFactory(new MongoClient(),
                env.getProperty("mongo.dbs.name"),
                new UserCredentials(env.getProperty("mongo.user.name"),
                        env.getProperty("mongo.user.password")));
    }

    @Bean
    public MongoTemplate mongoTemplate() throws Exception {
        return new MongoTemplate(mongoDbFactory());
    }
}

