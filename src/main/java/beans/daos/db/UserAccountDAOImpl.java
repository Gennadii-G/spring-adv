package beans.daos.db;

import beans.daos.AbstractDAO;
import beans.daos.UserAccountDAO;
import beans.models.User;
import beans.models.UserAccount;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository(value = "userAccountDAO")
public class UserAccountDAOImpl extends AbstractDAO implements UserAccountDAO {

    @Override
    public UserAccount create(UserAccount userAccount) {
        Long accId = (Long) getCurrentSession().save(userAccount);
        userAccount.setId(accId);
        return userAccount;
    }

    @Override
    public UserAccount update(UserAccount userAccount) {
        return ((UserAccount) getCurrentSession().merge(userAccount));
    }

    @Override
    public UserAccount get(User user) {
        return (UserAccount) createBlankCriteria(UserAccount.class)
                .add(Restrictions.eq("user", user.getId()));asdftg
    }

    @Override
    public void delete(UserAccount event) {

    }

    @Override
    public UserAccount getById(Long id) {
        return null;
    }
}
