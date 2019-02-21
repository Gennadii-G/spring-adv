package web.controllers;

import beans.models.Event;
import beans.models.User;
import beans.services.DiscountService;
import beans.services.EventService;
import beans.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/discount")
public class DiscountController {

    @Autowired
    private DiscountService discountService;

    @Autowired
    private UserService userService;

    @Autowired
    private EventService eventService;

    @RequestMapping(method = RequestMethod.GET)
    public String getDiscount(@RequestParam String eventId, @RequestParam String userId){
        Event event = eventService.getById(Long.valueOf(eventId));
        User user = userService.getById(Long.valueOf(userId));
        discountService.getDiscount(user, event);
        return "somePage";
    }
}
