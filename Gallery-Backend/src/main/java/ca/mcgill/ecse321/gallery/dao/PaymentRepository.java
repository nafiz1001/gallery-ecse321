package ca.mcgill.ecse321.gallery.dao;

import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.gallery.model.Address;
import ca.mcgill.ecse321.gallery.model.DeliveryType;
import ca.mcgill.ecse321.gallery.model.Identity;
import ca.mcgill.ecse321.gallery.model.Listing;
import ca.mcgill.ecse321.gallery.model.Payment;
import ca.mcgill.ecse321.gallery.model.PaymentType;


public interface PaymentRepository extends CrudRepository<Payment, Long>{
	Payment findPaymentByConfirmationNumber(long confirmationNumber);
	
	void deleteAll();
}