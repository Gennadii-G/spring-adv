package web.soap;

import beans.models.Event;
import beans.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.jws.WebService;

@WebService(endpointInterface = "web.soap.EventWS")
public class EventWSImpl implements EventWS {

    @Autowired
    EventService eventService;

    @Override
    public Event getEvent(Long id) {
        return eventService.getById(id);
    }

    @Override
    public void addEvent(Event event) {
        eventService.create(event);
    }

    @Override
    public boolean deleteEvent(Long id) {
        eventService.remove(eventService.getById(id));
        return true;
    }

    @Override
    public Event[] getAllEvents() {
        return (Event[]) eventService.getAll().toArray();
    }
}
