package ca.mcgill.ecse321.gallery.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.gallery.dto.AccountDto;

import ca.mcgill.ecse321.gallery.dto.AddressDto;
import ca.mcgill.ecse321.gallery.dto.ArtDto;
import ca.mcgill.ecse321.gallery.dto.GalleryDto;
import ca.mcgill.ecse321.gallery.dto.IdentityDto;

import ca.mcgill.ecse321.gallery.dto.GalleryDto;

import ca.mcgill.ecse321.gallery.dto.ListingDto;
import ca.mcgill.ecse321.gallery.dto.PaymentDto;
import ca.mcgill.ecse321.gallery.dto.ProfileDto;
import ca.mcgill.ecse321.gallery.dto.RevenuDto;

import ca.mcgill.ecse321.gallery.model.Account;
import ca.mcgill.ecse321.gallery.model.Address;
import ca.mcgill.ecse321.gallery.model.Art;

import ca.mcgill.ecse321.gallery.model.DeliveryType;
import ca.mcgill.ecse321.gallery.model.Gallery;
import ca.mcgill.ecse321.gallery.model.Identity;
import ca.mcgill.ecse321.gallery.model.Listing;
import ca.mcgill.ecse321.gallery.model.Payment;
import ca.mcgill.ecse321.gallery.model.PaymentType;


import ca.mcgill.ecse321.gallery.model.Profile;
import ca.mcgill.ecse321.gallery.model.Revenu;
import ca.mcgill.ecse321.gallery.service.*;

@CrossOrigin(origins = "*")
@RestController
public class GalleryController {

	@Autowired
	private PaymentService paymentService;
	
	@Autowired

	private AccountService accountService;

	@Autowired
	private AddressService addressService;

	@Autowired
	private ArtService artService;

	@Autowired
	private GalleryService galleryService;
	
	@Autowired
	private IdentityService identityService;

	@Autowired
	private ListingService listingService;
	
	@Autowired
	private ProfileService profileService;

	@Autowired
	private RevenuService revenuService;
	
	
	@PostMapping(value = { "/account/create", "/account/create/" })
	private AccountDto createAccount(@RequestParam(name = "account") AccountDto aDto) {
		return null;
	}
	
	@PostMapping(value = { "/account/edit", "/account/edit/" })
	private AccountDto editAccount(@RequestParam(name = "account") AccountDto aDto) {
		return null;
	}
	
	@GetMapping(value = { "/account", "/account/" })
	private AccountDto viewAccount(@RequestParam(name = "username") String username, @RequestParam(name = "password") String password) {
		return null;
	}
	
	@PostMapping(value = { "/profile/create", "/profile/create/"})
	private ProfileDto createProfile(@RequestParam(name = "profile") ProfileDto pDto, @RequestParam(name = "username") String username, @RequestParam(name = "password") String password) {
		return null;
	}
	
	@PostMapping(value = { "/profile/edit", "/profile/edit/"})
	private ProfileDto editProfile(@RequestParam(name = "profile") ProfileDto pDto, @RequestParam(name = "username") String username, @RequestParam(name = "password") String password) {
		return null;
	}
	
	@GetMapping(value = { "/profile/{id}", "/profile/{id}/" })
	private ProfileDto viewProfile(@PathVariable("id") String id) {
		return null;
	}
	
	@PostMapping(value = { "/pay", "/pay/" })
	private PaymentDto pay(@RequestParam(name = "payment") PaymentDto pDto) {
		List<Listing> listings = listingService.getAllListings();
		ArrayList<Listing> relevantListings = new ArrayList<>();
		
		for (Listing l : listings) {
			for (ListingDto lDto : pDto.getListing()) {
				if (l.equals(lDto.getId()))
					relevantListings.add(l);
			}
		}
		
		Optional<Identity> relevantIdentity = identityService.findIdentityByEmail(pDto.getIdentity().getEmail());
		
		Optional<Address> relevantAddress = addressService.getAddressById(pDto.getAddress().getId());
		
		Optional<Payment> payment = paymentService.pay(
				pDto.getDeliveryType(), 
				pDto.getPaymentType(), 
				relevantIdentity, 
				relevantListings, 
				relevantAddress);
		
		return convertToDto(payment);
	}
	
	@GetMapping(value = { "/revenus", "/revenus/" })
	private RevenuDto getAllRevenus(@RequestParam(name = "username") String username, @RequestParam(name = "password") String password) {
		return null;
	}
	
	@GetMapping(value = { "/listings", "/listings/" })
	private List<ListingDto> getAllListings() {
		return null;
	}
	
	@PostMapping(value = { "/listing/create", "/listing/create/" })
	private ListingDto createListing(@RequestParam(name = "listing") ListingDto lDto) {
		return null;
	}
	
	@PostMapping(value = { "/listing/edit", "/listing/edit/" })
	private ListingDto editListing(@RequestParam(name = "listing") ListingDto lDto) {
		return null;
	}
	
	@GetMapping(value = { "/aboutus", "/aboutus/" })
	private GalleryDto aboutUs() {
		GalleryDto gallery = new GalleryDto();
		gallery.setName("Gallery");
		return gallery;
	}
	
	private PaymentDto convertToDto(Optional<Payment> payment) {
		if (payment.isEmpty()) {
			throw new IllegalArgumentException("There is no such Payment!");
		}
		Payment p = payment.get();
		PaymentDto paymentDto = new PaymentDto();
		paymentDto.setConfirmationNumber(p.getConfirmationNumber());
		paymentDto.setTransactionNumber(p.getTransactionNumber());
		paymentDto.setDeliveryType(p.getDeliveryType());
		paymentDto.setPaymentType(p.getPaymentType());
		paymentDto.setIdentity(convertToDto(p.getIdentity()));
		paymentDto.setPaymentDate(p.getPaymentDate());
		paymentDto.setAddress(convertToDto(p.getAddress()));
		Set<ListingDto> listingsDto = new HashSet<ListingDto>();
		for(Listing l : p.getListing()) {
			listingsDto.add(convertToDto(l));
		}
		paymentDto.setListings(listingsDto);
		return paymentDto;
	}
	

	private AddressDto convertToDto(Address a) {
		if (a == null) {
			throw new IllegalArgumentException("There is no such Address!");
		}
		AddressDto addressDto = new AddressDto();
		addressDto.setCity(a.getCity());
		addressDto.setId(a.getId());
		addressDto.setPostalCode(a.getPostalCode());
		addressDto.setProvince(a.getProvince());
		addressDto.setStreet(a.getStreet());
		addressDto.setStreetNumber(a.getStreetNumber());
		return addressDto;
	}
	
	private AccountDto convertToDto(Account a) {
		if (a == null) {
			throw new IllegalArgumentException("There is no such Account!");
		}
		AccountDto accountDto = new AccountDto();
		accountDto.setAccountHolderType(a.getAccountHolderType());
		accountDto.setAccountNumber(a.getAccountNumber());
		accountDto.setAddress(convertToDto(a.getAddress()));
		accountDto.setDateJoined(a.getDateJoined());
		accountDto.setDateOfBirth(a.getDateOfBirth());
		accountDto.setIdentity(convertToDto(a.getIdentity()));
		accountDto.setPassword(a.getPassword());
		accountDto.setPaymentType(a.getPaymentType());
		Set<ProfileDto> profilesDto = new HashSet<ProfileDto>();
		for(Profile p : a.getProfile()) {
			profilesDto.add(convertToDto(p));
		}
		accountDto.setProfile(profilesDto);
		Set<RevenuDto> revenusDto = new HashSet<RevenuDto>();
		for(Revenu r : a.getRevenus()) {
			revenusDto.add(convertToDto(r));
		}
		accountDto.setRevenus(revenusDto);
		accountDto.setUsername(a.getUsername());
		return accountDto;
	}
	
	private ArtDto convertToDto(Art a) {
		if (a == null) {
			throw new IllegalArgumentException("There is no such Art!");
		}
		ArtDto artDto = new ArtDto();
		artDto.setAuthor(a.getAuthor());
		artDto.setDate(a.getDate());
		artDto.setDepth(a.getDepth());
		artDto.setDescription(a.getDescription());
		artDto.setHeight(a.getHeight());
		artDto.setId(a.getId());
		artDto.setImage(a.getImage());
		artDto.setListing(convertToDto(a.getListing()));
		artDto.setName(a.getName());
		artDto.setOwner(convertToDto(a.getOwner()));
		artDto.setType(a.getType());
		artDto.setWidth(a.getWidth());
		return artDto;
		
	}
	
	private GalleryDto convertToDto(Gallery g) {
		if (g == null) {
			throw new IllegalArgumentException("There is no such Gallery!");
		}
		GalleryDto galleryDto = new GalleryDto(); 
		galleryDto.setAddress(convertToDto(g.getAddress()));
		galleryDto.setClosingTime(g.getClosingTime());
		galleryDto.setCommissionPercentage(g.getCommissionPercentage());
		galleryDto.setEmail(g.getEmail());
		galleryDto.setName(g.getName());
		galleryDto.setOpeningTime(g.getOpeningTime());
		galleryDto.setPhoneNumber(g.getPhoneNumber());
		return galleryDto;
	}
	
	private IdentityDto convertToDto(Identity i) {
		if (i == null) {
			throw new IllegalArgumentException("There is no such Identity!");
		}
		IdentityDto identityDto = new IdentityDto();
		identityDto.setAccount(convertToDto(i.getAccount()));
		identityDto.setEmail(i.getEmail());
		return identityDto;
	}
	
	private ListingDto convertToDto(Listing l) {
		if (l == null) {
			throw new IllegalArgumentException("There is no such Identity!");
		}
		ListingDto listingDto = new ListingDto();
		listingDto.setArt(convertToDto(l.getArt()));
		listingDto.setCanDeliver(l.isCanDeliver());
		listingDto.setCanPickUp(l.isCanPickUp());
		listingDto.setDatePublished(l.getDatePublished());
		listingDto.setId(l.getId());
		listingDto.setPrice(l.getPrice());
		listingDto.setPublisher(convertToDto(l.getPublisher()));
		listingDto.setQuantity(l.getQuantity());
		listingDto.setTags(l.getTags());
		return listingDto;
	}
	
	private ProfileDto convertToDto(Profile p) {
		if (p == null) {
			throw new IllegalArgumentException("There is no such Profile!");
		}
		ProfileDto profileDto = new ProfileDto();
		profileDto.setAccountDto(convertToDto(p.getAccount()));
		Set<ArtDto> artsDto = new HashSet<ArtDto>();
		for(Art a : p.getArts()) {
			artsDto.add(convertToDto(a));
		}
		profileDto.setArts(artsDto);
		profileDto.setBio(p.getBio());
		profileDto.setFullname(p.getFullname());
		profileDto.setId(p.getId());
		Set<ListingDto> listingsDto = new HashSet<ListingDto>();
		for(Listing l : p.getListings()) {
			listingsDto.add(convertToDto(l));
		}
		profileDto.setListingDtos(listingsDto);
		profileDto.setPicture(p.getPicture());
		return profileDto;
	}
	
	private RevenuDto convertToDto(Revenu r) {
		if (r == null) {
			throw new IllegalArgumentException("There is no such Revenu!");
		}
		RevenuDto revenuDto = new RevenuDto();
		revenuDto.setAccount(convertToDto(r.getAccount()));
		revenuDto.setComission(r.getComission());
		revenuDto.setId(r.getId());
		revenuDto.setListing(convertToDto(r.getListing()));
		revenuDto.setListingPrice(r.getListingPrice());
		return revenuDto;
	}

}
