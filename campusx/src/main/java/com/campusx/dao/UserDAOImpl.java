package com.campusx.dao;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.campusx.amg.AuthenticationManager;
import com.campusx.ety.OtpEntity;
import com.campusx.ety.UserEntity;
import com.campusx.mdl.Otp;
import com.campusx.mdl.User;

@Repository(value="userDAO")
public class UserDAOImpl implements UserDAO {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public User registerUser(User user) throws Exception {
		
		// creating object of user entity.
		UserEntity userEn = new UserEntity();
		
		// populating the user entity object with data.
		userEn.setFirstName(user.getFirstName());
		userEn.setLastName(user.getLastName());
		userEn.setDateOfBirth(user.getDateOfBirth());
		userEn.setPhoneNumber(user.getPhoneNumber());
		userEn.setPassword(AuthenticationManager.encryptPassword(user.getPassword()));
		userEn.setProfilePicture(null);
		userEn.setAddTimestamp(LocalDateTime.now());
		userEn.setLastUpdateTimestamp(null);
		userEn.setAccountStatus(true);
		userEn.setIsAccountClosed(false);
		
		// persisting the user entity data into the database.
		entityManager.persist(userEn);
		
		// creating an object of class user.
		User u = new User();
		
		// populating the object with data from user entity.
		u.setUserId(userEn.getUserId());
		u.setFirstName(userEn.getFirstName());
		u.setLastName(userEn.getLastName());
		u.setDateOfBirth(userEn.getDateOfBirth());
		u.setPhoneNumber(userEn.getPhoneNumber());
		u.setPassword(userEn.getPassword());
		u.setProfilePicture(userEn.getProfilePicture());
		u.setAddTimestamp(userEn.getAddTimestamp());
		u.setLastUpdateTimestamp(userEn.getLastUpdateTimestamp());
		u.setAccountStatus(userEn.getAccountStatus());
		u.setIsAccountClosed(userEn.getIsAccountClosed());
		
		// returning the user object.
		return u;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public User loginUser(Long phoneNumber, String password) throws Exception {
		
		// checking whether the entered phone number exists or not and its password matches or not
		String phoneNumberCheckQuery = "SELECT ue FROM UserEntity ue WHERE ue.phoneNumber=?1";
		Query query = entityManager.createQuery(phoneNumberCheckQuery);
		query.setParameter(1, phoneNumber);
		List<UserEntity> ueList = query.getResultList();
		
		// if list is empty, return -1;
		if(ueList.isEmpty()) {
			return null;
		}
		
		// else, decrypt password and check account status
		else {
			
			// creating object of user class.
			User u = new User();
			for(UserEntity ue : ueList) {
				u.setUserId(ue.getUserId());
				u.setFirstName(ue.getFirstName());
				u.setLastName(ue.getLastName());
				u.setDateOfBirth(ue.getDateOfBirth());
				u.setPhoneNumber(ue.getPhoneNumber());
				u.setPassword(ue.getPassword());
				u.setProfilePicture(ue.getProfilePicture());
				u.setAddTimestamp(ue.getAddTimestamp());
				u.setLastUpdateTimestamp(ue.getLastUpdateTimestamp());
				u.setAccountStatus(ue.getAccountStatus());
				u.setIsAccountClosed(ue.getIsAccountClosed());
			}
			
			// checking whether the entered password matches with encrypted password or not
			Boolean passwordMatching = AuthenticationManager.decryptPassword(password, u.getPassword());
			
			// if matches, check for account status
			if(passwordMatching) {
				
				// checking account status
				// if all good, return user object.
				if(u.getAccountStatus()==true && u.getIsAccountClosed()==false) {
					return u;
				}
				
				// else, throw new exception.
				else {
					throw new Exception("DAO.ACCOUNT_CLOSED");
				}
			}
			
			// else throw an exception.
			else {
				throw new Exception("DAO.PASSWORD_NOT_MATCH");
			}
		}
	}

	@Override
	public User getProfile(Integer userId) throws Exception {
		
		// checking whether the requested user id exist or not.
		UserEntity ue = entityManager.find(UserEntity.class, userId);
		
		// if not exist, return null
		if(ue == null) {
			return null;
		}
		
		// else return object of user.
		else {
			
			// creating an object of class user.
			User u = new User();
			
			// populating the object with data from user entity.
			u.setUserId(ue.getUserId());
			u.setFirstName(ue.getFirstName());
			u.setLastName(ue.getLastName());
			u.setDateOfBirth(ue.getDateOfBirth());
			u.setPhoneNumber(ue.getPhoneNumber());
			u.setPassword(ue.getPassword());
			u.setProfilePicture(ue.getProfilePicture());
			u.setAddTimestamp(ue.getAddTimestamp());
			u.setLastUpdateTimestamp(ue.getLastUpdateTimestamp());
			u.setAccountStatus(ue.getAccountStatus());
			u.setIsAccountClosed(ue.getIsAccountClosed());
			
			// returning the user object.
			return u;
			
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Integer updatePhoneNumber(Integer userId, Long phoneNumber) throws Exception {

		// checking whether the requested user id exist or not.
		UserEntity ue = entityManager.find(UserEntity.class, userId);
		
		// if not exist, return -1
		if(ue == null) {
			return -1;
		}
		
		// else, other security checks
		else {
			
			// check whether the phone number already exist or not
			Query query = entityManager.createQuery("SELECT ue FROM UserEntity ue WHERE ue.phoneNumber=?1");
			query.setParameter(1, phoneNumber);
			List<UserEntity> ueList = query.getResultList();
			
			// phone number does not exists, update old with new
			if(ueList.isEmpty()) {
				ue.setPhoneNumber(phoneNumber);
				return 1;
			}
			
			// else, throw unique value duplicate error
			else {
				return -2;
			}
			
		}
	}
	
	@Override
	public Integer updateProfilePicture(Integer userId, MultipartFile pic) throws Exception {
		
		// checking whether user id exist or not
		UserEntity ue = entityManager.find(UserEntity.class, userId);
		
		// if does not exists, return -1
		if(ue == null) {
			return -1;
		}
		
		// else, get the picture and store
		else {
			
			// folder where file will be stored
			String folder = "photos/users/";
			
			// getting bytes of picture
			byte[] bytes = pic.getBytes();
			
			// if byte array length is less than or equal to zero, return -1
			if(bytes.length <= 0) {
				return -2;
			}
			
			// else, store the file into the system and update the assigned path into the database. 
			else {
				Path path = Paths.get(folder, LocalDateTime.now()+pic.getOriginalFilename());
				Files.write(path, bytes);
				
				ue.setProfilePicture(path.toString());
				return 1;
			}
		}
	}


	@Override
	public Integer closeAccount(Integer userId) throws Exception {

		// checking whether the requested user id exist or not.
		UserEntity ue = entityManager.find(UserEntity.class, userId);
		
		// if not exist, return -1
		if(ue == null) {
			return -1;
		}
		
		// else, make account status changes.
		else {
			ue.setAccountStatus(false);
			ue.setIsAccountClosed(true);
			return 1;
		}
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public Integer verifyPhoneNumberAndSendOTP(Long phoneNumber) throws Exception {
		
		// checking whether the phone number exists or not
		String strQuery = "SELECT ue FROM UserEntity ue WHERE ue.phoneNumber=?1";
		Query query = entityManager.createQuery(strQuery);
		query.setParameter(1, phoneNumber);
		List<UserEntity> ueList = query.getResultList();
		
		// if not exists, return -1
		if(ueList.isEmpty()) {
			return -1;
		}
		
		// else, send an otp
		else {
			Integer otp = AuthenticationManager.generateOTP(6);
			
			// store otp into database
			OtpEntity oe = new OtpEntity();
			oe.setOtp(otp);
			oe.setAddTimestamp(LocalDateTime.now());
			oe.setExpiryTimestamp(LocalDateTime.now().plusMinutes(15));
			oe.setUseTimestamp(null);
			oe.setGeneratedFor(phoneNumber);
			oe.setStatus(false);
			entityManager.persist(oe);
			
			return otp;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Integer verifyOTP(Integer otp) throws Exception {
		
		// check whether the otp is valid or not
		String strQuery = "SELECT oe FROM OtpEntity oe WHERE oe.otp=?1";
		Query query = entityManager.createQuery(strQuery);
		query.setParameter(1, otp);
		List<OtpEntity> oeList = query.getResultList();
		
		// if not found, return -1
		if(oeList.isEmpty()) {
			return -1;
		}
		
		// else, perform other checks
		else {
			
			// create otp object and populate it with required information
			Otp o = new Otp();
			for(OtpEntity oe : oeList) {
				o.setOtpId(oe.getOtp());
				o.setOtp(oe.getOtp());
				o.setAddTimestamp(oe.getAddTimestamp());
				o.setExpiryTimestamp(oe.getExpiryTimestamp());
				o.setUseTimestamp(oe.getUseTimestamp());
				o.setGeneratedFor(oe.getGeneratedFor());
				o.setStatus(oe.getStatus());
			}
			
			// if otp is expiry time is already passed, return -2
			if(o.getExpiryTimestamp().isBefore(LocalDateTime.now())) {
				return -2;
			}
			
			// else, if otp is already used, return -3
			else if(o.getStatus() == true) {
				return -3;
			}
			
			// else, verify otp and update changes
			else {
				
				String strQuery1 = "UPDATE OtpEntity oe SET oe.status=true, oe.useTimestamp=?1";
				Query query1 = entityManager.createQuery(strQuery1);
				query1.setParameter(1, LocalDateTime.now());
				query1.executeUpdate();
				
				return 1;
			}
		}
	}
	
	@Override
	public Integer updatePassword(Integer userId, String password) throws Exception {

		// checking whether the requested user id exist or not.
		UserEntity ue = entityManager.find(UserEntity.class, userId);
		
		// if not exist, return -1
		if(ue == null) {
			return -1;
		}
		
		// else, update old password with new.
		else {
			ue.setPassword(password);
			return 1;
		}
		
	}
	
}
