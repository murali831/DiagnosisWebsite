package com.cg.healthcare.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cg.healthcare.dto.HealthMessage;
import com.cg.healthcare.entity.CheckUp;
import com.cg.healthcare.entity.DiagnosisCentre;
import com.cg.healthcare.exceptions.DiagnosisException;
import com.cg.healthcare.exceptions.LoginException;
import com.cg.healthcare.service.DiagnosisService;
import com.cg.healthcare.util.HealthCareConstants;

@RestController
public class DiagnosisRestController {

	@Autowired
	public DiagnosisService service;

	@CrossOrigin
	@PostMapping("/adddiagnosis")
	public HealthMessage addDiagnosisCentre(@RequestBody DiagnosisCentre centre, HttpServletRequest request) throws DiagnosisException,LoginException {
		
		try {
			if((boolean) request.getAttribute("authFlag")) {
			service.addDiagnosisCentre(centre);
			return new HealthMessage(HealthCareConstants.DIAGNOSIS_ADDED);
			}throw  new LoginException();
		 } catch (DataIntegrityViolationException ex) {
			throw new DiagnosisException(HealthCareConstants.ID_ALREADY_EXISTS);
		 }
		
	}

	@CrossOrigin
	@GetMapping("/viewalldiagnosis")
	public List<DiagnosisCentre> viewdDiagnosisCentre() throws DiagnosisException {
		return service.viewDiagmosisCentre();
	}
	
	@CrossOrigin
	@GetMapping("/viewalltests")
	public List<CheckUp> viewdTests() throws DiagnosisException {
		return service.viewCheckUp();
	}

	@CrossOrigin
	@PutMapping("/editdiagnosis")
	public HealthMessage editDiagnosisCentre(@RequestBody DiagnosisCentre centre) throws DiagnosisException {
		service.editDiagnosisCentre(centre);
		return new HealthMessage(HealthCareConstants.DIAGNOSIS_EDITED);

	}
}
