package beans.services;

import beans.daos.UserDAO;
import beans.models.Role;
import beans.models.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    @Qualifier("userDAO")
    private UserDAO userDAO;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userDAO.getByEmail(email);


        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();

        for(Role role : user.getRoles()){
            String code = role.getCode();
            grantedAuthorities.add(new SimpleGrantedAuthority(code));
        }
//        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_ANONYMOUS"));

        logger.debug("USER: '{} is logged in.", user.getEmail());
        return new org.springframework.security.core.userdetails.User(user.getEmail(),
                user.getPassword(), grantedAuthorities);
    }
}
