package edu.spring.brewery.model.dto;


import java.util.UUID;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {
	@Null
	@JsonProperty("customerId")
	private UUID id;
	@NotBlank
	@Length(min = 3,max=100)
	private String name;
	@Email
	private String email;

}

