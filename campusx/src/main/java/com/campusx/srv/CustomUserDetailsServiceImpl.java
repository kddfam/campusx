package com.campusx.srv;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.campusx.dao.UserDAO;
import com.campusx.mdl.User;
import com.campusx.sec.UserProfile;

@Service(value="customUserDetailsService")
@Transactional
public class CustomUserDetailsServiceImpl implements CustomUserDetailsService, UserDetailsService {
	
	@Autowired
	private UserDAO userDAO;

	public UserDetails loadUserByUsername(String phoneNumber) throws UsernameNotFoundException {
		Long pn = Long.parseLong(phoneNumber);
		User user;
		try {
			user = userDAO.loadUserByPhoneNumber(pn);
			return new UserProfile(user);
		} 
		catch (Exception e) {
			e.printStackTrace();
			throw new UsernameNotFoundException("Error");
		}
	};
	
}
