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

/**
 * 
 * @author ericpelletier
 * Test the methods inside of RevenuService.
 */

@ExtendWith(MockitoExtension.class)
public class RevenuServiceTests {
	@Mock
	private RevenuRepository revenuRepository;


	@InjectMocks
	RevenuService revenuService;
	
	private HashSet<Revenu> savedRevenu= new HashSet<>();
	
	/**
	 * Sets up the test environment.
	 */
	@BeforeEach
	public void setupMockup() {

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
		lenient().when(revenuRepository.findById(any())).thenAnswer((InvocationOnMock invocation) -> {
			long id = ((long) invocation.getArgument(0));
			for (Revenu revenu : savedRevenu) {
				if (revenu.getId() == id)
					return Optional.ofNullable(revenu);
			}
			return Optional.empty();
		});
		
		// simulate finding all accounts
		lenient().when(revenuRepository.findAll()).thenAnswer((InvocationOnMock invocation) -> {
			return savedRevenu;
		});
		
	}
	
	/**
	 * Test a successful creation of revenu.
	 */
	@Test 
	void testSuccesfullRevenu() {
		
		int commission = 1;
		int listingprice= 2;
		Account account = new Account();
		Listing listing = new Listing();
		Optional<Revenu> revenu = revenuService.createRevenu(commission, listingprice, account, listing);
		assertTrue(revenu.isPresent());


	}
	
	/**
	 * Test a listingPrice violation.
	 */
	@Test
	void testListingPriceViolation() {
		assertEquals(0, revenuService.getAllRevenu().size());
		boolean exceptionCaught = false;
		int commission = 1;
		int listingprice= -2;
		Account account = new Account();
		Listing listing = new Listing();
		try {
		Optional<Revenu> revenu = revenuService.createRevenu(commission, listingprice, account, listing);
		}
		catch (IllegalArgumentException e) {
			exceptionCaught = true;
		}
		assertTrue(exceptionCaught);
		assertEquals(0, revenuService.getAllRevenu().size());
	}
	
	/**
	 * Test a commission violation.
	 */
	@Test
	void testCommissionViolation() {
		assertEquals(0, revenuService.getAllRevenu().size());
		boolean exceptionCaught = false;
		int commission = -1;
		int listingprice= 2;
		Account account = new Account();
		Listing listing = new Listing();
		try {
		Optional<Revenu> revenu = revenuService.createRevenu(commission, listingprice, account, listing);
		}
		catch (IllegalArgumentException e) {
			exceptionCaught = true;
		}
		assertTrue(exceptionCaught);
		assertEquals(0, revenuService.getAllRevenu().size());
	}
	
	/**
	 * Test the retrieval of a revenu by id.
	 */
	@Test
	void testGetRevenu() {
		assertTrue(revenuService.getRevenu((long)0).isEmpty());
		
		int commission = 1;
		int listingprice= 2;
		Account account = new Account();
		Listing listing = new Listing();
		Optional<Revenu> revenu = revenuService.createRevenu(commission, listingprice, account, listing);
		
		assertTrue(revenuService.getRevenu((long)0).isPresent());
		
	}

	/**
	 * Test the retrieval of all revenus inside the database.
	 */
	@Test
	void testGetAllRevenu() {
		assertEquals(0, revenuService.getAllRevenu().size());
		
		int commission = 1;
		int listingprice= 2;
		Account account = new Account();
		Listing listing = new Listing();
		Optional<Revenu> revenu = revenuService.createRevenu(commission, listingprice, account, listing);
		
		assertEquals(1, revenuService.getAllRevenu().size());
		
		Optional<Revenu> revenu1 =  revenuService.createRevenu(commission, listingprice, account, listing);
		
		assertEquals(2, revenuService.getAllRevenu().size());
	}
	

}
