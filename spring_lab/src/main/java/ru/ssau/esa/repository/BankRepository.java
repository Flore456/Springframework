package ru.ssau.esa.repository;

import org.springframework.data.repository.CrudRepository;
import ru.ssau.esa.entity.Bank;

public interface BankRepository extends CrudRepository<Bank, String>{

}
