package ca.mcgill.ecse321.gallery.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.gallery.model.Art;
import ca.mcgill.ecse321.gallery.model.Gallery;

public interface ArtRepository extends CrudRepository<Art, String> {
Art findArtByID(String id);
	
	void deleteAll();
}
