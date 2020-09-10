package edu.spring.brewery.service.impl;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.spring.brewery.exception.NotFoundException;
import edu.spring.brewery.mapper.BeerMapper;
import edu.spring.brewery.model.dto.BeerDto;
import edu.spring.brewery.model.entity.Beer;
import edu.spring.brewery.repository.BeerRepository;
import edu.spring.brewery.service.BeerService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@RequiredArgsConstructor
@Slf4j
public class BeerServiceImpl implements BeerService {

	private BeerMapper beerMapper;

	private BeerRepository beerRepository;

	@Override
	@Transactional
	public BeerDto getBeerById(UUID id) {
		log.debug("Get beer by id {}", id.toString());
		Optional<Beer> beer = beerRepository.findById(id);
		beer.orElseThrow(() -> {
			log.error(String.format("Beer with id {} not found", id.toString()));
			return new NotFoundException(String.format("Beer with id {} not found", id.toString()));
		});
		return beerMapper.toBeerDto(beer.get());
	}

	@Override
	@Transactional
	public BeerDto createBeer(BeerDto beerDto) {
		log.debug("Create beer");
		Beer beer = beerMapper.toBeer(beerDto);
		beerRepository.save(beer);
		beerDto = beerMapper.toBeerDto(beer);
		return beerDto;
	}

	@Override
	@Transactional
	public void updateBeer(BeerDto beerDto) {
		log.debug("Update beer {}", beerDto.getId().toString());
		getBeerById(beerDto.getId());
		Beer beer = beerMapper.toBeer(beerDto);
		beerRepository.save(beer);
	}

	@Override
	@Transactional
	public void deleteBeer(UUID id) {
		log.debug("Delete beer {}", id.toString());
		getBeerById(id);
		beerRepository.deleteById(id);
	}

}
