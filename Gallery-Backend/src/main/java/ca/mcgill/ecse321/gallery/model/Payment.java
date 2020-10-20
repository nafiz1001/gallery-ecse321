package ca.mcgill.ecse321.gallery.model;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Date;
import javax.persistence.ManyToOne;
import java.util.Set;
import javax.persistence.OneToMany;

@Entity
public class Payment{
	private long confirmationNumber;
	public void setConfirmationNumber(long value) {
	   this.confirmationNumber = value;
    }
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long getConfirmationNumber() {
		return this.confirmationNumber;
    }


	private String transactionNumber;
	
	public void setTransactionNumber(String value) {
		this.transactionNumber = value;
    }
	public String getTransactionNumber() {
		return this.transactionNumber;
	}

	private Date paymentDate;
	public void setPaymentDate(Date value) {
		this.paymentDate = value;
	}
	public Date getPaymentDate() {
		return this.paymentDate;
	}

	@Enumerated
	private PaymentType paymentType;
	public void setPaymentType(PaymentType value) {
		this.paymentType = value;
	}
	public PaymentType getPaymentType() {
		return this.paymentType;
	}

	@Enumerated
	private DeliveryType deliveryType;
	public void setDeliveryType(DeliveryType value) {
	this.deliveryType = value;
	}
	public DeliveryType getDeliveryType() {
	return this.deliveryType;
	}

	private Address address;
	@ManyToOne(optional=true)
	public Address getAddress() {
	   return this.address;
	}
	public void setAddress(Address address) {
	   this.address = address;
	}

	private Set<Listing> listing;
	@OneToMany(fetch = FetchType.EAGER)
	public Set<Listing> getListing() {
	   return this.listing;
	}
	public void setListing(Set<Listing> listings) {
	   this.listing = listings;
	}

	private Identity identity;
	@ManyToOne(optional=true)
	public Identity getIdentity() {
	   return this.identity;
	}
	public void setIdentity(Identity identity) {
	   this.identity = identity;
	}
}
