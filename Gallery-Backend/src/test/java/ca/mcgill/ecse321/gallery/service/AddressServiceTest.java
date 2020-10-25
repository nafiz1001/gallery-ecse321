package ca.mcgill.ecse321.gallery.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.lenient;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import ca.mcgill.ecse321.gallery.dao.AddressRepository;
import ca.mcgill.ecse321.gallery.model.Address;

@ExtendWith(MockitoExtension.class)
public class AddressServiceTest {

	@Mock
	private AddressRepository addressRepository;
	
	@InjectMocks
	AddressService addressService;
	
	private String id = "0";
	private HashSet<Address> savedAddresses = new HashSet<>();
	
	@BeforeEach
	public void setupMockup() {
		// address id generator mock
		id = "0";
		
		// database mock
		savedAddresses.clear();
		
		// lamba for simulating saving address
		Answer<Address> updateIdAndReturn = (InvocationOnMock invocation) -> {
			Address a = (Address)invocation.getArgument(0);
			a.setId(id);
			savedAddresses.add(a);
			int idAsNum = Integer.parseInt(id);
			idAsNum++;
			id = Integer.toString(idAsNum);
			return a;
		};
		
		// simulate saving address
		lenient().when(addressRepository.save(any(Address.class))).thenAnswer(updateIdAndReturn);
		
		// simulate finding address by id
		lenient().when(addressRepository.findById(anyString())).thenAnswer((InvocationOnMock invocation) -> {
			String id = ((String)invocation.getArgument(0));
			for (Address a : savedAddresses) {
				if(a.getId().equals(id)) {
					return Optional.ofNullable(a);
				}
			}
			return Optional.empty();
		});
		
		
		// simulate finding all addresses
		lenient().when(addressRepository.findAll()).thenAnswer((InvocationOnMock invocation) -> {
			return savedAddresses;
		});
	}
	
	@Test
	void testSuccessfulAccount() {
		Optional<Address> address = addressService.createAddress("8", "Lakeview", "Baie-D'Urfe", "Quebec", "H9X 3B1");
		
		assertTrue(address.isPresent());
		
	}
	
	@Test
	void testPostalCodeViolations() {
		// length > 7
		Optional<Address> address = addressService.createAddress("8", "Lakeview", "Baie-D'Urfe", "Quebec", "H9X 3B15");
		assertTrue(address.isEmpty());
		
		// length < 7
		address = addressService.createAddress("8", "Lakeview", "Baie-D'Urfe", "Quebec", "H9X 3B");
		assertTrue(address.isEmpty());
		
		// first character is not a letter
		address = addressService.createAddress("8", "Lakeview", "Baie-D'Urfe", "Quebec", "59X 3B1");
		assertTrue(address.isEmpty());
		
		// second character is not a digit
		address = addressService.createAddress("8", "Lakeview", "Baie-D'Urfe", "Quebec", "HIX 3B1");
		assertTrue(address.isEmpty());
		
		// third character is not a letter
		address = addressService.createAddress("8", "Lakeview", "Baie-D'Urfe", "Quebec", "H92 3B1");
		assertTrue(address.isEmpty());
		
		// fourth character is not a black space
		address = addressService.createAddress("8", "Lakeview", "Baie-D'Urfe", "Quebec", "H9Xa3B1");
		assertTrue(address.isEmpty());
		
		// fifth character is not a digit
		address = addressService.createAddress("8", "Lakeview", "Baie-D'Urfe", "Quebec", "H9X oB1");
		assertTrue(address.isEmpty());
		
		// sixth character is not a letter
		address = addressService.createAddress("8", "Lakeview", "Baie-D'Urfe", "Quebec", "H9X 3+1");
		assertTrue(address.isEmpty());
		
		// seventh character is not a digit
		address = addressService.createAddress("8", "Lakeview", "Baie-D'Urfe", "Quebec", "H9X 3BP");
		assertTrue(address.isEmpty());
	}
	
	@Test
	void testGetAddress() {
		assertTrue(addressService.getAddressById("0").isEmpty());
		
		addressService.createAddress("8", "Lakeview", "Baie-D'Urfe", "Quebec", "H9X 3B1");
		assertTrue(addressService.getAddressById("0").isPresent());
		
	}
	
	@Test 
	void testGetAddressesByPostalCode() {
		// there should be no addresses with postal code H9X 3B1 before creation of address
		assertEquals(0, addressService.getAddressesByPostalCode("H9X 3B1").size());
		
		addressService.createAddress("8", "Lakeview", "Baie-D'Urfe", "Quebec", "H9X 3B1");
		
		// there should be an address with postal code H9X 3B1 after creation of address
		assertEquals(1, addressService.getAddressesByPostalCode("H9X 3B1").size());
		
	}
	
}
