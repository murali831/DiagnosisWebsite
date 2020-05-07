package com.cg.healthcare.dao;

import java.time.LocalDate;
import java.util.List;

import com.cg.healthcare.entity.Appointment;
import com.cg.healthcare.entity.CheckUp;
import com.cg.healthcare.entity.CheckUpSlot;
import com.cg.healthcare.entity.DiagnosisCentre;
import com.cg.healthcare.entity.DiagnosisTest;
import com.cg.healthcare.entity.User;

public interface IHealthCareDao {

    User getUser(String userID);
	
	List<DiagnosisCentre> viewDiagnosisCentre();
	
	boolean addDiadnosisCentre(DiagnosisCentre centre);
	
	boolean editDiadnosisCentre(DiagnosisCentre centre);
	
	 DiagnosisCentre viewDiagnosisCentre(String diagnosisId);
	
	CheckUp viewCheckup(String testId);
	
	boolean addTest(CheckUp checkUp);
	
	boolean addTestForDiagnosisCentre(DiagnosisTest diagnosisTest);
	
	List<CheckUp> viewCheckUp();
	
	List<DiagnosisTest> viewTestsForDiagnosisCentre(String diagnosisCentreId);
	
	DiagnosisTest getDiagnosisTest(String centreId, String testID);
	
	DiagnosisTest getDiagnosisTest(String diagnosisTestID);
	
	List<DiagnosisTest> getDiagnosisCentreForTests(String testID);
	
	public List<DiagnosisTest> searchDiagnosisCentreForTests(String searchStr);
	
	List<CheckUpSlot> viewSlots(String testId, String centreId);
	
	boolean createNewSlot(CheckUpSlot slot);
	
	boolean editSlot(CheckUpSlot slot);
	
	List<Appointment> viewAppointments(LocalDate appDate, String centreId, String testID);
	
	List<Appointment> viewAppointments(String slotId);
	
	List<Appointment> viewUserAppointments(String contactNo);
	
	boolean makeAppointment(Appointment appointment);
	
	boolean removeAppointment(Appointment appoinment);
	
	Appointment viewAppointment(String apmtID);
	
	int countDiagnosisTest();
	
	
	CheckUpSlot getCheckupslot(String slotID);
}
