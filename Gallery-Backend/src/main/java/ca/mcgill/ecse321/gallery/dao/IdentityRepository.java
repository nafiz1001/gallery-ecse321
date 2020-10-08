package ca.mcgill.ecse321.gallery.dao;

import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.Set;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.gallery.model.Account;
import ca.mcgill.ecse321.gallery.model.Identity;
import ca.mcgill.ecse321.gallery.model.Payment;

public interface IdentityRepository extends CrudRepository<Identity, String>{
	Identity findIdentityByEmail(String email);
	
	void deleteAll();
}
