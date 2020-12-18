package com.ssau.spring_lab.repository;

import com.ssau.spring_lab.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository  extends JpaRepository<Person, Long>{

}
