package vikas.RatanRaman.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity

public class UserEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	private long userId;
	@Column(name="userName",nullable = false,unique = true)
	private String userName;
	private String userPassword;
	private String userEmail;
	private String userContact;
	private String userRole;
	public UserEntity() {
		super();
	}
	public UserEntity(String userName, String userPassword, String userEmail, String userContact, String userRole) {
		super();
		this.userName = userName;
		this.userPassword = userPassword;
		this.userEmail = userEmail;
		this.userContact = userContact;
		this.userRole = userRole;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getUserContact() {
		return userContact;
	}
	public void setUserContact(String userContact) {
		this.userContact = userContact;
	}
	public String getUserRole() {
		return userRole;
	}
	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
	public long getUserId() {
		return userId;
	}
	
	

}
