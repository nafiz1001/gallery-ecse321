/**
 * @author Thomas Alarcon
 * 
 */
package ca.mcgill.ecse321.gallery.dao;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.gallery.model.Art;

public interface ArtRepository extends CrudRepository<Art, Long> {
	Art findArtById(Long id);
	
	void deleteAll();

}
