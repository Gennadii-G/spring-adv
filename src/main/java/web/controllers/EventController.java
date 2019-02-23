package web.controllers;

import beans.models.Event;
import beans.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;
import java.util.List;


@Controller
@RequestMapping(value = "/event")
public class EventController {

    @Autowired
    private EventService eventService;

    @RequestMapping(value = "/byName")
    public String getEvent(Model model, @RequestParam String name) {
        List<Event> events = eventService.getByName(name);
        model.addAttribute("events", events);
        return "events";
    }

    @RequestMapping(value = "/byId")
    public String getById(Model model, @RequestParam Long id) {
        Event event = eventService.getById(id);
        model.addAttribute("events", Collections.singletonList(event));
        return "events";
    }

    @RequestMapping(value = "/all")
    public String getAll(Model model) {
        List<Event> events = eventService.getAll();
        model.addAttribute("events", events);
        return "events";
    }

}
