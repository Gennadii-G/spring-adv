package web.soap;

import beans.models.User;
import beans.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.jws.WebService;

@WebService(endpointInterface = "web.soap.UserWS")
public class UserWSImpl implements UserWS {

    @Autowired
    private UserService userService;
    
    @Override
    public User getUser(Long id) {
        return userService.getById(id);
    }

    @Override
    public void addUser(User user) {
        userService.register(user);
    }

    @Override
    public boolean deleteUser(Long id) {
        userService.remove(userService.getById(id));
        return true;
    }

    @Override
    public User[] getAllUsers() {
        return (User[]) userService.getAll().toArray();
    }
}
