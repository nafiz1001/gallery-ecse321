package ca.mcgill.ecse321.gallery.service;

import java.sql.Date;
import java.sql.Time;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import ca.mcgill.ecse321.gallery.dao.ArtRepository;
import ca.mcgill.ecse321.gallery.dao.GalleryRepository;
import ca.mcgill.ecse321.gallery.model.Address;
import ca.mcgill.ecse321.gallery.model.Art;
import ca.mcgill.ecse321.gallery.model.Gallery;
import ca.mcgill.ecse321.gallery.model.Listing;
import ca.mcgill.ecse321.gallery.model.Profile;
import ca.mcgill.ecse321.gallery.utils.Utils;

public class ArtService {

	@Autowired
	ArtRepository artRepository;

	@Transactional
	public Optional<Art> createArt(String title, String description, double height, double width, String depth,
			String image, Date creationDate, Profile uploader, String type, String author) {
		boolean isArtValid = true;
		Art art = null;

		// does title already exist?
		
		// is height negative?
		
		// is width negative?
		
		// is depth a positive number?

		// is creation date before current day

		// if Art is valid
		if (isArtValid) {

			// create Art
			Art validArt = new Art();
			validArt.setAuthor(author);
			validArt.setDate(creationDate);
			validArt.setDepth(depth);
			validArt.setDescription(description);
			validArt.setHeight(height);
			validArt.setImage(image);
			validArt.setName(title);
			validArt.setType(type);
			validArt.setWidth(width);
			validArt.setOwner(uploader);
			
			artRepository.save(validArt);
		}
		return Optional.ofNullable(art);
	}

//	@Transactional
//	public Optional<Gallery> getArt(String name) {
//		return galleryRepository.findById(name);
//	}
}
