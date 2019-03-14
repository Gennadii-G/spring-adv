package beans.services;

import beans.models.User;
import beans.models.UserAccount;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Dmytro_Babichev
 * Date: 2/1/2016
 * Time: 7:32 PM
 */
public interface UserAccountService {

    UserAccount create(UserAccount user);

    void remove(UserAccount userAccount);

    UserAccount getById(long id);

    UserAccount getByUser(User user);

    List<UserAccount> getAll();

}
