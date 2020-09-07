package edu.spring.brewery.controller;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.spring.brewery.model.dto.CustomerDto;
import edu.spring.brewery.service.CustomerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController()
@Slf4j
@RequestMapping("/api/v1/customer")
@AllArgsConstructor
public class CustomerController {

	private CustomerService customerService;
	
	@GetMapping(path = { "/{customerId}" }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CustomerDto> getCustomerById(@PathVariable("customerId") UUID customerId) {
		log.debug("Get customer {}", customerId);
		return new ResponseEntity<CustomerDto>(customerService.getCustomer(customerId), HttpStatus.OK);
	}

	@PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CustomerDto> createCustomer(@Valid @RequestBody CustomerDto customerDto) {
		HttpHeaders headers = new HttpHeaders();
		customerDto = customerService.createCustomer(customerDto);
		headers.add("location", "/api/v1/customer/" + customerDto.getId());
		return new ResponseEntity<>(customerDto, headers, HttpStatus.CREATED);
	}

	@PutMapping(path = { "/{customerId}" }, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity updateCustomer(@PathVariable("customerId") UUID customerId,
			@Valid @RequestBody CustomerDto customerDto) {
		customerService.updateCustomer(customerId, customerDto);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@DeleteMapping(path = { "/{customerId}" }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity deleteCustomer(@PathVariable("customerId") UUID customerId) {
		customerService.deleteCustomer(customerId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	

}

