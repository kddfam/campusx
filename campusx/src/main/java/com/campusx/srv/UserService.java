package com.campusx.srv;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.multipart.MultipartFile;

import com.campusx.mdl.Item;
import com.campusx.mdl.Shop;
import com.campusx.mdl.User;

public interface UserService {
	
	public User registerUser(User user) throws Exception;
	public User loginUser(Long phoneNumber, String password) throws Exception;
	public User getProfile(Integer userId) throws Exception;
	public Integer updatePhoneNumber(Integer userId, Long phoneNumber) throws Exception;
	public Integer updateProfilePicture(Integer userId, MultipartFile pic) throws Exception;
	public Integer closeAccount(Integer userId) throws Exception;
	
	public Integer verifyPhoneNumberAndSendOTP(Long phoneNumber) throws Exception;
	public Integer verifyOTP(Integer otp) throws Exception;
	public Integer updatePassword(Integer userId, String password) throws Exception;
	
	public List<Shop> shopList() throws Exception;
	public Shop specificShop(Integer shopId) throws Exception;
	public Item specificItem(Integer itemId) throws Exception;
	public List<Shop> searchShopList(String shopName) throws Exception;
	
	public UserDetails loadUserByUsername(String phoneNumber) throws UsernameNotFoundException;
	
}
