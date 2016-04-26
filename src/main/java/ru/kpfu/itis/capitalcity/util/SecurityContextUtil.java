package ru.kpfu.itis.capitalcity.util;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import ru.kpfu.itis.capitalcity.model.Credentials;

import java.util.Arrays;
import java.util.Collection;

/**
 * Created by a.gilmullin on 11.04.2016.
 */
public class SecurityContextUtil {

    public static Credentials getCurrentCredentials() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return isNoAuth(authentication) ? null : (Credentials) authentication.getPrincipal();
    }


    public static Collection<? extends GrantedAuthority> getCurrentUserRoles() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return isNoAuth(authentication) ? null : authentication.getAuthorities();
    }

    public static String getCurrentCredentialsId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return isNoAuth(authentication) ? null : ((Credentials) authentication.getPrincipal()).getId();
    }

    private static boolean isNoAuth(Authentication authentication) {
        return authentication == null || !(authentication instanceof UsernamePasswordAuthenticationToken);
    }

    public static Authentication setAuthentication(Credentials credentials) {
        Authentication authToken =
                new UsernamePasswordAuthenticationToken(
                        credentials,
                        null,
                        //// TODO: 11.04.2016 fix this if we have more than role
                        Arrays.asList(new SimpleGrantedAuthority("ROLE_USER")));
        SecurityContextHolder.getContext().setAuthentication(authToken);
        return authToken;
    }


    public static boolean isAuthenticated() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return !isNoAuth(authentication);
    }
}
