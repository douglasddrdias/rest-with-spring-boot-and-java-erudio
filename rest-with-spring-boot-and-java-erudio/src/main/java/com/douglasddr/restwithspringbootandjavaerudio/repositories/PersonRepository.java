package com.douglasddr.restwithspringbootandjavaerudio.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.douglasddr.restwithspringbootandjavaerudio.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long>{

}
