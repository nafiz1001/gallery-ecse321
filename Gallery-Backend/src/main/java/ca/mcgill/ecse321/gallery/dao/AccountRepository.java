package ca.mcgill.ecse321.gallery.dao;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.gallery.model.Account;

public interface AccountRepository extends CrudRepository<Account, String>{
	Account findAccountByUsername(String id);
	
	void deleteAll();
}
