package beans.services;

/**
 * Service for security.
 *
 * @author Gennadii Borodin
 * @version 1.0
 *
 */
public interface SecurityService {

    String findLoggedInUsername();

    void autoLogin(String username, String password);

}
