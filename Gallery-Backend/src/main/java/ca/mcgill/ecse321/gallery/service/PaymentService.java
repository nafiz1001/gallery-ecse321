package ca.mcgill.ecse321.gallery.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.mcgill.ecse321.gallery.dao.PaymentRepository;
import ca.mcgill.ecse321.gallery.model.DeliveryType;
import ca.mcgill.ecse321.gallery.model.Payment;
import ca.mcgill.ecse321.gallery.model.PaymentType;

@Service
public class PaymentService {
	@Autowired
	PaymentRepository paymentRepository;
	
	@Transactional
	public Payment pay(String transactionNumber) {
		Payment payment = new Payment();
		payment.setTransactionNumber(transactionNumber);
		payment.setDeliveryType(DeliveryType.PICKUP);
		payment.setPaymentType(PaymentType.CREDIT_CARD);
		payment = paymentRepository.save(payment);
		return payment;
	}

	@Transactional
	public Payment getPayment(long confirmationNumber) {
		Payment payment = paymentRepository.findPaymentByConfirmationNumber(confirmationNumber);
		return payment;
	}

	@Transactional
	public List<Payment> getAllPayments() {
		return toList(paymentRepository.findAll());
	}
	
	private <T> List<T> toList(Iterable<T> iterable){
		List<T> resultList = new ArrayList<T>();
		for (T t : iterable) {
			resultList.add(t);
		}
		return resultList;
	}
}
