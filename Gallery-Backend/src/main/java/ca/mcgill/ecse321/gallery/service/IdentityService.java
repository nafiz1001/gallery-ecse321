package ca.mcgill.ecse321.gallery.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.gallery.dao.IdentityRepository;
import ca.mcgill.ecse321.gallery.model.Identity;
import ca.mcgill.ecse321.gallery.utils.Utils;

@Service
public class IdentityService {
	@Autowired
	IdentityRepository identityRepository;

	@Transactional
	public Optional<Identity> createIdentity(String email) {
		boolean emailValid = Utils.isEmailValid(email, true);
		if (emailValid == false)
			throw new IllegalArgumentException("Email entered is not valid");

		if(isEmailAlreadyUsed(email)==true) throw new IllegalArgumentException("Email entered is already in use");
		
		Identity identity = new Identity();
		identity.setEmail(email);

		identityRepository.save(identity);

		return Optional.ofNullable(identity);
		}

	

	@Transactional
	public Optional<Identity> findIdentityByEmail(String email) {

		boolean emailValid = Utils.isEmailValid(email, true);

		if (emailValid == false)
			throw new IllegalArgumentException("Email entered is not valid");
		else { 
			return Optional.ofNullable(identityRepository.findIdentityByEmail(email));
			
		}
			
	}

	@Transactional
	public List<Identity> getAllIdentities() {
		return Utils.toList(identityRepository.findAll());
	}
	
	
	public boolean isEmailAlreadyUsed(String email) {
		
		boolean emailUsed = false;
		List<Identity> allId = getAllIdentities();
		
		for (Identity id : allId) {
			if(id.getEmail().equalsIgnoreCase(email))
				emailUsed =true;
		}
		return emailUsed;
		
	}

}
