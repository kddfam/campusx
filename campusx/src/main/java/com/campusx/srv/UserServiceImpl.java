package com.campusx.srv;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.campusx.dao.UserDAO;
import com.campusx.mdl.Item;
import com.campusx.mdl.Shop;
import com.campusx.mdl.User;
import com.campusx.sec.UserInformation;
import com.campusx.vdr.Validator;

@Service(value="userService")
@Transactional
public class UserServiceImpl implements UserService, UserDetailsService {

	@Autowired
	private UserDAO userDAO;
	
	@Override
	public User registerUser(User user) throws Exception {
		if(Validator.validatePassword(user.getPassword())) {
			if(Validator.validateNumber(user.getPhoneNumber())) {
				return userDAO.registerUser(user);
			}
			else {
				throw new Exception("Service.INVALID_PHONE_NUMBER_FORMAT");
			}
		}
		else {
			throw new Exception("Service.INVALID_PASSWORD_FORMAT");
		}
	}
	
	@Override
	public User loginUser(Long phoneNumber, String password) throws Exception {
		User u = userDAO.loginUser(phoneNumber, password);
		if(u == null) {
			throw new Exception("Service.USER_DOES_NOT_EXISTS");
		}
		else {
			return u;
		}
	}

	@Override
	public User getProfile(Integer userId) throws Exception {
		User user = userDAO.getProfile(userId);
		if(user == null) {
			throw new Exception("Service.USER_DOES_NOT_EXISTS");
		}
		else {
			return user;
		}
	}

	@Override
	public Integer updatePhoneNumber(Integer userId, Long phoneNumber) throws Exception {
		Integer _statusCode = userDAO.updatePhoneNumber(userId, phoneNumber);
		if(_statusCode == -1) {
			throw new Exception("Service.USER_DOES_NOT_EXISTS");
		}
		else if(_statusCode == -2) {
			throw new Exception("Service.USER_PHONE_NUMBER_ALREADY_EXISTS");
		}
		else {
			return _statusCode;
		}
	}
	
	@Override
	public Integer updateProfilePicture(Integer userId, MultipartFile pic) throws Exception {
		Integer _statusCode = userDAO.updateProfilePicture(userId, pic);
		if(_statusCode == -1) {
			throw new Exception("Service.USER_DOES_NOT_EXISTS");
		}
		else if(_statusCode == -2) {
			throw new Exception("Service.INVALID_FILE_SIZE");
		}
		else {
			return _statusCode;
		}
	}

	@Override
	public Integer closeAccount(Integer userId) throws Exception {
		Integer _statusCode = userDAO.closeAccount(userId);
		if(_statusCode == -1) {
			throw new Exception("Service.USER_DOES_NOT_EXISTS");
		}
		else {
			return _statusCode;
		}
	}

	@Override
	public Integer verifyPhoneNumberAndSendOTP(Long phoneNumber) throws Exception {
		Integer _statusCode = userDAO.verifyPhoneNumberAndSendOTP(phoneNumber);
		if(_statusCode == -1) {
			throw new Exception("Service.USER_DOES_NOT_EXISTS");
		}
		else {
			return _statusCode;
		}
	}

	@Override
	public Integer verifyOTP(Integer otp) throws Exception {
		Integer _statusCode = userDAO.verifyOTP(otp);
		if(_statusCode == -1) {
			throw new Exception("Service.USER_DOES_NOT_EXISTS");
		}
		else if(_statusCode == -2) {
			throw new Exception("Service.OTP_EXPIRED");
		}
		else if(_statusCode == -3) {
			throw new Exception("Service.INVALID_OTP");
		}
		else {
			return _statusCode;
		}
	}
	
	@Override
	public Integer updatePassword(Integer userId, String password) throws Exception {
		if(Validator.validatePassword(password)) {
			Integer _statusCode = userDAO.updatePassword(userId, password);
			if(_statusCode == -1) {
				throw new Exception("Service.USER_DOES_NOT_EXISTS");
			}
			else {
				return _statusCode;
			}
		}
		else {
			throw new Exception("Service.INVALID_PASSWORD_FORMAT");
		}
	}

	@Override
	public List<Shop> shopList() throws Exception {
		List<Shop> sList = userDAO.shopList();
		if(sList.isEmpty()) {
			throw new Exception("Service.NO_SHOPS_FOUND");
		}
		else {
			return sList;
		}
	}

	@Override
	public Shop specificShop(Integer shopId) throws Exception {
		Shop s = userDAO.specificShop(shopId);
		if(s == null) {
			throw new Exception("Service.NO_SHOP_FOUND");
		}
		else {
			return s;
		}
	}

	@Override
	public Item specificItem(Integer itemId) throws Exception {
		Item i = userDAO.specificItem(itemId);
		if(i == null) {
			throw new Exception("Service.NO_ITEM_FOUND");
		}
		else {
			return i;
		}
	}

	@Override
	public List<Shop> searchShopList(String shopName) throws Exception {
		List<Shop> sList = userDAO.searchShopList(shopName);
		if(sList.isEmpty()) {
			throw new Exception("Service.NO_SHOPS_FOUND");
		}
		else {
			return sList;
		}
	}

	@Override
	public UserDetails loadUserByUsername(String phoneNumber) throws UsernameNotFoundException {
		User u;
		UserInformation userInfo;
		try {
			u = userDAO.loadUserByUsername(phoneNumber);
			userInfo = new UserInformation(u);
		} catch (Exception e) {
			e.printStackTrace();
			throw new UsernameNotFoundException("Error 404");
		}
		return userInfo;
	}

}
