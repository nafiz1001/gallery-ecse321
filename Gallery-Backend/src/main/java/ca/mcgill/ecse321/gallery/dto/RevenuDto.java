package ca.mcgill.ecse321.gallery.dto;

public class RevenuDto{
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

   private AccountDto account;

   public AccountDto getAccount() {
	   return this.account;
   }

	public void setAccount(AccountDto account) {
	   this.account = account;
	}
	
	private ListingDto listing;
	
	public ListingDto getListing() {
	   return this.listing;
	}
	
	public void setListing(ListingDto listing) {
	   this.listing = listing;
	}
	
	private String id;
	
	public void setId(String value) {
		this.id = value;
	}

	public String getId() {
		return this.id;
    }
 }
