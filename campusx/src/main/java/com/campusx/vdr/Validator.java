package com.campusx.vdr;

public class Validator {

	public static boolean validateEmail(String emailId) throws Exception {
		String regex = "^[a-zA-Z0-9_.]+@[a-z]+\\.[a-z]+";
		if(emailId.matches(regex)) {
			return true;
		}
		return false;
	}
	
	public static boolean validateNumber(Long phoneNumber) throws Exception {
		String regex = "^(7|8|9)\\d{2,9}";
		if(phoneNumber.toString().matches(regex)) {
			return true;
		}
		return false;
	}
	
	public static boolean validatePassword(String password) throws Exception {
		String regex = "[a-zA-Z0-9@#$%&*\\s]{8,32}";
		if(password.matches(regex)) {
			return true;
		}
		return false;
	}
	
}
