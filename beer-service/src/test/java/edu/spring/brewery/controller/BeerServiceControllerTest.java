package edu.spring.brewery.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;

import java.math.BigDecimal;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;
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
		mockMvc.perform(get("/api/v1/beer/" + UUID.randomUUID()).accept(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk());
	}

	@Test
	public void createBeer() throws Exception {
		BeerDto beer = BeerDto.builder().beerName("test-beer").beerStyle(BeerStyle.ALE).upc("73495929736").price(new BigDecimal(10)).build();
		mockMvc.perform(post("/api/v1/beer").contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(objectMapper.writeValueAsString(beer))).andExpect(status().isCreated())
				.andExpect(header().exists("location"));
	}

	@Test
	public void updateBeer() throws Exception {
		BeerDto beer = BeerDto.builder().beerName("test-beer").beerStyle(BeerStyle.ALE).upc("73495929736").price(new BigDecimal(10)).build();
		mockMvc.perform(put("/api/v1/beer/" + UUID.randomUUID()).contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(objectMapper.writeValueAsString(beer))).andExpect(status().isNoContent());
	}

	@Test
	public void deleteBeer() throws Exception {
		mockMvc.perform(delete("/api/v1/beer/" + UUID.randomUUID())).andExpect(status().isNoContent());
	}

}
