package com.cg.healthcare.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.cg.healthcare.entity.User;

@Component

public class AdminInterceptor implements HandlerInterceptor{

@Autowired 
@Qualifier("authenticatemap") 
private Map<String, User> authMap;
@Override public boolean preHandle(HttpServletRequest request, HttpServletResponse resp, Object handler) throws Exception { 
	HandlerInterceptor.super.preHandle(request, resp, handler);
	String token = request.getHeader("userId"); 
	User user = (User)authMap.get(token); 
	System.out.println(user); 
	System.out.println("tokenid " + request.getHeader("userId")); 
	if(user != null && user.getRole().equals("admin")) request.setAttribute("authFlag", true); 
	else
   request.setAttribute("authFlag",false);
   return true;
}}