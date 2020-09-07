package edu.spring.brewery.service;

import java.util.UUID;

import edu.spring.brewery.model.dto.CustomerDto;



public interface CustomerService {
	public CustomerDto getCustomer(UUID customerId);
	public CustomerDto createCustomer(CustomerDto customer);
	public void updateCustomer(UUID customerId,CustomerDto customer);
	public void deleteCustomer(UUID customerId);
}
