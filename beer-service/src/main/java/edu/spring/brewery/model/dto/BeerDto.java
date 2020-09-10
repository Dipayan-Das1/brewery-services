package edu.spring.brewery.model.dto;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatTypes;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@Builder
@NoArgsConstructor
public class BeerDto {
	@Null
	private Integer version;

	@Null
	private UUID id;
	@Null
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
	private OffsetDateTime createdDate;
	@Null
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
	private OffsetDateTime lastModifiedDate;

	@NotBlank
	private String beerName;

	@NotNull
	private BeerStyle beerStyle;

	@NotBlank
	private String upc;

	@NotNull
	@Positive
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	private BigDecimal price;

	private Integer quantityOnHand;

}
