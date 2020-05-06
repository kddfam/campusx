package com.campusx.amg;

import java.util.Random;

import org.mindrot.jbcrypt.BCrypt;

public class AuthenticationManager {

	public static Integer generateOTP(int length) {
		char[] otp = new char[length];
		Random random = new Random();
		for(int i=0; i<length; i++) {
			otp[i] = (char) (random.nextInt(10)+48);
		}
		Integer otpInt = Integer.parseInt(new String(otp));
		return otpInt;
	}
	
	public static String encryptPassword(String plainTextPassword) {
		return BCrypt.hashpw(plainTextPassword, BCrypt.gensalt());
	}
	
	public static Boolean decryptPassword(String enteredPassword, String encryptedPassword) {
		return BCrypt.checkpw(enteredPassword, encryptedPassword);
	}
	
}
