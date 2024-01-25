package com.jeipz.etl.controller;

import com.jeipz.etl.model.Person;
import com.jeipz.etl.model.dto.CsvData;
import com.jeipz.etl.service.CsvHelperService;
import com.jeipz.etl.service.PersonService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping("/home")
public class HomeController {

    private final PersonService personService;

    private final CsvHelperService csvHelperService;

    public HomeController(PersonService personService, CsvHelperService csvHelperService) {
        this.personService = personService;
        this.csvHelperService = csvHelperService;
    }

    @GetMapping
    public String displayHomePage(Model model) {
        model.addAttribute("persons", personService.getAllPersons());
        return "home";
    }

    @PostMapping
    public String uploadCsv(Model model, @RequestParam("file") MultipartFile file) {
        if (csvHelperService.isCsvFile(file)) {
            List<CsvData> csvDataList = csvHelperService.extractCsvContent(file);
            List<Person> personList = personService.transformCsvContent(csvDataList);
            personService.loadContent(personList);
        }
        return "redirect:/home";
    }

}
