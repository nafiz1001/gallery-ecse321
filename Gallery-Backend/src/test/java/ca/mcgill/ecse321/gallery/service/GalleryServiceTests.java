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

import ca.mcgill.ecse321.gallery.dao.AddressRepository;
import ca.mcgill.ecse321.gallery.dao.GalleryRepository;
import ca.mcgill.ecse321.gallery.model.Address;
import ca.mcgill.ecse321.gallery.model.Gallery;

@ExtendWith(MockitoExtension.class)
public class GalleryServiceTests {
	@Mock
	private GalleryRepository galleryRepository;

	@Mock
	private AddressRepository addressRepository;

	@InjectMocks
	GalleryService galleryService;

	private String id = "0";
	private HashSet<Gallery> savedGallery = new HashSet<>();
	private HashSet<Address> savedAddress = new HashSet<>();

	@BeforeEach
	public void setupMockup() {
		// address id generator mock
		id = "0";

		// database mock
		savedGallery.clear();
		savedAddress.clear();

		// lambda for simulating saving Gallery
		Answer<Gallery> saveGalleryAndReturn = (InvocationOnMock invocation) -> {
			Gallery gallery = (Gallery) invocation.getArgument(0);
			savedGallery.add(gallery);
			return gallery;
		};

		// lambda for simulating saving Address
		Answer<Address> saveAddressAndReturn = (InvocationOnMock invocation) -> {
			Address address = (Address) invocation.getArgument(0);
			savedAddress.add(address);
			return address;
		};

		// simulate saving gallery
		lenient().when(galleryRepository.save(any(Gallery.class))).thenAnswer(saveGalleryAndReturn);

		// simulate saving address
		lenient().when(addressRepository.save(any(Address.class))).thenAnswer(saveAddressAndReturn);

		// simulate finding gallery by Id (name)
		lenient().when(galleryRepository.findById(anyString())).thenAnswer((InvocationOnMock invocation) -> {
			String name = ((String) invocation.getArgument(0));
			for (Gallery gallery : savedGallery) {
				if (gallery.getName().equals(name))
					return Optional.ofNullable(gallery);
			}
			return Optional.empty();
		});
	}

	@Test
	void testSuccesfullAccount() {
		String name = "McGill";
		String phoneNumber = "1231231234";
		Time openingTime = new Time(100000);
		Time closingTime = new Time(170000);
		String email = "McGill@yahoo.com";
		int commissionPercentage = 25;
		Address address = new Address();

		Optional<Gallery> gallery = galleryService.createGallery(name, phoneNumber, openingTime, closingTime, email,
				commissionPercentage, address);

		assertTrue(gallery.isPresent());
	}

	@Test
	void testPhoneNumberViolations() {
		// phoneNumber length is too long
		String name = "McGill";
		String phoneNumber = "12312312345";
		Time openingTime = new Time(100000);
		Time closingTime = new Time(170000);
		String email = "McGill@yahoo.com";
		int commissionPercentage = 25;
		Address address = new Address();

		Optional<Gallery> gallery1 = galleryService.createGallery(name, phoneNumber, openingTime, closingTime, email,
				commissionPercentage, address);

		assertTrue(gallery1.isEmpty());

		// phoneNumber contains non-digits
		phoneNumber = "123-123-1234";

		Optional<Gallery> gallery2 = galleryService.createGallery(name, phoneNumber, openingTime, closingTime, email,
				commissionPercentage, address);

		assertTrue(gallery2.isEmpty());
	}

	@Test
	void testTimingViolations() {
		// opening time happens after closing time
		String name = "McGill";
		String phoneNumber = "1231231234";
		Time openingTime = new Time(170000);
		Time closingTime = new Time(100000);
		String email = "McGill@yahoo.com";
		int commissionPercentage = 25;
		Address address = new Address();

		Optional<Gallery> gallery1 = galleryService.createGallery(name, phoneNumber, openingTime, closingTime, email,
				commissionPercentage, address);

		assertTrue(gallery1.isEmpty());

		// opening time is at closing time
		openingTime.setTime(100000);

		Optional<Gallery> gallery2 = galleryService.createGallery(name, phoneNumber, openingTime, closingTime, email,
				commissionPercentage, address);

		assertTrue(gallery2.isEmpty());
	}

	@Test
	void testEmailViolations() {
		// email has more than one period
		String name = "McGill";
		String phoneNumber = "1231231234";
		Time openingTime = new Time(100000);
		Time closingTime = new Time(170000);
		String email = "McGill@mail.mcgill.ca";
		int commissionPercentage = 25;
		Address address = new Address();

		Optional<Gallery> gallery1 = galleryService.createGallery(name, phoneNumber, openingTime, closingTime, email,
				commissionPercentage, address);

		assertTrue(gallery1.isEmpty());

		// email has no period
		email = "McGill@yahooca";

		Optional<Gallery> gallery2 = galleryService.createGallery(name, phoneNumber, openingTime, closingTime, email,
				commissionPercentage, address);

		assertTrue(gallery2.isEmpty());

		// email has period but to the left of @
		email = "McGill.yahoo@yahooca";

		Optional<Gallery> gallery3 = galleryService.createGallery(name, phoneNumber, openingTime, closingTime, email,
				commissionPercentage, address);

		assertTrue(gallery3.isEmpty());

		// email missing @
		email = "McGillyahoo.ca";

		Optional<Gallery> gallery4 = galleryService.createGallery(name, phoneNumber, openingTime, closingTime, email,
				commissionPercentage, address);

		assertTrue(gallery4.isEmpty());

		// email has more than 1 @
		email = "McGill@mail@mcgill.ca";

		Optional<Gallery> gallery5 = galleryService.createGallery(name, phoneNumber, openingTime, closingTime, email,
				commissionPercentage, address);

		assertTrue(gallery5.isEmpty());
	}

	@Test
	void testCommissionPercentageViolations() {
		// commissionPercentage >= 100
		String name = "McGill";
		String phoneNumber = "1231231234";
		Time openingTime = new Time(100000);
		Time closingTime = new Time(170000);
		String email = "McGill@yahoo.ca";
		int commissionPercentage = 101;
		Address address = new Address();

		Optional<Gallery> gallery1 = galleryService.createGallery(name, phoneNumber, openingTime, closingTime, email,
				commissionPercentage, address);

		assertTrue(gallery1.isEmpty());

		// commissionPercentage <= 0
		commissionPercentage = -1;

		Optional<Gallery> gallery2 = galleryService.createGallery(name, phoneNumber, openingTime, closingTime, email,
				commissionPercentage, address);

		assertTrue(gallery2.isEmpty());
	}

	@Test
	void testGetGalleryByName() {

		String name = "McGill";
		String phoneNumber = "1231231234";
		Time openingTime = new Time(100000);
		Time closingTime = new Time(170000);
		String email = "McGill@yahoo.ca";
		int commissionPercentage = 25;
		Address address = new Address();

		Optional<Gallery> gallery = galleryService.createGallery(name, phoneNumber, openingTime, closingTime, email,
				commissionPercentage, address);

		assertEquals(gallery, galleryService.getGallery("McGill"));
	}
}
