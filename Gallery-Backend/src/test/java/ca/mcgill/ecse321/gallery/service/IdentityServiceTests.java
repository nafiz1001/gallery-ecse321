package ca.mcgill.ecse321.gallery.service;

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

import ca.mcgill.ecse321.gallery.dao.IdentityRepository;
import ca.mcgill.ecse321.gallery.model.Identity;
import ca.mcgill.ecse321.gallery.model.Listing;
import ca.mcgill.ecse321.gallery.model.Payment;

import static org.mockito.Mockito.lenient;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

@ExtendWith(MockitoExtension.class)
public class IdentityServiceTests {
	
	@Mock 
	private IdentityRepository identityRepository;
	
	@InjectMocks
	IdentityService identityService;
	
	private String email = "0";
	private HashSet<Identity> savedIdentities= new HashSet<>();
	
	@BeforeEach
	public void setupMockup() {
		
//		email="0";
		savedIdentities.clear();
		
		//lambda for sim saving identity
		Answer<Identity> updateEmailAndReturn = (InvocationOnMock invocation) -> {
			Identity i = (Identity)invocation.getArgument(0);
//			i.setEmail(email);
			savedIdentities.add(i);
//			int emailAsNum = Integer.parseInt(email);
//			emailAsNum++;
//			email=Integer.toString(emailAsNum);
			return i;
		};
		
		//simulate save identity
		lenient().when(identityRepository.save(any(Identity.class))).thenAnswer(updateEmailAndReturn);
		
		//sim find all identities
		lenient().when(identityRepository.findAll()).thenAnswer((InvocationOnMock invocation)-> {
			return savedIdentities;
		});
		
		//sim find identity by id
		lenient().when(identityRepository.findById(anyString())).thenAnswer((InvocationOnMock invocation)-> {
			
			String email = ((String)invocation.getArgument(0));
			for (Identity i: savedIdentities) {
				if (i.getEmail().equals(email))
					return Optional.ofNullable(i);
							
			}
			return Optional.empty();
		});
		
		//sim find identity by email
		lenient().when(identityRepository.findIdentityByEmail(anyString())).thenAnswer((InvocationOnMock invocation) -> {
			String email = ((String)invocation.getArgument(0));
			for (Identity i: savedIdentities) {
				if (i.getEmail().equals(email))
					return i;
							
			}
			return null;
		});
		
	}
	
	@Test
	void testSuccessfulIdentityCreation() {
		assertEquals(0, identityService.getAllIdentities().size());
		
		Optional<Identity> identity= identityService.createIdentity("te3st@test.com");
		assertTrue(identity.isPresent());
	}
	
	@Test
	void testInvalidEmailAtCreation() {
		boolean exceptionCaught = false;
		assertEquals(0, identityService.getAllIdentities().size());
		
		try {
			Optional<Identity> identity= identityService.createIdentity("testtest.com");
		}
		catch (IllegalArgumentException e) {
			exceptionCaught = true;
		}
		
		assertTrue(exceptionCaught);
		assertEquals(0,identityService.getAllIdentities().size());
	}
	
	@Test
	void testEmailAlreadyUsed() {
		boolean exceptionCaught = false;
		assertEquals(0, identityService.getAllIdentities().size());
		Optional<Identity> identity= identityService.createIdentity("test3@test.com");
		
		try {
			Optional<Identity> identity2= identityService.createIdentity("test3@test.com");

		}
		catch (IllegalArgumentException e) {
			exceptionCaught= true;	
		}
		
		assertTrue(exceptionCaught);
		assertEquals(1, identityService.getAllIdentities().size());
	
	
	
	}
	
	@Test
	void testFindAllIdentities() {
		assertEquals(0, identityService.getAllIdentities().size());
		
		Optional<Identity> identity= identityService.createIdentity("test1@test.com");
		Optional<Identity> identity2= identityService.createIdentity("test25@test.com");
	
		assertEquals(2, identityService.getAllIdentities().size());
	}
	
	@Test
	void testFindByEmail() {
		assertEquals(0, identityService.getAllIdentities().size());
		
		
		Optional<Identity> identity2= identityService.createIdentity("test25@test.com");

		assertTrue(identityService.findIdentityByEmail("test25@test.com").isPresent());
		
	}
	
	
}
