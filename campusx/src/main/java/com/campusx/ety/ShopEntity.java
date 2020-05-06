package com.campusx.ety;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
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
@Table(name="shop")
public class ShopEntity {
	
	@Id
	@SequenceGenerator(name="shop_id",sequenceName="shop_sequence",allocationSize=1)
	@GeneratedValue(generator="shop_id",strategy=GenerationType.SEQUENCE)
	private Integer shopId;
	private String name;
	private Long officialPhoneNumber;
	private String officialEmailId;
	private String shopPicture;
	private Integer numberOfItems;
	private Double averagePrice;
	private Float shopRating;
	private LocalDateTime addTimestamp;
	private LocalDateTime lastUpdateTimestamp;
	private Boolean status;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="address_id")
	private AddressEntity address;
	
	@OneToOne(mappedBy="shop")
	private KycEntity kyc;
	
	@OneToOne(cascade= {CascadeType.REFRESH,CascadeType.PERSIST})
	@JoinColumn(name="vendor_id")
	private VendorEntity vendor;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="shop_id")
	private List<ItemEntity> items;
	
	/**
	 * Gets the id of the shop.
	 * @return An Integer represents id of shop.
	 */
	public Integer getShopId() {
		return shopId;
	}
	
	/**
	 * Sets the id of the shop.
	 * An Integer containing id of the shop.
	 * @param shopId
	 */
	public void setShopId(Integer shopId) {
		this.shopId = shopId;
	}
	
	/**
	 * Gets the name of the shop.
	 * @return A string represents name of the shop.
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Sets the name of the shop.
	 * A String containing name of the shop.
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Gets the official phone number of the shop.
	 * @return A Long represents official Phone Number of the shop.
	 */
	public Long getOfficialPhoneNumber() {
		return officialPhoneNumber;
	}
	
	/**
	 * Sets the official phone number of the shop.
	 * A Long containing official phone number of the shop.
	 * @param officialPhoneNumber
	 */
	public void setOfficialPhoneNumber(Long officialPhoneNumber) {
		this.officialPhoneNumber = officialPhoneNumber;
	}
	
	/**
	 * Gets the official email id of the shop.
	 * @return A string represents official email id of the shop.
	 */
	public String getOfficialEmailId() {
		return officialEmailId;
	}
	
	/**
	 * Sets the official email id of the shop.
	 * A String containing official email id of the shop.
	 * @param officialEmailId
	 */
	public void setOfficialEmailId(String officialEmailId) {
		this.officialEmailId = officialEmailId;
	}
	
	/**
	 * Gets the picture of the shop.
	 * @return A string represents picture of the shop.
	 */
	public String getShopPicture() {
		return shopPicture;
	}
	
	/**
	 * Sets the picture of the shop.
	 * A String containing path of the picture of the shop.
	 * @param shopPicture
	 */
	public void setShopPicture(String shopPicture) {
		this.shopPicture = shopPicture;
	}
	
	/**
	 * Gets the number of items of the shop.
	 * @return An Integer represents number of items of shop.
	 */
	public Integer getNumberOfItems() {
		return numberOfItems;
	}
	
	/**
	 * Sets the number of items of the shop.
	 * An Integer containing number of items of the shop.
	 * @param numberOfItems
	 */
	public void setNumberOfItems(Integer numberOfItems) {
		this.numberOfItems = numberOfItems;
	}
	
	/**
	 * Gets the average price of items of the shop.
	 * @return A Double represents average price of items of the shop.
	 */
	public Double getAveragePrice() {
		return averagePrice;
	}
	
	/**
	 * Sets the average price of items of the shop.
	 * A Long containing average price of items of the shop.
	 * @param averagePrice
	 */
	public void setAveragePrice(Double averagePrice) {
		this.averagePrice = averagePrice;
	}
	
	/**
	 * Gets the rating of the shop.
	 * @return A Float represents rating of the shop.
	 */
	public Float getShopRating() {
		return shopRating;
	}
	
	/**
	 * Sets the rating of the shop.
	 * A Float containing rating of the shop.
	 * @param shopRating
	 */
	public void setShopRating(Float shopRating) {
		this.shopRating = shopRating;
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
	 * Gets the last update timestamp of the shop
	 * @return A LocalDateTime represents the last update timestamp of shop.
	 */
	public LocalDateTime getLastUpdateTimestamp() {
		return lastUpdateTimestamp;
	}
	
	/**
	 * Sets the last update timestamp of the shop.
	 * A LocalDateTime contains the last update time stamp of shop.
	 * @param addTimestamp
	 */
	public void setLastUpdateTimestamp(LocalDateTime lastUpdateTimestamp) {
		this.lastUpdateTimestamp = lastUpdateTimestamp;
	}
	
	/**
	 * Gets the status of the shop.
	 * @return A boolean represents status of the shop.
	 */
	public Boolean getStatus() {
		return status;
	}

	/**
	 * Sets the status of the shop.
	 * A boolean containing status of the shop.
	 * @param status
	 */
	public void setStatus(Boolean status) {
		this.status = status;
	}

	/**
	 * Gets the address for the shop
	 * @return An object represents Address for the shop
	 */
	public AddressEntity getAddress() {
		return address;
	}
	
	/**
	 * Sets the address value for the shop.
	 * An object of address containing value of address for the shop.
	 * @param address
	 */
	public void setAddress(AddressEntity address) {
		this.address = address;
	}
	
	/**
	 * Gets the kyc for the shop
	 * @return An object represents Kyc for the shop
	 */
	public KycEntity getKyc() {
		return kyc;
	}
	
	/**
	 * Sets the kyc value for the shop.
	 * An object of kyc containing value of kyc for the shop.
	 * @param kyc
	 */
	public void setKyc(KycEntity kyc) {
		this.kyc = kyc;
	}
	
	/**
	 * Gets the object of vendor
	 * @return An object pointing to the vendor
	 */
	public VendorEntity getVendor() {
		return vendor;
	}

	/**
	 * Sets the value of the vendor
	 * An object containing value for vendor
	 * @param vendor 
	 */
	public void setVendor(VendorEntity vendor) {
		this.vendor = vendor;
	}
	
	/**
	 * Gets the list of all the items added by the vendor.
	 * @return A list of items added by vendor.
	 */
	public List<ItemEntity> getItems() {
		return items;
	}
	
	/**
	 * Sets the new items into the list.
	 * An object of Item model.
	 * @param items
	 */
	public void setItems(List<ItemEntity> items) {
		this.items = items;
	}

}
