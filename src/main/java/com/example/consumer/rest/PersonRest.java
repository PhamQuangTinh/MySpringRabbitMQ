package com.example.consumer.rest;

import com.example.consumer.config.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/api/person")
public class PersonRest {

    @Autowired
    private PersonService personService;
    @PostMapping()
    public ResponseEntity<?> createPerson(){
        this.personService.saveUser();
        return ResponseEntity.ok("create user success");
    }
}
