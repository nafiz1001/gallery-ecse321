package ca.mcgill.ecse321.gallery.dao;

import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.gallery.model.Account;
import ca.mcgill.ecse321.gallery.model.Identity;
import ca.mcgill.ecse321.gallery.model.Payment;

@Repository
public class IdentityRepository {

	@Autowired
	EntityManager entityManager;
	
	@Transactional
	public Identity createIdentity(String email, Account account) {
		Identity identity = new Identity(email, account);
		entityManager.persist(identity);
		return identity;
	}
	
	@Transactional
	public Identity getIdentity(String email) {
		Identity identity = entityManager.find(Identity.class, email);
		return identity;
	}
	
	@Transactional
	public Identity removeIndentity(String email) {
		Identity identity = entityManager.find(Identity.class, email);
		entityManager.remove(identity);
		return identity;
	}
	
	@Transactional
	public void deleteAll() {
		Query deleteQuery = entityManager.createQuery("DELETE FROM Identity;");
		deleteQuery.executeUpdate();
	}
}
