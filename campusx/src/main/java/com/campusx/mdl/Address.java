package com.campusx.mdl;

/**
 * 
 * @author campusx
 * @author https://campusx.com
 * @version 1.0
 * @since 2020-05-02
 *
 */
public class Address {

	private Integer addressId;
	private String street;
	private String city;
	private String state;
	private String country;
	private Integer zipCode;
	private Float geoLat;
	private Float geoLang;
	private Shop shop;
	
	/** Gets the vendor's address id.
	 * @return An Integer represents the vendor's address id. 
	 */
	public Integer getAddressId() {
		return addressId;
	}
	
	/** Sets the vendor's address id.
	 * An Integer containing the vendor's address id.
	 * @param addressId
	 */
	public void setAddressId(Integer addressId) {
		this.addressId = addressId;
	}
	
	/** Gets the vendor's street.
	 * @return A String represents the vendor's street. 
	 */
	public String getStreet() {
		return street;
	}
	
	/** Sets the vendor's street.
	 * A string containing the vendor's street.
	 * @param street
	 */
	public void setStreet(String street) {
		this.street = street;
	}
	
	/** Gets the vendor's city.
	 * @return A String represents the vendor's city. 
	 */
	public String getCity() {
		return city;
	}
	
	/** Sets the vendor's city.
	 * A string containing the vendor's city.
	 * @param city
	 */
	public void setCity(String city) {
		this.city = city;
	}
	
	/** Gets the vendor's state.
	 * @return A String represents the vendor's state. 
	 */
	public String getState() {
		return state;
	}
	
	/** Sets the vendor's state.
	 * A string containing the vendor's state.
	 * @param state
	 */
	public void setState(String state) {
		this.state = state;
	}
	
	/** Gets the vendor's country.
	 * @return A String represents the vendor's country. 
	 */
	public String getCountry() {
		return country;
	}
	
	/** Sets the vendor's country.
	 * A string containing the vendor's country.
	 * @param country
	 */
	public void setCountry(String country) {
		this.country = country;
	}
	
	/** Gets the vendor's zip code.
	 * @return An Integer represents the vendor's zip code. 
	 */
	public Integer getZipCode() {
		return zipCode;
	}
	
	/** Sets the vendor's zip code.
	 * An Integer containing the vendor's zip code.
	 * @param zipCode
	 */
	public void setZipCode(Integer zipCode) {
		this.zipCode = zipCode;
	}
	
	/** Gets the vendor's geostationary latitude.
	 * @return An Float represents the vendor's geostationary latitude. 
	 */
	public Float getGeoLat() {
		return geoLat;
	}
	
	/** Sets the vendor's address id.
	 * A Float containing the vendor's geostationary latitude.
	 * @param geoLat
	 */
	public void setGeoLat(Float geoLat) {
		this.geoLat = geoLat;
	}
	
	/** Gets the vendor's geostationary longitude.
	 * @return A Float represents the vendor's geostationary longitude. 
	 */
	public Float getGeoLang() {
		return geoLang;
	}
	
	/** Sets the vendor's geostationary longitude.
	 * A Float containing the vendor's geostationary longitude.
	 * @param geoLang
	 */
	public void setGeoLang(Float geoLang) {
		this.geoLang = geoLang;
	}
	
	/**
	 * Gets the value for the shop associated with address.
	 * @return An object pointing to the Shop
	 */
	public Shop getShop() {
		return shop;
	}

	/**
	 * Sets the value for the shop.
	 * An object pointing to the shop.
	 * @param shop
	 */
	public void setShop(Shop shop) {
		this.shop = shop;
	}
	
}
