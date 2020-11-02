package ca.mcgill.ecse321.gallery.dto;

import java.util.Set;

/**
 * 
 * @author halukcalin
 * 
 */
public class ProfileDto {
	private String bio;
	   
	public void setBio(String value) {
		this.bio = value;
    }
	
	public String getBio() {
		return this.bio;
	}
	
	private String picture;
	
	public void setPicture(String value) {
		this.picture = value;
    }
	
	public String getPicture() {
		return this.picture;
    }

	private Set<ListingDto> ListingDtos;
	
	public Set<ListingDto> getListingDtos() {
	   return this.ListingDtos;
	}
	
	public void setListingDtos(Set<ListingDto> ListingDtos) {
	   this.ListingDtos = ListingDtos;
	}
	
	private AccountDto AccountDto;
	
	public AccountDto getAccountDto() {
	   return this.AccountDto;
	}
	
	public void setAccountDto(AccountDto AccountDto) {
	   this.AccountDto = AccountDto;
	}
	
	private String fullname;
	
	public void setFullname(String value) {
		this.fullname = value;
    }

	public String getFullname() {
		return this.fullname;
    }

	private Set<ArtDto> arts;
	
	public Set<ArtDto> getArts() {
	   return this.arts;
	}
	
	public void setArts(Set<ArtDto> artss) {
	   this.arts = artss;
	}
	
	private String id;
	
	public void setId(String value) {
		this.id = value;
    }

	public String getId() {
		return this.id;
    }
}