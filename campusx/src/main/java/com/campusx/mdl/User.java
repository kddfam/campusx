package com.campusx.mdl;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 
 * @author campusx
 * @author https://campusx.com
 * @version 1.0
 * @since 2020-05-05
 *
 */
public class User {

	private Integer userId;
	private String firstName;
	private String lastName;
	private LocalDate dateOfBirth;
	private Long phoneNumber;
	private String password;
	private String profilePicture;
	private LocalDateTime addTimestamp;
	private LocalDateTime lastUpdateTimestamp;
	private Boolean accountStatus;
	private Boolean isAccountClosed;
	
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
	 * Gets the date of birth of user.
	 * @return A LocalDate object represents date of birth of user.
	 */
	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}
	
	/**
	 * Sets the value of data of birth of user.
	 * A LocalDate object containing value of DOB of user.
	 * @param dateOfBirth
	 */
	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
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
	
	/**
	 * Gets the Profile picture of the user.
	 * @return A string representing path of user's profile picture stored in FS.
	 */
	public String getProfilePicture() {
		return profilePicture;
	}
	
	/**
	 * Sets the profile picture of the user.
	 * A string containing path of the user's profile picture stored in FS.
	 * @param profilePicture
	 */
	public void setProfilePicture(String profilePicture) {
		this.profilePicture = profilePicture;
	}
	
	/**
	 * Gets the add timestamp of the user
	 * @return A LocalDateTime represents the add timestamp of user.
	 */
	public LocalDateTime getAddTimestamp() {
		return addTimestamp;
	}
	
	/**
	 * Sets the add timestamp of the user.
	 * A LocalDateTime contains the add time stamp of user.
	 * @param addTimestamp
	 */
	public void setAddTimestamp(LocalDateTime addTimestamp) {
		this.addTimestamp = addTimestamp;
	}
	
	/**
	 * Gets the last update timestamp of the user
	 * @return A LocalDateTime represents the last update timestamp of user.
	 */
	public LocalDateTime getLastUpdateTimestamp() {
		return lastUpdateTimestamp;
	}
	
	/**
	 * Sets the last update timestamp of the user.
	 * A LocalDateTime contains the last update time stamp of user.
	 * @param secretPinLastUpdateTimestamp
	 */
	public void setLastUpdateTimestamp(LocalDateTime lastUpdateTimestamp) {
		this.lastUpdateTimestamp = lastUpdateTimestamp;
	}
	
	/**
	 * Gets the status of the account
	 * @return A boolean represent status of the account.
	 */
	public Boolean getAccountStatus() {
		return accountStatus;
	}

	/**
	 * Sets the status of the account.
	 * A boolean containing status of the account.
	 * @param accountStatus
	 */
	public void setAccountStatus(Boolean accountStatus) {
		this.accountStatus = accountStatus;
	}

	/**
	 * Gets the close status of the account.
	 * @return A boolean represents close status of the account.
	 */
	public Boolean getIsAccountClosed() {
		return isAccountClosed;
	}

	/**
	 * Sets the close account status of the user.
	 * A boolean containing close status of account.
	 * @param isAccountClosed
	 */
	public void setIsAccountClosed(Boolean isAccountClosed) {
		this.isAccountClosed = isAccountClosed;
	}
	
}
