package ca.mcgill.ecse321.gallery.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import ca.mcgill.ecse321.gallery.dao.PaymentRepository;
import ca.mcgill.ecse321.gallery.model.Payment;

public class PaymentService {
	@Autowired
	PaymentRepository paymentRepository;
	
	@Transactional
	public Payment createPayment(String transactionNumber) {
		Payment Payment = new Payment();
		Payment.setTransactionNumber(transactionNumber);
		paymentRepository.save(Payment);
		return Payment;
	}

	@Transactional
	public Payment getPayment(long confirmationNumber) {
		Payment Payment = paymentRepository.findPaymentByConfirmationNumber(confirmationNumber);
		return Payment;
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
