package ca.mcgill.ecse321.gallery.dto;

import java.sql.Date;
import java.util.Set;

public class AccountDto{
	private String accountHolderType;
	   
	public void setAccountHolderType(String value) {
		this.accountHolderType = value;
    }

	public String getAccountHolderType() {
		return this.accountHolderType;
	}
	
	private IdentityDto identity;
	
	public IdentityDto getIdentity() {
	   return this.identity;
	}
	
	public void setIdentity(IdentityDto identity) {
	   this.identity = identity;
	}
	
	private Set<ProfileDto> profile;
	
	public Set<ProfileDto> getProfile() {
	   return this.profile;
	}
	
	public void setProfile(Set<ProfileDto> profiles) {
	   this.profile = profiles;
	}
	
	private String username;
	
	public void setUsername(String value) {
		this.username = value;
    }
	
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

	private AddressDto address;
	
	public AddressDto getAddress() {
	   return this.address;
	}
	
	public void setAddress(AddressDto address) {
	   this.address = address;
	}
	
	private Date dateOfBirth;
	
	public void setDateOfBirth(Date value) {
		this.dateOfBirth = value;
    }
	
	public Date getDateOfBirth() {
		return this.dateOfBirth;
    }
	
	private Set<RevenuDto> revenus;
	
	
	public Set<RevenuDto> getRevenus() {
	   return this.revenus;
	}
	
	public void setRevenus(Set<RevenuDto> revenus) {
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
