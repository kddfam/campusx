package com.campusx.ety;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * 
 * @author campusx
 * @author https://campusx.com
 * @version 1.0
 * @since 2020-05-02
 *
 */
@Entity
@Table(name="vendor")
public class VendorEntity {

	@Id
	@SequenceGenerator(name="vendor_id",sequenceName="vendor_sequence",allocationSize=1)
	@GeneratedValue(generator="vendor_id",strategy=GenerationType.SEQUENCE)
	private Integer vendorId;
	private String firstName;
	private String lastName;
	private Long phoneNumber;
	private Integer secretPin;
	private String password;
	private LocalDateTime addTimestamp;
	private LocalDateTime secretPinLastUpdateTimestamp;
	private LocalDateTime passwordLastUpdateTimestamp;
	private Boolean accountStatus;
	private Boolean isAccountClosed;
	@OneToOne(mappedBy="vendor")
	private ShopEntity shop;
	
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
	
	/**
	 * Gets the add timestamp of the shop
	 * @return A LocalDateTime represents the add timestamp of shop.
	 */
	public LocalDateTime getAddTimestamp() {
		return addTimestamp;
	}
	
	/**
	 * Sets the add timestamp of the shop.
	 * A LocalDateTime contains the add time stamp of shop.
	 * @param addTimestamp
	 */
	public void setAddTimestamp(LocalDateTime addTimestamp) {
		this.addTimestamp = addTimestamp;
	}
	
	/**
	 * Gets the secret pin last update timestamp of the shop
	 * @return A LocalDateTime represents the secret pin last update timestamp of shop.
	 */
	public LocalDateTime getSecretPinLastUpdateTimestamp() {
		return secretPinLastUpdateTimestamp;
	}
	
	/**
	 * Sets the secret pin last update timestamp of the shop.
	 * A LocalDateTime contains the secret pin last update time stamp of shop.
	 * @param secretPinLastUpdateTimestamp
	 */
	public void setSecretPinLastUpdateTimestamp(LocalDateTime secretPinLastUpdateTimestamp) {
		this.secretPinLastUpdateTimestamp = secretPinLastUpdateTimestamp;
	}
	
	/**
	 * Gets the password last update timestamp of the shop
	 * @return A LocalDateTime represents the password last update timestamp of shop.
	 */
	public LocalDateTime getPasswordLastUpdateTimestamp() {
		return passwordLastUpdateTimestamp;
	}
	
	/**
	 * Sets the password last update timestamp of the shop.
	 * A LocalDateTime contains the password last update time stamp of shop.
	 * @param passwordLastUpdateTimestamp
	 */
	public void setPasswordLastUpdateTimestamp(LocalDateTime passwordLastUpdateTimestamp) {
		this.passwordLastUpdateTimestamp = passwordLastUpdateTimestamp;
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
	 * Sets the close account status of the vendor.
	 * A boolean containing close status of account.
	 * @param isAccountClosed
	 */
	public void setIsAccountClosed(Boolean isAccountClosed) {
		this.isAccountClosed = isAccountClosed;
	}

	/**
	 * Gets the object of shop 
	 * @return An object represents shop of the vendor.
	 */
	public ShopEntity getShop() {
		return shop;
	}
	
	/**
	 * Sets the new object of the shop respected to vendor.
	 * An object of shop for a specific vendor.
	 * @param shop
	 */
	public void setShop(ShopEntity shop) {
		this.shop = shop;
	}
	
}
