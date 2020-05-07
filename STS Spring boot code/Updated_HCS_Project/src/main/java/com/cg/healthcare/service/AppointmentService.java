package com.cg.healthcare.service;

import java.time.LocalDate;
import java.util.List;

import com.cg.healthcare.dto.AppointmentForm;
import com.cg.healthcare.entity.Appointment;
import com.cg.healthcare.exceptions.AppointmentException;
import com.cg.healthcare.exceptions.SlotException;
/**
 * 
 * @author Arshad
 *
 */
public interface AppointmentService {
	
	
	
	String makeAppointment(AppointmentForm appointmentForm)throws SlotException;
	boolean removeAppointment(String apmtID) throws AppointmentException;
	public List<Appointment> viewAppointments(LocalDate appDate, String centreId, String testID) throws AppointmentException;
	public List<Appointment> viewUserAppointments(String contactNo) throws AppointmentException;
	public List<Appointment> viewAdmnAppointments(String slotId) throws AppointmentException;
	
}
