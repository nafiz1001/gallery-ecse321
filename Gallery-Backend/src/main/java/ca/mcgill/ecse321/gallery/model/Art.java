package ca.mcgill.ecse321.gallery.model;

import javax.persistence.Entity;
import javax.persistence.CascadeType;
import javax.persistence.OneToOne;
import javax.persistence.ManyToOne;
import javax.persistence.Id;

@Entity
public class Art{
private String name;

public void setName(String value) {
   this.name = value;
}

public String getName() {
   return this.name;
}

private String description;

public void setDescription(String value) {
   this.description = value;
}

public String getDescription() {
   return this.description;
}

private double height;

public void setHeight(double value) {
this.height = value;
    }
public double getHeight() {
return this.height;
    }
private double width;

public void setWidth(double value) {
this.width = value;
    }
public double getWidth() {
return this.width;
    }
private String image;

public void setImage(String value) {
this.image = value;
    }
public String getImage() {
return this.image;
    }
private Listing listing;

@OneToOne(mappedBy="art", cascade={CascadeType.ALL})
public Listing getListing() {
   return this.listing;
}

public void setListing(Listing listing) {
   this.listing = listing;
}

private String license;

public void setLicense(String value) {
this.license = value;
    }
public String getLicense() {
return this.license;
    }
private Profile author;

@ManyToOne(optional=false)
public Profile getAuthor() {
   return this.author;
}

public void setAuthor(Profile author) {
   this.author = author;
}

private String type;

public void setType(String value) {
this.type = value;
    }
public String getType() {
return this.type;
    }
private String depth;

public void setDepth(String value) {
this.depth = value;
    }
public String getDepth() {
return this.depth;
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
