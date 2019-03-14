package beans.services;

import beans.daos.UserAccountDAO;
import beans.models.User;
import beans.models.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service("userAccountServiceImpl")
public class UserAccountServiceImpl implements UserAccountService {

    @Autowired
    private UserAccountDAO userAccountDAO;

    @Override
    @Transactional
    public UserAccount create(UserAccount userAccount) {
        return userAccountDAO.create(userAccount);
    }

    @Override
    @Transactional
    public void remove(UserAccount userAccount) {
        userAccountDAO.delete(userAccount);
    }

    @Override
    @Transactional
    public UserAccount getById(long id) {
        return userAccountDAO.getById(id);
    }

    @Override
    @Transactional
    public UserAccount getByUser(User user) {
        return userAccountDAO.getByUser(user);
    }

    @Override
    @Transactional
    public List<UserAccount> getAll() {
        return userAccountDAO.getAll();
    }
}
