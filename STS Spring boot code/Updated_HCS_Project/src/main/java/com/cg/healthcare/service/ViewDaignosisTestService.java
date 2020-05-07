package com.cg.healthcare.service;

import java.time.LocalDate;
import java.util.List;

import com.cg.healthcare.entity.Appointment;
import com.cg.healthcare.entity.DiagnosisTest;
import com.cg.healthcare.exceptions.AppointmentException;
import com.cg.healthcare.exceptions.DiagnosisException;
import com.cg.healthcare.exceptions.SearchTestException;
/**
 * 
 * @author Laxman
 *
 */
public interface ViewDaignosisTestService {

	

	List<DiagnosisTest> getDiagnosisCentreForTests(String testID) throws DiagnosisException;
    
	public List<DiagnosisTest> viewTestsForDiagnosisCentre(String diagnosisId) throws DiagnosisException;
	
	public List<DiagnosisTest> searchTestsForDiagnosisCentre(String searchTest)throws SearchTestException;
	
	public List<Appointment> viewAppointments(LocalDate appDate, String centreId, String testID)throws AppointmentException;
}
