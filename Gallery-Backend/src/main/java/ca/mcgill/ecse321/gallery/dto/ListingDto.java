package ca.mcgill.ecse321.gallery.dto;

import java.sql.Date;

import javax.persistence.ManyToOne;

import ca.mcgill.ecse321.gallery.model.Art;
import ca.mcgill.ecse321.gallery.model.Profile;

/**
 * 
 * @author antonianistor
 *
 */
public class ListingDto {

	private long id;

	public void setId(long value) {
		this.id = value;
	}

	public long getId() {
		return this.id;
	}

	private int price;

	public void setPrice(int value) {
		this.price = value;
	}

	public int getPrice() {
		return this.price;
	}

	private Date datePublished;

	public void setDatePublished(Date value) {
		this.datePublished = value;
	}

	public Date getDatePublished() {
		return this.datePublished;
	}

	private boolean canPickUp;

	public void setCanPickUp(boolean value) {
		this.canPickUp = value;
	}

	public boolean isCanPickUp() {
		return this.canPickUp;
	}

	private boolean canDeliver;

	public void setCanDeliver(boolean value) {
		this.canDeliver = value;
	}

	public boolean isCanDeliver() {
		return this.canDeliver;
	}

	private int quantity;

	public void setQuantity(int value) {
		this.quantity = value;
	}

	public int getQuantity() {
		return this.quantity;
	}

	private ArtDto art;

	public ArtDto getArt() {
		return this.art;
	}

	public void setArt(ArtDto art) {
		this.art = art;
	}

	private ProfileDto publisher;

	public ProfileDto getPublisher() {
		return this.publisher;
	}

	public void setPublisher(ProfileDto publisher) {
		this.publisher = publisher;
	}

	private String tags;

	public void setTags(String value) {
		this.tags = value;
	}

	public String getTags() {
		return this.tags;
	}

}
