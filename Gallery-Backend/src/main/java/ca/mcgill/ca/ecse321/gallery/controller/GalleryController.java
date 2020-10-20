package ca.mcgill.ca.ecse321.gallery.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.gallery.dto.PaymentDto;
import ca.mcgill.ecse321.gallery.model.Payment;
import ca.mcgill.ecse321.gallery.service.PaymentService;

@CrossOrigin(origins = "*")
@RestController
public class GalleryController {
	@Autowired
	private PaymentService paymentService;
	
	@GetMapping(value = { "/payments", "/payments/" })
	public List<PaymentDto> getAllPersons() {
		return paymentService.getAllPayments().stream().map(p -> convertToDto(p)).collect(Collectors.toList());
	}

	@PostMapping(value = { "/payment/{transactionNumber}", "/payment/{TransactionNumber}/" })
	public PaymentDto createPerson(@PathVariable("name") String transactionNumber) throws IllegalArgumentException {
		Payment payment = paymentService.createPayment(transactionNumber);
		return convertToDto(payment);
	}
	
	private PaymentDto convertToDto(Payment p) {
		if (p == null) {
			throw new IllegalArgumentException("There is no such Person!");
		}
		PaymentDto paymentDto = new PaymentDto();
		paymentDto.setConfirmationNumber(p.getConfirmationNumber());
		return paymentDto;
	}
}
