package ca.mcgill.ecse321.gallery.dao;

import org.springframework.data.repository.CrudRepository;
import ca.mcgill.ecse321.gallery.model.Revenu;

public interface RevenuRepository extends CrudRepository<Revenu, String> {

	Revenu findRevenuById(String id);
	
	void deleteAll();
}
