/**
 * @author antonianistor
 * 
 * This class has the purpose of testing the Listing class from the model
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
import ca.mcgill.ecse321.gallery.model.DeliveryType;
import ca.mcgill.ecse321.gallery.model.Identity;
import ca.mcgill.ecse321.gallery.model.Listing;
import ca.mcgill.ecse321.gallery.model.Payment;
import ca.mcgill.ecse321.gallery.model.PaymentType;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ListingPersistenceTests {
	@Autowired
	private ProfileRepository profileRepository;
	
	@Autowired
	private PaymentRepository paymentRepository;
	
	@Autowired
	private ListingRepository listingRepository;
	
	@Autowired
	private RevenuRepository revenuRepository;

	/**
	 * Clears all listings in database before each test
	 */
	@BeforeEach
	public void clearDatabaseBefore() {
		revenuRepository.deleteAll();
		paymentRepository.deleteAll();
		profileRepository.deleteAll();
		listingRepository.deleteAll();
	}

	/**
	 * Tests to see if listings can be created in the database
	 */
	@Test
	public void testCreateAndLoadListing() {
		Listing listing1 = new Listing();

		listing1.setCanDeliver(true);
		listing1.setCanPickUp(false);
		listing1.setDatePublished(new Date(0));
		listing1.setId((long)123);
		listing1.setPrice(12);

		listing1.setQuantity(0);
		listing1.setTags("hi");

		listing1 = listingRepository.save(listing1);

		Listing listing2 = listingRepository.findListingById(listing1.getId());

		assertNotNull(listing2);
		assertEquals(listing1.getPrice(), listing2.getPrice());
	}

	/**
	 * Tests to see if value gets updated in database
	 */
	@Test
	public void testUpdateListing() {

		Listing listing1 = new Listing();

		listing1.setCanDeliver(true);
		listing1.setCanPickUp(false);
		listing1.setDatePublished(new Date(0));
		listing1.setId((long)123);
		listing1.setPrice(12);

		listing1.setQuantity(0);
		listing1.setTags("hi");

		listing1 = listingRepository.save(listing1);

		listing1.setPrice(1);

		listing1 = listingRepository.save(listing1);

		assertEquals(listing1.getPrice(), 1);
	}
}
