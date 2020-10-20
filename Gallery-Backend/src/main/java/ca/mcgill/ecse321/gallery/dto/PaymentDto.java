package ca.mcgill.ecse321.gallery.dto;

import java.sql.Date;
import java.util.Set;

import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import ca.mcgill.ecse321.gallery.model.DeliveryType;
import ca.mcgill.ecse321.gallery.model.PaymentType;

public class PaymentDto {
	private long confirmationNumber;
	public void setConfirmationNumber(long value) {
	   this.confirmationNumber = value;
    }
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

	private PaymentType paymentType;
	public void setPaymentType(PaymentType value) {
		this.paymentType = value;
	}
	public PaymentType getPaymentType() {
		return this.paymentType;
	}

	private DeliveryType deliveryType;
	public void setDeliveryType(DeliveryType value) {
	this.deliveryType = value;
	}
	public DeliveryType getDeliveryType() {
	return this.deliveryType;
	}

	private AddressDto address;
	public AddressDto getAddress() {
	   return this.address;
	}
	public void setAddress(AddressDto address) {
	   this.address = address;
	}

	private Set<ListingDto> Seting;
	public Set<ListingDto> getSeting() {
	   return this.Seting;
	}
	public void setSeting(Set<ListingDto> Setings) {
	   this.Seting = Setings;
	}

	private IdentityDto identity;
	public IdentityDto getIdentity() {
	   return this.identity;
	}
	public void setIdentity(IdentityDto identity) {
	   this.identity = identity;
	}
}
