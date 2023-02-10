package com.ss.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="AddressBlock_Details")
public class AddressBlock {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int addressBlockID;
	private String addressLineFirst;
	private String addressLineSecond;

}
