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
			}
			if (a.getIdentity().equals(identity)) {
				isAccountValid = false;
			}
		}
		
		// is password valid?
		if (password.length() < 6) isAccountValid = false;
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
			
			accountRepository.save(account);
		
		}
		
		return Optional.ofNullable(account);
		
	}
	
	@Transactional
	public List<Account> getAllAccounts() {
		return Utils.toList(accountRepository.findAll());
	}
	
	@Transactional 
	public List<Account> getAccountByUsername(String username) {
		List<Account> account = new ArrayList<Account>();
		if (accountRepository.findAccountByUsername(username) != null) {
			account.add(accountRepository.findAccountByUsername("username"));
		}
		return account;
	}
	
	@Transactional
	public Optional<Account> getAccountById(String id) {
		return accountRepository.findById(id);
	}
	

	
}