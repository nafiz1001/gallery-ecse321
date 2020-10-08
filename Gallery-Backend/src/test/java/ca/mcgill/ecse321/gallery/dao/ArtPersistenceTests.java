package ca.mcgill.ecse321.gallery.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.HashSet;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ca.mcgill.ecse321.gallery.model.Address;
import ca.mcgill.ecse321.gallery.model.Art;
import ca.mcgill.ecse321.gallery.model.DeliveryType;
import ca.mcgill.ecse321.gallery.model.Listing;
import ca.mcgill.ecse321.gallery.model.Payment;
import ca.mcgill.ecse321.gallery.model.PaymentType;
import ca.mcgill.ecse321.gallery.model.Profile;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ArtPersistenceTests {
	
	@Autowired
	private ArtRepository artRepository;
	
	@BeforeEach
	public void clearDatabse() {
		artRepository.deleteAll();
	}
	
	@AfterEach
	public void clearDatabase() {
		artRepository.deleteAll();
	}
	
	@Test
	public void testCreateAndLoadAddress() {
		Profile profile = new Profile();
		profile.setAccount(account);
		profile.setArts(artss);
		profile.setBio("My name is Bob");
		profile.setFullname("Bob the Builder");
		profile.setId("#2609");
		profile.setListings(listingss);
		profile.setPicture("0");
		
		Listing listing = new Listing();
		listing.setArt(art);
		listing.setCanDeliver(true);
		listing.setCanPickUp(true);
		listing.setDatePublished(null);
		listing.setId("abcd");
		listing.setPrice(100);
		listing.setPublisher(publisher);
		listing.setQuantity(1);
		listing.setTags("Abstract");
		
		Art art1 = new Art();
		art1.setAuthor("a");
		art1.setDate(null);
		art1.setDepth("b");
		art1.setDescription("c");
		art1.setHeight(10);
		art1.setId("1234");
		art1.setImage("d");
		art1.setListing(listing);
		art1.setName("e");
		art1.setOwner(profile);
		art1.setType("f");
		art1.setWidth(10);
		
		artRepository.save(art1);
		
		Art art2 = artRepository.findArtById("1234");
		
		assertNotNull(art2);
		assertEquals(art1.getImage(), art2.getImage());
	}
	
//	@Test
//	public void testUpdateAddress() {
//		Address address1 = new Address();
//		address1.setCity("a");
//		address1.setId("b");
//		address1.setPostalCode("c");
//		address1.setProvince("d");
//		address1.setStreet("d");
//		address1.setStreetNumber("e");
//		
//		address1 = addressRepository.save(address1);
//		
//		address1.setCity("Moscow");
//		
//		address1 = addressRepository.save(address1);
//		
//		assertEquals(address1.getCity(), "Moscow");
//	}
}