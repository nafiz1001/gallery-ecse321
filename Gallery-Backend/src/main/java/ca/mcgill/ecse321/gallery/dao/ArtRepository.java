/**
 * @author Thomas Alarcon
 * 
 */
package ca.mcgill.ecse321.gallery.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.gallery.model.Art;

public interface ArtRepository extends CrudRepository<Art, String> {
Art findArtById(String id);
	
	void deleteAll();
}
