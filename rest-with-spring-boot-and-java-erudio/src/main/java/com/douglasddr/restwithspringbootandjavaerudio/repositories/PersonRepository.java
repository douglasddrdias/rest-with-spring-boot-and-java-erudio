package com.douglasddr.restwithspringbootandjavaerudio.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.douglasddr.restwithspringbootandjavaerudio.model.Person;

public interface PersonRepository extends JpaRepository<Person, Long>{

}
