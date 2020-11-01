package ca.mcgill.ecse321.gallery.controller;

import java.sql.Time;
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
import org.springframework.web.bind.annotation.RequestBody;
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
	
	@PostMapping(value = { "/identity/create", "identity/create/"} )
	private IdentityDto createIdentity(@RequestParam(name = "email") String email) {
		Optional<Identity> identity = identityService.findIdentityByEmail(email);
		if (identity.isEmpty()) {
			return convertToDto(identityService.createIdentity(email).get());
		}
		
		return convertToDto(identity.get());
	}

	@PostMapping(value = { "/account/create", "/account/create/" })
	private AccountDto createAccount(@RequestBody AccountDto aDto) {
		IdentityDto email = aDto.getIdentity();
		Optional<Identity> identity = identityService.findIdentityByEmail(aDto.getIdentity().getEmail());
		if (identity.isEmpty())
			identity = identityService.createIdentity(aDto.getIdentity().getEmail());

		Set<Profile> profiles = new HashSet<Profile>();
			
		Set<Revenu> revenus = new HashSet<Revenu>();

		Address address = null;
		if (aDto.getAddress() != null) {
			address = addressService.createAddress(
						aDto.getAddress().getStreetNumber(), aDto.getAddress().getStreet(), aDto.getAddress().getCity(), 
						aDto.getAddress().getProvince(), aDto.getAddress().getPostalCode()).get();
		}
		
		Account account = accountService.createAccount(aDto.getAccountHolderType(), identity.get(), profiles,
				aDto.getUsername(), aDto.getPassword(), aDto.getDateJoined(), address, aDto.getDateOfBirth(),
				revenus, aDto.getPaymentType()).get();

		return convertToDto(account);
	}

	@PostMapping(value = { "/account/edit", "/account/edit/" })
	private AccountDto editAccount(@RequestBody AccountDto aDto,
			@RequestParam(name = "password") String password) {
		
		List<Account> listAccount = accountService.getAccountByUsername(aDto.getUsername());
		if (listAccount.isEmpty())
			throw new IllegalArgumentException("There is no such Account with username " + aDto.getUsername());
				
		Address address = null;
		if (aDto.getAddress() != null) {
			address = addressService.createAddress(
				aDto.getAddress().getStreetNumber(), aDto.getAddress().getStreet(), aDto.getAddress().getCity(), 
				aDto.getAddress().getProvince(), aDto.getAddress().getPostalCode()).get();
		}

		Optional<Account> account = Optional.ofNullable(accountService.editAccount(aDto.getAccountHolderType(), aDto.getUsername(),
				aDto.getPassword(), address, aDto.getDateOfBirth(), aDto.getPaymentType(), password)
				.get());

		return convertToDto(account.get());
	}

	@GetMapping(value = { "/account", "/account/" })
	private AccountDto viewAccount(@RequestParam(name = "username") String username,
			@RequestParam(name = "password") String password) {
		Account account = accountService.getAccountById(username).get();
		if (account.getPassword().equals(password)) {
			return convertToDto(account);
		} else {
			throw new IllegalArgumentException("Password entered is incorrect");
		}
	}


	@PostMapping(value = { "/profile/edit", "/profile/edit/" })
	private ProfileDto editProfile(@RequestBody ProfileDto pDto, @RequestParam(name = "password") String password) {
		Optional<Account> account = accountService.getAccountById(pDto.getAccountDto().getAccountNumber());
		
		Set<Listing> listings = new HashSet<Listing>();
		for (ListingDto l : pDto.getListingDtos()) {
			listings.add(listingService.findListingById(l.getId()).get());
		}
		
		Set<Art> arts = new HashSet<Art>();
		for (ArtDto a : pDto.getArts()) {
			arts.add(artService.getArtById(a.getId()).get());
		}

		if (password.equals(account.get().getPassword())) {
			Profile profile = profileService.editProfile(pDto.getBio(), pDto.getPicture(), listings, account.get(), pDto.getFullname(), arts).get();
			return convertToDto(profile);
		} else {
			throw new IllegalArgumentException("Password entered is incorrect");
		}
	}
	
	@PostMapping(value = { "/profile/create", "/profile/create/"})
	private ProfileDto createProfile(@RequestBody ProfileDto pDto, @RequestParam(name = "password") String password) 
	{
	Optional<Account> account = accountService.getAccountById(pDto.getAccountDto().getUsername());
	if (account.isEmpty())
		throw new IllegalArgumentException("There is no such Account!");
	
	Account acc = account.get();
	
	if (!acc.getPassword().equals(password)) {
		throw new IllegalArgumentException("The password is incorrect!");
	}
	Set<Listing> listings = new HashSet<Listing>();
	
	Set<Art> arts = new HashSet<Art>();
	
	Optional<Profile> p = profileService.createProfile(pDto.getBio(), pDto.getPicture(), listings, acc, pDto.getFullname(), arts);
	Profile profile = p.get();
	
	return convertToDto(profile);
}
	

	

	@GetMapping(value = { "/profile/{id}", "/profile/{id}/" })
	private ProfileDto viewProfile(@PathVariable("id") String id) {
		Optional<Profile> profile = profileService.getProfile(id);
		return convertToDto(profile.get());
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

		Optional<Payment> payment = paymentService.pay(pDto.getDeliveryType(), pDto.getPaymentType(), relevantIdentity,
				relevantListings, relevantAddress);

		return convertToDto(payment);
	}

	@GetMapping(value = { "/revenus", "/revenus/" })
	private ArrayList<RevenuDto> getAllRevenus(@RequestParam(name = "username") String username,
			@RequestParam(name = "password") String password) {
		Optional<Account> account = accountService.getAccountById(username);
		if (account.isEmpty())
			throw new IllegalArgumentException("There is no such Account!");

		Account relevantAccount = account.get();

		if (!relevantAccount.getPassword().equals(password)) {
			throw new IllegalArgumentException("The password is incorrect!");
		}

		Set<Revenu> revenus = relevantAccount.getRevenus();
		ArrayList<RevenuDto> revenusDto = new ArrayList<>();
		for (Revenu r : revenus) {
			revenusDto.add(convertToDto(r));
		}

		return revenusDto;
	}

	@GetMapping(value = { "/listings", "/listings/" })
	private List<ListingDto> getAllListings() {

		return listingService.getAllListings().stream().map(l -> convertToDto(l)).collect(Collectors.toList());
	}

	@PostMapping(value = { "/listing/create", "/listing/create/" })
	private ListingDto createListing(@RequestBody ListingDto lDto, @RequestParam(name = "password") String password) throws IllegalArgumentException {

		Optional<Art> art = artService.getArtById(lDto.getArt().getId());

		Optional<Listing> l = listingService.createListing(art.get(), lDto.getPrice(), lDto.getQuantity(),
				lDto.getTags(), lDto.isCanPickUp(), lDto.isCanDeliver(), lDto.getDatePublished());
		Listing listing = l.get();

		return convertToDto(listing);
	}

	@PostMapping(value = { "/listing/edit", "/listing/edit/" })
	private ListingDto editListing(@RequestBody ListingDto lDto, @RequestParam(name = "password") String password) {

		Optional<Art> art = artService.getArtById(lDto.getArt().getId());

		Listing listing = listingService.editListing(art.get(), lDto.getPrice(), lDto.getQuantity(), lDto.getTags(),
				lDto.isCanPickUp(), lDto.isCanDeliver(), lDto.getDatePublished()).get();

		return convertToDto(listing);
	}

	@GetMapping(value = { "/gallery/view", "/gallery/view/" })
	private GalleryDto aboutUs() {
		Optional<Gallery> gallery = galleryService.getGallery("Gallery");
		if (gallery.isEmpty()) {
			Optional<Address> address = addressService.createAddress("0", "0", "Montreal", "Quebec", "H0H 0H0");
			return convertToDto(galleryService.createGallery("Gallery", "0123456789", new Time(0), new Time(1), "gallery@gallery.com", 10, address.get()).get());
		} else {
			return convertToDto(gallery.get());
		}
	}
	
	@GetMapping(value = { "/arts/{id}", "/arts/{id}/" })
	private List<ArtDto> getAllArts(@PathVariable("id") String id) {
		Optional<Profile> profile = profileService.getProfile(id);
		if (profile.isEmpty())
			throw new IllegalArgumentException("There is no such Profile with id " + id);
		
		return profile.get().getArts().stream().map(l -> convertToDto(l)).collect(Collectors.toList());
	}

	@PostMapping(value = { "/art/create", "/art/create/" })
	private ArtDto createArt(@RequestBody ArtDto aDto, @RequestParam(name = "password") String password) throws IllegalArgumentException {
		Optional<Profile> profile = profileService.getProfile(aDto.getOwner().getId());
		Optional<Art> art = artService.createArt(
				aDto.getName(), aDto.getDescription(), aDto.getHeight(), aDto.getWidth(), aDto.getDepth(), aDto.getImage(), aDto.getDate(), profile.get(), aDto.getType(), aDto.getAuthor()
				);

		return convertToDto(art.get());
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
		for (Listing l : p.getListing()) {
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
		accountDto.setAddress(null);
		accountDto.setDateJoined(a.getDateJoined());
		accountDto.setDateOfBirth(a.getDateOfBirth());
		accountDto.setIdentity(convertToDto(a.getIdentity()));
		accountDto.setPassword(a.getPassword());
		accountDto.setPaymentType(a.getPaymentType());
		Set<ProfileDto> profilesDto = new HashSet<ProfileDto>();
		for (Profile p : a.getProfile()) {
			profilesDto.add(convertToDto(p));
		}
		accountDto.setProfile(profilesDto);
		Set<RevenuDto> revenusDto = new HashSet<RevenuDto>();
		for (Revenu r : a.getRevenus()) {
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
		identityDto.setAccount(null);
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
		for (Art a : p.getArts()) {
			artsDto.add(convertToDto(a));
		}
		profileDto.setArts(artsDto);
		profileDto.setBio(p.getBio());
		profileDto.setFullname(p.getFullname());
		profileDto.setId(p.getId());
		Set<ListingDto> listingsDto = new HashSet<ListingDto>();
		for (Listing l : p.getListings()) {
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
