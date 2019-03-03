package beans.daos;

import beans.models.Role;

import java.util.List;

public interface RoleDAO {

    Role getOne(long id);

    List<Role> getAll();

}
