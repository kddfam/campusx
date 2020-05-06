package com.campusx.mdl;

import java.time.LocalDateTime;

/**
 * @author campusx
 * @author https://campusx.com
 * @version 1.0
 * @since 2020-05-06
 *
 */
public class Otp {

	private Integer otpId;
	private Integer otp;
	private LocalDateTime addTimestamp;
	private LocalDateTime expiryTimestamp;
	private LocalDateTime useTimestamp;
	private Long generatedFor;
	private Boolean status;
	
	/**
	 * Gets the otp id
	 * @return An integer represents otp id.
	 */
	public Integer getOtpId() {
		return otpId;
	}
	
	/**
	 * Sets the otp id
	 * An Integer containing otp id
	 * @param otpId
	 */
	public void setOtpId(Integer otpId) {
		this.otpId = otpId;
	}
	
	/**
	 * Gets the otp itself
	 * @return An Integer represents otp.
	 */
	public Integer getOtp() {
		return otp;
	}
	
	/**
	 * Sets the otp value.
	 * An integer containing otp.
	 * @param otp
	 */
	public void setOtp(Integer otp) {
		this.otp = otp;
	}
	
	/**
	 * Gets the add timestamp of otp
	 * @return A LocalDateTime object represents add timestamp of otp
	 */
	public LocalDateTime getAddTimestamp() {
		return addTimestamp;
	}
	
	/**
	 * Sets the add timestamp for otp.
	 * A LocalDateTime object containing otp add timestamp.
	 * @param addTimestamp
	 */
	public void setAddTimestamp(LocalDateTime addTimestamp) {
		this.addTimestamp = addTimestamp;
	}
	
	/**
	 * Gets the expiry timestamp of otp
	 * @return A LocalDateTime object represents expiry timestamp of otp
	 */
	public LocalDateTime getExpiryTimestamp() {
		return expiryTimestamp;
	}
	
	/**
	 * Sets the expiry timestamp of otp
	 * A LocalDateTime object containing expiry timestamp of otp
	 * @param expiryTimestamp
	 */
	public void setExpiryTimestamp(LocalDateTime expiryTimestamp) {
		this.expiryTimestamp = expiryTimestamp;
	}
	
	/**
	 * Gets the use timestamp of otp
	 * @return A LocalDateTime object represents use timestamp of otp
	 */
	public LocalDateTime getUseTimestamp() {
		return useTimestamp;
	}
	
	/**
	 * Sets the use timestamp of otp
	 * A LocalDateTime object containing use timestamp of otp.
	 * @param useTimestamp
	 */
	public void setUseTimestamp(LocalDateTime useTimestamp) {
		this.useTimestamp = useTimestamp;
	}
	
	/**
	 * Gets the phone number for which otp is generated
	 * @return A Long value represents phone number for which otp is generated.
	 */
	public Long getGeneratedFor() {
		return generatedFor;
	}
	
	/**
	 * Sets the phone number for which otp is generated
	 * A Long value containing phone number for which otp is generated.
	 * @param generatedFor
	 */
	public void setGeneratedFor(Long generatedFor) {
		this.generatedFor = generatedFor;
	}
	
	/**
	 * Gets the usage status of otp
	 * @return A boolean value represents the status of otp
	 */
	public Boolean getStatus() {
		return status;
	}
	
	/**
	 * Sets the status of otp
	 * A boolean value containing status of otp
	 * @param status
	 */
	public void setStatus(Boolean status) {
		this.status = status;
	}
	
}
