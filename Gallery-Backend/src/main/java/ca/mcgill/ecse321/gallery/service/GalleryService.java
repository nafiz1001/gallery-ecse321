package ca.mcgill.ecse321.gallery.service;

import java.sql.Time;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.mcgill.ecse321.gallery.dao.AddressRepository;
import ca.mcgill.ecse321.gallery.dao.GalleryRepository;
import ca.mcgill.ecse321.gallery.model.Address;
import ca.mcgill.ecse321.gallery.model.DeliveryType;
import ca.mcgill.ecse321.gallery.model.Gallery;
import ca.mcgill.ecse321.gallery.model.Identity;
import ca.mcgill.ecse321.gallery.model.Listing;
import ca.mcgill.ecse321.gallery.model.Payment;
import ca.mcgill.ecse321.gallery.model.PaymentType;
import ca.mcgill.ecse321.gallery.utils.Utils;

@Service
public class GalleryService {

	@Autowired
	GalleryRepository galleryRepository;

	@Transactional
	public Optional<Gallery> createGallery(String name, String phoneNumber, Time openingTime, Time closingTime,
			String email, int commissionPercentage, Address address) {
		boolean isGalleryValid = true;
		Gallery gallery = null;

		// does phoneNumber have exactly 10 digits?
		if (phoneNumber.length() != 10) {
			isGalleryValid = false;
		}

		// does phoneNumber only contain numbers?
		for (int i = 0; i < phoneNumber.length(); i++) {
			if (Character.isDigit(phoneNumber.charAt(i)) == false) {
				isGalleryValid = false;
			}
		}

		// is closing time before opening time?
		if (openingTime.after(closingTime)) {
			isGalleryValid = false;
		}

		// is email valid?
		isGalleryValid = Utils.isEmailValid(email);

		// is commisionPercentage in between 0 and 100?
		if (commissionPercentage <= 0 || commissionPercentage >= 100) {
			isGalleryValid = false;
		}

		// if Gallery is valid
		if (isGalleryValid) {

			// create Gallery
			Gallery validGallery = new Gallery();
			validGallery.setName(name);
			validGallery.setPhoneNumber(phoneNumber);
			validGallery.setEmail(email);
			validGallery.setOpeningTime(openingTime);
			validGallery.setClosingTime(closingTime);
			validGallery.setCommissionPercentage(commissionPercentage);
			validGallery.setAddress(address);

			galleryRepository.save(validGallery);
		}
		return Optional.ofNullable(gallery);
	}

	@Transactional
	public Optional<Gallery> getGallery(String name) {
		return galleryRepository.findById(name);
	}
}
