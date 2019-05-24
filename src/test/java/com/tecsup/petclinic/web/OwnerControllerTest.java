package com.tecsup.petclinic.web;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import com.tecsup.petclinic.domain.Owner;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class OwnerControllerTest {
	
	private static final ObjectMapper om = new ObjectMapper();
	
	@Autowired
	private MockMvc mockMvc;
		
	 @Test
	    public void testCreateOwner() throws Exception {
			
	    	String FIRST_NAME = "Daniel";
	    	String LAST_NAME = "Figueroa";
			String CITY = "Lima";
			
			Owner newOwner = new Owner(FIRST_NAME,LAST_NAME,CITY);
		
		    mockMvc.perform(post("/owners")
		            .content(om.writeValueAsString(newOwner))
		            .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
		            .andDo(print())
		            .andExpect(status().isCreated())
		            //.andExpect(jsonPath("$.id", is(1)))
		            .andExpect(jsonPath("$.firstName", is(FIRST_NAME)))
		            .andExpect(jsonPath("$.lastName", is(LAST_NAME)))
		            .andExpect(jsonPath("$.city", is(CITY)));
		} 
	 
	 @Test
	 public void testFindOwner() throws Exception{
		 	String FIRST_NAME = "Daniel";
	    	String LAST_NAME = "Figueroa";
	    	String ADDRESS = "Av.Magdalena";
			String CITY = "Lima";
			String TELEPHONE = "987451478";
			
			Owner newOwner = new Owner(FIRST_NAME,LAST_NAME,ADDRESS,CITY,TELEPHONE);
			
			ResultActions mvcActions = mockMvc.perform(post("/owners")
					.content(om.writeValueAsString(newOwner))
		            .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
		            .andDo(print())
		            .andExpect(status().isCreated());
			
			String response = mvcActions.andReturn().getResponse().getContentAsString();
			
			Integer id = JsonPath.parse(response).read("$.id");
			
			mockMvc.perform(get("/owners/" + id ))
            .andExpect(status().isOk());
	 } 	 
}
