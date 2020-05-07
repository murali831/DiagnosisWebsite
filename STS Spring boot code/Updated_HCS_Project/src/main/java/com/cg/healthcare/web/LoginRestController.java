package com.cg.healthcare.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cg.healthcare.entity.User;
import com.cg.healthcare.exceptions.LoginException;
import com.cg.healthcare.service.LoginService;

@RestController
public class LoginRestController {

	@Autowired
	private LoginService ser;

	@Autowired
	@Qualifier("authenticatemap")
	private Map<String, User> authMap;
	
	@CrossOrigin
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String getLogin(@RequestParam("userid") String userId, @RequestParam("password") String password,
			HttpSession sess) throws LoginException {
		User user = ser.doLogin(userId, password);
        
		String token = ser.encryptUser(user);
		authMap.put(token, user);
		return token;
	}

	@CrossOrigin
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(@RequestHeader("userId") String token,
			HttpServletRequest req) {
		authMap.remove(token);
		return "logged out";
	}

}
