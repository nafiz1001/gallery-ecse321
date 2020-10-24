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
import ca.mcgill.ecse321.gallery.dao.ListingRepository;
import ca.mcgill.ecse321.gallery.dao.PaymentRepository;
import ca.mcgill.ecse321.gallery.dao.ProfileRepository;
import ca.mcgill.ecse321.gallery.model.Account;
import ca.mcgill.ecse321.gallery.model.Address;
import ca.mcgill.ecse321.gallery.model.DeliveryType;
import ca.mcgill.ecse321.gallery.model.Identity;
import ca.mcgill.ecse321.gallery.model.Listing;
import ca.mcgill.ecse321.gallery.model.Payment;
import ca.mcgill.ecse321.gallery.model.PaymentType;
import ca.mcgill.ecse321.gallery.model.Profile;

@Service
public class ProfileService {
	@Autowired
	ProfileRepository profileRepository;
	
	@Autowired
	ListingRepository listingRepository;
	
	@Autowired
	AccountRepository accountRepository;
	
	@Transactional
	public Optional<Profile> createProfile(String bio, String picture, String fullname, String id ) {
		if (bio == null || bio.trim().length() == 0) {
			throw new IllegalArgumentException("Bio cannot be empty!");
		}
		if (picture == null || picture.trim().length() == 0) {
				throw new IllegalArgumentException("Picture cannot be empty!");
		}
		if (fullname == null || fullname.trim().length() == 0) {
			throw new IllegalArgumentException("Full Name cannot be empty!");
		}
		if (id == null || id.trim().length() == 0) {
			throw new IllegalArgumentException("ID cannot be empty!");
		}
		
		Profile profile = new Profile();
		profile.setBio(bio);
		profile.setPicture(picture);
		profile.setFullname(fullname);
		profile.setId(id);
	/*	profile.setAccount(account);
		profile. */
		
		return Optional.ofNullable(profile);

	}
	
	@Transactional
	public Optional<Profile> getProfile(String id) {
		return profileRepository.findById(id);
	}

	

}
