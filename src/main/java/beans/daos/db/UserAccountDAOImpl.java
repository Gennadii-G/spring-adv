package beans.daos.db;

import beans.daos.AbstractDAO;
import beans.daos.UserAccountDAO;
import beans.models.User;
import beans.models.UserAccount;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

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
    public UserAccount getByUser(User user) {
        Query query = getCurrentSession().createQuery("from UserAccount a where a.user = :user");
        query.setParameter("user", user);
        return (UserAccount) query.uniqueResult();
    }

    @Override
    public void delete(UserAccount userAccount) {
        getCurrentSession().delete(userAccount);
    }

    @Override
    public UserAccount getById(Long id) {
        return ((UserAccount) createBlankCriteria(UserAccount.class)
                .add(Restrictions.eq("id", id)).uniqueResult());
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<UserAccount> getAll() {
        return (List<UserAccount>) createBlankCriteria(UserAccount.class).list();
    }
}
