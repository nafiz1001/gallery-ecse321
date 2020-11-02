package ca.mcgill.ecse321.gallery.dto;

/**
 * 
 * @author antonianistor
 *
 */
public class IdentityDto {
	private String email;

	public void setEmail(String value) {
		this.email = value;
	}

	public String getEmail() {
		return this.email;
	}

	private AccountDto account;

	public AccountDto getAccount() {
		return this.account;
	}

	public void setAccount(AccountDto account) {
		this.account = account;
	}
}
