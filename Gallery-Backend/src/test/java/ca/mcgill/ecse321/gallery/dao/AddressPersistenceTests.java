package ca.mcgill.ecse321.gallery.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.HashSet;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ca.mcgill.ecse321.gallery.model.Address;
import ca.mcgill.ecse321.gallery.model.DeliveryType;
import ca.mcgill.ecse321.gallery.model.Payment;
import ca.mcgill.ecse321.gallery.model.PaymentType;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class AddressPersistenceTests {
	
	@Autowired
	private AddressRepository addressRepository;
	
	@BeforeEach
	public void clearDatabse() {
		addressRepository.deleteAll();
	}
	
	@AfterEach
	public void clearDatabase() {
		addressRepository.deleteAll();
	}
	
	@Test
	public void testCreateAndLoadAddress() {
		Address address1 = new Address();
		address1.setCity("a");
		address1.setId("b");
		address1.setPostalCode("c");
		address1.setProvince("d");
		address1.setStreet("d");
		address1.setStreetNumber("e");
		
		addressRepository.save(address1);
		
		Address address2 = addressRepository.findAddressById("b");
		
		assertNotNull(address2);
		assertEquals(address1.getCity(), address2.getCity());
	}
}
