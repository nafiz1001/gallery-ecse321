/**
 * @author antonianistor
 * 
 * This class has the purpose of testing the Profile class from the model
 */

package ca.mcgill.ecse321.gallery.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.HashSet;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ca.mcgill.ecse321.gallery.model.Address;
import ca.mcgill.ecse321.gallery.model.DeliveryType;
import ca.mcgill.ecse321.gallery.model.Payment;
import ca.mcgill.ecse321.gallery.model.PaymentType;
import ca.mcgill.ecse321.gallery.model.Profile;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ProfilePersistenceTests {

	@Autowired
	private ProfileRepository profileRepository;

	/**
	 * Clears all profiles in database before each test
	 */
	@BeforeEach
	public void clearDatabaseBefore() {
		profileRepository.deleteAll();

	}

	/**
	 * Clears all profiles from database after each test
	 */
	@AfterEach
	public void clearDatabaseAfter() {
		profileRepository.deleteAll();

	}

	/**
	 * Tests to see if profiles can be created in the database
	 */
	@Test
	public void testCreateAndLoadProfile() {
		Profile profile1 = new Profile();
		profile1.setBio("12");
		profile1.setFullname("gruau");
		profile1.setId("1");
		profile1.setPicture("url");

		profileRepository.save(profile1);

		Profile profile2 = profileRepository.findProfileById("1");

		assertNotNull(profile2);
		assertEquals(profile1.getFullname(), profile2.getFullname());
	}

	/**
	 * Tests to see if value gets updated in database
	 */
	@Test
	public void testUpdateProfile() {

		Profile profile1 = new Profile();
		profile1.setBio("12");
		profile1.setFullname("gruau");
		profile1.setId("1");
		profile1.setPicture("url");

		profile1 = profileRepository.save(profile1);

		profile1.setFullname("john");

		profile1 = profileRepository.save(profile1);
		assertEquals(profile1.getFullname(), "john");

	}

}
