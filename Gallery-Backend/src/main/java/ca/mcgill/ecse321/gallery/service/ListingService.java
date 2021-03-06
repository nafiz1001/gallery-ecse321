package ca.mcgill.ecse321.gallery.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import ca.mcgill.ecse321.gallery.dao.ListingRepository;
import ca.mcgill.ecse321.gallery.dto.ListingDto;
import ca.mcgill.ecse321.gallery.model.Account;
import ca.mcgill.ecse321.gallery.model.Art;
import ca.mcgill.ecse321.gallery.model.Listing;
import ca.mcgill.ecse321.gallery.model.Profile;
import ca.mcgill.ecse321.gallery.utils.Utils;

/**
 * 
 * @author antonianistor
 *
 */
@Service
public class ListingService {

	@Autowired
	ListingRepository listingRepository;

	/**
	 * This method allows backend to create a listing using the following parameters
	 * 
	 * @param art
	 * @param price
	 * @param quantity
	 * @param tags
	 * @param canPickUp
	 * @param canDeliver
	 * @param datePublished
	 * @return Listing
	 */
	@Transactional
	public Optional<Listing> createListing(Art art, int price, int quantity, String tags, boolean canPickUp,
			boolean canDeliver, Date datePublished) {

		if (price < 0)
			throw new IllegalArgumentException("You cannot enter a negative price");

		if (quantity <= 0)
			throw new IllegalArgumentException("Quantity needs to be 1 or more");

		if (Utils.areTagsValid(tags) == false)
			throw new IllegalArgumentException(
					"Tags must only contain letters and must be separated by a comma and space");

		if (canPickUp == false && canDeliver == false)
			throw new IllegalArgumentException("At least one shipping method needs to be selected");

		if (art.getListing() != null)
			throw new IllegalArgumentException("Art already associated to a listing");

		Listing listing = new Listing();
		listing.setArt(art);
		art.setListing(listing);
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

	/**
	 * This method allows to modify a listing using the following parameters
	 * 
	 * @param art
	 * @param price
	 * @param quantity
	 * @param tags
	 * @param canPickUp
	 * @param canDeliver
	 * @param datePublished
	 * @return
	 */
	@Transactional
	public Optional<Listing> editListing(Art art, int price, int quantity, String tags, boolean canPickUp,
			boolean canDeliver, Date datePublished) {

		// Does listing to edit include an art that is listed?
		List<Listing> allListings = getAllListings();
		for (Listing listing : allListings) {
			if (listing.getArt().equals(art)) {

				if (price < 0)
					throw new IllegalArgumentException("You cannot enter a negative price");

				if (quantity <= 0)
					throw new IllegalArgumentException("Quantity needs to be 1 or more");

				if (Utils.areTagsValid(tags) == false)
					throw new IllegalArgumentException(
							"Tags must only contain letters and must be separated by a comma and space");

				if (canPickUp == false && canDeliver == false)
					throw new IllegalArgumentException("At least one shipping method needs to be selected");

				if (art.getListing() == null)
					throw new IllegalArgumentException("No listing found");

				listing.setArt(art);
				art.setListing(listing);
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
		}
		return null;
	}

	/**
	 * This method allows backend to request a list of all the listings that exist
	 * in the repository
	 * 
	 * @return
	 */
	@Transactional
	public List<Listing> getAllListings() {
		return Utils.toList(listingRepository.findAll());
	}

	/**
	 * This method allows the backend to find a listing by its ID
	 * 
	 * @param l
	 * @return
	 */
	@Transactional
	public Optional<Listing> findListingById(long l) {
		return listingRepository.findById(l);
	}

	/**
	 * This method allows the backend to find all the listings created by a profile
	 * 
	 * @param publisher
	 * @return list of listings
	 */
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

	/**
	 * This method allows the backend to find listings according to the price range
	 * inputed
	 * 
	 * @param minPrice
	 * @param maxPrice
	 * @return
	 */
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