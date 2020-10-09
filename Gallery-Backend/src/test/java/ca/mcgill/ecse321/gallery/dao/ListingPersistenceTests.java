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
import ca.mcgill.ecse321.gallery.model.Identity;
import ca.mcgill.ecse321.gallery.model.Listing;
import ca.mcgill.ecse321.gallery.model.Payment;
import ca.mcgill.ecse321.gallery.model.PaymentType;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ListingPersistenceTests {
	
	@Autowired	
	private ListingRepository listingRepository;
	
	@Autowired
	private ArtRepository artRepository;
	
	@Autowired
	private ProfileRepository profileRepository;
	
	@BeforeEach
	public void clearDatabse() {
		listingRepository.deleteAll();
		profileRepository.deleteAll();
		artRepository.deleteAll();
	}

	@AfterEach
	public void clearDatabase() {
		listingRepository.deleteAll();
		profileRepository.deleteAll();
		artRepository.deleteAll();
	}
	
	@Test
	public void testCreateAndLoadListing() {
		Listing listing1 = new Listing();
		
		listing1.setCanDeliver(true);
		listing1.setCanPickUp(false);
		listing1.setDatePublished(new Date(0));
		listing1.setId("123");
		listing1.setPrice(12);
		
		listing1.setQuantity(0);
		listing1.setTags("hi");
		
		listingRepository.save(listing1);
		
		Listing listing2 = listingRepository.findListingById("123");
		
		assertNotNull(listing2);
		assertEquals(listing1.getPrice(), listing2.getPrice());
	}	
	
	@Test
	public void testUpdateListing() {
		
		Listing listing1 = new Listing();
		
		listing1.setCanDeliver(true);
		listing1.setCanPickUp(false);
		listing1.setDatePublished(new Date(0));
		listing1.setId("123");
		listing1.setPrice(12);
		
		listing1.setQuantity(0);
		listing1.setTags("hi");
		
		
		
		
		listing1 = listingRepository.save(listing1);
		
		listing1.setPrice(1);
		
		listing1 = listingRepository.save(listing1);
		
		assertEquals(listing1.getPrice(), 1);
	}
}
