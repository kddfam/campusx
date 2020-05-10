package com.campusx.res;

public class VendorResponseR {

	private Integer vendorId;
	private String firstName;
	private String lastName;
	private Long phoneNumber;
	private Integer secretPin;
	private String password;

	/**
	 * Gets the id of the vendor.
	 * @return An Integer represents id of vendor.
	 */
	public Integer getVendorId() {
		return vendorId;
	}
	
	/**
	 * Sets the id of the vendor.
	 * An Integer containing id of the vendor.
	 * @param vendorId
	 */
	public void setVendorId(Integer vendorId) {
		this.vendorId = vendorId;
	}
	
	/**
	 * Gets the first name of the vendor.
	 * @return A string represents first name of the vendor.
	 */
	public String getFirstName() {
		return firstName;
	}
	
	/**
	 * Sets the first name of the vendor.
	 * A String containing  first name of the vendor.
	 * @param firstName
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	/**
	 * Gets the last name of the vendor.
	 * @return A string represents last name of the vendor.
	 */
	public String getLastName() {
		return lastName;
	}
	
	/**
	 * Sets the last name of the vendor.
	 * A String containing last name of the vendor.
	 * @param lastName
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	/**
	 * Gets the phone number of the vendor.
	 * @return A Long represents phone Number of the vendor.
	 */
	public Long getPhoneNumber() {
		return phoneNumber;
	}
	
	/**
	 * Sets the phone number of the vendor.
	 * A Long containing phone number of the vendor.
	 * @param phoneNumber
	 */
	public void setPhoneNumber(Long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	/**
	 * Gets the secret pin of the vendor.
	 * @return An Integer represents secret pin of vendor.
	 */
	public Integer getSecretPin() {
		return secretPin;
	}
	
	/**
	 * Sets the secret pin of the vendor.
	 * An Integer containing secret pin of the vendor.
	 * @param secretPin
	 */
	public void setSecretPin(Integer secretPin) {
		this.secretPin = secretPin;
	}
	
	/**
	 * Gets the password of the vendor.
	 * @return A string represents password of the vendor.
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * Sets the password of the vendor.
	 * A String containing password of the vendor.
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

}
