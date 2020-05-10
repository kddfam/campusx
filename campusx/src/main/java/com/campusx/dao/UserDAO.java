package com.campusx.dao;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.campusx.mdl.User;
import com.campusx.res.ItemResponse;
import com.campusx.res.ShopResponse;
import com.campusx.res.UserResponseR;

public interface UserDAO {

	public UserResponseR registerUser(User user) throws Exception;
	public User loginUser(Long phoneNumber, String password) throws Exception;
	public User getProfile(Integer userId) throws Exception;
	public Integer updatePhoneNumber(Integer userId, Long phoneNumber) throws Exception;
	public Integer updateProfilePicture(Integer userId, MultipartFile pic) throws Exception;
	public Integer closeAccount(Integer userId) throws Exception;
	
	public Integer verifyPhoneNumberAndSendOTP(Long phoneNumber) throws Exception;
	public Integer verifyOTP(Integer otp) throws Exception;
	public Integer updatePassword(Integer userId, String password) throws Exception;
	
	public List<ShopResponse> shopList() throws Exception;
	public ShopResponse specificShop(Integer shopId) throws Exception;
	public ItemResponse specificItem(Integer itemId) throws Exception;
	public List<ShopResponse> searchShopList(String shopName) throws Exception;
	
}
