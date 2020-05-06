package com.campusx.srv;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.campusx.mdl.Address;
import com.campusx.mdl.Item;
import com.campusx.mdl.Kyc;
import com.campusx.mdl.Shop;
import com.campusx.mdl.Vendor;

public interface VendorService {

	// C
	public Vendor registerVendor(Vendor vendor) throws Exception;
	public Vendor loginVendor(Long phoneNumber, String password) throws Exception;
	public Integer addShop(Integer vendorId, Shop shop) throws Exception;
	public Integer completeKYC(Integer vendorId, Kyc kyc) throws Exception;
	public Integer addNewItem(Integer vendorId, Item item) throws Exception; 
	
	// R
	public Vendor getProfile(Integer vendorId) throws Exception;
	public Shop getShop(Integer vendorId) throws Exception;
	public List<Item> getItems(Integer vendorId) throws Exception;
	
	// U
	public Integer updateVendorPhoneNumber(Integer vendorId, Long phoneNumber) throws Exception;
	public Integer updateShopContactPhoneNumber(Integer vendorId, Long phoneNumber) throws Exception;
	public Integer updateShopContactEmail(Integer vendorId, String emailId) throws Exception;
	public Integer updateShopAddress(Integer vendorId, Address address) throws Exception;
	public Integer updateShopPicture(Integer vendorId, MultipartFile pic) throws Exception;
	
	// D
	public Integer closeAccount(Integer vendorId) throws Exception;
	public Integer deleteItem(Integer itemId) throws Exception;
	
	// Auth
	public Integer verifyPhoneNumberAndSendOTP(Long phoneNumber) throws Exception;
	public Integer verifyOTP(Integer otp) throws Exception;
	public Integer updatePassword(Integer vendorId, String password) throws Exception;
	
}
