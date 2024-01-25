package com.jeipz.etl.controller;

import com.jeipz.etl.service.PersonService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {

    private final PersonService personService;

    public HomeController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public String displayHomePage(Model model) {
        model.addAttribute("persons", personService.getAllPersons());
        return "home";
    }

    @PostMapping
    public String uploadCsv(Model model) {
        return "redirect:/home";
    }

}
