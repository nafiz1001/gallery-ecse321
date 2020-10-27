package ca.mcgill.ecse321.gallery.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.lenient;

import java.sql.Date;
import java.util.ArrayList;
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

import ca.mcgill.ecse321.gallery.dao.AccountRepository;
import ca.mcgill.ecse321.gallery.dao.IdentityRepository;
import ca.mcgill.ecse321.gallery.dao.ProfileRepository;
import ca.mcgill.ecse321.gallery.model.Account;
import ca.mcgill.ecse321.gallery.model.Address;
import ca.mcgill.ecse321.gallery.model.Art;
import ca.mcgill.ecse321.gallery.model.Identity;
import ca.mcgill.ecse321.gallery.model.Listing;
import ca.mcgill.ecse321.gallery.model.Payment;
import ca.mcgill.ecse321.gallery.model.Profile;
import ca.mcgill.ecse321.gallery.model.Revenu;

@ExtendWith(MockitoExtension.class)
public class ProfileServiceTests {
	@Mock
	private ProfileRepository profileRepository;
	
	@Mock
	private AccountRepository accountRepository;
	
	@InjectMocks
	ProfileService profileService;
	
	private String id = "0";
	private HashSet<Account> savedAccounts = new HashSet<>();
	private HashSet<Profile> savedProfiles = new HashSet<>();
	
	@BeforeEach
	public void setupMockup() {
		
		id = "0";
		
		// database mock
		savedAccounts.clear();
		savedProfiles.clear();
		
//lambda for simulating saving profile

		Answer<Profile> updateProfileNumberAndReturn = (InvocationOnMock invocation) -> {
			Profile a = (Profile)invocation.getArgument(0);
			a.setId(id);
			savedProfiles.add(a);
			int profileNumberAsNum = Integer.parseInt(id);
			profileNumberAsNum++;
			id = Integer.toString(profileNumberAsNum);
			return a;

};

//lambda for simulating saving account
		Answer<Account> saveAndReturn = (InvocationOnMock invocation) -> {
			Account i = (Account)invocation.getArgument(0);
			savedAccounts.add(i);
			return i;
		};
		
		// simulate saving profile
				lenient().when(profileRepository.save(any(Profile.class))).then(updateProfileNumberAndReturn);
				
				// simulate saving account
				lenient().when(accountRepository.save(any(Account.class))).thenAnswer(saveAndReturn);
				
				
				// simulate finding profile by Id
				lenient().when(profileRepository.findById(anyString())).thenAnswer((InvocationOnMock invocation) -> {
					String Id = ((String)invocation.getArgument(0));
					for (Profile a : savedProfiles) {
						if (a.getId().equals(Id))
							return Optional.ofNullable(a);
					}
					return Optional.empty();
				});
						
				// simulate finding all Profiles
				lenient().when(profileRepository.findAll()).thenAnswer((InvocationOnMock invocation) -> {
					return savedProfiles;
				});
				
		
		
	}
	
	@Test
	void testSuccessfullProfile() {
		
		Account account = new Account();
		ArrayList<Listing> listings = new ArrayList<Listing>();
		ArrayList<Art> arts = new ArrayList<Art>();

		
		Optional<Profile> profile = profileService.createProfile("Bio","Pic", listings, account, "Haluk", arts);
		
		assertTrue(profile.isPresent());


	}
	
	
	
	
	
	
	
}
