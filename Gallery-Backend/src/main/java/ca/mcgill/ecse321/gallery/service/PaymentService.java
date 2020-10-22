package ca.mcgill.ecse321.gallery.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.mcgill.ecse321.gallery.dao.ListingRepository;
import ca.mcgill.ecse321.gallery.dao.PaymentRepository;
import ca.mcgill.ecse321.gallery.model.Address;
import ca.mcgill.ecse321.gallery.model.DeliveryType;
import ca.mcgill.ecse321.gallery.model.Identity;
import ca.mcgill.ecse321.gallery.model.Listing;
import ca.mcgill.ecse321.gallery.model.Payment;
import ca.mcgill.ecse321.gallery.model.PaymentType;

@Service
public class PaymentService {
	@Autowired
	PaymentRepository paymentRepository;
	
	@Autowired
	ListingRepository listingRepository;
	
	@Transactional
	public Optional<Payment> pay(String transactionNumber, DeliveryType deliveryType, PaymentType paymentType, Optional<Identity> identity, Iterable<Listing> listings, Optional<Address> address) {
		boolean isPaymentValid = true;
		Payment payment = null;
		
		// is max quantity violated?
		HashMap<Listing, Integer> listingCounts = new HashMap<>(); 
		for (Listing l : listings) {
			listingCounts.put(l, listingCounts.putIfAbsent(l, 0) + 1);
		}
		
		for (Listing l : listingCounts.keySet()) {
			if (listingCounts.get(l) > l.getQuantity())
				isPaymentValid = false;
		}
		
		// is delivery type valid?
		if (deliveryType == DeliveryType.SHIPPING) {
			int errorCount = 0;
			int size = 0;
			for (Listing l : listings) {
				size++;
				if (!l.isCanDeliver())
					errorCount++;
			}
			if (errorCount == size)
				isPaymentValid = false;
		}
		
		if (deliveryType == DeliveryType.PICKUP) {
			int errorCount = 0;
			int size = 0;
			for (Listing l : listings) {
				size++;
				if (!l.isCanPickUp())
					errorCount++;
			}
			if (errorCount == size)
				isPaymentValid = false;
		}
		
		// is address available for delivery?
		if (deliveryType == DeliveryType.SHIPPING && address.isEmpty()) {
				isPaymentValid = false;
		}
		
		if (isPaymentValid) {
			// update listing quantity
			for (Listing l : listings) {
				l.setQuantity(l.getQuantity() - listingCounts.get(l));
				listingRepository.save(l);
			}
			
			// create payment
			payment = new Payment();
			payment.setTransactionNumber(transactionNumber);
			payment.setDeliveryType(deliveryType);
			payment.setPaymentType(paymentType);
			payment.setIdentity(identity.get());
			payment.setAddress(address.get());
			payment.setListing(toList(listings));
			payment = paymentRepository.save(payment);
		}
		
		return Optional.ofNullable(payment);
	}

	@Transactional
	public Optional<Payment> getPayment(long confirmationNumber) {
		return paymentRepository.findById(confirmationNumber);
	}
	
	@Transactional
	public List<Payment> getAllPaymentsByEmail(String email) {
		return toList(paymentRepository.findByIdentityEmail(email));
	}
	
	private <T> List<T> toList(Iterable<T> iterable){
		List<T> resultList = new ArrayList<T>();
		for (T t : iterable) {
			resultList.add(t);
		}
		return resultList;
	}
	
	private <T> Set<T> toSet(Iterable<T> iterable){
		Set<T> resultList = new HashSet<T>();
		for (T t : iterable) {
			resultList.add(t);
		}
		return resultList;
	}
}
