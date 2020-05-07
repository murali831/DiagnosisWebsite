package com.cg.healthcare.web;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.cg.healthcare.entity.Appointment;
import com.cg.healthcare.entity.DiagnosisTest;
import com.cg.healthcare.exceptions.AppointmentException;
import com.cg.healthcare.exceptions.DiagnosisException;
import com.cg.healthcare.exceptions.SearchTestException;
import com.cg.healthcare.service.ViewDaignosisTestService;

@RestController
public class ViewDiagnosisTestRestController {

	@Autowired
	public ViewDaignosisTestService service;
	
	
	@CrossOrigin
	@GetMapping("/viewalldiagnosiscenter/{testid}")
	public List<DiagnosisTest> getDiagnosisCentreForTests(@PathVariable("testid") String testID) throws DiagnosisException {
		return service.getDiagnosisCentreForTests(testID);
	}
	
	@CrossOrigin
	@GetMapping("/viewtestfordiagnosisid/{centreid}")
	public List<DiagnosisTest> viewTestsForDiagnosisCentre(@PathVariable("centreid") String diagnosisCentreId) throws DiagnosisException {
		
		List<DiagnosisTest> lst = service.viewTestsForDiagnosisCentre(diagnosisCentreId);
		return lst;
		
	}
	
	@CrossOrigin
	@GetMapping("/searchtest/{str}")
	public List<DiagnosisTest> saerchTestsForDiagnosisCentre(@PathVariable("str") String searchTest) throws SearchTestException  {
		
		List<DiagnosisTest> lst = service.searchTestsForDiagnosisCentre(searchTest);
		return lst;
		
	}
	@CrossOrigin
	@GetMapping("/ViewAppointmnets/{appdt}/{centreid}/{testid}")
	public List<Appointment> viewAppointments(@PathVariable("appdt") @DateTimeFormat(pattern="yyyy-M-d")LocalDate appDate, 
			@PathVariable("centreid") String centreId, @PathVariable("testid") String testID) throws AppointmentException {
		return service.viewAppointments(appDate, centreId, testID);
	}
}
