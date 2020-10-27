package ca.mcgill.ecse321.gallery.dao;

import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.Set;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import ca.mcgill.ecse321.gallery.model.Account;
import ca.mcgill.ecse321.gallery.model.Identity;
import ca.mcgill.ecse321.gallery.model.Payment;

@RepositoryRestResource(collectionResourceRel = "listing_data", path = "listing_data")
public interface IdentityRepository extends CrudRepository<Identity, String>{
	Identity findIdentityByEmail(String email);
	
	void deleteAll();
}
