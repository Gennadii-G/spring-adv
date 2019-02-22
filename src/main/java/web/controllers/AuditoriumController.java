package web.controllers;

import beans.models.Auditorium;
import beans.services.AuditoriumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping(value = "/auditorium")
public class AuditoriumController {

    @Autowired
    private AuditoriumService auditoriumService;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public String getAllAuditorium(Model model){

        List<Auditorium> auditoriums = auditoriumService.getAuditoriums();
        model.addAttribute("auditoriums", auditoriums);
        return "auditoriums";
    }

//    /auditorium/byName?name=Blue%20hall
    @RequestMapping(value = "/byName")
    public String getAuditorium(Model model, @RequestParam String name){
        Auditorium auditorium = auditoriumService.getByName(name);
        model.addAttribute("auditoriums", Collections.singletonList(auditorium));
        return "auditoriums";
    }
}
