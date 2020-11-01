package ca.mcgill.ecse321.gallery.service;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.mcgill.ecse321.gallery.dao.ArtRepository;
import ca.mcgill.ecse321.gallery.dao.GalleryRepository;
import ca.mcgill.ecse321.gallery.model.Account;
import ca.mcgill.ecse321.gallery.model.Address;
import ca.mcgill.ecse321.gallery.model.Art;
import ca.mcgill.ecse321.gallery.model.Gallery;
import ca.mcgill.ecse321.gallery.model.Listing;
import ca.mcgill.ecse321.gallery.model.Profile;
import ca.mcgill.ecse321.gallery.utils.Utils;

@Service
public class ArtService {

	@Autowired
	ArtRepository artRepository;

	@Transactional
	public Optional<Art> createArt(String title, String description, double height, double width, double depth,
			String image, Date creationDate, Profile publisher, String type, String author) {
		boolean isArtValid = true;
		Art validArt = null;

		// is title unique?
		List<Art> allArt = getAllArt();

		for (Art art : allArt) {
			if (art.getName().equals(title) && art.getOwner().equals(publisher)) {
				isArtValid = false;
			}
		}
		
		// is height negative?
		if (height < 0) {
			isArtValid = false;
		}

		// is width negative?
		if (width < 0) {
			isArtValid = false;
		}

		// is depth negative?
		if (depth < 0) {
			isArtValid = false;
		}
		// is title empty or null?
		if (title == null || title.trim().length() == 0) {
			isArtValid = false;
		}
		
		// if Art is valid
		if (isArtValid) {
			// create Art
			validArt = new Art();
			validArt.setAuthor(author);
			validArt.setDate(creationDate);
			validArt.setDepth(depth);
			validArt.setDescription(description);
			validArt.setHeight(height);
			validArt.setImage(image);
			validArt.setName(title);
			validArt.setType(type);
			validArt.setWidth(width);
			validArt.setOwner(publisher);

			validArt = artRepository.save(validArt);
		}
		return Optional.ofNullable(validArt);
	}

	@Transactional
	public Optional<Art> getArt(Long id) {
		return artRepository.findById(id);
	}
	
	@Transactional
	public List<Art> getAllArt() {
		return Utils.toList(artRepository.findAll());
	}
	
	@Transactional
	public List<Art> findArtByPublisher(Profile publisher) {

		List<Art> allArt = getAllArt();
		List<Art> byPublisher = new ArrayList<Art>();

		for (Art art : allArt) {
			if (art.getOwner().equals(publisher)) {
				byPublisher.add(art);
			}
		}
		return byPublisher;
	}
	
	@Transactional
	public List<Art> findArtByAuthor(String author) {

		List<Art> allArt = getAllArt();
		List<Art> byAuthor = new ArrayList<Art>();

		for (Art art : allArt) {
			if (art.getAuthor().equals(author)) {
				byAuthor.add(art);
			}
		}
		return byAuthor;
	}
	
	@Transactional
	public List<Art> findArtByType(String type) {

		List<Art> allArt = getAllArt();
		List<Art> byType = new ArrayList<Art>();

		for (Art art : allArt) {
			if (art.getType().equals(type)) {
				byType.add(art);
			}
		}
		return byType;
	}
	
	@Transactional
	public List<Art> findArtByDate(Date creationDate) {

		List<Art> allArt = getAllArt();
		List<Art> byDate = new ArrayList<Art>();

		for (Art art : allArt) {
			if (art.getDate().equals(creationDate)) {
				byDate.add(art);
			}
		}
		return byDate;
	}
	
	@Transactional
	public Optional<Art> getArtById(Long id) {
		return artRepository.findById(id);
	}
}
