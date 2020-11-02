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
import ca.mcgill.ecse321.gallery.model.Account;
import ca.mcgill.ecse321.gallery.model.Address;
import ca.mcgill.ecse321.gallery.model.Art;
import ca.mcgill.ecse321.gallery.model.DeliveryType;
import ca.mcgill.ecse321.gallery.model.Identity;
import ca.mcgill.ecse321.gallery.model.Listing;
import ca.mcgill.ecse321.gallery.model.Payment;
import ca.mcgill.ecse321.gallery.model.PaymentType;
import ca.mcgill.ecse321.gallery.model.Profile;
import ca.mcgill.ecse321.gallery.utils.Utils;

/**
 * 
 * @author HalukCalin
 * This service allows the create, editing and retrieval of profiles. 
 */

@Service
public class ProfileService {
	@Autowired
	ProfileRepository profileRepository;

	/**
	 * Creates a Profile.
	 * @param bio The bio associated to the profile
	 * @param picture A display picture of the creator
	 * @param listings The listings associated to this profile
	 * @param account The account associated to this profile
	 * @param fullname The full name of the creator
	 * @param arts The arts associated to this profile
	 * @return The profile created
	 */
	@Transactional
	public Optional<Profile> createProfile(String bio, String picture, Iterable<Listing> listings, Account account,
			String fullname, Iterable<Art> arts) {
		if (bio == null || bio.trim().length() == 0) {
			throw new IllegalArgumentException("Bio cannot be empty!");
		}
		if (picture == null || picture.trim().length() == 0) {
			throw new IllegalArgumentException("Picture cannot be empty!");
		}
		if (fullname == null || fullname.trim().length() == 0) {
			throw new IllegalArgumentException("Full Name cannot be empty!");
		}
		
		
		if (profileRepository.findById(account.getUsername() + ":" + fullname).isPresent())
			throw new IllegalArgumentException("Profile with fullname " + fullname + " already created");
		
		Profile profile = new Profile();

		profile.setBio(bio);
		profile.setPicture(picture);
		profile.setListings(Utils.toSet(listings));
		profile.setAccount(account);
		profile.setFullname(fullname);
		profile.setArts(Utils.toSet(arts));
		profile.setId(account.getUsername() + ":" + fullname);

		profileRepository.save(profile);

		return Optional.ofNullable(profile);

	}

	/**
	 * Edits a Profile.
	 * @param bio The edited bio
	 * @param picture The edited picture
	 * @param listings The edited listings
	 * @param account The edited account
	 * @param fullname The edited full name
	 * @param arts The edited arts
	 * @return The new profile created
	 */
	@Transactional
	public Optional<Profile> editProfile(String bio, String picture, Iterable<Listing> listings, Account account,
			String fullname, Iterable<Art> arts) {
		
		if (bio == null || bio.trim().length() == 0) {
			throw new IllegalArgumentException("Bio cannot be empty!");
		}
		if (picture == null || picture.trim().length() == 0) {
			throw new IllegalArgumentException("Picture cannot be empty!");
		}
		if (fullname == null || fullname.trim().length() == 0) {
			throw new IllegalArgumentException("Full Name cannot be empty!");
		} // add something for account?
		
		Optional<Profile> profile = profileRepository.findById(account.getUsername() + ":" + fullname);
		
		if (profile.isPresent()) {
			profile.get().setBio(bio);
			profile.get().setPicture(picture);
			profile.get().setListings(Utils.toSet(listings));
			profile.get().setAccount(account);
			//profile.setFullname(fullname);
			profile.get().setArts(Utils.toSet(arts));
			profile.get().setId(account.getUsername() + ":" + fullname);
	
			profileRepository.save(profile.get());
			return profile;
		} else {
			throw new IllegalArgumentException("Profile with fullname " + fullname + " does not exist");
		}
	}

	/**
	 * Retrieves a profile with a specific id
	 * @param id The id of the profile to find
	 * @return A Profile with the given id
	 */
	@Transactional
	public Optional<Profile> getProfile(String id) {
		return profileRepository.findById(id); // find profile by id not working why?
	}

	/**
	 * Retrives all profiles
	 * @return A list of all profiles inside of the database 
	 */
	@Transactional
	public List<Profile> getAllProfiles() {
		return Utils.toList(profileRepository.findAll());

	}

}