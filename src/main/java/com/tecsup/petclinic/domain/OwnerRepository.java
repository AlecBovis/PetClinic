package com.tecsup.petclinic.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface OwnerRepository extends CrudRepository<Owner, Long> {
	
	List<Owner> findByFirstName(String firstName);
	
	List<Owner> findByLastName(String lastName);
	
	List<Owner> findBycity(String city);
	
}
