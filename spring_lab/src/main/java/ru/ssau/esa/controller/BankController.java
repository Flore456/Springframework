package ru.ssau.esa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ssau.esa.entity.Bank;
import ru.ssau.esa.repository.BankRepository;
import ru.ssau.esa.response.BadResponse;
import ru.ssau.esa.response.GoodResponse;
import ru.ssau.esa.response.ServerResponse;

import java.util.UUID;

@RestController
public class BankController {
    private final BankRepository repository;

    @Autowired
    public BankController(BankRepository repository){
        this.repository = repository;
    }

    @GetMapping(path = "/banks", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    private Iterable<Bank> findAll(){
        return repository.findAll();
    }

    @GetMapping(path = "/add/bank",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    private ServerResponse add(String account_num, int account_year, int number_card, String manager_name){
        Bank bank = new Bank();
        bank.setAccount_num(UUID.randomUUID().toString());
        bank.setAccount_year(account_year);
        bank.setNumber_card(number_card);
        bank.setManager_name(manager_name);
        Bank b = repository.save(bank);
        return new GoodResponse(b);
    }

    @GetMapping(path = "/delete/bank", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    private ServerResponse delete(String account_num){
        Bank bank = repository.findById(account_num).orElse(null);
        if (bank == null){
            return new BadResponse("No Bank!");
        }
        if (!bank.getPersons().isEmpty()){
            return new BadResponse("The Bank has customers!");
        }
        repository.delete(bank);
        return new GoodResponse();
    }
}
