/**
 * @author antonianistor
 */
package ca.mcgill.ecse321.gallery.dao;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import ca.mcgill.ecse321.gallery.model.Listing;

@RepositoryRestResource(collectionResourceRel = "listing_data", path = "listing_data")
public interface ListingRepository extends CrudRepository<Listing, Long>{ 
	
	Listing findListingById(long id);
	
	void deleteAll();

}
