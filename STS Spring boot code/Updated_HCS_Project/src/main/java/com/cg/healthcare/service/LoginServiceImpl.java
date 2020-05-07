package com.cg.healthcare.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.healthcare.dao.IHealthCareDao;
import com.cg.healthcare.entity.User;
import com.cg.healthcare.exceptions.LoginException;
import com.cg.healthcare.util.HealthCareConstants;

@Service
public class LoginServiceImpl implements LoginService{

	@Autowired
	private IHealthCareDao dao;
	
		
	Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);
	
	@Override
	public User doLogin(String userId, String password)throws LoginException {
		User user = dao.getUser(userId);
		logger.debug("doing login process");
		
		if (user != null && user.getPassword().contentEquals(password)){
			logger.info("User Authenticated for " + userId);
			return user;
		}
		throw new LoginException(HealthCareConstants.NOT_AUTHENTICATED);
	}

	@Override
	public String encryptUser(User user) {
		return encryptString(user.getUserID())+"-" +encryptString(user.getUserName())+"-"
		      +encryptString(user.getRole());
	}
	
	public String encryptString(String str) {
		char[] arr = str.toCharArray();
		StringBuffer sb = new StringBuffer();
		int ch ;
		for (int idx=0; idx < arr.length; ++idx) {
			ch = arr[idx]+3;
			sb.append((char)ch);
		}
		return sb.toString();
	}

	public String decryptString(String str) {
		char[] arr = str.toCharArray();
		StringBuffer sb = new StringBuffer();
		int ch ;
		for (int idx=0; idx < arr.length; ++idx) {
			ch = arr[idx]-3;
			sb.append((char)ch);
		}
		return sb.toString();
	}

	
}
