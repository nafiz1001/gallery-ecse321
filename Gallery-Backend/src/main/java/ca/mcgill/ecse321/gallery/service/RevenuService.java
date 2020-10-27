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

@Service
public class RevenuService {
	@Autowired
	RevenuRepository revenuRepository;
	
	@Autowired
    ListingRepository listingRepository;
	
	@Autowired
	AccountRepository accountRepository;
	
	@Transactional
	public Optional<Revenu> createRevenu(int comission, int listingPrice, Account account, Listing listing) {
		//boolean isRevenuValid = true;
		//Revenu validRevenu = null;

		//What are some limitations?
		
		Revenu revenu = new Revenu();
		
		revenu.setComission(comission);
		revenu.setListingPrice(listingPrice);
		revenu.setAccount(account);
		revenu.setListing(listing);
		
		revenuRepository.save(revenu);
		
	
		
		return Optional.ofNullable(revenu);

		
		
	}
	
	@Transactional
	public Optional<Revenu> getRevenu(String id) {
		return revenuRepository.findById(id);
	}
	
	@Transactional
	public List<Revenu> getAllRevenu(String id) {
		List<Revenu> revenu = (List<Revenu>) revenuRepository.findAll();
		return revenu;
	}

}
