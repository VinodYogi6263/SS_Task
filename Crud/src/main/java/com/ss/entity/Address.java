package com.ss.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Data;

@Data
@Entity
@Table(name = "Address_Details")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "addressId")
public class Address {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer addressId;
	private String city;
	private String state;
	private String zipCode;
	private String country;
	@OneToOne(cascade = CascadeType.ALL, targetEntity = AddressBlock.class)
	@JoinColumn(name = "addressBlockID")
	@JsonManagedReference
	private AddressBlock addressBlock;
	
	public Address(String city, String state, String zipCode, String country, AddressBlock addressBlock) {
		super();
		this.city = city;
		this.state = state;
		this.zipCode = zipCode;
		this.country = country;
		this.addressBlock = addressBlock;
	}

	public Address() {
		super();

	}

}
