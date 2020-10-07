package ca.mcgill.ecse321.gallery.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Set;
import javax.persistence.OneToMany;

@Entity
public class Identity{
private String email;
   
   public void setEmail(String value) {
this.email = value;
    }
@Id
public String getEmail() {
return this.email;
    }
private Set<Payment> payment;

@OneToMany(mappedBy="identity")
public Set<Payment> getPayment() {
   return this.payment;
}

public void setPayment(Set<Payment> payments) {
   this.payment = payments;
}

}
