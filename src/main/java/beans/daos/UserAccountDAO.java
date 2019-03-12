package beans.daos;

import beans.models.User;
import beans.models.UserAccount;

public interface UserAccountDAO {

    UserAccount create(UserAccount userAccount);

    UserAccount update(UserAccount event);

    UserAccount get(User user);

    void delete(UserAccount event);

    UserAccount getById(Long id);

}
