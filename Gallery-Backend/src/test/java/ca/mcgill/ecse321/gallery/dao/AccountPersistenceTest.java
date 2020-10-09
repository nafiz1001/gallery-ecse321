package ca.mcgill.ecse321.gallery.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Date;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ca.mcgill.ecse321.gallery.model.Account;
import ca.mcgill.ecse321.gallery.model.Address;
import ca.mcgill.ecse321.gallery.model.Identity;


@ExtendWith(SpringExtension.class)
@SpringBootTest
public class AccountPersistenceTest {

	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private IdentityRepository identityRepository;
		
	@BeforeEach
	public void clearDatabse() {
		accountRepository.deleteAll();
		addressRepository.deleteAll();
		identityRepository.deleteAll();
	}

	@AfterEach 
	public void clearDatabase() {
		accountRepository.deleteAll();
		addressRepository.deleteAll();
		identityRepository.deleteAll();
	}
	
	
	@Test
	public void testCreateAndLoadAccount() {
		Date date1 = new Date(2020, 9, 8);
		Date date2 = new Date(2000, 2, 9);
		
		Address address1 = new Address();
		address1.setCity("a");
		address1.setId("b");
		address1.setPostalCode("c");
		address1.setProvince("d");
		address1.setStreet("d");
		address1.setStreetNumber("e");
		
		addressRepository.save(address1);
		
		Identity identity = new Identity();
		identity.setEmail("eric@gmail.com");
		
		identityRepository.save(identity);
		
		
		Account account1 = new Account();
		account1.setAccountHolderType("Artist");
		account1.setUsername("peller1");
		account1.setPassword("eric");
		account1.setDateJoined(date1);
		account1.setAddress(address1);
		account1.setDateOfBirth(date2);
		account1.setAccountNumber("12345");
		account1.setPaymentType("MasterCard");
		account1.setIdentity(identity);
		
		accountRepository.save(account1);
		
		Account account2 = accountRepository.findAccountByUsername("peller1");
		
		assertNotNull(account2);
		assertEquals(account1.getPaymentType(), account2.getPaymentType());
			
	}
	
	@Test
	public void testUpdateAccount() {
		Date date1 = new Date(2020, 9, 8);
		Date date2 = new Date(2000, 2, 9);
		
		Address address1 = new Address();
		address1.setCity("a");
		address1.setId("b");
		address1.setPostalCode("c");
		address1.setProvince("d");
		address1.setStreet("d");
		address1.setStreetNumber("e");
		
		addressRepository.save(address1);
		
		Identity identity = new Identity();
		identity.setEmail("eric@gmail.com");
		
		identityRepository.save(identity);
		
		Account account1 = new Account();
		account1.setAccountHolderType("Artist");
		account1.setUsername("peller1");
		account1.setPassword("eric");
		account1.setDateJoined(date1);
		account1.setAddress(address1);
		account1.setDateOfBirth(date2);
		account1.setAccountNumber("12345");
		account1.setPaymentType("MasterCard");
		account1.setIdentity(identity);
		
		accountRepository.save(account1);
		
		account1.setPassword("David");
		
		accountRepository.save(account1);
		
		assertEquals(account1.getPassword(), "David");
	}
	
	
}