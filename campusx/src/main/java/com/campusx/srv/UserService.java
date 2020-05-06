package com.campusx.srv;

import org.springframework.web.multipart.MultipartFile;

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
	
}
