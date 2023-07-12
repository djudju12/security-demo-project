package com.example.security.securitydemo.repository;

import com.example.security.securitydemo.model.Costumer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CostumerRepository extends JpaRepository<Costumer, Integer> {
    List<Costumer> findByEmail(String email);
}
