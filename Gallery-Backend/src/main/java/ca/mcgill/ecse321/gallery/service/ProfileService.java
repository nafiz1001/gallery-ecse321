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

@Service
public class ProfileService {
	@Autowired
	ProfileRepository profileRepository;
	
	@Autowired
	ListingRepository listingRepository;
	
	@Autowired
	AccountRepository accountRepository;
	
	@Autowired
	ArtRepository artRepository;
	
	@Transactional
	public Optional<Profile> createProfile(String bio, String picture, Iterable<Listing> listings, Account account, String fullname, Iterable<Art> arts ) {
		if (bio == null || bio.trim().length() == 0) {
			throw new IllegalArgumentException("Bio cannot be empty!");
		}
		if (picture == null || picture.trim().length() == 0) {
				throw new IllegalArgumentException("Picture cannot be empty!");
		}
		if (fullname == null || fullname.trim().length() == 0) {
			throw new IllegalArgumentException("Full Name cannot be empty!");
		} 																				//add something for account?
		
		Profile profile = new Profile();
		
		profile.setBio(bio);
		profile.setPicture(picture);
		profile.setListings(Utils.toSet(listings));
		profile.setAccount(account);
		profile.setFullname(fullname);
		profile.setArts(Utils.toSet(arts));
		
		profileRepository.save(profile);
		
	
		
		return Optional.ofNullable(profile);

	}
	
	@Transactional
	public Optional<Profile> getProfile(String id) {
		return profileRepository.findById(id);
	}

	@Transactional 
	public List<Profile> getAllProfiles() {
		List<Profile> profiles = (List<Profile>) profileRepository.findAll();
		return profiles;
	}
	
}
	
/**	@Transactional
	public List<Profile> getProfileByFullname(String fullname) {
		List<Profile> profile = new ArrayList<Profile>();
		if (profileRepository.findProfileByFullname != null) {
			profile.add(profileRepository.findProfileByFullname("username"));
		}
		return profile;
	}

} */
