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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/person/v1")
@Tag(name = "People", description = "Endpoints for Managing People.")
public class PersonController {
	
	@Autowired
	private PersonServices service;

	@GetMapping(produces = {MediaTypeUtils.APPLICATION_JSON, MediaTypeUtils.APPLICATION_XML, MediaTypeUtils.APPLICATION_YAML})
	@Operation(summary = "Finds all people", description = "Finds all people", 
		tags = {"People"}, 
		responses = {
				@ApiResponse(description = "Success", responseCode = "200", 
						content = {
								@Content(mediaType = "application/json",
								array = @ArraySchema(schema = @Schema(implementation = PersonVO.class)))
								}),
				@ApiResponse(description = "Bad request", responseCode = "400", content = @Content),
				@ApiResponse(responseCode = "401", content = @Content),
				@ApiResponse(responseCode = "404", content = @Content),
				@ApiResponse(responseCode = "500", content = @Content),
		})
	public List<PersonVO> findAll() {
		return service.findAll();
	}
	
	@GetMapping(value = "/{id}", produces = {MediaTypeUtils.APPLICATION_JSON, MediaTypeUtils.APPLICATION_XML, MediaTypeUtils.APPLICATION_YAML})
	@Operation(summary = "Finds a person", description = "Finds a person", 
	tags = {"People"}, 
	responses = {
			@ApiResponse(description = "Success", responseCode = "200", 
					content = {
							@Content(schema = @Schema(implementation = PersonVO.class))
							}),
			@ApiResponse(description = "Bad request", responseCode = "400", content = @Content),
			@ApiResponse(description = "No Content", responseCode = "204", content = @Content),
			@ApiResponse(responseCode = "401", content = @Content),
			@ApiResponse(responseCode = "404", content = @Content),
			@ApiResponse(responseCode = "500", content = @Content),
			})	
	public PersonVO findById(@PathVariable(value = "id" ) Long id) throws Exception {
		return service.findById(id);
	}
	
	@PostMapping(produces = {MediaTypeUtils.APPLICATION_JSON, MediaTypeUtils.APPLICATION_XML, MediaTypeUtils.APPLICATION_YAML}, 
				 consumes = {MediaTypeUtils.APPLICATION_JSON, MediaTypeUtils.APPLICATION_XML, MediaTypeUtils.APPLICATION_YAML})
	@Operation(summary = "Adds a new person", 
			   description = "Adds a new person by passing in a JSON, XML ou YAML representation of a person.",
			   tags = {"People"}, 
	responses = {
			@ApiResponse(description = "Success", responseCode = "200", 
					content = {
							@Content(schema = @Schema(implementation = PersonVO.class))
							}),
			@ApiResponse(description = "No Content", responseCode = "204", content = @Content),
			@ApiResponse(responseCode = "401", content = @Content),
			@ApiResponse(responseCode = "500", content = @Content),
			})		
	public PersonVO createV2(@RequestBody PersonVO PersonVO) throws Exception {
		return service.create(PersonVO);
	}
	@PutMapping(produces = {MediaTypeUtils.APPLICATION_JSON, MediaTypeUtils.APPLICATION_XML, MediaTypeUtils.APPLICATION_YAML}, 
			consumes = {MediaTypeUtils.APPLICATION_JSON, MediaTypeUtils.APPLICATION_XML, MediaTypeUtils.APPLICATION_YAML})
	@Operation(summary = "Updates a person", 
	   description = "Updates a person by passing in a JSON, XML ou YAML representation of a person.",
	   tags = {"People"}, 
	responses = {
			@ApiResponse(description = "Updated", responseCode = "200", 
					content = {
						@Content(schema = @Schema(implementation = PersonVO.class))
						}),
			@ApiResponse(description = "No Content", responseCode = "204", content = @Content),
			@ApiResponse(responseCode = "401", content = @Content),
			@ApiResponse(responseCode = "404", content = @Content),
			@ApiResponse(responseCode = "500", content = @Content),
			})		
	public PersonVO update(@RequestBody PersonVO PersonVO) throws Exception {
		return service.update(PersonVO);
	}
	
	@DeleteMapping(value = "/{id}")
	@Operation(summary = "Deletes a person", 
	   description = "Deletes a person by passing in a JSON, XML ou YAML representation of a person.",
	   tags = {"People"}, 
	responses = {
			@ApiResponse(description = "No content", responseCode = "204", content = @Content),
			@ApiResponse(responseCode = "401", content = @Content),
			@ApiResponse(responseCode = "404", content = @Content),
			@ApiResponse(responseCode = "500", content = @Content),
			})		
	public ResponseEntity<?> delete(@PathVariable(value = "id" ) Long id) throws Exception {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}	
}
