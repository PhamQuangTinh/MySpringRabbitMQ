package com.example.consumer.rest;


import com.example.consumer.annotation.AccumulatedEventCalculation;
import com.example.consumer.enums.AccumulatedEventAction;
import com.example.consumer.request.AddNewOrderChildS1Request;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/event")
public class EventRest {

    @PostMapping("/valid_event")
    @AccumulatedEventCalculation(action = AccumulatedEventAction.VALID_EVENT)
    public ResponseEntity<?> getValidEvent(@RequestBody AddNewOrderChildS1Request addNewOrderChildS1Request){
        return ResponseEntity.ok("abc");
    }
}
