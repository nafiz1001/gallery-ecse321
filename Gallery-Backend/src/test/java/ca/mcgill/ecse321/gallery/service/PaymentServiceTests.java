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
		// payment id generator mock
		confirmationNumber = 0;
		
		// database mock
		savedPayments.clear();
		savedListings.clear();
		
		// lambda for simulating saving payment
		Answer<Payment> updateConfirmationNumberAndReturn = (InvocationOnMock invocation) -> {
			Payment p = (Payment)invocation.getArgument(0);
			p.setConfirmationNumber(confirmationNumber);
			savedPayments.add(p);
			confirmationNumber++;
			return p;
		};
		
		// lambda for simulating saving listing
		Answer<Listing> saveAndReturn = (InvocationOnMock invocation) -> {
			Listing l = (Listing)invocation.getArgument(0);
			savedListings.add(l);
			return l;
		};
		
		// simulate saving payment
		lenient().when(paymentRepository.save(any(Payment.class))).thenAnswer(updateConfirmationNumberAndReturn);
		
		// simulate saving listing
		lenient().when(listingRepository.save(any(Listing.class))).thenAnswer(saveAndReturn);
		
		// simulate finding payment by confirmation number
		lenient().when(paymentRepository.findById(any())).thenAnswer((InvocationOnMock invocation) -> {
			long confirmationNumber = ((Long)invocation.getArgument(0));
			for (Payment p : savedPayments) {
				if (p.getConfirmationNumber() == confirmationNumber)
					return Optional.ofNullable(p);
			}
			return Optional.empty();
		});
		
		// simulate finding payment based on identity email
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
		
		// successful payment
		assertTrue(payment.isPresent());
	}
	
	@Test
	void testPaymentListingViolations() {
		ArrayList<Listing> listings = new ArrayList<>();
		Address address = new Address();
		Listing l1 = new Listing();
		Listing l2 = new Listing();
		
		Optional<Payment> payment = paymentService.pay(DeliveryType.PICKUP, PaymentType.CREDIT_CARD, Optional.empty(), listings, Optional.ofNullable(address));
		// listing cannot be empty
		assertTrue(payment.isEmpty());
		
		l1.setQuantity(0);
		l1.setCanDeliver(true);
		listings.add(l1);
		l2.setQuantity(2);
		l2.setCanPickUp(true);
		listings.add(l2);
		
		payment = paymentService.pay(DeliveryType.PICKUP, PaymentType.CREDIT_CARD, Optional.empty(), listings, Optional.ofNullable(address));
		// cannot buy something that has 0 quantity
		assertTrue(payment.isEmpty());
		
		// add l1 listing (to simulate user wanting to buy two l1 listing)
		l1.setQuantity(1);
		listings.add(l1);
		
		payment = paymentService.pay(DeliveryType.PICKUP, PaymentType.CREDIT_CARD, Optional.empty(), listings, Optional.ofNullable(address));
		// cannot buy more than what is available
		assertTrue(payment.isEmpty());
	}
	
	@Test
	void testPaymentDeliveryTypeViolations() {
		ArrayList<Listing> listings = new ArrayList<>();
		Address address = new Address();
		Listing l1 = new Listing();
		Listing l2 = new Listing();
		
		l1.setQuantity(2);
		l1.setCanDeliver(false);
		listings.add(l1);
		l2.setQuantity(2);
		l2.setCanPickUp(false);
		listings.add(l2);
		
		Optional<Payment> payment = paymentService.pay(DeliveryType.PICKUP, PaymentType.CREDIT_CARD, Optional.empty(), listings, Optional.ofNullable(address));
		// cannot do pickup if no listings allow pickup
		assertTrue(payment.isEmpty());
		
		payment = paymentService.pay(DeliveryType.SHIPPING, PaymentType.CREDIT_CARD, Optional.empty(), listings, Optional.ofNullable(address));
		// cannot do delivery if no listings allow delivery
		assertTrue(payment.isEmpty());
		
		l1.setCanDeliver(true);
		payment = paymentService.pay(DeliveryType.SHIPPING, PaymentType.CREDIT_CARD, Optional.empty(), listings, Optional.ofNullable(address));
		// allow delivery if at least 1 listing allow delivery
		assertTrue(payment.isPresent());
		
		l1.setCanPickUp(true);
		payment = paymentService.pay(DeliveryType.SHIPPING, PaymentType.CREDIT_CARD, Optional.empty(), listings, Optional.ofNullable(address));
		// allow pick if at least 1 listing allow pickup
		assertTrue(payment.isPresent());
	}
	
	@Test
	void testPaymentShippingViolations() {
		ArrayList<Listing> listings = new ArrayList<>();
		Address address = new Address();
		Listing l1 = new Listing();
		Listing l2 = new Listing();
		
		l1.setQuantity(2);
		l1.setCanDeliver(true);
		listings.add(l1);
		l2.setQuantity(2);
		l2.setCanPickUp(true);
		listings.add(l2);
		address = null;
		
		Optional<Payment> payment = paymentService.pay(DeliveryType.PICKUP, PaymentType.CREDIT_CARD, Optional.empty(), listings, Optional.ofNullable(address));
		// address is optional for pickup option
		assertTrue(payment.isPresent());
		
		payment = paymentService.pay(DeliveryType.SHIPPING, PaymentType.CREDIT_CARD, Optional.empty(), listings, Optional.ofNullable(address));
		// address is mandatory for delivery option
		assertTrue(payment.isEmpty());
	}
	
	@Test
	void testPaymentEnumViolations() {
		ArrayList<Listing> listings = new ArrayList<>();
		Address address = new Address();
		Listing l1 = new Listing();
		Listing l2 = new Listing();
		
		l1.setQuantity(2);
		l1.setCanDeliver(true);
		listings.add(l1);
		l2.setQuantity(2);
		l2.setCanPickUp(true);
		listings.add(l2);
		address = null;
		
		Optional<Payment> payment = paymentService.pay(null, PaymentType.CREDIT_CARD, Optional.empty(), listings, Optional.ofNullable(address));
		// delivery type cannot be null
		assertTrue(payment.isEmpty());
		
		payment = paymentService.pay(DeliveryType.PICKUP, null, Optional.empty(), listings, Optional.ofNullable(address));
		// payment type cannot be null
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
		// there should be no payment with identity of email 69 before payment
		assertEquals(0, paymentService.getAllPaymentsByEmail("69").size());
		
		Listing l = new Listing();
		l.setQuantity(1);
		l.setCanPickUp(true);
		ArrayList<Listing> ls = new ArrayList<>();
		ls.add(l);
		
		Identity identity = new Identity();
		identity.setEmail("69");
		
		paymentService.pay(DeliveryType.PICKUP, PaymentType.CREDIT_CARD, Optional.ofNullable(identity), ls, Optional.empty());
		
		// there should be a payment with identity of email 69 after payment
		assertEquals(1, paymentService.getAllPaymentsByEmail("69").size());
	}
}
