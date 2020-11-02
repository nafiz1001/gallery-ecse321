package ca.mcgill.ecse321.gallery.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.gallery.dao.AddressRepository;
import ca.mcgill.ecse321.gallery.model.Address;
import ca.mcgill.ecse321.gallery.utils.Utils;

/**
 * 
 * @author Ericpelletier135
 * This service allows the create and retrieval of address'. 
 */
@Service
public class AddressService {
	@Autowired
	AddressRepository addressRepository;
	/**
	 * Creates an Address.
	 * @param streetNumber The street number of the address
	 * @param street The street of the address
	 * @param province The province of the address
	 * @param postalCode The postal code of the address
	 * @return The address created
	 */
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
			address.setId(postalCode + streetNumber);
			
			addressRepository.save(address);	
		}
		
		return Optional.ofNullable(address);
		
	}
	
	/**
	 * Retrieves an address with a specific id 
	 * @param id The id of the address to find
	 * @return An address with the given id
	 */
	@Transactional
	public Optional<Address> getAddressById(String id) {
		return addressRepository.findById(id);
	}
	
	/**
	 * Retrieves all account
	 * @return A list of all the address in the database 
	 */
	@Transactional
	public List<Address> getAllAddresses() {
		return Utils.toList(addressRepository.findAll());
	}
	
	/**
	 * Retrieves an address with a specific postal code
	 * @param postalCode The postal code of the address to find
	 * @return A list of all the address with the given postal code
	 */
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