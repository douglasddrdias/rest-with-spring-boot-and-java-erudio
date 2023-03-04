package com.douglasddr.restwithspringbootandjavaerudio.services;

import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

import org.springframework.stereotype.Service;

import com.douglasddr.restwithspringbootandjavaerudio.model.Person;

@Service
public class PersonServices {

	private final AtomicLong counter = new AtomicLong();
	private Logger logger = Logger.getLogger(PersonServices.class.getName());
	
	public Person findById(String id) {
		logger.info("Finding one person!");
		Person person = new Person();
		person.setId(counter.incrementAndGet());
		person.setFirstName("Douglas");
		person.setLastName("Rocha");
		person.setAddress("Santa Luzia - Minas Gerais - Brasil");
		person.setGender("Male");
		return person;
	}
}
