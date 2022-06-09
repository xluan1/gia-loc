package com.gialoc.springboot.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gialoc.springboot.model.enums.RoleUser;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "users")
public class User {
	@Id
	private String id;
	@JsonFormat(shape = JsonFormat.Shape.NUMBER)
	private Date createdAt;
	@JsonFormat(shape = JsonFormat.Shape.NUMBER)
	private Date updatedAt;

	private String userName;
	private String email;
	private String password;
	private String firstName;
	private String lastName;
	private String phoneNumber;
	private String country; // (quốc gia)
	private String province; // or city (tỉnh)
	private String town; // or district (huyện)
	private String village; // or ward (xã)
	private String hamlet; // or street (thôn)
	private boolean active;
	@Enumerated(EnumType.STRING)
	private RoleUser roleUser;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getTown() {
		return town;
	}

	public void setTown(String town) {
		this.town = town;
	}

	public String getVillage() {
		return village;
	}

	public void setVillage(String village) {
		this.village = village;
	}

	public String getHamlet() {
		return hamlet;
	}

	public void setHamlet(String hamlet) {
		this.hamlet = hamlet;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public RoleUser getRoleUser() {
		return roleUser;
	}

	public void setRoleUser(RoleUser roleUser) {
		this.roleUser = roleUser;
	}
}
