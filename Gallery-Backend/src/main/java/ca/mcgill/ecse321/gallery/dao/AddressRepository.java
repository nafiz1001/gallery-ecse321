package ca.mcgill.ecse321.gallery.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.gallery.model.Address;
import ca.mcgill.ecse321.gallery.model.Identity;

public interface AddressRepository extends CrudRepository<Address, String>{
	Address findAddressById(String id);
	
	void deleteAll();
}
