package ca.mcgill.ecse321.gallery.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Date;
import java.util.ArrayList;
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
import ca.mcgill.ecse321.gallery.model.DeliveryType;
import ca.mcgill.ecse321.gallery.model.Identity;
import ca.mcgill.ecse321.gallery.model.Listing;
import ca.mcgill.ecse321.gallery.model.Payment;
import ca.mcgill.ecse321.gallery.model.PaymentType;

@ExtendWith(MockitoExtension.class)
public class PaymentServiceTests {
	@Mock
	private PaymentRepository paymentRepository;
	
	@InjectMocks
	PaymentService paymentService;
	
	private long confirmationNumber = 0;
	private Optional<Payment> p1;
	private Optional<Payment> p2;
	
	@BeforeEach
	public void setMockOutput() {
		confirmationNumber = 0;
		Answer<Payment> updateConfirmationNumberAndReturn = (InvocationOnMock invocation) -> {
			Payment p = (Payment)invocation.getArgument(0);
			p.setConfirmationNumber(confirmationNumber);
			confirmationNumber++;
			return p;
		};
		lenient().when(paymentRepository.save(any(Payment.class))).thenAnswer(updateConfirmationNumberAndReturn);
		
		p1 = paymentService.pay("", DeliveryType.PICKUP, PaymentType.CREDIT_CARD, null, new ArrayList<>());
		p2 = paymentService.pay("", DeliveryType.PICKUP, PaymentType.CREDIT_CARD, null, new ArrayList<>());
		assertTrue(p1.isPresent());
		assertTrue(p2.isPresent());
		
		lenient().when(paymentRepository.findById(any())).thenAnswer((InvocationOnMock invocation) -> {
			int confirmationNumber = ((Long)invocation.getArgument(0)).intValue();
			switch (confirmationNumber) {
				case 0:
					return Optional.ofNullable(p1);
				case 1:
					return Optional.ofNullable(p2);
				default:
					return Optional.empty();
			}
		});
	}
	
	@Test
	void testCreatePayment() {
		assertTrue(paymentService.getPayment(p1.get().getConfirmationNumber()).isPresent());
		assertTrue(paymentService.getPayment(p2.get().getConfirmationNumber()).isPresent());
		
		long notValidId = 2;
		assertTrue(paymentService.getPayment(notValidId).isEmpty());
	}
	
	@Test
	void testGetPayments() {
		lenient().when(paymentRepository.findAll()).thenAnswer((InvocationOnMock invocation) -> {
			ArrayList<Payment> payments = new ArrayList<>();
			payments.add(p1.get());
			payments.add(p2.get());
			return payments;
		});
		
		List<Payment> payments = paymentService.getAllPayments(); 
		
		assertEquals(2, payments.size());
		for (int i = 0; i < payments.size(); ++i) {
			assertEquals(i, payments.get(i).getConfirmationNumber());
		}
	}
}
