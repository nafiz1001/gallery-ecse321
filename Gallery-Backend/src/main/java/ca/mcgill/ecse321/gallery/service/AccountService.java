package ca.mcgill.ecse321.gallery.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.gallery.dao.AccountRepository;
import ca.mcgill.ecse321.gallery.model.Account;
import ca.mcgill.ecse321.gallery.model.Address;
import ca.mcgill.ecse321.gallery.model.Identity;
import ca.mcgill.ecse321.gallery.model.Profile;
import ca.mcgill.ecse321.gallery.model.Revenu;
import ca.mcgill.ecse321.gallery.utils.Utils;

public class AccountService {
	
	@Autowired
	AccountRepository accountRepository;
	
	@Transactional
	public Optional<Account> createAccount(String accountHolderType, Identity identity, Iterable<Profile> profiles, String username, String password, Date date, Address address, Date dateOfBirth, Iterable<Revenu> revenus, String paymentType) {
		Account account = null;
		Boolean isAccountValid = true;
		
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
			identity.setAccount(account);
			
			accountRepository.save(account);
		
		}
		
		return Optional.ofNullable(account);
		
	}
	
	public Optional<Account> editAccount(String accountHolderType, Iterable<Profile> profiles,String username, String password, Address address, Date dateOfBirth, Iterable<Revenu> revenus, String paymentType, String OldPassword) {
		Boolean isAccountValid = true;
		
		// is username and identity unique?
		List<Account> accounts = getAllAccounts();
		for(Account a : accounts) {
			if (a.getUsername().equals(username) && a.getPassword().equals(OldPassword)) {
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
					a.setAccountHolderType(accountHolderType);
					a.setProfile(Utils.toSet(profiles));
					a.setPassword(password);
					a.setAddress(address);
					a.setDateOfBirth(dateOfBirth);
					a.setRevenus(Utils.toSet(revenus));
					a.setPaymentType(paymentType);
					a.setAccountNumber("1");
					accountRepository.save(a);
					return Optional.ofNullable(a);
				}
			}
		}
		
		return null;
	}
	
	@Transactional
	public List<Account> getAllAccounts() {
		return Utils.toList(accountRepository.findAll());
	}
	
	@Transactional 
	public List<Account> getAccountByUsername(String username) {
		List<Account> account = new ArrayList<Account>();
		if (accountRepository.findAccountByUsername(username) != null) {
			account.add(accountRepository.findAccountByUsername(username));
		}
		return account;
	}
	
	@Transactional
	public Optional<Account> getAccountById(String id) {
		return accountRepository.findById(id);
	}
	

	
}