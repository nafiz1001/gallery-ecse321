/**
 * @author BbananasS
 * 
 */
package ca.mcgill.ecse321.gallery.dto;

import java.sql.Time;

import javax.persistence.Id;
import javax.persistence.ManyToOne;

import ca.mcgill.ecse321.gallery.model.Address;

/**
 * 
 * @author BbananasS
 *
 */
public class GalleryDto {
	
	private String name;
	   
	public void setName(String value) {
		this.name = value;
	}
	
	public String getName() {
		return this.name;
	}
	
	private String phoneNumber;

	public void setPhoneNumber(String value) {
		this.phoneNumber = value;
	}
	
	public String getPhoneNumber() {
		return this.phoneNumber;
	}
	
	private Time openingTime;

	public void setOpeningTime(Time value) {
		this.openingTime = value;
	}
	
	public Time getOpeningTime() {
		return this.openingTime;
	}
	
	private Time closingTime;

	public void setClosingTime(Time value) {
		this.closingTime = value;
	}
	
	public Time getClosingTime() {
		return this.closingTime;
	}
	
	private String email;

	public void setEmail(String value) {
		this.email = value;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	private AddressDto address;

	public AddressDto getAddress() {
	   return this.address;
	}

	public void setAddress(AddressDto address) {
	   this.address = address;
	}

	private int commissionPercentage;

	public void setCommissionPercentage(int value) {
		this.commissionPercentage = value;
	}
	
	public int getCommissionPercentage() {
		return this.commissionPercentage;
	}
}
