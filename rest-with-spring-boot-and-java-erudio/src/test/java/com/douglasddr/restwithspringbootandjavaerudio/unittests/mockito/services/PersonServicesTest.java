package com.douglasddr.restwithspringbootandjavaerudio.unittests.mockito.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.Optional;

import javax.management.relation.RelationNotFoundException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.douglasddr.restwithspringbootandjavaerudio.data.vo.v1.PersonVO;
import com.douglasddr.restwithspringbootandjavaerudio.exceptions.RequiredObjectsIsNullException;
import com.douglasddr.restwithspringbootandjavaerudio.model.Person;
import com.douglasddr.restwithspringbootandjavaerudio.repositories.PersonRepository;
import com.douglasddr.restwithspringbootandjavaerudio.services.PersonServices;
import com.douglasddr.restwithspringbootandjavaerudio.unittests.mapper.mocks.MockPerson;

@TestInstance(Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class PersonServicesTest {

	MockPerson input;
	
	@InjectMocks
	private PersonServices service;
	
	@Mock
	PersonRepository repository;
	
	@BeforeEach
	void setUpMocks() throws Exception {
		input = new MockPerson();
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testFindAll() throws Exception {
		var list = input.mockEntityList();
		when(repository.findAll()).thenReturn(list);
		var people = service.findAll();
		assertNotNull(people);	
		assertEquals(14, people.size());
		var personOne = people.get(1);
		assertNotNull(personOne);
		assertNotNull(personOne.getKey());
		assertNotNull(personOne.getLinks());
		assertTrue(personOne.toString().contains("links: [</api/person/v1/1>;rel=\"self\"]"));
		assertEquals("Addres Test" + personOne.getKey(), personOne.getAddress());
		assertEquals("First Name Test"+ personOne.getKey(), personOne.getFirstName());
		assertEquals("Last Name Test"+ personOne.getKey(), personOne.getLastName());
		assertEquals("Female", personOne.getGender());		
		
		var personFour = people.get(4);
		assertNotNull(personFour);
		assertNotNull(personFour.getKey());
		assertNotNull(personFour.getLinks());
		assertTrue(personFour.toString().contains("links: [</api/person/v1/4>;rel=\"self\"]"));
		assertEquals("Addres Test" + personFour.getKey(), personFour.getAddress());
		assertEquals("First Name Test"+ personFour.getKey(), personFour.getFirstName());
		assertEquals("Last Name Test"+ personFour.getKey(), personFour.getLastName());
		assertEquals("Male", personFour.getGender());
		
		var personSeven = people.get(7);
		assertNotNull(personSeven);
		assertNotNull(personSeven.getKey());
		assertNotNull(personSeven.getLinks());
		assertTrue(personSeven.toString().contains("links: [</api/person/v1/7>;rel=\"self\"]"));
		assertEquals("Addres Test" + personSeven.getKey(), personSeven.getAddress());
		assertEquals("First Name Test"+ personSeven.getKey(), personSeven.getFirstName());
		assertEquals("Last Name Test"+ personSeven.getKey(), personSeven.getLastName());
		assertEquals("Female", personSeven.getGender());			
	}

	@Test
	void testFindById() throws Exception {
		Long id = 1L;
		Person entity = input.mockEntity(id.intValue());
		entity.setId(id);
		when(repository.findById(id)).thenReturn(Optional.of(entity));
		var result = service.findById(id);
		assertNotNull(result);
		assertNotNull(result.getKey());
		assertNotNull(result.getLinks());
		assertTrue(result.toString().contains("links: [</api/person/v1/1>;rel=\"self\"]"));
		assertEquals("Addres Test" + id, result.getAddress());
		assertEquals("First Name Test"+ id, result.getFirstName());
		assertEquals("Last Name Test"+ id, result.getLastName());
		assertEquals("Female", result.getGender());
	}

	@Test
	void testCreate() throws Exception {
		Long id = 1L;
		Person entity = input.mockEntity(id.intValue());
		Person persisted = entity;
		persisted.setId(id);
		PersonVO vo = input.mockVO(id.intValue());
		vo.setKey(id);
		when(repository.save(entity)).thenReturn(persisted);
		var result = service.create(vo);
		assertNotNull(result);
		assertNotNull(result.getKey());
		assertNotNull(result.getLinks());
		assertTrue(result.toString().contains("links: [</api/person/v1/1>;rel=\"self\"]"));
		assertEquals("Addres Test" + id, result.getAddress());
		assertEquals("First Name Test"+ id, result.getFirstName());
		assertEquals("Last Name Test"+ id, result.getLastName());
		assertEquals("Female", result.getGender());		
	}
	@Test
	void testCreateWithNullPerson() throws Exception {
		Exception exception = assertThrows(RequiredObjectsIsNullException.class, ()-> {
			service.create(null);
		});
		String expectedMessage = "It is not allowed to persist a null object!";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.contains(actualMessage));
	}

	@Test
	void testUpdate() throws Exception {
		Long id = 1L;
		Person entity = input.mockEntity(id.intValue());
		entity.setId(id);
		Person persisted = entity;
		persisted.setId(id);
		PersonVO vo = input.mockVO(id.intValue());
		vo.setKey(id);
		when(repository.findById(id)).thenReturn(Optional.of(entity));
		when(repository.save(entity)).thenReturn(persisted);
		var result = service.update(vo);
		assertNotNull(result);
		assertNotNull(result.getKey());
		assertNotNull(result.getLinks());
		assertTrue(result.toString().contains("links: [</api/person/v1/1>;rel=\"self\"]"));
		assertEquals("Addres Test" + id, result.getAddress());
		assertEquals("First Name Test"+ id, result.getFirstName());
		assertEquals("Last Name Test"+ id, result.getLastName());
		assertEquals("Female", result.getGender());		
	}
	@Test
	void testUpdateWithNullPerson() throws Exception {
		Exception exception = assertThrows(RequiredObjectsIsNullException.class, ()-> {
			service.update(null);
		});
		String expectedMessage = "It is not allowed to persist a null object!";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.contains(actualMessage));
	}

	@Test
	void testDelete() throws RelationNotFoundException {
		Long id = 1L;
		Person entity = input.mockEntity(id.intValue());
		entity.setId(id);
		when(repository.findById(id)).thenReturn(Optional.of(entity));
		service.delete(id);
	}

}
