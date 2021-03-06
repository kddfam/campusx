package com.campusx.ety;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
@Table(name="kyc")
public class KycEntity {

	@Id
	@SequenceGenerator(name="kyc_id",sequenceName="kyc_sequence",allocationSize=1)
	@GeneratedValue(generator="kyc_id",strategy=GenerationType.SEQUENCE)
	private Integer kycId;
	private String gstNumber;
	private LocalDateTime kycIssueTimestamp;
	
	@OneToOne(cascade=CascadeType.PERSIST)
	@JoinColumn(name="shop_id")
	private ShopEntity shop;
	
	/** Gets the vendor's KYC id.
	 * @return An Integer represents vendor's KYC id.
	 */
	public Integer getKycId() {
		return kycId;
	}
	
	/** Sets the vendor's KYC id.
	 * An Integer containing the vendor's KYC id.
	 * @param kycId
	 */
	public void setKycId(Integer kycId) {
		this.kycId = kycId;
	}
	
	/** Gets the vendor's GST Number
	 * @return A String represents vendor's GST Number
	 */
	public String getGstNumber() {
		return gstNumber;
	}
	
	/** Sets the vendor's GST Number.
	 * A string containing vendor's GST Number
	 * @param gstNumber
	 */
	public void setGstNumber(String gstNumber) {
		this.gstNumber = gstNumber;
	}
	
	/** Gets the vendor's KYC issue timestamp.
	 * @return A timestamp of vendor's kyc issue.
	 */
	public LocalDateTime getKycIssueTimestamp() {
		return kycIssueTimestamp;
	}
	
	/** Sets the vendor's KYC issue timestamp.
	 * A timestamp containing vendor's KYC issue information.
	 * @param kycIssueTimestamp
	 */
	public void setKycIssueTimestamp(LocalDateTime kycIssueTimestamp) {
		this.kycIssueTimestamp = kycIssueTimestamp;
	}

	/**
	 * Gets the shop for the kyc.
	 * @return An object refering to Shop
	 */
	public ShopEntity getShop() {
		return shop;
	}

	/**
	 * Sets the values for shop.
	 * An object containing values for the shop.
	 * @param shop
	 */
	public void setShop(ShopEntity shop) {
		this.shop = shop;
	}
	
	
	
}
