package com.douglasddr.restwithspringbootandjavaerudio.services;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

import javax.management.relation.RelationNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douglasddr.restwithspringbootandjavaerudio.model.Person;
import com.douglasddr.restwithspringbootandjavaerudio.repositories.PersonRepository;

@Service
public class PersonServices {

	private Logger logger = Logger.getLogger(PersonServices.class.getName());
	
	@Autowired
	PersonRepository repository;
	
	public List<Person> findAll() {
		logger.info("Finding all peplo!");

		return repository.findAll();
	}
	
	public Person findById(Long id) throws RelationNotFoundException {
		logger.info("Finding one person!");
		Person person = new Person();
		return repository.findById(id).orElseThrow(() -> new RelationNotFoundException("No records found for this ID!"));
	}
	
	public Person create(Person person) {
		logger.info("Creating one person!");
		return repository.save(person);
	}
	public Person update(Person person) throws RelationNotFoundException {
		logger.info("Updating one person!");
		Person entity = repository.findById(person.getId()).orElseThrow(() -> new RelationNotFoundException("No records found for this ID!"));
		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName());
		entity.setAddress(person.getAddress());
		entity.setGender(person.getGender());
		return repository.save(person);
	}
	public void delete(Long id) throws RelationNotFoundException {
		logger.info("Deleting one person!");
		Person entity = repository.findById(id).orElseThrow(() -> new RelationNotFoundException("No records found for this ID!"));
		repository.delete(entity);
	}
	
}
