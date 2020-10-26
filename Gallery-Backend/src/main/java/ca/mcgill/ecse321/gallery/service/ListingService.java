package ca.mcgill.ecse321.gallery.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.gallery.dao.ListingRepository;
import ca.mcgill.ecse321.gallery.model.Art;
import ca.mcgill.ecse321.gallery.model.Listing;
import ca.mcgill.ecse321.gallery.model.Profile;
import ca.mcgill.ecse321.gallery.utils.Utils;

public class ListingService {

	@Autowired
	ListingRepository listingRepository;

	@Transactional
	public Optional<Listing> createListing(Art art, int price, int quantity, String tags, boolean canPickUp,
			boolean canDeliver, Date datePublished) {

		Listing listing = new Listing();
		listing.setArt(art);
		listing.setPublisher(art.getOwner());
		listing.setPrice(price);
		listing.setQuantity(quantity);
		listing.setCanDeliver(canDeliver);
		listing.setCanPickUp(canPickUp);
		listing.setTags(tags);
		listing.setDatePublished(datePublished);

		listingRepository.save(listing);

		return Optional.ofNullable(listing);
	}

	@Transactional
	public List<Listing> getAllListings() {
		return (List<Listing>) listingRepository.findAll();
	}

	@Transactional
	public Optional<Listing> findListingById(String id) {
		return listingRepository.findById(id);
	}

	@Transactional
	public List<Listing> findListingsByPublisher(Profile publisher) {

		List<Listing> allListings = getAllListings();
		List<Listing> byPublisher = new ArrayList();

		for (Listing l : allListings) {
			if (l.getPublisher().equals(publisher)) {
				byPublisher.add(l);
			}
		}

		return byPublisher;
	}

	@Transactional
	public List<Listing> findListingByPriceRange(int minPrice, int maxPrice) {

		if (minPrice < 0 || maxPrice < 0)
			throw new IllegalArgumentException("You cannot enter a negative value");
		if (minPrice == maxPrice)
			throw new IllegalArgumentException("Range too small");
		if (minPrice > maxPrice)
			throw new IllegalArgumentException("Minimum price cannot be larger than Maximum price");

		List<Listing> allListings = getAllListings();
		List<Listing> byPrice = new ArrayList();

		for (Listing l : allListings) {
			if (l.getPrice() >= minPrice && l.getPrice() <= maxPrice) {
				byPrice.add(l);
			}
		}

		return byPrice;

	}

}