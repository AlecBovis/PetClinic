package com.tecsup.petclinic.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.tecsup.petclinic.domain.Owner;
import com.tecsup.petclinic.domain.Pet;
import com.tecsup.petclinic.exception.OwnerNotFoundException;
import com.tecsup.petclinic.exception.PetNotFoundException;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class OwnerServiceTest {
	
	private static final Logger logger = LoggerFactory.getLogger(OwnerServiceTest.class);
	
	@Autowired
	private OwnerService ownerService;
	
	@Test
	public void testFindByName() {
		String NAME = "Maria";
		int SIZE_EXPECTED = 1;
		
		List<Owner> owners = ownerService.findByFirstName(NAME);
		assertEquals(SIZE_EXPECTED, owners.size());
		
	}
	
	@Test
	public void testFindByLastName() {
		String LAST_NAME = "Black";
		int SIZE_EXPECTED = 1;
		
		List<Owner> owners = ownerService.findByLastName(LAST_NAME);
		assertEquals(SIZE_EXPECTED, owners.size());
		
	}
	
	@Test
	public void testFindByCity() {
		String CITY = "Waunakee";
		int SIZE_EXPECTED = 1;
		
		List<Owner> owners = ownerService.findByCity(CITY);
		assertEquals(SIZE_EXPECTED, owners.size());
		
	}
	
	@Test
	public void testDeletePet() {

		String FIRST_NAME = "Carla";
		String LAST_NAME = "Perez";
		String ADDRESS = "152 Mapache";
		String CITY = "Lima";
		String TELEPHONE ="1547954";

		Owner owner = new Owner(FIRST_NAME,LAST_NAME,ADDRESS,CITY,TELEPHONE);
		owner = ownerService.create(owner);
		logger.info("" + owner);

		try {
			ownerService.delete(owner.getId());
		} catch (OwnerNotFoundException e) {
			fail(e.getMessage());
		}		
		try {
			ownerService.findById(owner.getId());
			assertTrue(false);
		} catch (OwnerNotFoundException e) {
			assertTrue(true);
		} 
				
	}
	
	
	

}
