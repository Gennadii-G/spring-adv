package beans.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

/**
 * implementation of {@link SecurityService}
 *
 * @author Gennadii Borodin
 * @version 1.0
 *
 */

@Service
public class SecurityServiceImpl implements SecurityService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public String findLoggedInUsername() {
        SecurityContext secContext = SecurityContextHolder.getContext();
        Object userDetails = secContext.getAuthentication().getDetails();

        if(userDetails instanceof UserDetails){
            return ((UserDetails) userDetails).getUsername();
        }
        return null;
    }

    @Override
    public boolean isRegistredUser(){
        SecurityContext secContext = SecurityContextHolder.getContext();
        return !"anonymousUser".equals(secContext.getAuthentication().getPrincipal().toString());
    }

    @Override
    public void autoLogin(String username, String password) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
        authenticationManager.authenticate(token);

        if(token.isAuthenticated()){
            SecurityContextHolder.getContext().setAuthentication(token);

            logger.debug("user: '{}' is successfully logged in.", username);
        }
    }
}
