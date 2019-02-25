package web.controllers;

import beans.models.Auditorium;
import beans.models.Event;
import beans.models.Rate;
import beans.services.AuditoriumService;
import beans.services.EventService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Controller
@RequestMapping(value = "upload")
public class BatchLoadingController {

    @Autowired
    private EventService eventService;

    @Autowired
    private AuditoriumService auditoriumService;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * @ExampleOfXml
     * <events>
     *   <event>
     *            <name>ipsum</name>
     *            <rate>HIGH</rate>
     *            <baseprice>30.00</baseprice>
     *            <datetime>2016-02-05T21:00:00+04:00</datetime>
     *           <auditorium>Yellow hall</auditorium>
     *   </event>
     * <events>
     * @DateForTest: LocalDateTime.of(LocalDate.of(2016, 2, 5), LocalTime.of(9, 0, 0)
     * @param file
     * @param modelMap
     * @throws IOException
     * @throws ParserConfigurationException
     * @throws SAXException
     */
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/events", method = RequestMethod.POST)
    public void uploadEvents(@RequestParam("eventsFile") MultipartFile file, ModelMap modelMap)
            throws IOException, ParserConfigurationException, SAXException {

        String body = new String(file.getBytes());
        logger.info("got file: {}", file.getOriginalFilename());
        logger.info(new String(file.getBytes()));
        File xmlFile = new File(body);

// Dom parsing
        DocumentBuilderFactory dbfactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = dbfactory.newDocumentBuilder();
        Document document = builder.parse(file.getInputStream());

        Element root = document.getDocumentElement();
        root.normalize();

        NodeList nList = document.getElementsByTagName("event");

        for (int i = 0; i < nList.getLength(); i++)
        {
            Node node = nList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE)
            {
                Element eElement = (Element) node;
                String name = eElement.getAttribute("name");
                String strRate = eElement.getElementsByTagName("rate").item(0).getTextContent();
                String baseprice = eElement.getElementsByTagName("baseprice").item(0).getTextContent();
                String strDateTime = eElement.getElementsByTagName("datetime").item(0).getTextContent();
                String strAuditorium = eElement.getElementsByTagName("auditorium").item(0).getTextContent();
                LocalDateTime dateTime = LocalDateTime.parse(strDateTime, DateTimeFormatter.ISO_DATE_TIME);

                Rate rate = Rate.valueOf(strRate);
                Auditorium auditorium = auditoriumService.getByName(strAuditorium);
                Double price = Double.valueOf(baseprice);
                Event event = eventService.create(new Event(name, rate, price, dateTime, auditorium));
            }
        }
    }


}
