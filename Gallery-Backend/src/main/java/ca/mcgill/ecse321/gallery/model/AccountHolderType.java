package ca.mcgill.ecse321.gallery.model;

import javax.persistence.Entity;

@Entity
public enum AccountHolderType{
	CLIENT, 
	ARTIST,
	GALLERY
}
