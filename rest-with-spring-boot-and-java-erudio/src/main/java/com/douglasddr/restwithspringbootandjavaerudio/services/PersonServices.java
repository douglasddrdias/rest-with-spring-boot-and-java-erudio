package com.douglasddr.restwithspringbootandjavaerudio.services;

import static com.douglasddr.restwithspringbootandjavaerudio.mapper.DozerMapper.parseListObjects;
import static com.douglasddr.restwithspringbootandjavaerudio.mapper.DozerMapper.parseObject;

import java.util.List;
import java.util.logging.Logger;

import javax.management.relation.RelationNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douglasddr.restwithspringbootandjavaerudio.data.vo.v1.PersonVO;
import com.douglasddr.restwithspringbootandjavaerudio.model.Person;
import com.douglasddr.restwithspringbootandjavaerudio.repositories.PersonRepository;

@Service
public class PersonServices {

	private Logger logger = Logger.getLogger(PersonServices.class.getName());
	
	@Autowired
	PersonRepository repository;
	
	public List<PersonVO> findAll() {
		logger.info("Finding all people!");

		return parseListObjects(repository.findAll(), PersonVO.class);
	}
	
	public PersonVO findById(Long id) throws RelationNotFoundException {
		logger.info("Finding one person!");
		var entity = repository.findById(id).orElseThrow(() -> new RelationNotFoundException("No records found for this ID!"));
		return parseObject(entity, PersonVO.class);
	}
	
	public PersonVO create(PersonVO person) {
		logger.info("Creating one person!");
		var entity = parseObject(person, Person.class);
		var vo = parseObject(repository.save(entity), PersonVO.class);
		return vo;
	}
	public PersonVO update(PersonVO person) throws RelationNotFoundException {
		logger.info("Updating one person!");
		Person entity = repository.findById(person.getId()).orElseThrow(() -> new RelationNotFoundException("No records found for this ID!"));
		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName());
		entity.setAddress(person.getAddress());
		entity.setGender(person.getGender());
		var vo = parseObject(repository.save(entity), PersonVO.class);
		return vo;
	}
	public void delete(Long id) throws RelationNotFoundException {
		logger.info("Deleting one person!");
		Person entity = repository.findById(id).orElseThrow(() -> new RelationNotFoundException("No records found for this ID!"));
		repository.delete(entity);
	}
	
}
