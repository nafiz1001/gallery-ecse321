package ca.mcgill.ecse321.gallery.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.mcgill.ecse321.gallery.dao.AccountRepository;
import ca.mcgill.ecse321.gallery.dao.ArtRepository;
import ca.mcgill.ecse321.gallery.dao.ListingRepository;
import ca.mcgill.ecse321.gallery.dao.PaymentRepository;
import ca.mcgill.ecse321.gallery.dao.ProfileRepository;
import ca.mcgill.ecse321.gallery.dao.RevenuRepository;
import ca.mcgill.ecse321.gallery.model.Account;
import ca.mcgill.ecse321.gallery.model.Address;
import ca.mcgill.ecse321.gallery.model.Art;
import ca.mcgill.ecse321.gallery.model.DeliveryType;
import ca.mcgill.ecse321.gallery.model.Identity;
import ca.mcgill.ecse321.gallery.model.Listing;
import ca.mcgill.ecse321.gallery.model.Payment;
import ca.mcgill.ecse321.gallery.model.PaymentType;
import ca.mcgill.ecse321.gallery.model.Profile;
import ca.mcgill.ecse321.gallery.model.Revenu;
import ca.mcgill.ecse321.gallery.utils.Utils;

/**
 * 
 * @author HalukCalin
 * This service allows the creation and retrieval of revenus. 
 */

@Service
public class RevenuService {
	@Autowired
	RevenuRepository revenuRepository;
	
	/**
	 * Creates a Revenu.
	 * @param commission The commission associated with this revenu
	 * @param listingPrice The listing price associated with this revenu
	 * @param account The account associated with this revenu
	 * @param listing The listing associated with this revenu
	 * @return The revenu created
	 */
	@Transactional
	public Optional<Revenu> createRevenu(int commission, int listingPrice, Account account, Listing listing) {
		boolean isRevenuValid = true;
		
		// is listingPrice < 0?
		if (listingPrice < 0) {
			isRevenuValid = false;
			throw new IllegalArgumentException("Listing Price cannot be negative");
		} 
		
		// is commission < 0?
		if (commission < 0) {
			isRevenuValid = false;
			throw new IllegalArgumentException("Commission cannot be negative");
		}
		
		Revenu revenu = new Revenu();
		revenu.setComission(commission);
		revenu.setListingPrice(listingPrice);
		revenu.setAccount(account);
		revenu.setListing(listing);
		
		revenu = revenuRepository.save(revenu);
		
		return Optional.ofNullable(revenu);

	}
	
	/**
	 * Retrieves a Revenu with a specific id
	 * @param id The id of the revenu to find
	 * @return A revenu with the given id
	 */
	@Transactional
	public Optional<Revenu> getRevenu(long id) {
		return revenuRepository.findById(id);
	}
	
	/**
	 * Retrieves all Revenus 
	 * @return A list of all revenus in the database
	 */
	@Transactional
	public List<Revenu> getAllRevenu() {
		return Utils.toList(revenuRepository.findAll());
	}

}
