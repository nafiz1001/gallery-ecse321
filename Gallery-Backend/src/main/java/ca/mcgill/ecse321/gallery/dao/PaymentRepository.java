package ca.mcgill.ecse321.gallery.dao;

import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.gallery.model.Address;
import ca.mcgill.ecse321.gallery.model.DeliveryType;
import ca.mcgill.ecse321.gallery.model.Identity;
import ca.mcgill.ecse321.gallery.model.Listing;
import ca.mcgill.ecse321.gallery.model.Payment;
import ca.mcgill.ecse321.gallery.model.PaymentType;

@Repository
public class PaymentRepository {

	@Autowired
	EntityManager entityManager;

	@Transactional
	public Payment createPayment(String transactionNumber, Date paymentDate, PaymentType paymentType,
			DeliveryType deliveryType, Address address, Set<Listing> listing, Identity identity) {
		
		Payment p = new Payment(transactionNumber, paymentDate, paymentType, deliveryType, address, listing, identity);
		entityManager.persist(p);
		return p;
	}

	@Transactional
	public Payment getPayment(long confirmationNumber) {
		Payment p = entityManager.find(Payment.class, confirmationNumber);
		return p;
	}
	
	@Transactional
	public Payment removePayment(String confirmationNumber) {
		Payment p = entityManager.find(Payment.class, confirmationNumber);
		entityManager.remove(p);
		return p;
	}
	
	@Transactional
	public void deleteAll() {
		Query deleteQuery = entityManager.createQuery("DELETE FROM Payment");
		deleteQuery.executeUpdate();
	}
}