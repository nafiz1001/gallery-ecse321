package ca.mcgill.ecse321.gallery.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.gallery.dao.AddressRepository;
import ca.mcgill.ecse321.gallery.model.Address;
import ca.mcgill.ecse321.gallery.utils.Utils;

public class AddressService {
	@Autowired
	AddressRepository addressRepository;
	
	@Transactional
	public Optional<Address> createAddress(String streetNumber, String street, String city, String province, String postalCode) {
		Address address = null;
		Boolean isAddressValid = true;
		
		// is postal code valid?
		if (postalCode.length() > 7) isAddressValid = false;
		if (postalCode.length() < 7) isAddressValid = false;
		if (postalCode.length() == 7) {
			char[] chars = new char[7];
			for (int i = 0; i < 7; i++) {
				chars[i] = postalCode.charAt(i);
			}
			if (!Character.isLetter(chars[0])) {
				isAddressValid = false;
			}
			if (!Character.isDigit(chars[1])) {
				isAddressValid = false;
			}
			if (!Character.isLetter(chars[2])) {
				isAddressValid = false;
			}
			if (!Character.isWhitespace(chars[3])) {
				isAddressValid = false;
			}
			if (!Character.isDigit(chars[4])) {
				isAddressValid = false;
			}
			if (!Character.isLetter(chars[5])) {
				isAddressValid = false;
			}
			if (!Character.isDigit(chars[6])) {
				isAddressValid = false;
			}
		}
		
		if (isAddressValid) {
			address = new Address();
			
			address.setStreetNumber(streetNumber);
			address.setStreet(street);
			address.setCity(city);
			address.setProvince(province);
			address.setPostalCode(postalCode);
			
			addressRepository.save(address);	
		}
		
		return Optional.ofNullable(address);
		
	}
	
	@Transactional
	public Optional<Address> getAddressById(String id) {
		return addressRepository.findById(id);
	}
	
	@Transactional
	public List<Address> getAllAddresses() {
		return Utils.toList(addressRepository.findAll());
	}
	
	@Transactional
	public List<Address> getAddressesByPostalCode(String postalCode) {
		Boolean isAddressValid = true;
		
		// is postal code valid?
		if (postalCode.length() > 7) isAddressValid = false;
		if (postalCode.length() < 6) isAddressValid = false;
		if (postalCode.length() == 7) {
			char[] chars = new char[7];
			for (int i = 0; i < 7; i++) {
				chars[i] = postalCode.charAt(i);
			}
			if (!Character.isLetter(chars[0])) {
				isAddressValid = false;
			}
			if (!Character.isDigit(chars[1])) {
				isAddressValid = false;
			}
			if (!Character.isLetter(chars[2])) {
				isAddressValid = false;
			}
			if (!Character.isWhitespace(chars[3])) {
				isAddressValid = false;
			}
			if (!Character.isDigit(chars[4])) {
				isAddressValid = false;
			}
			if (!Character.isLetter(chars[5])) {
				isAddressValid = false;
			}
			if (!Character.isDigit(chars[6])) {
				isAddressValid = false;
			}
		}
		
		if (!isAddressValid) {
			throw new IllegalArgumentException("Invalid Postal Code entered");
		}
		
		List<Address> addresses = getAllAddresses();
		List<Address> byPostalCode = new ArrayList();
		for(Address address : addresses) {
			if (address.getPostalCode().equals(postalCode)) {
				byPostalCode.add(address);
			}
		}
		
		return byPostalCode;
	}
	
}