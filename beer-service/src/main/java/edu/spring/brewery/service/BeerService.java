package edu.spring.brewery.service;

import java.util.UUID;

import edu.spring.brewery.model.dto.BeerDto;

public interface BeerService {
public BeerDto getBeerById(UUID id);
public BeerDto createBeer(BeerDto beerDto);
public BeerDto updateBeer(BeerDto beerDto);
public void deleteBeer(UUID id);
}
