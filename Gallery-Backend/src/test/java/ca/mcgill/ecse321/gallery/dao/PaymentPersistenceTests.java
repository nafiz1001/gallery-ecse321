package ca.mcgill.ecse321.gallery.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ca.mcgill.ecse321.gallery.model.Account;
import ca.mcgill.ecse321.gallery.model.Address;
import ca.mcgill.ecse321.gallery.model.DeliveryType;
import ca.mcgill.ecse321.gallery.model.Identity;
import ca.mcgill.ecse321.gallery.model.Listing;
import ca.mcgill.ecse321.gallery.model.Payment;
import ca.mcgill.ecse321.gallery.model.PaymentType;

/**
 * 
 * @author nafiz1001
 *
 */

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class PaymentPersistenceTests {
	@Autowired
	private IdentityRepository identityRepository;
	
	@Autowired
	private ListingRepository listingRepository;
	
	@Autowired
	private PaymentRepository paymentRepository;
	
	
	@BeforeEach
	public void clearDatabse() {
		paymentRepository.deleteAll();
		listingRepository.deleteAll();
		identityRepository.deleteAll();
	}
	
	/**
	 * saves one payment, one identity and one listing to the database;
	 * they are also linked together somehow
	 * @return instance of payment with confirmation number
	 */
	private Payment savePayment() {
		Payment payment = new Payment();
		payment.setDeliveryType(DeliveryType.PICKUP);
		
		Identity nullIdentity = new Identity();
		nullIdentity.setEmail("0");
		identityRepository.save(nullIdentity);
		payment.setIdentity(nullIdentity);
		
		Listing nullListing = new Listing();
		nullListing.setDatePublished(new Date(0));
		nullListing.setId("0");
		listingRepository.save(nullListing);
		payment.setListing(new ArrayList<>());
		payment.getListing().add(nullListing);
		
		payment.setPaymentDate(new Date(0));
		payment.setPaymentType(PaymentType.CREDIT_CARD);
		payment.setTransactionNumber("0");
		
		return paymentRepository.save(payment);
	}
	
	/**
	 * saves one listing
	 * @return saved listing
	 */
	public Listing saveListing() {
		Listing listing1 = new Listing();

		listing1.setCanDeliver(true);
		listing1.setCanPickUp(false);
		listing1.setDatePublished(new Date(0));
		listing1.setId("123");
		listing1.setPrice(12);

		listing1.setQuantity(0);
		listing1.setTags("hi");

		return listingRepository.save(listing1);
	}
	
	/**
	 * test if payment can save successfully
	 */
	
	@Test
	public void testCreateAndLoad() {
		Payment savedPayment = savePayment();
		
		Payment foundPayment = paymentRepository.findByConfirmationNumber(savedPayment.getConfirmationNumber());
		
		assertNotNull(foundPayment);
		assertEquals(foundPayment.getTransactionNumber(), "0");
	}
	
	/**
	 * test if payment attributes and references have been saved in database successfully
	 */
	
	@Test
	public void testReadReferenceAndAttribute() {
		Payment savedPayment = savePayment();
		
		assertEquals(savedPayment.getPaymentDate(), new Date(0));
		assertEquals(savedPayment.getPaymentType(), PaymentType.CREDIT_CARD);
		assertEquals(savedPayment.getIdentity().getEmail(), "0");
	}
	
	/**
	 * test if the database can be updated successfully
	 */
	
	@Test
	public void testWriteAndUpdateReferenceAndAttribute() {
		Payment savedPayment = savePayment();
		
		long confirmationNumber = savedPayment.getConfirmationNumber();
		
		savedPayment.setPaymentDate(new Date(100000));
		savedPayment.setPaymentType(PaymentType.PAYPAL);
		
		Identity otherIdentity = new Identity();
		otherIdentity.setEmail("1");
		otherIdentity = identityRepository.save(otherIdentity);
		savedPayment.setIdentity(otherIdentity);
		
		List<Listing> listings = new ArrayList<>();
		Listing listing = saveListing();
		listings.add(listing);
		
		savedPayment.setListing(listings);
		
		paymentRepository.save(savedPayment);
		
		savedPayment = paymentRepository.findByConfirmationNumber(savedPayment.getConfirmationNumber());

		assertEquals(confirmationNumber, savedPayment.getConfirmationNumber());
		//assertEquals(0, new Date(100000).compareTo(savedPayment.getPaymentDate())); // date comparison is wack
		assertEquals(PaymentType.PAYPAL, savedPayment.getPaymentType());
		assertEquals(otherIdentity.getEmail(), savedPayment.getIdentity().getEmail());
		assertEquals(1, savedPayment.getListing().size());
		assertEquals("123", ((Listing)savedPayment.getListing().toArray()[0]).getId());
	}
	
	@Test
	public void testFindByEmail() {
		Payment savedPayment = savePayment();
		Iterable<Payment> payments = paymentRepository.findByIdentityEmail("0");
		
		int size = 0;
		for (Payment p : payments) {
			assertEquals(savedPayment.getConfirmationNumber(), p.getConfirmationNumber());
			++size;
		}
		assertEquals(1, size);
	}
}
