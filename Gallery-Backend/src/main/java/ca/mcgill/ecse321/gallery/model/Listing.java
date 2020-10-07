package ca.mcgill.ecse321.gallery.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;
import javax.persistence.OneToOne;
import javax.persistence.ManyToOne;

@Entity
public class Listing{
private String id;
   
   public void setId(String value) {
this.id = value;
    }
@Id
public String getId() {
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
private Art art;

@OneToOne(optional=false)
public Art getArt() {
   return this.art;
}

public void setArt(Art art) {
   this.art = art;
}

private Profile publisher;

@ManyToOne(optional=false)
public Profile getPublisher() {
   return this.publisher;
}

public void setPublisher(Profile publisher) {
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
