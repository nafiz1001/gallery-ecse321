package ca.mcgill.ecse321.gallery.model;

import javax.persistence.Entity;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import javax.persistence.Id;
import java.sql.Date;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Account{
private String accountHolderType;
   
   public void setAccountHolderType(String value) {
this.accountHolderType = value;
    }
public String getAccountHolderType() {
return this.accountHolderType;
    }
private Identity identity;

@OneToOne
public Identity getIdentity() {
   return this.identity;
}

public void setIdentity(Identity identity) {
   this.identity = identity;
}

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

public void setRevenus(Set<Revenu> revenus) {
   this.revenus = revenus;
}

private String accountNumber;

public void setAccountNumber(String value) {
this.accountNumber = value;
    }
public String getAccountNumber() {
return this.accountNumber;
    }
private String paymentType;

public void setPaymentType(String value) {
this.paymentType = value;
    }
public String getPaymentType() {
return this.paymentType;
    }

}
