package web.controllers;

import beans.converters.TicketsToPdfConverter;
import beans.models.Ticket;
import beans.models.User;
import beans.services.BookingService;
import beans.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
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

    @Autowired
    private TicketsToPdfConverter ticketsToPdfConverter;

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
     * @request example '/booking/ticketsForEvent?event=The%20revenant&auditorium=Yellow%20hall&datetime=2016-02-05T21:18:00.000-05:00'
     */
    @RequestMapping(value = "/ticketsForEvent", method = RequestMethod.GET)
    public String getTicketsForEvent(Model model, @RequestParam String event, @RequestParam String auditorium,
                                     @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateTime){

        List<Ticket> tickets;
        tickets = bookingService.getTicketsForEvent(event, auditorium, dateTime);
        model.addAttribute("tickets", tickets);
        return "tickets";
    }


    @RequestMapping(value = "/pdf", method = RequestMethod.GET, headers = "Accept=application/pdf")
    public void generateReport(HttpServletResponse response, @RequestParam String event, @RequestParam String auditorium,
                               @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateTime)
            throws Exception {

        List<Ticket> tickets;
        tickets = bookingService.getTicketsForEvent(event, auditorium, dateTime);

        byte[] bytes = ticketsToPdfConverter.convert(tickets);
        streamReport(response, bytes, "tickets.pdf");
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

    private void streamReport(HttpServletResponse response, byte[] data, String name)
            throws IOException {

        response.setContentType("application/pdf");
        response.setHeader("Content-disposition", "attachment; filename=" + name);
        response.setContentLength(data.length);

        response.getOutputStream().write(data);
        response.getOutputStream().flush();
    }

}
