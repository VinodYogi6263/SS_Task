package com.ss.service;

import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.ss.constant.Message;
import com.ss.entity.Address;
import com.ss.repository.AddressRepository;
import com.ss.request.AddressRequest;
import com.ss.response.GeneralResponse;

@Service
public class AddressServiceImpl implements AddressService {

	@Autowired
	AddressRepository addressRepository;

	private final Logger log = LoggerFactory.getLogger(AddressServiceImpl.class);

	@Override
	public ResponseEntity<GeneralResponse> find() {

		List<Address> addressList = null;

		try {
			addressList = addressRepository.findAll();
			log.info(Message.found);
			return ResponseEntity.of(Optional.of(new GeneralResponse(addressList, Message.found, 200)));
		} catch (Exception e) {
			log.error(e.getMessage());
			return new ResponseEntity<GeneralResponse>(new GeneralResponse(addressList, Message.notfound, 404),
					HttpStatus.NOT_FOUND);
		}

	}

	@Override
	public ResponseEntity<GeneralResponse> save(AddressRequest addressRequest) {
		Address address = null;
		try {
			address = addressRepository.save(new Address(addressRequest.getCity(), addressRequest.getState(),
					addressRequest.getZipCode(), addressRequest.getCountry(), addressRequest.getAddressBlock()));
			log.info(Message.save);
			return new ResponseEntity<GeneralResponse>(new GeneralResponse(address, Message.save, 200), HttpStatus.OK);

		} catch (Exception e) {
			log.error(e.getMessage());
			return new ResponseEntity<GeneralResponse>(new GeneralResponse(address, Message.notSave, 500),
					HttpStatus.INTERNAL_SERVER_ERROR);

		}

	}

}
