package ca.mcgill.ecse321.gallery.service;

import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;

import ca.mcgill.ecse321.gallery.dao.IdentityRepository;
import ca.mcgill.ecse321.gallery.model.Identity;
import ca.mcgill.ecse321.gallery.utils.Utils;

public class IdentityService {
	@Autowired
	IdentityRepository identityRepository;
	
	public Optional<Identity> createIdentity(String email){
		boolean emailValid = Utils.isEmailValid(email);
		if (emailValid == false) throw new IllegalArgumentException("Email entered is not valid");
		
		else {
			Identity identity = new Identity();
			identity.setEmail(email);
			
			identityRepository.save(identity);
			
			return Optional.ofNullable(identity);
		}
	
		
	}
	
	public Optional<Identity> getIdentity(String email) {
		
		boolean emailValid = Utils.isEmailValid(email);
	
		if (emailValid == false) throw new IllegalArgumentException("Email entered is not valid");
		else return Optional.ofNullable(identityRepository.findIdentityByEmail(email));
	}
}
