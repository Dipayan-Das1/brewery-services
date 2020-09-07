package edu.spring.brewery.repository;

import java.util.UUID;

import org.springframework.data.repository.PagingAndSortingRepository;

import edu.spring.brewery.model.entity.Beer;

public interface BeerRepository extends PagingAndSortingRepository<Beer, UUID> {

}
