package ca.mcgill.ecse321.gallery.controller;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.gallery.dto.PaymentDto;
import ca.mcgill.ecse321.gallery.model.DeliveryType;
import ca.mcgill.ecse321.gallery.model.Listing;
import ca.mcgill.ecse321.gallery.model.Payment;
import ca.mcgill.ecse321.gallery.model.PaymentType;
import ca.mcgill.ecse321.gallery.service.PaymentService;

@CrossOrigin(origins = "*")
@RestController
public class GalleryController {
	@Autowired
	private PaymentService paymentService;
	
	@GetMapping(value = { "/payments", "/payments/" })
	public List<PaymentDto> getAllPayments() {
		return paymentService.getAllPayments().stream().map(p -> convertToDto(p)).collect(Collectors.toList());
	}
	
	@GetMapping(value = { "/payment/{confirmationNumber}", "/payment/{confirmationNumber}" })
	public PaymentDto getPayment(@PathVariable("confirmationNumber") long confirmationNumber) {
		return convertToDto(paymentService.getPayment(confirmationNumber));
	}

	@PostMapping(value = { "/pay/{transactionNumber}", "/pay/{TransactionNumber}/" })
	public PaymentDto pay(@PathVariable("transactionNumber") String transactionNumber) throws IllegalArgumentException {
		Payment payment = paymentService.pay(transactionNumber, DeliveryType.PICKUP, PaymentType.CREDIT_CARD, null, new HashSet<Listing>());
		return convertToDto(payment);
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
		return paymentDto;
	}
}
