package ca.mcgill.ecse321.gallery.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.lenient;

import java.sql.Time;
import java.util.HashSet;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import ca.mcgill.ecse321.gallery.dao.RevenuRepository;
import ca.mcgill.ecse321.gallery.dao.AccountRepository;
import ca.mcgill.ecse321.gallery.dao.AddressRepository;
import ca.mcgill.ecse321.gallery.dao.GalleryRepository;
import ca.mcgill.ecse321.gallery.dao.ListingRepository;


import ca.mcgill.ecse321.gallery.model.Revenu;
import ca.mcgill.ecse321.gallery.model.Account;
import ca.mcgill.ecse321.gallery.model.Gallery;
import ca.mcgill.ecse321.gallery.model.Listing;

@ExtendWith(MockitoExtension.class)
public class RevenuServiceTests {
	@Mock
	private RevenuRepository revenuRepository;


	@InjectMocks
	RevenuService revenuService;
	
	private String id = "0";
	private HashSet<Revenu> savedRevenu= new HashSet<>();
	
	@BeforeEach
	public void setupMockup() {
		// address id generator mock
		id = "0";

		// database mock
		savedRevenu.clear();
		
		// lambda for simulating saving Revenu
				Answer<Revenu> saveRevenuAndReturn = (InvocationOnMock invocation) -> {
					Revenu revenu = (Revenu) invocation.getArgument(0);
					savedRevenu.add(revenu);
					return revenu;
				};
				
				// simulate saving revenu
				lenient().when(revenuRepository.save(any(Revenu.class))).thenAnswer(saveRevenuAndReturn);
				
				// simulate finding gallery by Id 
				lenient().when(revenuRepository.findById(anyString())).thenAnswer((InvocationOnMock invocation) -> {
					String id = ((String) invocation.getArgument(0));
					for (Revenu revenu : savedRevenu) {
						if (revenu.getId().equals(id))
							return Optional.ofNullable(revenu);
					}
					return Optional.empty();
				});
		
	}
	
	@Test 
	void testSuccesfullRevenu() {
		
		int comission = 1;
		int listingprice= 2;
		Account account = new Account();
		Listing listing = new Listing();
		Optional<Revenu> revenu = revenuService.createRevenu(comission, listingprice, account, listing);
		assertTrue(revenu.isPresent());


	}

	

}
