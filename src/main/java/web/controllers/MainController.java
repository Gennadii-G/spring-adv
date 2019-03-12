package web.controllers;

import beans.aspects.CounterAspect;
import beans.aspects.DiscountAspect;
import beans.aspects.LuckyWinnerAspect;
import beans.models.*;
import beans.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

@Controller
public class MainController {

    @Autowired
    private AuditoriumService auditoriumService;
    @Autowired
    private BookingService bookingService;
    @Autowired
    private EventService eventService;
    @Autowired
    private UserService userService;
    @Autowired
    private DiscountService discountService;


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String mainPage() {
        return "main";
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String admin(Model model) {
        return "admin";
    }

    @RequestMapping(value = "/npe", method = RequestMethod.GET)
    public String exc() {
        if(true){
            throw new NullPointerException("something wrong");
        }
        return "index";
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String testRequest() {

        String email = "dmitriy.vbabichev@gmail.com";
        String name = "Dmytro Babichev";
        String eventName = "The revenant";
        String auditoriumName = "Blue hall";
        Auditorium blueHall = auditoriumService.getByName(auditoriumName);
        Auditorium yellowHall = auditoriumService.getByName("Yellow hall");
        Auditorium redHall = auditoriumService.getByName("Red hall");
        LocalDateTime dateOfEvent = LocalDateTime.of(LocalDate.of(2016, 2, 5), LocalTime.of(15, 45, 0));

        userService.register(new User(email, name, LocalDate.now()));
        userService.register(new User("laory@yandex.ru", name, LocalDate.of(1992, 4, 29)));

        User userByEmail = userService.getUserByEmail(email);
        System.out.println("User with email: [" + email + "] is " + userByEmail);
        System.out.println();

        System.out.println("All users with name: [" + name + "] are: ");
        userService.getUsersByName(name).forEach(System.out:: println);
        System.out.println();

        Event event1 = eventService.create(
                new Event(eventName, Rate.HIGH, 60, LocalDateTime.of(LocalDate.of(2016, 2, 5), LocalTime.of(9, 0, 0)),
                        blueHall));
        System.out.println();
        System.out.println("Event by name: " + eventService.getByName(event1.getName()));
        System.out.println();
        eventService.create(new Event(eventName, Rate.HIGH, 60, dateOfEvent, blueHall));
        Event event2 = eventService.create(
                new Event(eventName, Rate.HIGH, 60, LocalDateTime.of(LocalDate.of(2016, 2, 5), LocalTime.of(21, 18, 0)),
                        blueHall));
        eventService.create(
                new Event(eventName, Rate.HIGH, 90, LocalDateTime.of(LocalDate.of(2016, 2, 5), LocalTime.of(21, 18, 0)),
                        redHall));
        Event event = new Event(eventName, Rate.HIGH, 150,
                LocalDateTime.of(LocalDate.of(2016, 2, 5), LocalTime.of(21, 18, 0)), yellowHall);
        event = eventService.create(event);

        System.out.println("List of all events:");
        eventService.getAll().forEach(System.out:: println);
        System.out.println();

        System.out.println(
                "Discount for user: [" + email + "] for event: [" + eventName + "] in auditorium: [" + auditoriumName +
                        "] on date: [" + dateOfEvent + "] is [" +
                        discountService.getDiscount(userByEmail, eventService.getEvent(eventName, blueHall, dateOfEvent))
                        + "]");
        System.out.println();

        eventService.remove(event2);
        System.out.println("List of all events:");
        eventService.getAll().forEach(System.out:: println);
        System.out.println();

        List<Integer> seats = Arrays.asList(23, 24, 25, 26);
        double ticketPrice = bookingService.getTicketPrice(event.getName(), event.getAuditorium().getName(),
                event.getDateTime(), seats, userByEmail);
        System.out.println("Price for event: [" + event + "], seats: [" + seats + "], user: [" + userByEmail + "] = "
                + ticketPrice);

        List<Integer> seats2 = Arrays.asList(27, 28, 29, 30);
        List<Integer> seats3 = Arrays.asList(2, 8, 9, 3);
        bookingService.bookTicket(userByEmail, new Ticket(event, LocalDateTime.now(), seats, userByEmail, ticketPrice));
        bookingService.bookTicket(userByEmail, new Ticket(event, LocalDateTime.now(), seats2, userByEmail,
                bookingService.getTicketPrice(event.getName(),
                        event.getAuditorium().getName(),
                        event.getDateTime(), seats2,
                        userByEmail)));
        bookingService.bookTicket(userByEmail, new Ticket(event, LocalDateTime.now(), seats3, userByEmail,
                bookingService.getTicketPrice(event.getName(),
                        event.getAuditorium().getName(),
                        event.getDateTime(), seats3,
                        userByEmail)));

        System.out.println();
        System.out.println("Tickets booked for event: [" + event + "]");
        List<Ticket> ticketsForEvent = bookingService.getTicketsForEvent(event.getName(),
                event.getAuditorium().getName(),
                event.getDateTime());
        IntStream.range(0, ticketsForEvent.size()).forEach(
                i -> System.out.println("" + i + ") " + ticketsForEvent.get(i)));

        System.out.println();
        eventService.getByName("testName1");
        eventService.getByName("testName2");
        eventService.getByName("testName2");
        eventService.getByName("testName3");
        eventService.getByName(eventName);
        eventService.getEvent(event.getName(), event.getAuditorium(), event.getDateTime());

        bookingService.getTicketPrice(event.getName(), event.getAuditorium().getName(), event.getDateTime(), seats,
                userByEmail);
        bookingService.getTicketPrice(event.getName(), event.getAuditorium().getName(), event.getDateTime(), seats,
                userByEmail);

        System.out.println("CounterAspect.getAccessByNameStat() = " + CounterAspect.getAccessByNameStat());
        System.out.println("CounterAspect.getGetPriceByNameStat() = " + CounterAspect.getGetPriceByNameStat());
        System.out.println("CounterAspect.getBookTicketByNameStat() = " + CounterAspect.getBookTicketByNameStat());
        System.out.println();
        System.out.println("DiscountAspect.getDiscountStatistics() = " + DiscountAspect.getDiscountStatistics());
        System.out.println();
        System.out.println("LuckyWinnerAspect.getLuckyUsers() = " + LuckyWinnerAspect.getLuckyUsers());

        return "success";
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
