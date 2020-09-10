package edu.spring.brewery.bootstrap;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import edu.spring.brewery.model.dto.BeerStyle;
import edu.spring.brewery.model.entity.Beer;
import edu.spring.brewery.repository.BeerRepository;

public class BeerBootstrap implements CommandLineRunner {
	public static final String BEER_1_UPC = "0631234200036";
	public static final String BEER_2_UPC = "0631234300019";
	public static final String BEER_3_UPC = "0083783375213";
	public static final String BEER_4_UPC = "0083783375215";

	@Autowired
	private BeerRepository beerRepository;

	@Override
	public void run(String... args) throws Exception {
		Beer b1 = Beer.builder().beerName("Mango Bobs").beerStyle(BeerStyle.IPA).minOnHand(12).quantityToBrew(200)
				.price(new BigDecimal("12.95")).upc(BEER_1_UPC).build();

		Beer b2 = Beer.builder().beerName("Galaxy Cat").beerStyle(BeerStyle.PALE_ALE).minOnHand(12).quantityToBrew(200)
				.price(new BigDecimal("12.95")).upc(BEER_2_UPC).build();

		Beer b3 = Beer.builder().beerName("Pinball Porter").beerStyle(BeerStyle.PALE_ALE).minOnHand(12)
				.quantityToBrew(200).price(new BigDecimal("12.95")).upc(BEER_3_UPC).build();

		Beer b4 = Beer.builder().beerName("Bira Light").beerStyle(BeerStyle.WHEAT).minOnHand(12)
				.quantityToBrew(200).price(new BigDecimal("10.95")).upc(BEER_4_UPC).build();

		
		beerRepository.save(b1);
		beerRepository.save(b2);
		beerRepository.save(b3);
		beerRepository.save(b4);

	}

}
