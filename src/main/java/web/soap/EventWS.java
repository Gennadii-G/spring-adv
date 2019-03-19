package web.soap;

import beans.models.Event;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import javax.jws.soap.SOAPBinding.Use;

@WebService(name = "EventWS", targetNamespace = "http://spring-course/ws/eventws")
@SOAPBinding(style = Style.DOCUMENT, use = Use.LITERAL)
public interface EventWS {

    @WebMethod
    Event getEvent(Long id);

    @WebMethod
    void addEvent(Event event);

    @WebMethod
    boolean deleteEvent(Long id);

    @WebMethod
    Event[] getAllEvents();
}
