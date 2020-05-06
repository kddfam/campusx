package com.campusx.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.campusx.mdl.Address;
import com.campusx.mdl.Item;
import com.campusx.mdl.Kyc;
import com.campusx.mdl.Shop;
import com.campusx.mdl.Vendor;
import com.campusx.srv.VendorService;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(value="/v/api")
public class VendorAPI {

	@Autowired
	private VendorService vendorService;
	
	@Autowired
	Environment env;
	
	@PostMapping(value="/vendors")
	public ResponseEntity<Vendor> registerVendor(@RequestBody Vendor vendor) throws Exception {
		try {
			Vendor v = vendorService.registerVendor(vendor);
			return new ResponseEntity<Vendor>(v, HttpStatus.CREATED);
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new ResponseStatusException(HttpStatus.BAD_GATEWAY, env.getProperty(e.getMessage()), e);
		}
	}
	
	@PostMapping(value="/vendors/login")
	public ResponseEntity<Vendor> loginVendor(@RequestParam("phoneNumber") Long phoneNumber, @RequestParam("password") String password) throws Exception {
		try {
			Vendor v = vendorService.loginVendor(phoneNumber, password);
			return new ResponseEntity<Vendor>(v, HttpStatus.OK);
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new ResponseStatusException(HttpStatus.BAD_GATEWAY, env.getProperty(e.getMessage()), e);
		}
	}
	
	@PostMapping(value="/shops/{vendorId}")
	public ResponseEntity<String> addShop(@PathVariable Integer vendorId, @RequestBody Shop shop) throws Exception {
		try {
			String res = env.getProperty("API.SHOP_REGISTRATION_SUCCESS");
			vendorService.addShop(vendorId, shop);
			return new ResponseEntity<String>(res, HttpStatus.CREATED);
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new ResponseStatusException(HttpStatus.BAD_GATEWAY, env.getProperty(e.getMessage()), e);
		}
	}
	
	@PostMapping(value="/shops/kyc/{shopId}") 
	public ResponseEntity<String> completeKYC(@PathVariable Integer shopId, @RequestBody Kyc kyc) throws Exception {
		try {
			String res = env.getProperty("API.KYC_VERIFICATION_SUCCESS");
			vendorService.completeKYC(shopId, kyc);
			return new ResponseEntity<String>(res, HttpStatus.CREATED);
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new ResponseStatusException(HttpStatus.BAD_GATEWAY, env.getProperty(e.getMessage()), e);
		}
	}
	
	@PostMapping(value="/items/{vendorId}") 
	public ResponseEntity<String> addNewItem(@PathVariable Integer vendorId, @RequestBody Item item) throws Exception {
		try {
			String res = env.getProperty("API.ITEM_ADDED_SUCCESS");
			vendorService.addNewItem(vendorId, item);
			return new ResponseEntity<String>(res, HttpStatus.CREATED);
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new ResponseStatusException(HttpStatus.BAD_GATEWAY, env.getProperty(e.getMessage()), e);
		}
	}
	
	@GetMapping(value="/vendors/{vendorId}")
	public ResponseEntity<Vendor> getProfile(@PathVariable Integer vendorId) throws Exception {
		try {
			Vendor v = vendorService.getProfile(vendorId);
			return new ResponseEntity<Vendor>(v, HttpStatus.FOUND);
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, env.getProperty(e.getMessage()), e);
		}
	}
	
	@GetMapping(value="/shops/{vendorId}")
	public ResponseEntity<Shop> getShop(@PathVariable Integer vendorId) throws Exception {
		try {
			Shop s = vendorService.getShop(vendorId);
			return new ResponseEntity<Shop>(s, HttpStatus.FOUND);
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, env.getProperty(e.getMessage()), e);
		}
	}
	
	@GetMapping(value="/items/{vendorId}")
	public ResponseEntity<List<Item>> getItems(@PathVariable Integer vendorId) throws Exception {
		try {
			List<Item> itemList = vendorService.getItems(vendorId);
			return new ResponseEntity<List<Item>>(itemList, HttpStatus.FOUND);
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, env.getProperty(e.getMessage()), e);
		}
	}
	
	@PutMapping(value="/vendors/{vendorId}")
	public ResponseEntity<String> updateVendorPhoneNumber(@PathVariable Integer vendorId, @RequestBody Long phoneNumber) throws Exception {
		try {
			String res = env.getProperty("API.VENDOR_PHONE_NUMBER_UPDATE_SUCCESS");
			vendorService.updateVendorPhoneNumber(vendorId, phoneNumber);
			return new ResponseEntity<String>(res, HttpStatus.OK);
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, env.getProperty(e.getMessage()), e);
		}
	}
	
	@PutMapping(value="/shops/{vendorId}")
	public ResponseEntity<String> updateShopContactInfo(@PathVariable Integer vendorId, 
			@RequestParam("emailId") String emailId , @RequestParam("phoneNumber") Long phoneNumber) throws Exception {
		try {
			String res = env.getProperty("API.SHOP_EMAIL_OR_PHONE_UPDATE_SUCCESS");
			vendorService.updateShopContactInfo(vendorId, emailId, phoneNumber);
			return new ResponseEntity<String>(res, HttpStatus.OK);
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, env.getProperty(e.getMessage()), e);
		}
	}
	
	@PutMapping(value="/shops/address/{vendorId}")
	public ResponseEntity<String> updateShopAddress(@PathVariable Integer vendorId, @RequestBody Address address) throws Exception {
		try {
			String res = env.getProperty("API.SHOP_ADDRESS_UPDATE_SUCCESS");
			vendorService.updateShopAddress(vendorId, address);
			return new ResponseEntity<String>(res, HttpStatus.OK);
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, env.getProperty(e.getMessage()), e);
		}
	}
	
	@PutMapping(value="/shops/pic/{vendorId}")
	public ResponseEntity<String> updateShopPicture(@PathVariable Integer vendorId, @RequestParam("pic") MultipartFile pic) throws Exception {
		try {
			String res = env.getProperty("API.PICTURE_UPDATE_SUCCESS");
			vendorService.updateShopPicture(vendorId, pic);
			return new ResponseEntity<String>(res, HttpStatus.OK);
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, env.getProperty(e.getMessage()), e);
		}
	}
	
	@PutMapping(value="/vendors/vas/{phoneNumber}")
	public ResponseEntity<Integer> verifyPhoneNumberAndSendOTP(@PathVariable Long phoneNumber) throws Exception {
		try {
			Integer otp = vendorService.verifyPhoneNumberAndSendOTP(phoneNumber);
			return new ResponseEntity<Integer>(otp, HttpStatus.OK);
		}
		catch(Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, env.getProperty(e.getMessage()), e);
		}
	}
	
	@PutMapping(value="/vendors/vo/{otp}")
	public ResponseEntity<String> verifyOTP(@PathVariable Integer otp) throws Exception {
		try {
			String res = env.getProperty("API.OTP_VERIFICATION_SUCCESS");
			vendorService.verifyOTP(otp);
			return new ResponseEntity<String>(res, HttpStatus.OK);
		}
		catch(Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, env.getProperty(e.getMessage()), e);
		}
	}
	
	@PutMapping(value="/vendors/p/{userId}")
	public ResponseEntity<String> updatePassword(@PathVariable Integer userId, @RequestBody String password) throws Exception {
		try {
			String res = env.getProperty("API.PASSWORD_UPDATE_SUCCESS");
			vendorService.updatePassword(userId, password);
			return new ResponseEntity<String>(res, HttpStatus.OK);
		}
		catch(Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, env.getProperty(e.getMessage()), e);
		}
	}
	
	@DeleteMapping(value="/vendors/{vendorId}")
	public ResponseEntity<String> closeAccount(@PathVariable Integer vendorId) throws Exception {
		try {
			String res = env.getProperty("API.ACCOUNT_CLOSE_SUCCESS");
			vendorService.closeAccount(vendorId);
			return new ResponseEntity<String>(res, HttpStatus.OK);
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, env.getProperty(e.getMessage()), e);
		}
	}
	
	@DeleteMapping(value="/items/{itemId}")
	public ResponseEntity<String> deleteItem(@PathVariable Integer itemId) throws Exception {
		try {
			String res = env.getProperty("API.ITEM_DELETE_SUCCESS");
			vendorService.deleteItem(itemId);
			return new ResponseEntity<String>(res, HttpStatus.OK);
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, env.getProperty(e.getMessage()), e);
		}
	}
}
