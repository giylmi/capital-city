package ru.kpfu.itis.capitalcity.model;

import org.springframework.data.annotation.Id;

/**
 * Created by a.gilmullin on 05.04.2016.
 */
public class Credentials {

    @Id
    private String id;
    private String login;
    private String password;
    private String salt;
    private String email;

    @Override
    public String toString() {
        return "Credentials{" +
                "id='" + id + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", salt='" + salt + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
