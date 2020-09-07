package edu.spring.brewery.service.impl;

import java.util.UUID;

import org.springframework.stereotype.Service;

import edu.spring.brewery.model.dto.BeerDto;
import edu.spring.brewery.model.dto.BeerStyle;
import edu.spring.brewery.service.BeerService;

@Service
public class BeerServiceImpl implements BeerService {

	@Override
	public BeerDto getBeerById(UUID id) {
		return BeerDto.builder().beerName("test-beer").id(UUID.randomUUID()).beerStyle(BeerStyle.ALE).upc("83564567389")
				.build();
	}

	@Override
	public BeerDto createBeer(BeerDto beerDto) {
		beerDto.setId(UUID.randomUUID());
		return beerDto;
	}

	@Override
	public BeerDto updateBeer(BeerDto beerDto) {
		return beerDto;
	}

	@Override
	public void deleteBeer(UUID id) {
		

	}

}
