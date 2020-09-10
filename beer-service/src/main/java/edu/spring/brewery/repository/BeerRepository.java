package edu.spring.brewery.repository;

import java.util.UUID;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import edu.spring.brewery.model.entity.Beer;

@Repository
public interface BeerRepository extends PagingAndSortingRepository<Beer, UUID> {

}
