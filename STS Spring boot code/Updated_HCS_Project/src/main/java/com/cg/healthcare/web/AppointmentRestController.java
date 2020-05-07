package com.cg.healthcare.web;

import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cg.healthcare.dto.AppointmentForm;
import com.cg.healthcare.dto.HealthMessage;
import com.cg.healthcare.entity.Appointment;
import com.cg.healthcare.exceptions.AppointmentException;
import com.cg.healthcare.exceptions.LoginException;
import com.cg.healthcare.exceptions.SlotException;
import com.cg.healthcare.service.AppointmentService;
import com.cg.healthcare.util.HealthCareConstants;

@RestController
public class AppointmentRestController {

	@Autowired
	public AppointmentService service;
	
	@CrossOrigin
	@PostMapping("/makeAppointment")
	public HealthMessage makeAppointment(@RequestBody AppointmentForm appointmentForm) throws AppointmentException, SlotException {
		try {
		String ID = service.makeAppointment(appointmentForm);
		return new HealthMessage(HealthCareConstants.APPOINTMENT_ADDED+ " " + ID);
		}catch(DataIntegrityViolationException ex) {
			throw new AppointmentException(HealthCareConstants.ID_ALREADY_EXISTS);
		}
	}
	@CrossOrigin
	@DeleteMapping("/removeAppointment/{apmtid}")
	public HealthMessage removeAppointment(@PathVariable("apmtid")  String apmtID) throws AppointmentException {
		
		service.removeAppointment(apmtID);
		return new HealthMessage(HealthCareConstants.APPOINTMENT_REMOVED);
		
	}
	@CrossOrigin
	@GetMapping("/viewUserAppointments/{contactno}")
	public List<Appointment> viewUserAppointments(@PathVariable("contactno")  String contactNo) throws AppointmentException {
		return service.viewUserAppointments(contactNo);
	}
	
	@CrossOrigin
	@GetMapping("/viewAdminAppointments/{slotid}")
	public List<Appointment> viewAdminAppointments(@PathVariable("slotid")  String slotId , HttpServletRequest request) throws AppointmentException, LoginException {
		if((boolean) request.getAttribute("authFlag")) {
		return service.viewAdmnAppointments(slotId);
		}throw new LoginException();
	}
	
}
