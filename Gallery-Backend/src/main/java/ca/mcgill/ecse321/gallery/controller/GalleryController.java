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

/**
 * 
 * @author ericpelletier, atoniannistor, nafiz1001, BbananasS, halukcalin
 * Gallery controller
 */
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
	
	/**
	 * Creates an Identity.
	 * @param email The email associated to the identity
	 * @return The identity created converted to Dto
	 */
	@PostMapping(value = { "/identity/create", "identity/create/"} )
	private IdentityDto createIdentity(@RequestParam(name = "email") String email) {
		Optional<Identity> identity = identityService.findIdentityByEmail(email);
		if (identity.isEmpty()) {
			return convertToDto(identityService.createIdentity(email).get());
		}
		
		return convertToDto(identity.get());
	}

	/**
	 * Creates an Account.
	 * @param aDto The account to be created
	 * @return The account created converted to Dto
	 */
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

	/**
	 * Edits an Account.
	 * @param aDto The account to edit
	 * @param password The password for verification
	 * @return The newly edited account converted to Dto
	 */
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

	/**
	 * Views an Account.
	 * @param username The username of the account to view
	 * @param password The password of the account for verification
	 * @return The account to view converted to Dto
	 */
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

	/**
	 * Edits a Profile
	 * @param pDto The profile to edit
	 * @param password The password of the account associated to the profile for verification
	 * @return The newly edited Profile converted to Dto
	 */
	@PostMapping(value = { "/profile/edit", "/profile/edit/" })
	private ProfileDto editProfile(@RequestBody ProfileDto pDto, @RequestParam(name = "password") String password) {
		Optional<Profile> profile = profileService.getProfile(pDto.getId());
		
		if (profile.isEmpty()) {
			throw new IllegalArgumentException("There is no such profile with id " + pDto.getId());
		}
		
		if (password.equals(profile.get().getAccount().getPassword())) {
			profile = profileService.editProfile(
					pDto.getBio(), pDto.getPicture(), profile.get().getListings(), 
					profile.get().getAccount(), pDto.getFullname(), profile.get().getArts());
			return convertToDto(profile.get());
		} else {
			throw new IllegalArgumentException("Password entered is incorrect");
		}
	}
	
	/**
	 * Creates a Profile
	 * @param pDto The profile to be created
	 * @param password The password of the account associated to the profile for verification
	 * @return The Profile created converted to Dto
	 */
	@PostMapping(value = { "/profile/create", "/profile/create/"})
	private ProfileDto createProfile(@RequestBody ProfileDto pDto, @RequestParam(name = "password") String password) {
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

	/**
	 * Views a Profile
	 * @param id The id of the profile to view
	 * @return The profile to view converted to Dto
	 */
	@GetMapping(value = { "/profile/{id}", "/profile/{id}/" })
	private ProfileDto viewProfile(@PathVariable("id") String id) {
		Optional<Profile> profile = profileService.getProfile(id);
		return convertToDto(profile.get());
	}

	/**
	 * Creates a Payment.
	 * @param pDto The payment to be created
	 * @return The payment created converted to Dto 
	 * @throws Exception Revenu can not be created for listing
	 */
	@PostMapping(value = { "/pay", "/pay/" })
	private PaymentDto pay(@RequestBody PaymentDto pDto) throws Exception {
		ArrayList<Listing> relevantListings = new ArrayList<>();
			for (ListingDto lDto : pDto.getListing()) {
			Optional<Listing> l = listingService.findListingById(lDto.getId());
			if (l.isPresent())
				relevantListings.add(l.get());
			}

		Optional<Identity> relevantIdentity = Optional.empty();
		if (pDto.getIdentity() != null)
			relevantIdentity = identityService.findIdentityByEmail(pDto.getIdentity().getEmail());


		Optional<Address> relevantAddress = Optional.empty(); 
		if (pDto.getAddress() != null)
			relevantAddress = addressService.getAddressById(pDto.getAddress().getId());

		Optional<Payment> payment = paymentService.pay(pDto.getDeliveryType(), pDto.getPaymentType(), relevantIdentity,
				relevantListings, relevantAddress);

		if (payment.isPresent()) {
		for (Listing l : payment.get().getListing()) {
			Optional<Revenu> revenu = revenuService.createRevenu(10, l.getPrice(), l.getPublisher().getAccount(), l);
			if (revenu.isEmpty())
				throw new Exception("Failed to generate revenu for listing " + l.getId());
			}
		}
		return convertToDto(payment);
	}

	/**
	 * Retrieves all revenus of a specific account
	 * @param username The username of the account 
	 * @param password The password of the account for verification
	 * @return An array of revenus converted to Dto
	 */
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

	/**
	 * Retrieves all listings
	 * @return A list of listings converted to Dto
	 */
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

	/**
	 * Edits a listing
	 * @param lDto The listing to edit
	 * @param password The password of the account associated to the account for verification
	 * @return The newly created listing converted to Dto
	 */
	@PostMapping(value = { "/listing/edit", "/listing/edit/" })
	private ListingDto editListing(@RequestBody ListingDto lDto, @RequestParam(name = "password") String password) {

		Optional<Art> art = artService.getArtById(lDto.getArt().getId());

		Listing listing = listingService.editListing(art.get(), lDto.getPrice(), lDto.getQuantity(), lDto.getTags(),
				lDto.isCanPickUp(), lDto.isCanDeliver(), lDto.getDatePublished()).get();

		return convertToDto(listing);
	}

	/**
	 * Creation of Gallery
	 * @return The created Gallery converted to Dto
	 */
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
	
	/**
	 * Retrieves all art of a specific profile
	 * @param id The id of the profile
	 * @return A list of art associated to a specific profile converted to Dto
	 */
	@GetMapping(value = { "/arts/{id}", "/arts/{id}/" })
	private List<ArtDto> getAllArts(@PathVariable("id") String id) {
		Optional<Profile> profile = profileService.getProfile(id);
		if (profile.isEmpty())
			throw new IllegalArgumentException("There is no such Profile with id " + id);
		
		return profile.get().getArts().stream().map(l -> convertToDto(l)).collect(Collectors.toList());
	}

	/**
	 * Creates a Art
	 * @param aDto The art to be created
	 * @param password The password of the account associated to the profile 
	 * @return The created Art converted to Dto
	 * @throws IllegalArgumentException The is no profile with id of the art's owner
	 */
	@PostMapping(value = { "/art/create", "/art/create/" })
	private ArtDto createArt(@RequestBody ArtDto aDto, @RequestParam(name = "password") String password) throws IllegalArgumentException {
		Optional<Profile> profile = profileService.getProfile(aDto.getOwner().getId());
		if (profile.isEmpty()) {
			throw new IllegalArgumentException("There is no profile with id " + aDto.getOwner().getId());
		}
		Optional<Art> art = artService.createArt(
				aDto.getName(), aDto.getDescription(), aDto.getHeight(), aDto.getWidth(), aDto.getDepth(), aDto.getImage(), aDto.getDate(), profile.get(), aDto.getType(), aDto.getAuthor()
				);

		return convertToDto(art.get());
	}

	/**
	 * Convert payment to Dto
	 * @param payment
	 * @return Payment converted to Dto
	 */
	private PaymentDto convertToDto(Optional<Payment> payment) {
		if (payment.isEmpty()) {
			return null;
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

	/**
	 * Convert Address to Dto
	 * @param address
	 * @return Address converted to Dto
	 */
	private AddressDto convertToDto(Address a) {
		if (a == null) {
			return null;
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

	/**
	 * Convert Account to Dto
	 * @param account
	 * @return Account converted to Dto
	 */
	private AccountDto convertToDto(Account a) {
		if (a == null) {
			return null;
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
			ProfileDto pDto = new ProfileDto();
			pDto.setId(p.getId());
			profilesDto.add(pDto);
		}
		accountDto.setProfile(profilesDto);
		Set<RevenuDto> revenusDto = new HashSet<RevenuDto>();
		accountDto.setRevenus(revenusDto);
		accountDto.setUsername(a.getUsername());
		return accountDto;
	}

	/**
	 * Convert Art to Dto
	 * @param art
	 * @return Art converted to Dto
	 */
	private ArtDto convertToDto(Art a) {
		if (a == null) {
			return null;
		}
		ArtDto artDto = new ArtDto();
		artDto.setAuthor(a.getAuthor());
		artDto.setDate(a.getDate());
		artDto.setDepth(a.getDepth());
		artDto.setDescription(a.getDescription());
		artDto.setHeight(a.getHeight());
		artDto.setId(a.getId());
		artDto.setImage(a.getImage());
		ListingDto lDto = null;
		if (a.getListing() != null) {
			lDto = new ListingDto();
			lDto.setId(a.getListing().getId());
		}
		artDto.setListing(lDto);
		artDto.setName(a.getName());
		ProfileDto pDto = new ProfileDto();
		pDto.setId(a.getOwner().getId());
		artDto.setOwner(pDto);
		artDto.setType(a.getType());
		artDto.setWidth(a.getWidth());
		return artDto;

	}

	/**
	 * Convert Gallery to Dto
	 * @param gallery
	 * @return Gallery converted to Dto
	 */
	private GalleryDto convertToDto(Gallery g) {
		if (g == null) {
			return null;
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

	/**
	 * Convert Identity to Dto
	 * @param Identity
	 * @return Identity converted to Dto
	 */
	private IdentityDto convertToDto(Identity i) {
		if (i == null) {
			return null;
		}
		IdentityDto identityDto = new IdentityDto();
		AccountDto aDto = new AccountDto();
		if (i.getAccount() != null) {
			aDto.setUsername(i.getAccount().getUsername());
			identityDto.setAccount(aDto);
		}
		identityDto.setEmail(i.getEmail());
		return identityDto;
	}

	/**
	 * Convert Listing to Dto
	 * @param listing
	 * @return Listing converted to Dto
	 */
	private ListingDto convertToDto(Listing l) {
		if (l == null) {
			return null;
		}
		ListingDto listingDto = new ListingDto();
		listingDto.setArt(convertToDto(l.getArt()));
		listingDto.setCanDeliver(l.isCanDeliver());
		listingDto.setCanPickUp(l.isCanPickUp());
		listingDto.setDatePublished(l.getDatePublished());
		listingDto.setId(l.getId());
		listingDto.setPrice(l.getPrice());
		ProfileDto pDto = new ProfileDto();
		pDto.setId(l.getPublisher().getId());
		listingDto.setPublisher(pDto);
		listingDto.setQuantity(l.getQuantity());
		listingDto.setTags(l.getTags());
		return listingDto;
	}

	/**
	 * Convert Profile to Dto
	 * @param profile
	 * @return Profile converted to Dto
	 */
	private ProfileDto convertToDto(Profile p) {
		if (p == null) {
			return null;
		}
		ProfileDto profileDto = new ProfileDto();
		AccountDto aDto = new AccountDto();
		aDto.setUsername(p.getAccount().getUsername());
		profileDto.setAccountDto(aDto);
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
			ListingDto lDto = new ListingDto();
			lDto.setId(l.getId());
			listingsDto.add(lDto);
		}
		profileDto.setListingDtos(listingsDto);
		profileDto.setPicture(p.getPicture());
		return profileDto;
	}

	/**
	 * Convert Revenu to Dto
	 * @param revenu
	 * @return Revenu converted to Dto
	 */
	private RevenuDto convertToDto(Revenu r) {
		if (r == null) {
			return null;
		}
		RevenuDto revenuDto = new RevenuDto();
		AccountDto aDto = new AccountDto();
		aDto.setUsername(aDto.getUsername());
		revenuDto.setAccount(aDto);
		revenuDto.setComission(r.getComission());
		revenuDto.setId(r.getId());
		revenuDto.setListing(convertToDto(r.getListing()));
		revenuDto.setListingPrice(r.getListingPrice());
		return revenuDto;
	}

}
