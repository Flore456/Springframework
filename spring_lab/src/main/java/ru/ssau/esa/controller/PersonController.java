package ru.ssau.esa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ssau.esa.entity.Car;
import ru.ssau.esa.entity.Bank;
import ru.ssau.esa.entity.Person;
import ru.ssau.esa.repository.PersonRepository;
import ru.ssau.esa.repository.BankRepository;
import ru.ssau.esa.repository.CarRepository;
import ru.ssau.esa.response.GoodResponse;
import ru.ssau.esa.response.BadResponse;
import ru.ssau.esa.response.ServerResponse;
import java.sql.Date;

@RestController
public class PersonController {

    private final PersonRepository repository;
    private final CarRepository carRepository;
    private final BankRepository bankRepository;

    @Autowired
    public PersonController(PersonRepository repository, CarRepository carRepository, BankRepository bankRepository) {
        this.repository=repository;
        this.bankRepository=bankRepository;
        this.carRepository=carRepository;
    }

    @GetMapping(path = "/persons",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    private Iterable<Person> findAll(){
        return repository.findAll();
    }

    @GetMapping(path = "/add/animal", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    private ServerResponse add(String name, String first_name, String city, Date birthday,String bankId,String carId){
        Person person = new Person();
        person.setName(name);
        person.setFirst_name(first_name);
        person.setCity(city);
        person.setBirthday(birthday);

        Bank b = bankRepository.findById(bankId).orElse(null);
        if (b == null){
            return new BadResponse("No Bank!");
        }
        person.setBank(b);

        Car c = carRepository.findById(carId).orElse(null);
        if (c == null){
            return new BadResponse("No Car!");
        }

        person.setCar(c);

        Person newPerson = repository.save(person);
        return new GoodResponse(newPerson);

    }

    @GetMapping(path = "/delete/person",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)

    private ServerResponse delete(String id){
        Person person = repository.findById(id).orElse(null);
        if (person == null){
            return new BadResponse("No Person!");
        }
        repository.delete(person);
        return new GoodResponse();
    }


}
