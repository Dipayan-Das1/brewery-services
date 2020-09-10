package edu.spring.brewery.controller;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import edu.spring.brewery.model.dto.CustomerDto;
import edu.spring.brewery.service.CustomerService;


@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {

	// this is spring mock bean
	@MockBean
	private CustomerService customerService;

	@Autowired
	private ObjectMapper objectMapper;

	// org.springframework.test.web.servlet.MockMvc
	@Autowired
	private MockMvc mockMvc;

	///works only with org.junit.jupiter.api.Test
	
	@Test
	public void testGetCustomer() throws Exception
	{
		CustomerDto customer = CustomerDto.builder().email("test@test.com").id(UUID.randomUUID()).name("test-user").build();
		given(customerService.getCustomer(any(UUID.class))).willReturn(customer);
		mockMvc.perform(get("/api/v1/customer/"+UUID.randomUUID())
				.accept(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(jsonPath("$.name",equalTo("test-user")))
				.andExpect(jsonPath("$.customerId",notNullValue()));
	}
	
	@Test
	public void testCreateCustomer() throws Exception
	{
		CustomerDto customer = CustomerDto.builder().email("test@test.com").name("test-user").build();
		given(customerService.createCustomer(any(CustomerDto.class))).willReturn(customer);
		mockMvc.perform(post("/api/v1/customer")
				.accept(MediaType.APPLICATION_JSON_VALUE)
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(objectMapper.writeValueAsString(customer)))
				.andExpect(status().isCreated())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(jsonPath("$.name",equalTo("test-user")));
	}
	
	@Test
	public void testUpdateCustomer() throws Exception
	{
		willDoNothing().given(customerService).updateCustomer(any(UUID.class), any(CustomerDto.class));
		UUID uuid = UUID.randomUUID();
		CustomerDto customer = CustomerDto.builder().email("test@test.com").name("test-user").build();
		given(customerService.createCustomer(any(CustomerDto.class))).willReturn(customer);
		mockMvc.perform(put("/api/v1/customer/"+uuid)
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(objectMapper.writeValueAsString(customer)))
				.andExpect(status().isNoContent());
	}
	
	@Test
	public void testUpdateCustomerNotNullId() throws Exception
	{
		CustomerDto customer = CustomerDto.builder().email("test@test.com").name("test-user").id(UUID.randomUUID()).build();
		mockMvc.perform(put("/api/v1/customer/"+UUID.randomUUID().toString())
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(objectMapper.writeValueAsString(customer)))
				.andExpect(status().isBadRequest());
	}
	
	
	
	@Test
	public void testDeleteCustomer() throws Exception
	{
		willDoNothing().given(customerService).deleteCustomer(any(UUID.class));
		mockMvc.perform(delete("/api/v1/customer/"+UUID.randomUUID()))
				.andExpect(status().isNoContent());
	}

}

