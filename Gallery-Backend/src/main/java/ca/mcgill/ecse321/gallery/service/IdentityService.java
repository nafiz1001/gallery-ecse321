package ca.mcgill.ecse321.gallery.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import ca.mcgill.ecse321.gallery.dao.IdentityRepository;
import ca.mcgill.ecse321.gallery.model.Identity;

public class IdentityService {
	@Autowired
	IdentityRepository identityRepository;
	
	public Optional<Identity> getIdentity(String email) {
		return identityRepository.findById(email);
	}
}
