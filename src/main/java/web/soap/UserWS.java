package web.soap;

import beans.models.User;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import javax.jws.soap.SOAPBinding.Use;

@WebService(name = "UserWS", targetNamespace = "http://spring-course/ws/userws")
@SOAPBinding(style = Style.DOCUMENT, use = Use.LITERAL)
public interface UserWS {

    @WebMethod
    User getUser(Long id);

    @WebMethod
    void addUser(User user);

    @WebMethod
    boolean deleteUser(Long id);

    @WebMethod
    User[] getAllUsers();
}
