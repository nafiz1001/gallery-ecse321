package ca.mcgill.ecse321.gallery.service;

import java.util.HashSet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.lenient;

import ca.mcgill.ecse321.gallery.dao.ArtRepository;
import ca.mcgill.ecse321.gallery.dao.ListingRepository;
import ca.mcgill.ecse321.gallery.dao.PaymentRepository;
import ca.mcgill.ecse321.gallery.model.Art;
import ca.mcgill.ecse321.gallery.model.Listing;

@ExtendWith(MockitoExtension.class)
public class ListingServiceTests {
	
	@Mock
	private ListingRepository listingRepository;
	
	@Mock
	private ArtRepository artRepository;
	
	@InjectMocks
	ListingService listingService;
	
	
	private String id = "0";
	private HashSet<Listing> savedListings= new HashSet<>();
	private HashSet<Art> savedArts= new HashSet<>();
	
	
	@BeforeEach
	public void setupMockup() {
		
		id = "0";
		savedListings.clear();
		savedArts.clear();
		
		//lambda for simulating saving listing
		Answer<Listing> updateListingNumAndReturn = (InvocationOnMock invocation) -> {
			Listing listing = (Listing)invocation.getArgument(0);
			listing.setId(id);
			savedListings.add(listing);
			int listingIdAsNum = Integer.parseInt(id);
			listingIdAsNum++;
			id = Integer.toString(listingIdAsNum);
			return listing;
		};
		
		//lambda for simulatign saving art
		Answer<Art> saveAndReturn = (InvocationOnMock invocation) -> {
			Art art = (Art) invocation.getArgument(0);
			savedArts.add(art);
			return art;
		};
		
		//sim save listing
		lenient().when(listingRepository.save(any(Listing.class))).thenAnswer(updateListingNumAndReturn);
		
		
		
		
		
	}
	

}
