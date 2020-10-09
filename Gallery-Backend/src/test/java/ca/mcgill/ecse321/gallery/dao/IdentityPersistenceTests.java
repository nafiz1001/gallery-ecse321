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

import ca.mcgill.ecse321.gallery.model.Account;
import ca.mcgill.ecse321.gallery.model.Address;
import ca.mcgill.ecse321.gallery.model.DeliveryType;
import ca.mcgill.ecse321.gallery.model.Identity;
import ca.mcgill.ecse321.gallery.model.Payment;
import ca.mcgill.ecse321.gallery.model.PaymentType;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class IdentityPersistenceTests {

	@Autowired
	private IdentityRepository identityRepository;
	
	@Autowired
	private PaymentRepository paymentRepository;
	
	@Autowired
	private AccountRepository accountRepository;
	
	@BeforeEach
	public void clearDatabaseBefore() {
		
		paymentRepository.deleteAll();
		identityRepository.deleteAll();
		accountRepository.deleteAll();
		
	}
	
	@AfterEach
	public void clearDatabaseAfter() {
		paymentRepository.deleteAll();
		identityRepository.deleteAll();
		accountRepository.deleteAll();
		
	}
	
	
	@Test
	public void testCreateAndLoadIdentity() {
		
		
		
		Identity identity1 = new Identity();
		identity1.setEmail("123");
		
		
		identityRepository.save(identity1);
		
		Identity identity2 = identityRepository.findIdentityByEmail("123");
		
		assertNotNull(identity2);
		assertEquals(identity1.getEmail(), identity2.getEmail());
		
	}
	
	@Test
	public void testUpdateIdentity() {
		
		
		Identity identity1 = new Identity();
		identity1.setEmail("123");
		
		
		
		identity1 = identityRepository.save(identity1);
		
		identity1.setEmail("321");
		
		identity1 = identityRepository.save(identity1);
		
		assertEquals(identity1.getEmail(), "321");
	}
}
