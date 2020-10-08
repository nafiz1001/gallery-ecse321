package ca.mcgill.ecse321.gallery.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

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
private Account account;

@OneToOne(mappedBy="identity", optional=false)
public Account getAccount() {
   return this.account;
}

public void setAccount(Account account) {
   this.account = account;
}

}
