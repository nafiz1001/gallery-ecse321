package ca.mcgill.ecse321.gallery.dao;

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
public class PaymentPersistenceTests {
	@Autowired
	private IdentityRepository identityRepository;
	
	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private PaymentRepository paymentRepository;
	
	
	@BeforeEach
	public void clearDatabse() {
		accountRepository.deleteAll();
		paymentRepository.deleteAll();
		addressRepository.deleteAll();
		identityRepository.deleteAll();
		
	}
	
	@AfterEach
	public void clearDatabase() {
		accountRepository.deleteAll();
		paymentRepository.deleteAll();
		addressRepository.deleteAll();
		identityRepository.deleteAll();
	}
	
	@Test
	public void testCreateAndLoad() {
		Account nullAccount = new Account();
		Address nullAddress = new Address();
		Identity nullIdentity = new Identity();
		
		nullAddress.setCity("");
		nullAddress.setId("");
		nullAddress.setPostalCode("");
		nullAddress.setProvince("");
		nullAddress.setStreet("");
		nullAddress.setStreetNumber("");
		nullAddress = addressRepository.save(nullAddress);
		
		nullIdentity.setEmail("");
		nullIdentity.setAccount(nullAccount);
		
		nullAccount.setAccountHolderType("");
		nullAccount.setAccountNumber("");
		nullAccount.setAddress(nullAddress);
		nullAccount.setDateJoined(new Date(0));
		nullAccount.setDateOfBirth(new Date(0));
		nullAccount.setIdentity(nullIdentity);
	}
}
