/**
 * @author BbananasS
 * 
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

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ca.mcgill.ecse321.gallery.model.Art;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ArtPersistenceTests {
	
	@Autowired
	private ArtRepository artRepository;
	
	/**
	 * Clears all Arts in database before each test
	 */
	@BeforeEach
	public void clearDatabse() {
		artRepository.deleteAll();
	}
	
	/**
	 * Clears all Arts in database after each test
	 */
	@AfterEach
	public void clearDatabase() {
		artRepository.deleteAll();
	}
	
	/**
	 * Tests to see if an Art can be created in the database
	 */
	@Test
	public void testCreateAndLoadAddress() {		
		Art art1 = new Art();
		art1.setAuthor("a");
		art1.setDate(null);
		art1.setDepth(10);
		art1.setDescription("c");
		art1.setHeight(10);
		art1.setId((long) 1234);
		art1.setImage("d");
		art1.setName("e");
		art1.setType("f");
		art1.setWidth(10);
		
		art1 = artRepository.save(art1);
		
		Art art2 = artRepository.findArtById(art1.getId());
		
		assertNotNull(art2);
		assertEquals(art1.getImage(), art2.getImage());
	}
	
	/**
	 * Tests to see if Art attribute values can be updated in the database
	 */
	@Test
	public void testUpdateArt() {
		Art art1 = new Art();
		art1.setAuthor("a");
		art1.setDate(null);
		art1.setDepth(10);
		art1.setDescription("c");
		art1.setHeight(10);
		art1.setId((long) 1234);
		art1.setImage("d");
		art1.setName("Vango");
		art1.setType("f");
		art1.setWidth(10);
		
		artRepository.save(art1);
		
		art1 = artRepository.save(art1);
		
		art1.setName("Mona Lisa");
		
		art1 = artRepository.save(art1);
		
		assertEquals(art1.getName(), "Mona Lisa");
	}
}