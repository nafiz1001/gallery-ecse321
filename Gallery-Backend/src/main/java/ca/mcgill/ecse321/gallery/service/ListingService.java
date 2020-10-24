package ca.mcgill.ecse321.gallery.service;

import org.springframework.beans.factory.annotation.Autowired;

import ca.mcgill.ecse321.gallery.dao.ListingRepository;

public class ListingService {
	
	@Autowired
	ListingRepository listingRepository;

}
