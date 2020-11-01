package ca.mcgill.ecse321.gallery.dao;

import org.springframework.data.repository.CrudRepository;
import ca.mcgill.ecse321.gallery.model.Revenu;

public interface RevenuRepository extends CrudRepository<Revenu, Long> {

	Revenu findRevenuById(long id);
	
	void deleteAll();
}
