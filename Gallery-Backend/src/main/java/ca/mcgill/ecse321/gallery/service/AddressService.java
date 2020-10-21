package ca.mcgill.ecse321.gallery.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import ca.mcgill.ecse321.gallery.dao.AddressRepository;
import ca.mcgill.ecse321.gallery.model.Address;

public class AddressService {
	@Autowired
	AddressRepository addressRepository;
	
	Optional<Address> getAddress(String id) {
		return addressRepository.findById(id);
	}
}
