package com.campusx.res;

public class UserResponseR {

	private Integer userId;
	private String firstName;
	private String lastName;
	private Long phoneNumber;
	private String password;
	
	/**
	 * Gets the id of the user.
	 * @return An Integer represents id of user.
	 */
	public Integer getUserId() {
		return userId;
	}
	
	/**
	 * Sets the id of the user.
	 * An Integer containing id of the user.
	 * @param vendorId
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	/**
	 * Gets the first name of the user.
	 * @return A string represents first name of the user.
	 */
	public String getFirstName() {
		return firstName;
	}
	
	/**
	 * Sets the first name of the user.
	 * A String containing  first name of the user.
	 * @param firstName
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	/**
	 * Gets the last name of the user.
	 * @return A string represents last name of the user.
	 */
	public String getLastName() {
		return lastName;
	}
	
	/**
	 * Sets the last name of the user.
	 * A String containing last name of the user.
	 * @param lastName
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	/**
	 * Gets the phone number of the user.
	 * @return A Long represents phone Number of the user.
	 */
	public Long getPhoneNumber() {
		return phoneNumber;
	}
	
	/**
	 * Sets the phone number of the user.
	 * A Long containing phone number of the user.
	 * @param phoneNumber
	 */
	public void setPhoneNumber(Long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	/**
	 * Gets the password of the user.
	 * @return A string represents password of the user.
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * Sets the password of the user.
	 * A String containing password of the user.
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
}
