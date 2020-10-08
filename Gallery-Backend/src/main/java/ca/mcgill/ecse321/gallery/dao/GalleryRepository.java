package ca.mcgill.ecse321.gallery.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.gallery.model.Gallery;


public interface GalleryRepository extends CrudRepository<Gallery, String>{
	Gallery findGalleryByName(String id);
	
	void deleteAll();
}
