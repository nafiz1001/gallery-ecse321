package ca.mcgill.ecse321.gallery.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import javax.persistence.Id;
import java.sql.Date;
import javax.persistence.ManyToOne;

@Entity
public class Account extends Identity{
private Set<Profile> profile;

@OneToMany(mappedBy="account", cascade={CascadeType.ALL})
public Set<Profile> getProfile() {
   return this.profile;
}

public void setProfile(Set<Profile> profiles) {
   this.profile = profiles;
}

private String username;

public void setUsername(String value) {
this.username = value;
    }
@Id
public String getUsername() {
return this.username;
    }
private String password;

public void setPassword(String value) {
this.password = value;
    }
public String getPassword() {
return this.password;
    }
private Date dateJoined;

public void setDateJoined(Date value) {
this.dateJoined = value;
    }
public Date getDateJoined() {
return this.dateJoined;
    }

@Enumerated(EnumType.ORDINAL)
private AccountHolderType accountHolderType;

public void setAccountHolderType(AccountHolderType value) {
this.accountHolderType = value;
    }
public AccountHolderType getAccountHolderType() {
return this.accountHolderType;
    }
private Address address;

@ManyToOne(optional=false)
public Address getAddress() {
   return this.address;
}

public void setAddress(Address address) {
   this.address = address;
}

private Date dateOfBirth;

public void setDateOfBirth(Date value) {
this.dateOfBirth = value;
    }
public Date getDateOfBirth() {
return this.dateOfBirth;
    }
private Set<Revenu> revenus;

@OneToMany(mappedBy="account")
public Set<Revenu> getRevenus() {
   return this.revenus;
}

public void setRevenus(Set<Revenu> revenuss) {
   this.revenus = revenuss;
}

private String accountNumber;

public void setAccountNumber(String value) {
this.accountNumber = value;
    }
public String getAccountNumber() {
return this.accountNumber;
    }

@Enumerated(EnumType.ORDINAL)
private PaymentType paymentType;

public void setPaymentType(PaymentType value) {
this.paymentType = value;
    }
public PaymentType getPaymentType() {
return this.paymentType;
       }
   }
