package com.tecsup.petclinic.service;

import java.util.List;

import com.tecsup.petclinic.domain.Owner;
import com.tecsup.petclinic.domain.Pet;
import com.tecsup.petclinic.exception.OwnerNotFoundException;
import com.tecsup.petclinic.exception.PetNotFoundException;

public interface OwnerService {
	
	List<Owner> findByFirstName(String firstName);
	
	List<Owner> findByLastName(String lastName);
	
	List<Owner> findByCity(String city);
	
	Owner create(Owner owner);
	
	Owner update(Owner owner);
	
	Owner findById(long id) throws OwnerNotFoundException;
	
	void delete(Long id) throws OwnerNotFoundException;
	
	Iterable<Owner> findAll();
	
	

}
