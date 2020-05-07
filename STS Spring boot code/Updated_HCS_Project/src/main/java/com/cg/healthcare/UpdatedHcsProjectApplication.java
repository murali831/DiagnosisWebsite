package com.cg.healthcare;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.cg.healthcare.entity.User;
import com.cg.healthcare.web.AdminInterceptor;
import com.cg.healthcare.web.LoginInterceptor;

@SpringBootApplication
public class UpdatedHcsProjectApplication implements WebMvcConfigurer{

	public static void main(String[] args) {
		SpringApplication.run(UpdatedHcsProjectApplication.class, args);
	}
	@Autowired 
	public LoginInterceptor loginInterceptor;

	@Autowired
	public AdminInterceptor adminInterceptor;
	
	@Bean(name="authenticatemap")
	public Map<String,User> getAuthenticateMap(){
		return new HashMap<>();
	}
	@Override 
	public void addInterceptors(InterceptorRegistry registry) { 
		registry.addInterceptor (loginInterceptor).addPathPatterns (new String[] { "/makeAppointment","/viewalldiagnosis","/viewalltests", 
				"/viewslots/{testid}/{centreid}","/viewalldiagnosiscenter/{testid}","/viewtestfordiagnosisid/{centreid}","/viewUserAppointments/{contactno}","/searchtest/{str}/*" }); 
		registry.addInterceptor(adminInterceptor).addPathPatterns (new String[] {"/removeAppointment/{apmtid}", "/adddiagnosis","/viewalldiagnosis",
				"/viewalltests","/editdiagnosis","/addtestfordiagnosiscentre/{testid}/{centreid}","/viewtestfordiagnosisid/{centreid}","/createnewslot",
				"/viewalldiagnosiscenter/{testid}","/viewslots/{testid}/{centreid}","/viewAdminAppointments/{slotid}","/searchtest/{str}/*" 
				});
	}

}

