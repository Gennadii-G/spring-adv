package web.controllers;

import beans.models.Ticket;
import beans.models.User;
import beans.services.BookingService;
import beans.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.Collections;

@Controller
@RequestMapping(value = "/booking")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/ticketPrice", method = RequestMethod.GET)
    public String getTicketPrice(@RequestParam String event, @RequestParam String auditorium,
                                       LocalDateTime date, String userId) {

        User user = userService.getById(Long.valueOf(userId));

        Double price = bookingService.getTicketPrice(event, auditorium, date,
                        Collections.singletonList(1), user);
        return String.valueOf(price);
    }

    @RequestMapping(value = "/bookTicket", method = RequestMethod.POST)
    public String bookTicket(@RequestParam String userId, @ModelAttribute Ticket ticket) {

        User user = userService.getById(Long.valueOf(userId));

        bookingService.bookTicket(user, ticket);
        return "success";
    }
}
