package edu.spring.brewery.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import edu.spring.brewery.model.dto.BeerDto;
import edu.spring.brewery.model.entity.Beer;

@Mapper(uses = {DateMapper.class})
public interface BeerMapper {
	BeerMapper MAPPER = Mappers.getMapper(BeerMapper.class);

	public BeerDto toBeerDto(Beer beer);

	public Beer toBeer(BeerDto beer);
}
