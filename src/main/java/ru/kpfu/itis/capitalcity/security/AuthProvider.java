package ru.kpfu.itis.capitalcity.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import ru.kpfu.itis.capitalcity.model.Credentials;
import ru.kpfu.itis.capitalcity.service.CredentialsService;
import ru.kpfu.itis.capitalcity.util.PasswordHelper;
import ru.kpfu.itis.capitalcity.util.SecurityContextUtil;

/**
 * Created by a.gilmullin on 11.04.2016.
 */
@Component
public class AuthProvider implements AuthenticationProvider {

    @Autowired
    private CredentialsService credentialsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String password = authentication.getCredentials().toString();
        String login = authentication.getName();
        Credentials credentials = credentialsService.findByLogin(login);
        if (credentials == null) {
            throw new UsernameNotFoundException("Credentials Not Found");
        }
        String hashedPassword = PasswordHelper.encrypt(password, credentials.getSalt());
        if (credentials.getPassword().equals(hashedPassword)) {
            return SecurityContextUtil.setAuthentication(credentials);
        } else {
            throw new BadCredentialsException("Bad credentials password");
        }
    }


    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }

}
