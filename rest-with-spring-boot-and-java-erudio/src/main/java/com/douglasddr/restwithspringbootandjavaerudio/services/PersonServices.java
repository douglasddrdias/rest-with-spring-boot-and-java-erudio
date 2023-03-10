package com.douglasddr.restwithspringbootandjavaerudio.services;

import static com.douglasddr.restwithspringbootandjavaerudio.mapper.DozerMapper.parseListObjects;
import static com.douglasddr.restwithspringbootandjavaerudio.mapper.DozerMapper.parseObject;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douglasddr.restwithspringbootandjavaerudio.controllers.PersonController;
import com.douglasddr.restwithspringbootandjavaerudio.data.vo.v1.PersonVO;
import com.douglasddr.restwithspringbootandjavaerudio.exceptions.RequiredObjectsIsNullException;
import com.douglasddr.restwithspringbootandjavaerudio.exceptions.ResourceNotFoundException;
import com.douglasddr.restwithspringbootandjavaerudio.model.Person;
import com.douglasddr.restwithspringbootandjavaerudio.repositories.PersonRepository;

@Service
public class PersonServices {

	private Logger logger = Logger.getLogger(PersonServices.class.getName());
	
	@Autowired
	PersonRepository repository;

	
	public List<PersonVO> findAll() {
		logger.info("Finding all people!");
		var persons =  parseListObjects(repository.findAll(), PersonVO.class);
		persons
			.stream()
			.forEach(p -> {
				try {
					p.add(linkTo(methodOn(PersonController.class).findById(p.getKey())).withSelfRel());
				} catch (Exception e) {
					new RuntimeException(e);
				}
			});
		return persons;
	}
	
	public PersonVO findById(Long id) throws Exception {
		logger.info("Finding one person!");
		var entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
		PersonVO vo = parseObject(entity, PersonVO.class);
		vo.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
		return vo;
	}
	
	public PersonVO create(PersonVO person) throws Exception {
		logger.info("Creating one person!");
		if (person == null) {
			throw new RequiredObjectsIsNullException();
		}
		var entity = parseObject(person, Person.class);
		var vo = parseObject(repository.save(entity), PersonVO.class);
		vo.add(linkTo(methodOn(PersonController.class).findById(entity.getId())).withSelfRel());
		return vo;
	}
	public PersonVO update(PersonVO person) throws Exception {
		logger.info("Updating one person!");
		if (person == null) {
			throw new RequiredObjectsIsNullException();
		}		
		Person entity = repository.findById(person.getKey()).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName());
		entity.setAddress(person.getAddress());
		entity.setGender(person.getGender());
		var vo = parseObject(repository.save(entity), PersonVO.class);
		vo.add(linkTo(methodOn(PersonController.class).findById(entity.getId())).withSelfRel());
		return vo;
	}
	public void delete(Long id) throws ResourceNotFoundException {
		logger.info("Deleting one person!");
		Person entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
		repository.delete(entity);
	}

	
}
