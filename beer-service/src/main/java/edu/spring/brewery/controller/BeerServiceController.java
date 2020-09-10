package edu.spring.brewery.controller;

import java.util.UUID;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.spring.brewery.model.dto.BeerDto;
import edu.spring.brewery.service.BeerService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/beer")
@Slf4j
@Validated
public class BeerServiceController {
	
	@Autowired
	private BeerService beerService;

	@GetMapping(path = { "/{beerId}" }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<BeerDto> getBeerById(@NotNull @PathVariable UUID beerId) {
		log.debug("Get beer for {}", beerId);
		BeerDto beer = beerService.getBeerById(beerId);
		return new ResponseEntity<BeerDto>(beer, HttpStatus.OK);
	}

	@PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<BeerDto> createBeer(@RequestBody @Valid BeerDto beer) {
		log.debug("Create beer");
		beer = beerService.createBeer(beer);
		HttpHeaders headers = new HttpHeaders();
		headers.add("location", "/api/v1/beer/"+beer.getId());
		return new ResponseEntity<BeerDto>(beer, headers, HttpStatus.CREATED);
	}
	
	@PutMapping(path= {"/{beerId}"},consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity updateBeer(@RequestBody @Valid BeerDto beer, @PathVariable UUID beerId) {
		log.debug("Update beer {}",beerId);
		beer.setId(beerId);
		beerService.updateBeer(beer);
		return new ResponseEntity<BeerDto>(HttpStatus.NO_CONTENT);
	}
	
	@DeleteMapping(path= {"/{beerId}"},produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity deleteBeer(@PathVariable UUID beerId) {
		log.debug("Delete beer {}",beerId);
		beerService.deleteBeer(beerId);
		return new ResponseEntity<BeerDto>(HttpStatus.NO_CONTENT);
	}
	
	

}
