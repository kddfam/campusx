package com.campusx.srv;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.campusx.dao.UserDAO;
import com.campusx.mdl.User;
import com.campusx.vdr.Validator;

@Service(value="userService")
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO userDAO;
	
	@Override
	public User registerUser(User user) throws Exception {
		if(Validator.validateNumber(user.getPhoneNumber())) {
			return userDAO.registerUser(user);
		}
		else {
			throw new Exception("Service.INVALID_PHONE_NUMBER_FORMAT");
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
		Integer _statusCode = userDAO.updatePassword(userId, password);
		if(_statusCode == -1) {
			throw new Exception("Service.USER_DOES_NOT_EXISTS");
		}
		else {
			return _statusCode;
		}
	}

}
