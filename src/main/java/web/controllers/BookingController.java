package web.controllers;

import beans.models.Ticket;
import beans.models.User;
import beans.services.BookingService;
import beans.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping(value = "/booking")
public class BookingController {

    private static final String VIEW = "page";

    @Autowired
    private BookingService bookingService;

    @Autowired
    private UserService userService;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value = "/ticketPrice", method = RequestMethod.GET)
    public String getTicketPrice(Model model, @RequestParam String event, @RequestParam String auditorium,
                                 @RequestParam LocalDateTime date, @RequestParam String userId) {

        User user = userService.getById(Long.valueOf(userId));
        Double price = bookingService.getTicketPrice(event, auditorium, date,
                        Collections.singletonList(1), user);
        model.addAttribute("price", price);
        return VIEW;
    }


    @RequestMapping(value = "/bookTicket", method = RequestMethod.POST)
    public String bookTicket(Model model,@RequestParam String userId, @ModelAttribute Ticket ticket) {

        User user = userService.getById(Long.valueOf(userId));
        bookingService.bookTicket(user, ticket);
        model.addAttribute("success", true);
        return VIEW;
    }


    /**
     * @request example '/booking/ticketsForEvent?event=The%20revenant&auditorium=Yellow%20hall'
     */
    @RequestMapping(value = "/ticketsForEvent", method = RequestMethod.GET)
    public String getTicketsForEvent(Model model, @RequestParam String event, @RequestParam String auditorium){
        LocalDateTime mock = LocalDateTime.of(LocalDate.of(2016, 2, 5),
                LocalTime.of(21, 18, 0));

        List<Ticket> tickets;
        tickets = bookingService.getTicketsForEvent(event, auditorium, mock);
        model.addAttribute("tickets", tickets);
        return "tickets";
    }
}
