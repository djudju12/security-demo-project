//package com.example.security.securitydemo.controller;
//
//import com.example.security.securitydemo.model.Costumer;
//import com.example.security.securitydemo.repository.CostumerRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//public class LoggingController {
//
//    private final CostumerRepository costumerRepository;
//
//    @Autowired
//    public LoggingController(CostumerRepository costumerRepository) {
//        this.costumerRepository = costumerRepository;
//    }
//
//    @PostMapping("/register")
//    public ResponseEntity<String> createUser(@RequestBody Costumer costumer) {
//        ResponseEntity<String> response = null;
//        Costumer savedCostumer;
//        try {
//            String hashedPass = passwordEncoder.encode(costumer.getPwd());
//            costumer.setPwd(hashedPass);
//            savedCostumer = costumerRepository.save(costumer);
//            if (savedCostumer.getId() != 0)
//                response = ResponseEntity.status(HttpStatus.CREATED).body("Created");
//        } catch (Exception exception) {
//            response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("fail");
//        }
//
//        return response;
//    }
//
//}
