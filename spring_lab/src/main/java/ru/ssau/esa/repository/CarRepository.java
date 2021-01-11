package ru.ssau.esa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ssau.esa.entity.Car;

@Repository
public interface CarRepository extends JpaRepository<Car,String>{

}
