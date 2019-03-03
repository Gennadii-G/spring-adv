package web.controllers;

import beans.models.User;
import beans.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/byId")
    public String getById(Model model, Long id){
        List<User> users = Collections.singletonList(userService.getById(id));
        model.addAttribute("users", users);
        return "users";
    }

    @RequestMapping(value = "/byEmail")
    public String getUserByEmail(Model model, String email){
        List<User> users = Collections.singletonList(userService.getUserByEmail(email));
        model.addAttribute("users", users);
        return "users";
    }

    @RequestMapping(value = "/byName")
    public String getById(Model model, String name){
        List<User> users = userService.getUsersByName(name);
        model.addAttribute("users", users);
        return "users";
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handleException(Exception exception, HttpServletRequest request) {
        exception.printStackTrace();

        ModelAndView mav = new ModelAndView();
        String message = exception.getMessage().replaceAll(";", ";\n");
        mav.addObject("excClass", exception.getClass());
        mav.addObject("message", message);
        mav.setViewName("error");
        return mav;
    }

}
