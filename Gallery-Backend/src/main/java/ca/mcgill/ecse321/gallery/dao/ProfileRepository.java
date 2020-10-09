package ca.mcgill.ecse321.gallery.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.gallery.model.Address;
import ca.mcgill.ecse321.gallery.model.Identity;
import ca.mcgill.ecse321.gallery.model.Profile;

public interface ProfileRepository extends CrudRepository<Profile, String>{
	Profile findProfileById(String id);
	
	void deleteAll();
}