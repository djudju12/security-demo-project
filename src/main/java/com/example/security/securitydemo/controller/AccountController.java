package com.example.security.securitydemo.controller;

import com.example.security.securitydemo.model.Costumer;
import com.example.security.securitydemo.repository.CostumerRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AccountController {

    private final CostumerRepository repository;

    public AccountController(CostumerRepository repository) {
        this.repository = repository;
    }


    @GetMapping("/myAccount")
    public Costumer getAccountDetails(@RequestParam String email) {
        List<Costumer> costumers;
        costumers = repository.findByEmail(email);
        if (costumers != null && !costumers.isEmpty()) {
            return costumers.get(0);
        }
        return null;
    }

}
