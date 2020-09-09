package edu.spring.brewery.model.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import edu.spring.brewery.model.dto.BeerStyle;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Beer {
	@Version
	private Integer version;
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(length = 36, columnDefinition = "varchar(36)", updatable = false, nullable = false)
	private UUID id;
    
    @CreationTimestamp
    @Column(updatable = false)
    private Timestamp createdDate;
    
    @UpdateTimestamp
    private Timestamp lastModifiedDate;
    
    @Column(nullable = false)
    private String beerName;
    
    private BeerStyle beerStyle;
    
    @Column(unique = true)
    private String upc;
    
    private BigDecimal price;
    
    private Integer minOnHand;
    
    private Integer quantityToBrew;

	
}
