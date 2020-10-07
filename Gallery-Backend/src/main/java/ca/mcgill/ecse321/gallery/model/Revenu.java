package ca.mcgill.ecse321.gallery.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Id;

@Entity
public class Revenu{
private int comission;
   
   public void setComission(int value) {
this.comission = value;
    }
public int getComission() {
return this.comission;
    }
private int listingPrice;

public void setListingPrice(int value) {
this.listingPrice = value;
    }
public int getListingPrice() {
return this.listingPrice;
    }
private Account account;

@ManyToOne(optional=false)
public Account getAccount() {
   return this.account;
}

public void setAccount(Account account) {
   this.account = account;
}

private Listing listing;

@ManyToOne(optional=false)
public Listing getListing() {
   return this.listing;
}

public void setListing(Listing listing) {
   this.listing = listing;
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
