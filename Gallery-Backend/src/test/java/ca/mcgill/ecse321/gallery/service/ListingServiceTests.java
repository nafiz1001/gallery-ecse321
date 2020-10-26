package ca.mcgill.ecse321.gallery.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import ca.mcgill.ecse321.gallery.dao.ArtRepository;
import ca.mcgill.ecse321.gallery.dao.ListingRepository;
import ca.mcgill.ecse321.gallery.dao.PaymentRepository;

@ExtendWith(MockitoExtension.class)
public class ListingServiceTests {
	
	@Mock
	private ListingRepository listingReposiory;
	
	@Mock
	private ArtRepository artRepository;
	
	@InjectMocks
	ListingService listingService;
	
	@BeforeEach
	public void setupMockup() {
		
	}
	

}
