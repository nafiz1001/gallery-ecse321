package ca.mcgill.ecse321.gallery.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.lenient;

import ca.mcgill.ecse321.gallery.dao.IdentityRepository;
import ca.mcgill.ecse321.gallery.dao.ListingRepository;
import ca.mcgill.ecse321.gallery.dao.PaymentRepository;
import ca.mcgill.ecse321.gallery.model.Address;
import ca.mcgill.ecse321.gallery.model.DeliveryType;
import ca.mcgill.ecse321.gallery.model.Identity;
import ca.mcgill.ecse321.gallery.model.Listing;
import ca.mcgill.ecse321.gallery.model.Payment;
import ca.mcgill.ecse321.gallery.model.PaymentType;

/**
 * 
 * @author nafiz
 * test PaymentService
 */
@ExtendWith(MockitoExtension.class)
public class PaymentServiceTests {
	@Mock
	private PaymentRepository paymentRepository;
	
	@Mock
	private ListingRepository listingRepository;
	
	@InjectMocks
	PaymentService paymentService;
	
	private long confirmationNumber = 0;
	private HashSet<Payment> savedPayments = new HashSet<>();
	private HashSet<Listing> savedListings = new HashSet<>();
	
	@BeforeEach
	public void setupMockup() {
		confirmationNumber = 0;
		
		savedPayments.clear();
		savedListings.clear();
		
		Answer<Payment> updateConfirmationNumberAndReturn = (InvocationOnMock invocation) -> {
			Payment p = (Payment)invocation.getArgument(0);
			p.setConfirmationNumber(confirmationNumber);
			savedPayments.add(p);
			confirmationNumber++;
			return p;
		};
		
		Answer<Listing> saveAndReturn = (InvocationOnMock invocation) -> {
			Listing l = (Listing)invocation.getArgument(0);
			savedListings.add(l);
			return l;
		};
		
		lenient().when(paymentRepository.save(any(Payment.class))).thenAnswer(updateConfirmationNumberAndReturn);
		
		lenient().when(listingRepository.save(any(Listing.class))).thenAnswer(saveAndReturn);
		
		lenient().when(paymentRepository.findById(any())).thenAnswer((InvocationOnMock invocation) -> {
			long confirmationNumber = ((Long)invocation.getArgument(0));
			for (Payment p : savedPayments) {
				if (p.getConfirmationNumber() == confirmationNumber)
					return Optional.ofNullable(p);
			}
			return Optional.empty();
		});
		
		lenient().when(paymentRepository.findByIdentityEmail(anyString())).thenAnswer((InvocationOnMock invocation) -> {
			String email = ((String)invocation.getArgument(0));
			ArrayList<Payment> payments = new ArrayList<>();
			
			if (email == null) {
				return payments;
			}
			
			for (Payment p : savedPayments) {
				if (p.getIdentity() != null && email.equals(p.getIdentity().getEmail())) {
					payments.add(p);
				}
			}
			return payments;
		});
	}
	
	@Test
	void testSuccessfulPayment() {
		ArrayList<Listing> listings = new ArrayList<>();
		Listing l = new Listing();
		l.setQuantity(1);
		l.setCanDeliver(true);
		listings.add(l);
		l = new Listing();
		l.setQuantity(1);
		l.setCanPickUp(true);
		listings.add(l);
		Address address = new Address();
		Optional<Payment> payment = paymentService.pay(DeliveryType.PICKUP, PaymentType.CREDIT_CARD, Optional.empty(), listings, Optional.ofNullable(address));
		
		assertTrue(payment.isPresent());
		assertTrue(paymentRepository.findById(confirmationNumber - 1).isPresent());
		
		assertEquals(2, savedListings.size());
		for (Listing el : savedListings) {
			assertEquals(el.getQuantity(), 0);
		}
	}
	
	@Test
	void testPaymentViolations() {
		ArrayList<Listing> listings = new ArrayList<>();
		Address address = new Address();
		Listing l1 = new Listing();
		Listing l2 = new Listing();
		
		// listing cannot be empty
		Optional<Payment> payment = paymentService.pay(DeliveryType.PICKUP, PaymentType.CREDIT_CARD, Optional.empty(), listings, Optional.ofNullable(address));
		assertTrue(payment.isEmpty());
		
		// violation of max quantity
		listings.clear();
		l1.setQuantity(0);
		l1.setCanDeliver(true);
		listings.add(l1);
		l2.setQuantity(2);
		l2.setCanPickUp(true);
		listings.add(l2);
		
		payment = paymentService.pay(DeliveryType.PICKUP, PaymentType.CREDIT_CARD, Optional.empty(), listings, Optional.ofNullable(address));
		assertTrue(payment.isEmpty());
		
		// violation of delivery type
		listings.clear();
		l1.setQuantity(2);
		l1.setCanDeliver(false);
		listings.add(l1);
		l2.setQuantity(2);
		l2.setCanPickUp(false);
		listings.add(l2);
		
		payment = paymentService.pay(DeliveryType.PICKUP, PaymentType.CREDIT_CARD, Optional.empty(), listings, Optional.ofNullable(address));
		assertTrue(payment.isEmpty());
		payment = paymentService.pay(DeliveryType.SHIPPING, PaymentType.CREDIT_CARD, Optional.empty(), listings, Optional.ofNullable(address));
		assertTrue(payment.isEmpty());
		
		// address must be available if it's by shipping
		listings.clear();
		l1.setQuantity(2);
		l1.setCanDeliver(true);
		listings.add(l1);
		l2.setQuantity(2);
		l2.setCanPickUp(true);
		listings.add(l2);
		address = null;
		
		payment = paymentService.pay(DeliveryType.PICKUP, PaymentType.CREDIT_CARD, Optional.empty(), listings, Optional.ofNullable(address));
		assertTrue(payment.isPresent());
		payment = paymentService.pay(DeliveryType.SHIPPING, PaymentType.CREDIT_CARD, Optional.empty(), listings, Optional.ofNullable(address));
		assertTrue(payment.isEmpty());
	}
	
	@Test
	void testGetPayment() {
		assertTrue(paymentService.getPayment(0).isEmpty());
		
		Listing l = new Listing();
		l.setQuantity(1);
		l.setCanPickUp(true);
		ArrayList<Listing> ls = new ArrayList<>();
		ls.add(l);
		paymentService.pay(DeliveryType.PICKUP, PaymentType.CREDIT_CARD, Optional.empty(), ls, Optional.empty());
		assertTrue(paymentService.getPayment(0).isPresent());
	}
	
	@Test
	void testFindByEmail() {
		assertEquals(0, paymentService.getAllPaymentsByEmail("69").size());
		
		Listing l = new Listing();
		l.setQuantity(1);
		l.setCanPickUp(true);
		ArrayList<Listing> ls = new ArrayList<>();
		ls.add(l);
		
		Identity identity = new Identity();
		identity.setEmail("69");
		
		paymentService.pay(DeliveryType.PICKUP, PaymentType.CREDIT_CARD, Optional.ofNullable(identity), ls, Optional.empty());
		assertEquals(1, paymentService.getAllPaymentsByEmail("69").size());
	}
}
