package it.vitalegi.budget.it;

import it.vitalegi.budget.auth.AuthenticationService;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@Primary
public class FakeAuthenticationService extends AuthenticationService {
    public String getUid() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info(">>>>>>>>> {}", authentication);
        log.info(">>>>>>>>> {}", authentication.getPrincipal());
        log.info(">>>>>>>>> {}", authentication.getAuthorities());
        log.info(">>>>>>>>> {}", authentication.getCredentials());
        User user = (User) authentication.getPrincipal();
        log.info("UID={}, name={}, verified={}", user.getUsername(), getName(), isVerified());
        return user.getUsername();
    }

    public String getName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        return user.getAuthorities().stream()//
                .map(GrantedAuthority::getAuthority) //
                .filter(a -> a.startsWith("USERNAME:"))//
                .map(a -> a.substring("USERNAME:".length()))
                .findFirst().orElseThrow();
    }

    public boolean isVerified() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        return user.getAuthorities().stream()//
                .filter(a -> a.getAuthority().equals("VERIFIED"))
                .count() > 0;
    }
}
