package edu.spring.brewery.service.impl;

import java.util.UUID;

import org.springframework.stereotype.Service;

import edu.spring.brewery.model.dto.CustomerDto;
import edu.spring.brewery.service.CustomerService;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService{

	@Override
	public CustomerDto getCustomer(UUID customerId) {
		return CustomerDto.builder()
				.email("abc@gmail.com")
				.id(UUID.randomUUID())
				.name("Dipayan")
				.build();

	}

	@Override
	public CustomerDto createCustomer(CustomerDto customer) {
		log.debug("Create customer invoked");
		customer.setId(UUID.randomUUID());
		return customer;
	}

	@Override
	public void updateCustomer(UUID customerId, CustomerDto customer) {
		log.debug("Update customer invoked");
		
	}

	@Override
	public void deleteCustomer(UUID customerId) {
		log.debug("Delete customer invoked");
		
	}

}
