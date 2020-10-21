package ca.mcgill.ecse321.gallery.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.mcgill.ecse321.gallery.dao.PaymentRepository;
import ca.mcgill.ecse321.gallery.model.DeliveryType;
import ca.mcgill.ecse321.gallery.model.Identity;
import ca.mcgill.ecse321.gallery.model.Listing;
import ca.mcgill.ecse321.gallery.model.Payment;
import ca.mcgill.ecse321.gallery.model.PaymentType;

@Service
public class PaymentService {
	@Autowired
	PaymentRepository paymentRepository;
	
	@Transactional
	public Optional<Payment> pay(String transactionNumber, DeliveryType deliveryType, PaymentType paymentType, Identity identity, Iterable<Listing> listings) {
		Payment payment = new Payment();
		payment.setTransactionNumber(transactionNumber);
		payment.setDeliveryType(deliveryType);
		payment.setPaymentType(paymentType);
		payment.setIdentity(identity);
		payment.setListing(toList(listings));
		payment = paymentRepository.save(payment);
		return Optional.ofNullable(payment);
	}

	@Transactional
	public Optional<Payment> getPayment(long confirmationNumber) {
		return paymentRepository.findById(confirmationNumber);
	}

	@Transactional
	public List<Payment> getAllPayments() {
		return toList(paymentRepository.findAll());
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
