package com.campusx.res;

public class ShopResponse {

	private Integer shopId;
	private String name;
	private String shopPicture;
	private Float shopRating;
	
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
	
}
