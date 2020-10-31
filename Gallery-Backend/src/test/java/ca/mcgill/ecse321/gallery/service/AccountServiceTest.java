package ca.mcgill.ecse321.gallery.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.lenient;

import java.sql.Date;
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

import ca.mcgill.ecse321.gallery.dao.AccountRepository;
import ca.mcgill.ecse321.gallery.dao.IdentityRepository;
import ca.mcgill.ecse321.gallery.model.Account;
import ca.mcgill.ecse321.gallery.model.Address;
import ca.mcgill.ecse321.gallery.model.Identity;
import ca.mcgill.ecse321.gallery.model.Payment;
import ca.mcgill.ecse321.gallery.model.Profile;
import ca.mcgill.ecse321.gallery.model.Revenu;

@ExtendWith(MockitoExtension.class)
public class AccountServiceTest {
	@Mock
	private AccountRepository accountRepository;
	
	@Mock
	private IdentityRepository identityRepository;
	
	@InjectMocks
	AccountService accountService;
	
	private String id = "0";
	private HashSet<Account> savedAccounts = new HashSet<>();
	private HashSet<Identity> savedIdentities = new HashSet<>();
	
	@BeforeEach
	public void setupMockup() {
		// payment id generator mock
		id = "0";
		
		// database mock
		savedAccounts.clear();
		savedIdentities.clear();
		
		// lambda for simulating saving account
		Answer<Account> updateAccountNumberAndReturn = (InvocationOnMock invocation) -> {
			Account a = (Account)invocation.getArgument(0);
			a.setAccountNumber(id);
			savedAccounts.add(a);
			int accountNumberAsNum = Integer.parseInt(id);
			accountNumberAsNum++;
			id = Integer.toString(accountNumberAsNum);
			return a;
		};
		
		// lambda for simulating saving identity
		Answer<Identity> saveAndReturn = (InvocationOnMock invocation) -> {
			Identity i = (Identity)invocation.getArgument(0);
			savedIdentities.add(i);
			return i;
		};
	
		// simulate saving account
		lenient().when(accountRepository.save(any(Account.class))).thenAnswer(updateAccountNumberAndReturn);
		
		// simulate saving identity
		lenient().when(identityRepository.save(any(Identity.class))).thenAnswer(saveAndReturn);
		
		// simulate finding account by username
		lenient().when(accountRepository.findAccountByUsername(anyString())).thenAnswer((InvocationOnMock invocation) -> {
			String username = ((String)invocation.getArgument(0));
			for (Account a : savedAccounts) {
				if (a.getUsername().equals(username))
					return a;
			}
			return null;
		});
		
		// simulate finding account by Id
		lenient().when(accountRepository.findById(anyString())).thenAnswer((InvocationOnMock invocation) -> {
			String Id = ((String)invocation.getArgument(0));
			for (Account a : savedAccounts) {
				if (a.getAccountNumber().equals(Id))
					return Optional.ofNullable(a);
			}
			return Optional.empty();
		});
				
		// simulate finding all accounts
		lenient().when(accountRepository.findAll()).thenAnswer((InvocationOnMock invocation) -> {
			return savedAccounts;
		});
		
	}
	
	@Test 
	void testSuccesfullAccount() {
		Identity identity = new Identity();
		ArrayList<Profile> profiles = new ArrayList<Profile>();
		Date date = new Date(25102020);
		Address address = new Address();
		Date dateOfBirth = new Date(25102020);
		ArrayList<Revenu> revenus = new ArrayList<Revenu>();
		Optional<Account> account = accountService.createAccount("Artist", identity, profiles, "peller1", "Mcgill13", date, address, dateOfBirth, revenus, "MasterCard");
		
		assertTrue(account.isPresent());
	}
	
	@Test
	void testUsernameViolation() {
		boolean exceptionCaught = false;
		assertEquals(0, accountService.getAllAccounts().size());
		
		Identity identity = new Identity();
		ArrayList<Profile> profiles = new ArrayList<Profile>();
		Date date = new Date(25102020);
		Address address = new Address();
		Date dateOfBirth = new Date(25102020);
		ArrayList<Revenu> revenus = new ArrayList<Revenu>();
		Optional<Account> account = accountService.createAccount("Artist", identity, profiles, "peller1", "Mcgill13", date, address, dateOfBirth, revenus, "MasterCard");
		
		// there should be 1 account after creating one
		assertEquals(1, accountService.getAllAccounts().size());
		
		Identity identity2 = new Identity();
		try {
			Optional<Account> account2 = accountService.createAccount("Artist", identity2, profiles, "peller1", "Mcgill13", date, address, dateOfBirth, revenus, "MasterCard");
		}
		catch (IllegalArgumentException e) {
			exceptionCaught = true;
		}
		assertTrue(exceptionCaught);
		assertEquals(1, accountService.getAllAccounts().size());
	}
	
	@Test
	void testIdentityViolation() {
		boolean exceptionCaught = false;
		assertEquals(0, accountService.getAllAccounts().size());
		
		Identity identity = new Identity();
		ArrayList<Profile> profiles = new ArrayList<Profile>();
		Date date = new Date(25102020);
		Address address = new Address();
		Date dateOfBirth = new Date(25102020);
		ArrayList<Revenu> revenus = new ArrayList<Revenu>();
		Optional<Account> account = accountService.createAccount("Artist", identity, profiles, "peller1", "Mcgill13", date, address, dateOfBirth, revenus, "MasterCard");
		
		// there should be 1 account after creating one
		assertEquals(1, accountService.getAllAccounts().size());
		
		try { 
			Optional<Account> account2 = accountService.createAccount("Artist", identity, profiles, "EricPelletier", "Mcgill13", date, address, dateOfBirth, revenus, "MasterCard");
		}
		catch (IllegalArgumentException e) {
			exceptionCaught = true;
		}
		assertTrue(exceptionCaught);
		assertEquals(1, accountService.getAllAccounts().size());
		
	}
	
	@Test
	void testPasswordVioaltionNumber() {
		boolean exceptionCaught1 = false;
		boolean exceptionCaught2 = false;
		boolean exceptionCaught3 = false;
		Identity identity = new Identity();
		ArrayList<Profile> profiles = new ArrayList<Profile>();
		Date date = new Date(25102020);
		Address address = new Address();
		Date dateOfBirth = new Date(25102020);
		ArrayList<Revenu> revenus = new ArrayList<Revenu>();
		try {
			Optional<Account> account = accountService.createAccount("Artist", identity, profiles, "peller1", "Mcgill", date, address, dateOfBirth, revenus, "MasterCard");
		}
		catch (IllegalArgumentException e) {
			exceptionCaught1 = true;
		}
		assertTrue(exceptionCaught1);
		assertEquals(0, accountService.getAllAccounts().size());
		
		try {
			Optional<Account> account = accountService.createAccount("Artist", identity, profiles, "peller1", "Mcgill", date, address, dateOfBirth, revenus, "MasterCard");
		}
		catch (IllegalArgumentException e) {
			exceptionCaught2 = true;
		}
		assertTrue(exceptionCaught2);
		assertEquals(0, accountService.getAllAccounts().size());
		
		try {
			Optional<Account> account = accountService.createAccount("Artist", identity, profiles, "peller1", "Mc1gi", date, address, dateOfBirth, revenus, "MasterCard");
		}
		catch (IllegalArgumentException e) {
			exceptionCaught3 = true;
		}
		assertTrue(exceptionCaught3);
		assertEquals(0, accountService.getAllAccounts().size());
		
	}
	
	@Test
	void testEditAccountValid() {
		Identity identity = new Identity();
		ArrayList<Profile> profiles = new ArrayList<Profile>();
		Date date = new Date(25102020);
		Address address = new Address();
		Date dateOfBirth = new Date(25102020);
		ArrayList<Revenu> revenus = new ArrayList<Revenu>();
		Optional<Account> account = accountService.createAccount("Artist", identity, profiles, "peller1", "Mcgill13", date, address, dateOfBirth, revenus, "MasterCard");
		
		Optional<Account> accountEdit = accountService.editAccount("Arist", profiles, "peller1", "Mcgill1234", address, dateOfBirth, revenus, "MasterCard", "Mcgill13");
		
		assertEquals("Mcgill1234", accountService.getAccountByUsername("peller1").get(0).getPassword());
		
	}
	
	@Test
	void testFindAccountByUsername() {
		// there should not be any accounts with username peller1 before creation
		assertEquals(0, accountService.getAccountByUsername("peller1").size());
		
		Identity identity = new Identity();
		ArrayList<Profile> profiles = new ArrayList<Profile>();
		Date date = new Date(25102020);
		Address address = new Address();
		Date dateOfBirth = new Date(25102020);
		ArrayList<Revenu> revenus = new ArrayList<Revenu>();
		Optional<Account> account = accountService.createAccount("Artist", identity, profiles, "peller1", "Mcgill13", date, address, dateOfBirth, revenus, "MasterCard");
		
		// there should be an account with username peller1 before creation
		assertEquals(1, accountService.getAccountByUsername("peller1").size());
	}
	
	@Test 
	void testGetAccountById() {
		assertTrue(accountService.getAccountById("0").isEmpty());
		
		Identity identity = new Identity();
		ArrayList<Profile> profiles = new ArrayList<Profile>();
		Date date = new Date(25102020);
		Address address = new Address();
		Date dateOfBirth = new Date(25102020);
		ArrayList<Revenu> revenus = new ArrayList<Revenu>();
		Optional<Account> account = accountService.createAccount("Artist", identity, profiles, "peller1", "Mcgill13", date, address, dateOfBirth, revenus, "MasterCard");
		
		assertTrue(accountService.getAccountById("0").isPresent());
	}
	
	@Test
	void testGetAllAccounts() {
		assertEquals(0, accountService.getAllAccounts().size());
		
		Identity identity = new Identity();
		ArrayList<Profile> profiles = new ArrayList<Profile>();
		Date date = new Date(25102020);
		Address address = new Address();
		Date dateOfBirth = new Date(25102020);
		ArrayList<Revenu> revenus = new ArrayList<Revenu>();
		Optional<Account> account = accountService.createAccount("Artist", identity, profiles, "peller1", "Mcgill13", date, address, dateOfBirth, revenus, "MasterCard");
		
		Identity identity2 = new Identity();
		Optional<Account> account2 = accountService.createAccount("Artist", identity2, profiles, "peller2", "Mcgill13", date, address, dateOfBirth, revenus, "MasterCard");
		
		assertEquals(2, accountService.getAllAccounts().size());
	}
}