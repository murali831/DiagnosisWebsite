package com.cg.healthcare.service;

import com.cg.healthcare.entity.User;
import com.cg.healthcare.exceptions.LoginException;

public interface LoginService {
    public User doLogin(String userId, String password)throws LoginException;
    public String encryptUser(User user);
    
}
