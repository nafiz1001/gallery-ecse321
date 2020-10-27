package ca.mcgill.ecse321.gallery.service;

import java.sql.Date;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.lenient;

import ca.mcgill.ecse321.gallery.dao.ArtRepository;
import ca.mcgill.ecse321.gallery.dao.ListingRepository;
import ca.mcgill.ecse321.gallery.dao.PaymentRepository;
import ca.mcgill.ecse321.gallery.model.Art;
import ca.mcgill.ecse321.gallery.model.Listing;

import ca.mcgill.ecse321.gallery.service.ListingService;

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
		
		//sim save art
		lenient().when(artRepository.save(any(Art.class))).thenAnswer(saveAndReturn);
		
		//sim find all listings
		lenient().when(listingRepository.findAll()).thenAnswer((InvocationOnMock invocation)-> {
			return savedListings;
		});
		
		//sim find listing by id
		lenient().when(listingRepository.findById(anyString())).thenAnswer((InvocationOnMock invocation)-> {
			
			String id = ((String)invocation.getArgument(0));
			for (Listing l: savedListings) {
				if (l.getId().equals(id))
					return Optional.ofNullable(l);
							
			}
			return Optional.empty();
		});
	}
	
	@Test
	void testSuccessfulListingCreation() {
		assertEquals(0, listingService.getAllListings().size());
		
		Art art = new Art();
		Optional<Listing> listing = listingService.createListing(art, 120, 2, "tagA, tagB", true, false, new Date(21102020));
		
		assertTrue(listing.isPresent());
	}
	
	@Test
	void testInvalidPriceAtCreation() {
		boolean exceptionCaught = false;
		assertEquals(0, listingService.getAllListings().size());
		try {
		Art art = new Art();
		Optional<Listing> listing = listingService.createListing(art, -120, 2, "tagA, tagB", true, false, new Date(21102020));
		}
		catch (IllegalArgumentException e){
			exceptionCaught=true;
		}
		assertTrue(exceptionCaught);
		assertEquals(0, listingService.getAllListings().size());
		
	}
	
	@Test
	void testInvalidQuantityAtCreation() {
		boolean exceptionCaught = false;
		assertEquals(0, listingService.getAllListings().size());
		
		try {
			Art art = new Art();
			Optional<Listing> listing = listingService.createListing(art, 120, -2, "tagA, tagB", true, false, new Date(21102020));
		}
		catch (IllegalArgumentException e){
			exceptionCaught=true;
		}
		assertTrue(exceptionCaught);
		
		try {
			Art art = new Art();
			Optional<Listing> listing = listingService.createListing(art, 120, 0, "tagA, tagB", true, false, new Date(21102020));
			}
			catch (IllegalArgumentException e){
				exceptionCaught=true;
			}
			assertTrue(exceptionCaught);
		
		exceptionCaught= false;
		
		assertEquals(0, listingService.getAllListings().size());
		
	}
	
	@Test
	void testInvalidTagsAtCreation() {
		boolean exceptionCaught = false;
		assertEquals(0, listingService.getAllListings().size());
		
		try {
			Art art = new Art();
			Optional<Listing> listing = listingService.createListing(art, 120, 2, "tagA. tagB", true, false, new Date(21102020));
		}
		catch (IllegalArgumentException e){
			exceptionCaught=true;
		}
		assertTrue(exceptionCaught);
		exceptionCaught =false;
		
		try {
			Art art = new Art();
			Optional<Listing> listing = listingService.createListing(art, 120, 2, "tag1, tag2", true, false, new Date(21102020));
		}
		catch (IllegalArgumentException e){
			exceptionCaught=true;
		}
		assertTrue(exceptionCaught);
		exceptionCaught =false;
		
		try {
			Art art = new Art();
			Optional<Listing> listing = listingService.createListing(art, 120, 2, "tag!, tagB", true, false, new Date(21102020));
		}
		catch (IllegalArgumentException e){
			exceptionCaught=true;
		}
		assertTrue(exceptionCaught);
		
		assertEquals(0, listingService.getAllListings().size());
	}
	
	@Test
	void testDelivOrPickupAtCreation() {
		boolean exceptionCaught = false;
		assertEquals(0, listingService.getAllListings().size());
		
		try {
			Art art = new Art();
			Optional<Listing> listing = listingService.createListing(art, 120, 2, "tagA, tagB", false, false, new Date(21102020));
		}
		catch (IllegalArgumentException e){
			exceptionCaught=true;
		}
		assertTrue(exceptionCaught);
		assertEquals(0, listingService.getAllListings().size());
	}
	
	
	

}
