package ca.mcgill.ecse321.gallery.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;
import javax.persistence.ManyToOne;
import java.util.Set;
import javax.persistence.OneToMany;

@Entity
public class Payment{
private String confirmationNumber;
   
   public void setConfirmationNumber(String value) {
this.confirmationNumber = value;
    }
@Id
public String getConfirmationNumber() {
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
private String paymentType;

public void setPaymentType(String value) {
this.paymentType = value;
    }
public String getPaymentType() {
return this.paymentType;
    }
private String deliveryType;

public void setDeliveryType(String value) {
this.deliveryType = value;
    }
public String getDeliveryType() {
return this.deliveryType;
    }
private Address address;

@ManyToOne(optional=false)
public Address getAddress() {
   return this.address;
}

public void setAddress(Address address) {
   this.address = address;
}

private Set<Listing> listing;

@OneToMany
public Set<Listing> getListing() {
   return this.listing;
}

public void setListing(Set<Listing> listings) {
   this.listing = listings;
}

private Identity identity;

@ManyToOne(optional=false)
public Identity getIdentity() {
   return this.identity;
}

public void setIdentity(Identity identity) {
   this.identity = identity;
}

}
