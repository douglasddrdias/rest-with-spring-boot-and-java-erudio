package com.douglasddr.restwithspringbootandjavaerudio.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.douglasddr.restwithspringbootandjavaerudio.data.vo.v1.PersonVO;
import com.douglasddr.restwithspringbootandjavaerudio.services.PersonServices;
import com.douglasddr.restwithspringbootandjavaerudio.utils.MediaTypeUtils;

@RestController
@RequestMapping("/api/person/v1")
public class PersonController {
	
	@Autowired
	private PersonServices service;

	@GetMapping(produces = {MediaTypeUtils.APPLICATION_JSON, MediaTypeUtils.APPLICATION_XML, MediaTypeUtils.APPLICATION_YAML})
	public List<PersonVO> findAll() {
		return service.findAll();
	}
	
	@GetMapping(value = "/{id}", produces = {MediaTypeUtils.APPLICATION_JSON, MediaTypeUtils.APPLICATION_XML, MediaTypeUtils.APPLICATION_YAML})
	public PersonVO findById(@PathVariable(value = "id" ) Long id) throws Exception {
		return service.findById(id);
	}
	
	@PostMapping(produces = {MediaTypeUtils.APPLICATION_JSON, MediaTypeUtils.APPLICATION_XML, MediaTypeUtils.APPLICATION_YAML}, 
				 consumes = {MediaTypeUtils.APPLICATION_JSON, MediaTypeUtils.APPLICATION_XML, MediaTypeUtils.APPLICATION_YAML})
	public PersonVO createV2(@RequestBody PersonVO PersonVO) throws Exception {
		return service.create(PersonVO);
	}
	@PutMapping(produces = {MediaTypeUtils.APPLICATION_JSON, MediaTypeUtils.APPLICATION_XML, MediaTypeUtils.APPLICATION_YAML}, 
			consumes = {MediaTypeUtils.APPLICATION_JSON, MediaTypeUtils.APPLICATION_XML, MediaTypeUtils.APPLICATION_YAML})
	public PersonVO update(@RequestBody PersonVO PersonVO) throws Exception {
		return service.update(PersonVO);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> delete(@PathVariable(value = "id" ) Long id) throws Exception {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}	
}
