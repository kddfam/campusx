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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.campusx.mdl.User;
import com.campusx.res.ItemResponse;
import com.campusx.res.ShopResponse;
import com.campusx.res.ShopResponseDetailed;
import com.campusx.res.UserResponseR;
import com.campusx.srv.UserService;

@RestController
@RequestMapping(value="/u/api")
public class UserAPI {

	@Autowired
	private UserService userService;
	
	@Autowired
	Environment env;
	
	@PostMapping(value="/users")
	public ResponseEntity<UserResponseR> registerUser(@RequestBody User user) throws Exception {
		try {
			UserResponseR ur = userService.registerUser(user);
			return new ResponseEntity<UserResponseR>(ur, HttpStatus.CREATED);
		}
		catch(Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, env.getProperty(e.getMessage()), e);
		}
	}
	
	@PostMapping(value="/users/login")
	public ResponseEntity<User> loginUser(@RequestParam("phoneNumber") Long phoneNumber, @RequestParam("password") String password) throws Exception {
		try {
			User u = userService.loginUser(phoneNumber, password);
			return new ResponseEntity<User>(u, HttpStatus.OK);
		}
		catch(Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, env.getProperty(e.getMessage()), e);
		}
	}
	
	@GetMapping(value="/users/{userId}")
	public ResponseEntity<User> getProfile(@PathVariable Integer userId) throws Exception {
		try {
			User u = userService.getProfile(userId);
			return new ResponseEntity<User>(u, HttpStatus.FOUND);
		}
		catch(Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, env.getProperty(e.getMessage()), e);
		}
	}
	
	@PutMapping(value="/users/pn/{userId}")
	public ResponseEntity<String> updatePhoneNumber(@PathVariable Integer userId, @RequestBody Long phoneNumber) throws Exception {
		try {
			String res = env.getProperty("API.USER_PHONE_NUMBER_UPDATE_SUCCESS");
			userService.updatePhoneNumber(userId, phoneNumber);
			return new ResponseEntity<String>(res, HttpStatus.OK);
		}
		catch(Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, env.getProperty(e.getMessage()), e);
		}
	}
	
	@PutMapping(value="/users/vas/{phoneNumber}")
	public ResponseEntity<Integer> verifyPhoneNumberAndSendOTP(@PathVariable Long phoneNumber) throws Exception {
		try {
			Integer otp = userService.verifyPhoneNumberAndSendOTP(phoneNumber);
			return new ResponseEntity<Integer>(otp, HttpStatus.OK);
		}
		catch(Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, env.getProperty(e.getMessage()), e);
		}
	}
	
	@PutMapping(value="/users/vo/{otp}")
	public ResponseEntity<String> verifyOTP(@PathVariable Integer otp) throws Exception {
		try {
			String res = env.getProperty("API.OTP_VERIFICATION_SUCCESS");
			userService.verifyOTP(otp);
			return new ResponseEntity<String>(res, HttpStatus.OK);
		}
		catch(Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, env.getProperty(e.getMessage()), e);
		}
	}
	
	@PutMapping(value="/users/p/{userId}")
	public ResponseEntity<String> updatePassword(@PathVariable Integer userId, @RequestBody String password) throws Exception {
		try {
			String res = env.getProperty("API.PASSWORD_UPDATE_SUCCESS");
			userService.updatePassword(userId, password);
			return new ResponseEntity<String>(res, HttpStatus.OK);
		}
		catch(Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, env.getProperty(e.getMessage()), e);
		}
	}
	
	@PutMapping(value="/users/pp/{userId}")
	public ResponseEntity<String> updateProfilePicture(@PathVariable Integer userId, @RequestParam("pic") MultipartFile pic) throws Exception {
		try {
			String res = env.getProperty("API.PICTURE_UPDATE_SUCCESS");
			userService.updateProfilePicture(userId, pic);
			return new ResponseEntity<String>(res, HttpStatus.OK);
		}
		catch(Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, env.getProperty(e.getMessage()), e);
		}
	}
	
	@DeleteMapping(value="/users/{userId}")
	public ResponseEntity<String> closeAccount(@PathVariable Integer userId) throws Exception {
		try {
			String res = env.getProperty("API.ACCOUNT_CLOSE_SUCCESS");
			userService.closeAccount(userId);
			return new ResponseEntity<String>(res, HttpStatus.OK);
		}
		catch(Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, env.getProperty(e.getMessage()), e);
		}
	}
	
	
	@GetMapping(value="/shops")
	public ResponseEntity<List<ShopResponse>> shopList() throws Exception {
		try {
			List<ShopResponse> srList = userService.shopList();
			return new ResponseEntity<List<ShopResponse>>(srList, HttpStatus.OK);
		}
		catch(Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, env.getProperty(e.getMessage()), e);
		}
	}
	
	@GetMapping(value="/shops/{shopId}")
	public ResponseEntity<ShopResponseDetailed> specificShop(@PathVariable Integer shopId) throws Exception {
		try {
			ShopResponseDetailed sr = userService.specificShop(shopId);
			return new ResponseEntity<ShopResponseDetailed>(sr, HttpStatus.OK);
		}
		catch(Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, env.getProperty(e.getMessage()), e);
		}
	}
	
	@GetMapping(value="/items/{itemId}")
	public ResponseEntity<ItemResponse> specificItem(@PathVariable Integer itemId) throws Exception {
		try {
			ItemResponse ir = userService.specificItem(itemId);
			return new ResponseEntity<ItemResponse>(ir, HttpStatus.OK);
		}
		catch(Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, env.getProperty(e.getMessage()), e);
		}
	}
	
	@GetMapping(value="/shops/search")
	public ResponseEntity<List<ShopResponse>> searchShopList(@RequestParam("shopName") String shopName) throws Exception {
		try {
			List<ShopResponse> srList = userService.searchShopList(shopName);
			return new ResponseEntity<List<ShopResponse>>(srList, HttpStatus.OK);
		}
		catch(Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, env.getProperty(e.getMessage()), e);
		}
	}
	
}
