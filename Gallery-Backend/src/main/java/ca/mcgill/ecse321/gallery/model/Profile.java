package ca.mcgill.ecse321.gallery.model;

import javax.persistence.Entity;
import java.util.Set;
import javax.persistence.OneToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Id;

@Entity
public class Profile{
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
private Set<Listing> listings;

@OneToMany(mappedBy="publisher")
public Set<Listing> getListings() {
   return this.listings;
}

public void setListings(Set<Listing> listingss) {
   this.listings = listingss;
}

private Account account;

@ManyToOne(optional=true)
public Account getAccount() {
   return this.account;
}

public void setAccount(Account account) {
   this.account = account;
}

private String fullname;

public void setFullname(String value) {
this.fullname = value;
    }
public String getFullname() {
return this.fullname;
    }
private Set<Art> arts;

@OneToMany(mappedBy="owner")
public Set<Art> getArts() {
   return this.arts;
}

public void setArts(Set<Art> artss) {
   this.arts = artss;
}

private String id;

public void setId(String value) {
this.id = value;
    }
@Id
public String getId() {
return this.id;
       }
   }
