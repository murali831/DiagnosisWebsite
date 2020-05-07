package com.cg.healthcare.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cg.healthcare.dao.IHealthCareDao;
import com.cg.healthcare.dto.AppointmentForm;
import com.cg.healthcare.entity.Appointment;
import com.cg.healthcare.entity.CheckUpSlot;
import com.cg.healthcare.exceptions.AppointmentException;
import com.cg.healthcare.exceptions.SlotException;
import com.cg.healthcare.util.HealthCareConstants;
//31,39,40
@Service
@Transactional
public class AppointmentServiceImpl implements AppointmentService {
	@Autowired
	private IHealthCareDao dao;

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	@Override
	public String makeAppointment(AppointmentForm appointmentForm) throws SlotException {
		Appointment appointment = new Appointment();
		CheckUpSlot slot = dao.getCheckupslot(appointmentForm.getSlotID());
		int numofApp=slot.getNumOfApp();
		if(slot== null)
			throw new SlotException(HealthCareConstants.SLOT_NOT_AVAILABLE);
		LocalDateTime now = LocalDateTime.now();
		String ID= "" + now.getYear()+now.getMonthValue()+ now.getDayOfYear()+now.getMinute()+now.getSecond()+appointmentForm.getSlotID();
		appointment.setApmtId(ID);
		appointment.setPatientName(appointmentForm.getPatientName());
		appointment.setUserContactNum(appointmentForm.getContactNo());
		if(numofApp== 0)
			throw new SlotException(HealthCareConstants.NO_APPOINTMENTS);
		slot.setNumOfApp(slot.getNumOfApp()- 1);
		appointment.setSlot(slot);
		dao.editSlot(slot);
		dao.makeAppointment(appointment);
		return ID;
	}

	@Override
	public boolean removeAppointment(String apmtID) throws AppointmentException {
		Appointment apmt = dao.viewAppointment(apmtID);
		if(apmt.getSlot().getSlotDate().isBefore(LocalDate.now())) 
			throw new AppointmentException(HealthCareConstants.APPOINTMENT_NOT_CANCELLED);

		CheckUpSlot slot = apmt.getSlot();
		slot.setNumOfApp(slot.getNumOfApp() + 1);
		dao.editSlot(slot);
		dao.removeAppointment(apmt);
		return true;
		
	}

	@Override
	public List<Appointment> viewAppointments(LocalDate appDate, String centreId, String testID) throws AppointmentException {
		List<Appointment> lst =  dao.viewAppointments(appDate, centreId, testID);
		if(lst.isEmpty())
			throw new AppointmentException(HealthCareConstants.NO_APPOINTMENTS);
		return lst;
	}
	
	@Override
	public List<Appointment> viewAdmnAppointments(String slotId) throws AppointmentException {
		List<Appointment> lst =  dao.viewAppointments(slotId);
		if(lst.isEmpty())
			throw new AppointmentException(HealthCareConstants.NO_APPOINTMENTS);
		lst.sort((a1,a2)->a1.getPatientName().compareTo(a2.getPatientName()));
		return lst;
	}
	@Override
	public List<Appointment> viewUserAppointments(String contactNo) throws AppointmentException {
		List<Appointment> lst =  dao.viewUserAppointments(contactNo);
		if(lst.isEmpty())
			throw new AppointmentException(HealthCareConstants.NO_APPOINTMENTS);
		lst.sort((a1,a2)->a2.getSlot().getSlotDate().compareTo(a1.getSlot().getSlotDate()));
		return lst;
	}
}




