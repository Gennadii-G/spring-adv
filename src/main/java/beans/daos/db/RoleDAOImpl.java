package beans.daos.db;

import beans.daos.AbstractDAO;
import beans.daos.RoleDAO;
import beans.models.Role;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "roleDAO")
public class RoleDAOImpl extends AbstractDAO implements RoleDAO {

    @Override
    public Role getOne(long id) {
        return ((Role) createBlankCriteria(Role.class).add(Restrictions.eq("id", id)).uniqueResult());
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Role> getAll() {
        return ((List<Role>) createBlankCriteria(Role.class).list());
    }
}
