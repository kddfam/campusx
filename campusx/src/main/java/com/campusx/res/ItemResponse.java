package com.campusx.res;

import com.campusx.enm.FoodType;

public class ItemResponse {

	private Integer itemId;
	private String name;
	private String description;
	private Double price;
	private FoodType foodType;
	private String picture;
	private Float itemRating;
	
	/** 
	 * Gets the item id.
	 * @return An integer represents item id.
	 */
	public Integer getItemId() {
		return itemId;
	}
	
	/** 
	 * Sets the item id.
	 * An integer container item id.
	 * @param itemId
	 */
	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}
	
	/**
	 * Gets the name of item.
	 * @return A string represents name of item.
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Sets the name of item.
	 * A string containing name of the item.
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Gets the description of item.
	 * @return A string represents description of item.
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * Sets the description of the string
	 * A string containing description of the item.
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * Gets the price of item.
	 * @return A Double represents the price of item.
	 */
	public Double getPrice() {
		return price;
	}
	
	/**
	 * Sets the price of item.
	 * A Float containing price of the item.
	 * @param price
	 */
	public void setPrice(Double price) {
		this.price = price;
	}
	
	/**
	 * Gets the Food type of the item.
	 * @return An enum FoodType represents food type of item.
	 */
	public FoodType getFoodType() {
		return foodType;
	}
	
	/**
	 * Sets the food type of item.
	 * An enum FoodType containing food type of item.
	 * @param foodType
	 */
	public void setFoodType(FoodType foodType) {
		this.foodType = foodType;
	}
	
	/**
	 * Gets the picture of the item.
	 * @return A string represents picture of item.
	 */
	public String getPicture() {
		return picture;
	}
	
	/**
	 * Sets the picture of the item.
	 * A string containing path to the picture of item in the file system.
	 * @param picture
	 */
	public void setPicture(String picture) {
		this.picture = picture;
	}
	
	/**
	 * Gets the rating of the item.
	 * @return A Float represents rating of item.
	 */
	public Float getItemRating() {
		return itemRating;
	}
	
	/**
	 * Sets the rating of item.
	 * A Float containing rating of the item.
	 * @param itemRating
	 */
	public void setItemRating(Float itemRating) {
		this.itemRating = itemRating;
	}
	
}
