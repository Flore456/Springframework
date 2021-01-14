package ru.ssau.esa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ssau.esa.entity.Car;
import ru.ssau.esa.repository.CarRepository;
import ru.ssau.esa.response.BadResponse;
import ru.ssau.esa.response.GoodResponse;
import ru.ssau.esa.response.ServerResponse;

@RestController
public class CarController {

    private final CarRepository repository;

    @Autowired
    public CarController(CarRepository repository) {
        this.repository = repository;
    }

    @GetMapping(path = "/cars", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    private Iterable<Car> findAll(){
        return repository.findAll();
    }

    @GetMapping(path = "/add/car", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    private ServerResponse add(String brand,String color){
        Car car = new Car();
        car.setBrand(brand);
        car.setColor(color);
        Car c = repository.save(car);
        return  new GoodResponse(c);
    }

    @GetMapping(path = "/delete/car", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    private  ServerResponse delete(String car_id){
        Car car = repository.findById(car_id).orElse(null);
        if (car == null){
            return new BadResponse("No Car!");
        }
        if (!car.getPersons().isEmpty()){
            return new BadResponse("There is at least one car with an owner.");
        }
        repository.delete(car);
        return new GoodResponse();
    }
}
