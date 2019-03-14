package beans.daos;

import beans.models.User;
import beans.models.UserAccount;

import java.util.List;

public interface UserAccountDAO {

    UserAccount create(UserAccount userAccount);

    UserAccount update(UserAccount event);

    UserAccount getByUser(User user);

    void delete(UserAccount event);

    UserAccount getById(Long id);

    List getAll();
}
