package edu.spring.brewery.mapper;

import org.mapstruct.Mapper;

import edu.spring.brewery.model.dto.CustomerDto;
import edu.spring.brewery.model.entity.Customer;

@Mapper
public interface CustomerMapper {

	public CustomerDto toCustomerDto(Customer customer);
	public Customer toCustomer(CustomerDto customer);
}
