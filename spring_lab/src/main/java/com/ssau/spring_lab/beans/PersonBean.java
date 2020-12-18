package com.ssau.spring_lab.beans;

import com.ssau.spring_lab.entity.Person;

import org.springframework.stereotype.Component;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;

@Component
public class PersonBean {

    @PersistenceContext(unitName = "esa")
    private EntityManager em;

    public Collection<Person> findAll(){
        return em.createQuery("SELECT p FROM Person p", Person.class).getResultList();
    }
}
