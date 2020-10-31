package ca.mcgill.ecse321.gallery.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
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
import ca.mcgill.ecse321.gallery.dto.GalleryDto;
import ca.mcgill.ecse321.gallery.dto.ListingDto;
import ca.mcgill.ecse321.gallery.dto.PaymentDto;
import ca.mcgill.ecse321.gallery.dto.ProfileDto;
import ca.mcgill.ecse321.gallery.dto.RevenuDto;
import ca.mcgill.ecse321.gallery.model.DeliveryType;
import ca.mcgill.ecse321.gallery.model.Listing;
import ca.mcgill.ecse321.gallery.model.Payment;
import ca.mcgill.ecse321.gallery.model.PaymentType;
import ca.mcgill.ecse321.gallery.service.ListingService;
import ca.mcgill.ecse321.gallery.service.PaymentService;

@CrossOrigin(origins = "*")
@RestController
public class GalleryController {
	@Autowired
	private PaymentService paymentService;
	
	//@Autowired
	//private ListingService listingService;
	
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
		return null;
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
	
	// TODO implement convertToDtos
	private List<ListingDto> convertToDtos(Collection<Listing> listings) {
		throw new UnsupportedOperationException();
	}
	
	private PaymentDto convertToDto(Payment p) {
		if (p == null) {
			throw new IllegalArgumentException("There is no such Person!");
		}
		PaymentDto paymentDto = new PaymentDto();
		paymentDto.setConfirmationNumber(p.getConfirmationNumber());
		paymentDto.setTransactionNumber(p.getTransactionNumber());
		paymentDto.setDeliveryType(p.getDeliveryType());
		paymentDto.setPaymentType(p.getPaymentType());
		//paymentDto.setIdentity(convertToDto(p.getIdentity()));
		paymentDto.setPaymentDate(p.getPaymentDate());
		//paymentDto.setAddress(convertToDto(p.getAddress()));
		//paymentDto.setListing(convertToDto(p.getListing()));
		return paymentDto;
	}
	
	
}
