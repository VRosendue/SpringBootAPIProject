package com.boxinator.models;

import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="users")
public class Users {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;

	@Column(nullable = false)
	private String firstName;

	@Column(nullable = false)
	private String lastName;

	@Column(nullable = false, unique = true)
	private String email;

	@Column(nullable = false)
	private String password;

	@Column
	private String dateOfBirth;

	@Column
	private String userCountry;

	@Column
	private String userZipCode; 

	@Column
	private String phoneNumber;
	
	@Basic
	@Temporal(TemporalType.DATE)
	private Date created_at;


	public boolean findByEmail() {
		if (getEmail()==this.email) {
			return true;
		}else{
			return false;
		}
	}


	public Long getUserId() {
		return userId;
	}


	public void setUserId(Long userId) {
		this.userId = userId;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getDateOfBirth() {
		return dateOfBirth;
	}


	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}


	public String getUserCountry() {
		return userCountry;
	}


	public void setUserCountry(String userCountry) {
		this.userCountry = userCountry;
	}


	public String getUserZipCode() {
		return userZipCode;
	}


	public void setUserZipCode(String userZipCode) {
		this.userZipCode = userZipCode;
	}


	public String getPhoneNumber() {
		return phoneNumber;
	}


	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}


	
	 public Date getCreated_at() { return created_at; }
	  
	  
	  public void setCreated_at(Date created_at) { this.created_at = created_at; }
	 
}
