package ca.mcgill.ecse321.gallery.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.gallery.dao.AccountRepository;
import ca.mcgill.ecse321.gallery.dao.IdentityRepository;
import ca.mcgill.ecse321.gallery.model.Account;
import ca.mcgill.ecse321.gallery.model.Address;
import ca.mcgill.ecse321.gallery.model.Identity;
import ca.mcgill.ecse321.gallery.model.Profile;
import ca.mcgill.ecse321.gallery.model.Revenu;
import ca.mcgill.ecse321.gallery.utils.Utils;

/**
 * 
 * @author Ericpelletier135
 * This service allows the create, editing and retrieval of accounts. 
 */

@Service
public class AccountService {
	
	@Autowired
	AccountRepository accountRepository;
	
	@Autowired
	IdentityRepository identityRepository;
	
	@Autowired
	IdentityService identityService = new IdentityService();
	
	/**
	 * Creates an Account.
	 * @param accountHolderType The type of account to be created
	 * @param identity The identity associated to this account
	 * @param profiles The profiles associated to this account
	 * @param username The username of the account
	 * @param parrword The password of the account
	 * @param date Date that the account is created
	 * @param address Address associated to this account
	 * @param dateOfBirth The date of birth of the account holder
	 * @param revenus The revenus associated to this account
	 * @param paymentType The payment type associated to this account
	 * @return The account created
	 */
	@Transactional
	public Optional<Account> createAccount(String accountHolderType, Identity identity, Iterable<Profile> profiles, String username, String password, Date date, Address address, Date dateOfBirth, Iterable<Revenu> revenus, String paymentType) {
		Account account = null;
		Boolean isAccountValid = true;
		Optional<Identity> identityFound = null;
		
		// is username and identity unique?
		List<Account> accounts = getAllAccounts();
		for(Account a : accounts) {
			if (a.getUsername().equals(username)) {
				isAccountValid = false;
				throw new IllegalArgumentException("Username already taken.");
			}
			if (a.getIdentity().equals(identity)) {
				isAccountValid = false;
				throw new IllegalArgumentException("An account is already created for this Identity");
			}
		}
		
		// is password valid?
		if (password.length() < 6) {
			isAccountValid = false;
			throw new IllegalArgumentException("Password must be at least 6 characters long.");
		}
		else {
			Boolean upperCase = false;
			Boolean hasDigit = false;
			for (int i = 0; i < password.length(); i++) {
				char a = password.charAt(i);
				if (Character.isUpperCase(a)) {
					upperCase = true;
				} else if (Character.isDigit(a)) {
					hasDigit = true;
				}
			}
			if (!upperCase || !hasDigit) {
				isAccountValid = false;
				throw new IllegalArgumentException("Password must contain at least one number and one upper case character.");
			}
			
		}
		
		
		if(isAccountValid) {
			account = new Account();
			account.setAccountHolderType(accountHolderType);
			account.setIdentity(identity);
			account.setProfile(Utils.toSet(profiles));
			account.setUsername(username);
			account.setPassword(password);
			account.setDateJoined(date);
			account.setAddress(address);
			account.setDateOfBirth(dateOfBirth);
			account.setRevenus(Utils.toSet(revenus));
			account.setPaymentType(paymentType);
			account.setAccountNumber("1");
			//identity.setAccount(account);
			
			account = accountRepository.save(account);
		
			identity.setAccount(account);
			
			identityRepository.save(identity);
		}
		
		return Optional.ofNullable(account);
		
	}
	
	/**
	 * Edits an Account.
	 * @param accountHolderType The type of account to be created
	 * @param username The username associated to the account to be edited
	 * @param parrword The new password
	 * @param address The new address
	 * @param dateOfBirth The new date of birth
	 * @param paymentType The new paymentType
	 * @param OldPassword The password associated to the account to be edited
	 * @return The new Account created
	 */
	
	public Optional<Account> editAccount(String accountHolderType,String username, String password, Address address, Date dateOfBirth, String paymentType, String OldPassword) {
		Boolean isAccountValid = true;
		Account account = null;
		
		List<Account> accounts = getAccountByUsername(username);
		if(accounts.size() != 0) {
			account = accounts.get(0);
		} else {
			throw new IllegalArgumentException("No account found with such username");
		}
		
		
		if (account.getPassword().equals(OldPassword)) {
			// is password valid?
			if (password.length() < 6) {
				isAccountValid = false;
				throw new IllegalArgumentException("Password must be at least 6 characters long.");
			}
			else {
				Boolean upperCase = false;
				Boolean hasDigit = false;
				for (int i = 0; i < password.length(); i++) {
					char b = password.charAt(i);
					if (Character.isUpperCase(b)) {
						upperCase = true;
					} else if (Character.isDigit(b)) {
						hasDigit = true;
					}
				}
				if (!upperCase || !hasDigit) {
					isAccountValid = false;
					throw new IllegalArgumentException("Password must contain at least one number and one upper case character.");
				}
				
			}
			
			if(isAccountValid) {
				account.setAccountHolderType(accountHolderType);
				account.setPassword(password);
				account.setAddress(address);
				account.setDateOfBirth(dateOfBirth);
				account.setPaymentType(paymentType);
				account.setAccountNumber("1");
				accountRepository.save(account);
				return Optional.ofNullable(account);
			}
		} else {
			throw new IllegalArgumentException("The password is incorect.");
		}
		
		return null;
		
	}
	
	/**
	 * Retrieves all accounts
	 * @return All the accounts in the database
	 */
	@Transactional
	public List<Account> getAllAccounts() {
		return Utils.toList(accountRepository.findAll());
	}
	
	/**
	 * Retrieves an account with a specific username
	 * @param username The username of the account to find
	 * @return An account with the given username inside of a list
	 */
	@Transactional 
	public List<Account> getAccountByUsername(String username) {
		List<Account> account = new ArrayList<Account>();
		if (accountRepository.findAccountByUsername(username) != null) {
			account.add(accountRepository.findAccountByUsername(username));
		}
		return account;
	}
	
	/**
	 * Retrieves an account with a specific id
	 * @param id The id of the account to find
	 * @return An account with the given id
	 */
	@Transactional
	public Optional<Account> getAccountById(String id) {
		return accountRepository.findById(id);
	}
	
	
}