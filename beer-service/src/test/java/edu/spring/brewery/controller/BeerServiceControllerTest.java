package edu.spring.brewery.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.math.BigDecimal;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;

import edu.spring.brewery.exception.NotFoundException;
import edu.spring.brewery.model.dto.BeerDto;
import edu.spring.brewery.model.dto.BeerStyle;
import edu.spring.brewery.service.BeerService;

@WebMvcTest(BeerServiceController.class)
public class BeerServiceControllerTest {

	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private BeerService beerService;

	@Test
	public void getBeerById() throws Exception {
		UUID id = UUID.randomUUID();
		BeerDto beer = BeerDto.builder().id(id).beerName("test-beer").beerStyle(BeerStyle.ALE).upc("73495929736").price(new BigDecimal(10)).build();
		when(beerService.getBeerById(any(UUID.class))).thenReturn(beer);
		mockMvc.perform(get("/api/v1/beer/" + id).accept(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk());
	}
	

	@Test
	public void getBeerByIdBeerNotFound() throws Exception {
		UUID id = UUID.randomUUID();
		when(beerService.getBeerById(any(UUID.class))).thenThrow(new NotFoundException("Beer not found"));
		mockMvc.perform(get("/api/v1/beer/" + id).accept(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isBadRequest());
	}

	@Test
	public void createBeer() throws Exception {
		BeerDto beer = BeerDto.builder().beerName("test-beer").beerStyle(BeerStyle.ALE).upc("73495929736").price(new BigDecimal(10)).build();
		BeerDto beerRes = BeerDto.builder().id(UUID.randomUUID()).beerName("test-beer").beerStyle(BeerStyle.ALE).upc("73495929736").price(new BigDecimal(10)).build();
		when(beerService.createBeer(any(BeerDto.class))).thenReturn(beerRes);
		mockMvc.perform(post("/api/v1/beer").contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(objectMapper.writeValueAsString(beer))).andExpect(status().isCreated())
				.andExpect(header().exists("location"));
	}

	@Test
	public void updateBeer() throws Exception {
		BeerDto beer = BeerDto.builder().beerName("test-beer").beerStyle(BeerStyle.ALE).upc("73495929736").price(new BigDecimal(10)).build();
		doNothing().when(beerService).updateBeer(beer);
		mockMvc.perform(put("/api/v1/beer/" + UUID.randomUUID()).contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(objectMapper.writeValueAsString(beer))).andExpect(status().isNoContent());
	}
	
	@Test
	public void updateBeerNotFound() throws Exception {
		BeerDto beer = BeerDto.builder().beerName("test-beer").beerStyle(BeerStyle.ALE).upc("73495929736").price(new BigDecimal(10)).build();
		doThrow(new NotFoundException("Beer not found")).when(beerService).updateBeer(any(BeerDto.class));
		mockMvc.perform(put("/api/v1/beer/" + UUID.randomUUID()).contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(objectMapper.writeValueAsString(beer))).andExpect(status().isBadRequest());
	}
	

	@Test
	public void deleteBeer() throws Exception {
		doNothing().when(beerService).deleteBeer(UUID.randomUUID());
		mockMvc.perform(delete("/api/v1/beer/" + UUID.randomUUID())).andExpect(status().isNoContent());
	}
	
	@Test
	public void deleteBeerNotFound() throws Exception {
		doThrow(new NotFoundException("Beer not found")).when(beerService).deleteBeer(any(UUID.class));
		mockMvc.perform(delete("/api/v1/beer/" + UUID.randomUUID())).andExpect(status().isBadRequest());
	}

}
