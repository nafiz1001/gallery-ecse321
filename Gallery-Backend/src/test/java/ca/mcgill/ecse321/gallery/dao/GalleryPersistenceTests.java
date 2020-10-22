/**
 * @author Thomas Alarcon
 * 
 */
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
import ca.mcgill.ecse321.gallery.model.Gallery;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class GalleryPersistenceTests {
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private GalleryRepository galleryRepository;
	
	@Autowired
	private AddressRepository addressRepository;
	
	/**
	 * Clears all Galleries and Addresses in database before each test
	 */
	@BeforeEach
	public void clearDatabase() {
		galleryRepository.deleteAll();
		accountRepository.deleteAll();
		addressRepository.deleteAll();
	}
	
	/**
	 * Tests to see if a Gallery can be created in the database
	 */
	@Test
	public void testCreateAndLoadGallery() {
		Address address = new Address();
		address.setCity("a");
		address.setId("b");
		address.setPostalCode("c");
		address.setProvince("d");
		address.setStreet("d");
		address.setStreetNumber("e");
		
		addressRepository.save(address);
		
		Gallery gallery1 = new Gallery();
		gallery1.setAddress(address);
		gallery1.setClosingTime(null);
		gallery1.setCommissionPercentage(10);
		gallery1.setEmail("helloWorld@eclipse.com");
		gallery1.setName("Thomas");
		gallery1.setOpeningTime(null);
		gallery1.setPhoneNumber("514-999-9999");
		
		galleryRepository.save(gallery1);
		
		Gallery gallery2 = galleryRepository.findGalleryByName("Thomas");
		
		assertNotNull(gallery2);
		assertEquals(gallery1.getPhoneNumber(), gallery2.getPhoneNumber());
	}
	
	/**
	 * Tests to see if the Gallery attribute values can be updated in the database
	 */
	@Test
	public void testUpdateGallery() {
		Address address = new Address();
		address.setCity("a");
		address.setId("b");
		address.setPostalCode("c");
		address.setProvince("d");
		address.setStreet("d");
		address.setStreetNumber("e");
		
		addressRepository.save(address);
		
		Gallery gallery1 = new Gallery();
		gallery1.setAddress(address);
		gallery1.setClosingTime(null);
		gallery1.setCommissionPercentage(10);
		gallery1.setEmail("helloWorld@eclipse.com");
		gallery1.setName("Thomas");
		gallery1.setOpeningTime(null);
		gallery1.setPhoneNumber("514-999-9999");
		
		gallery1 = galleryRepository.save(gallery1);
		
		gallery1.setName("Matt");
		
		gallery1 = galleryRepository.save(gallery1);
		
		assertEquals(gallery1.getName(), "Matt");
	}
}