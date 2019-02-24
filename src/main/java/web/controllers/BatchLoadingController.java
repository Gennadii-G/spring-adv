package web.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping(value = "upload")
public class BatchLoadingController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value = "/events", method = RequestMethod.POST)
    public String uploadEvents(@RequestParam("eventsFile") MultipartFile file, ModelMap modelMap) throws IOException {
//        file.getInputStream();
        String body = new String(file.getBytes());
        logger.info("got file: {}", file.getOriginalFilename());
        logger.info(new String(file.getBytes()));
        File file1 = new File(body);
        return "success";
    }

}
