package controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String homePage(){
        return "home"; // Здесь "home" должно соответствовать имени представления "home.html" в директории /WEB-INF/views/
    }
}
