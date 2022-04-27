package com.example.consumer.config.service.impl;

import com.example.consumer.config.service.PersonService;
import com.example.consumer.spring.db.tables.Person;
import com.example.consumer.spring.db.tables.records.PersonRecord;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;

import static com.example.consumer.spring.db.Tables.PERSON;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private DSLContext dsl;

    @Override
    public void saveUser() {
        PersonRecord person = dsl.newRecord(PERSON);

        person.setId(10L);
        person.setEmail("10@gmail.com");
        person.setStatus(1);

        person.store();
    }
}
