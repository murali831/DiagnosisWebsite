package com.cg.healthcare.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cg.healthcare.dto.HealthMessage;
import com.cg.healthcare.dto.SlotForm;
import com.cg.healthcare.entity.CheckUpSlot;
import com.cg.healthcare.exceptions.DiagnosisException;
import com.cg.healthcare.exceptions.DiagnosisTestException;
import com.cg.healthcare.exceptions.LoginException;
import com.cg.healthcare.exceptions.SlotException;
import com.cg.healthcare.service.SlotTestService;
import com.cg.healthcare.util.HealthCareConstants;

@RestController
public class SlotTestRestController {

	@Autowired
	public SlotTestService service;

	@CrossOrigin
	@PostMapping("/addtestfordiagnosiscentre/{testid}/{centreid}")
	public HealthMessage addTestForDiagnosisCentre(@PathVariable("testid") String testId,
			@PathVariable("centreid") String centreId,HttpServletRequest request) throws DiagnosisTestException, DiagnosisException, LoginException {
		try {
			if((boolean) request.getAttribute("authFlag")) {
			service.addTestForDiagnosisCentre(testId, centreId);
			return new HealthMessage(HealthCareConstants.TEST_DIAGNOSIS_ADDED);
			}throw new LoginException();
		 } catch (DataIntegrityViolationException ex) {
			throw new DiagnosisTestException(HealthCareConstants.ID_ALREADY_EXISTS);
		}
	}
	@CrossOrigin
	@PostMapping("/createnewslot")
	public HealthMessage createNewSlot(@RequestBody SlotForm form, HttpServletRequest request) throws DiagnosisTestException, LoginException {
		try {
			if((boolean) request.getAttribute("authFlag")) {
			service.createNewSlot(form);
			return new HealthMessage(HealthCareConstants.SLOT_ADDED);
			}throw new LoginException();
		} catch (DataIntegrityViolationException ex) {
			throw new DiagnosisTestException(HealthCareConstants.ID_ALREADY_EXISTS);
		}
	}
	@CrossOrigin
	@GetMapping("/viewslots/{testid}/{centreid}")
	public List<CheckUpSlot> viewSlots(@PathVariable("testid") String testId,
			@PathVariable("centreid") String centreId) throws SlotException {
		return service.viewSlots(testId, centreId);
	}

}
