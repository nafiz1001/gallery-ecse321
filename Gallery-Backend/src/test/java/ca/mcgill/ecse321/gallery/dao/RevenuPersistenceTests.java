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

import ca.mcgill.ecse321.gallery.model.Revenu;
import ca.mcgill.ecse321.gallery.model.Account;
import ca.mcgill.ecse321.gallery.model.Listing;

/**
 * 
 * @author halukcalin
 *
 */

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class RevenuPersistenceTests {
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private RevenuRepository revenuRepository;
	
	@BeforeEach
	public void clearDatabse() {
		accountRepository.deleteAll();
		revenuRepository.deleteAll();
	}
	
	@AfterEach
	public void clearDatabase() {
		accountRepository.deleteAll();
		revenuRepository.deleteAll();
	}
	
	@Test
	public void testCreateAndLoadRevenu() {
		//Account account1 = new Account();
		Revenu revenu1 = new Revenu();
		//Listing listing1 = new Listing();
		revenu1.setComission(1);
		revenu1.setListingPrice(2);
		//revenu1.setAccount(account1);
		//revenu1.setListing(listing1);
		revenu1.setId((long)1);
		
		revenu1 = revenuRepository.save(revenu1);
		
		Revenu revenu2 = revenuRepository.findRevenuById(revenu1.getId());
		
		assertNotNull(revenu2);
		assertEquals(revenu1.getComission(), revenu2.getComission());
	}
	
	@Test
	public void testUpdateRevenu() {
		//Account account1 = new Account(); 
		Revenu revenu1 = new Revenu();
		//Listing listing1 = new Listing();
		revenu1.setComission(1);
		revenu1.setListingPrice(2);
		//revenu1.setAccount(account1);
		//revenu1.setListing(listing1);
		revenu1.setId((long)0);
		
		revenu1 = revenuRepository.save(revenu1);
		
		revenu1.setComission(2);
		
		revenu1 = revenuRepository.save(revenu1);
		
		assertEquals(revenu1.getComission(),2);
	}
}
