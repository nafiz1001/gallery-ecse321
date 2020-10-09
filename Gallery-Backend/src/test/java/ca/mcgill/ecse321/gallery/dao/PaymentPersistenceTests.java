package ca.mcgill.ecse321.gallery.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Date;
import java.util.HashSet;

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
	
	@AfterEach
	public void clearDatabase() {
		paymentRepository.deleteAll();
		listingRepository.deleteAll();
		identityRepository.deleteAll();
	}
	
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
		payment.setListing(new HashSet<>());
		payment.getListing().add(nullListing);
		
		payment.setPaymentDate(new Date(0));
		payment.setPaymentType(PaymentType.CREDIT_CARD);
		payment.setTransactionNumber("0");
		
		return paymentRepository.save(payment);
	}
	
	@Test
	public void testCreateAndLoad() {
		Payment savedPayment = savePayment();
		
		Payment foundPayment = paymentRepository.findPaymentByConfirmationNumber(savedPayment.getConfirmationNumber());
		
		assertNotNull(foundPayment);
		assertEquals(foundPayment.getTransactionNumber(), "0");
	}
	
	@Test
	public void testReadReferenceAndAttribute() {
		Payment savedPayment = savePayment();
		
		assertEquals(savedPayment.getPaymentDate(), new Date(0));
		assertEquals(savedPayment.getPaymentType(), PaymentType.CREDIT_CARD);
		assertEquals(savedPayment.getIdentity().getEmail(), "0");
	}
	
	@Test
	public void testWriteAndUpdateReferenceAndAttribute() {
		Payment savedPayment = savePayment();
		
		savedPayment.setPaymentDate(new Date(100000));
		savedPayment.setPaymentType(PaymentType.PAYPAL);
		
		Identity otherIdentity = new Identity();
		otherIdentity.setEmail("1");
		otherIdentity = identityRepository.save(otherIdentity);
		savedPayment.setIdentity(otherIdentity);
		
		paymentRepository.save(savedPayment);
		
		savedPayment = paymentRepository.findPaymentByConfirmationNumber(savedPayment.getConfirmationNumber());

		assertEquals(savedPayment.getPaymentDate().toString(), new Date(100000).toString(), savedPayment.getPaymentDate() + " != " + new Date(100000));
		assertEquals(savedPayment.getPaymentType(), PaymentType.PAYPAL);
		assertEquals(savedPayment.getIdentity().getEmail(), otherIdentity.getEmail());
	}
}
