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
	void testListingViolation() {
		ArrayList<Listing> listings = new ArrayList<>();
		Address address = new Address();
		Listing l1 = new Listing();
		Listing l2 = new Listing();
		
		// listing cannot be empty
		Optional<Payment> payment = paymentService.pay(DeliveryType.PICKUP, PaymentType.CREDIT_CARD, Optional.empty(), listings, Optional.ofNullable(address));
		assertTrue(payment.isEmpty());
		
		// violation of max quantity
		l1.setQuantity(0);
		l1.setCanDeliver(true);
		listings.add(l1);
		l2.setQuantity(2);
		l2.setCanPickUp(true);
		listings.add(l2);
		
		payment = paymentService.pay(DeliveryType.PICKUP, PaymentType.CREDIT_CARD, Optional.empty(), listings, Optional.ofNullable(address));
		assertTrue(payment.isEmpty());
	}
}
