package ru.ssau.esa.controller;

import ru.ssau.esa.entity.Person;
import ru.ssau.esa.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class MainController {

    @Autowired
    private PersonRepository personRepository;

    @GetMapping("/persons")
    @ResponseBody
    public List<Person> showAllPersons() {

        return personRepository.findAll();



    }


}
