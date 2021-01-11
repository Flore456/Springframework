package ru.ssau.esa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ssau.esa.entity.Bank;

@Repository
public interface BankRepository extends JpaRepository<Bank, String>{

}
