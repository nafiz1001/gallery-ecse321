package ca.mcgill.ecse321.gallery.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.gallery.model.Listing;

public interface ListingRepository extends CrudRepository<Listing, String>{ 
	
	Listing findListingById(String id);
	
	void deleteAll();

}
