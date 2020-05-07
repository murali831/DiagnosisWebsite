package com.cg.healthcare.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cg.healthcare.dao.IHealthCareDao;
import com.cg.healthcare.entity.Appointment;
import com.cg.healthcare.entity.DiagnosisTest;
import com.cg.healthcare.exceptions.AppointmentException;
import com.cg.healthcare.exceptions.DiagnosisException;
import com.cg.healthcare.exceptions.SearchTestException;
import com.cg.healthcare.util.HealthCareConstants;

@Service
@Transactional
public class ViewDiagnosisTestServiceImpl implements ViewDaignosisTestService{

	@Autowired
	IHealthCareDao dao;
	
	@Override
	public List<DiagnosisTest> getDiagnosisCentreForTests(String testID) throws DiagnosisException {
		List<DiagnosisTest> dlist= dao.getDiagnosisCentreForTests(testID);
		if(dlist.isEmpty())
			throw new DiagnosisException(HealthCareConstants.CENTRE_NOT_AVAILABLE);
		dlist.sort((d1,d2)-> d1.getDiagnosisCentre().getCentreId().compareTo(d2.getDiagnosisCentre().getCentreId()));
		return dlist;
	}

	@Override
	public List<DiagnosisTest> viewTestsForDiagnosisCentre(String diagnosisCentreId) throws DiagnosisException {
		List<DiagnosisTest> dlist= dao.viewTestsForDiagnosisCentre(diagnosisCentreId);
		if(dlist.isEmpty())
			throw new DiagnosisException(HealthCareConstants.TEST_NOT_AVAILABLE);
		dlist.sort((d1,d2)-> d1.getCheckup().getTestId().compareTo(d2.getCheckup().getTestId()));
		return dlist;
	}

	@Override
	public List<Appointment> viewAppointments(LocalDate appDate, String centreId, String testID) throws AppointmentException {
		List<Appointment> lst =  dao.viewAppointments(appDate, centreId, testID);
		if(lst.isEmpty())
			throw new AppointmentException(HealthCareConstants.NO_APPOINTMENTS);
		return lst;
	}

	@Override
	public List<DiagnosisTest> searchTestsForDiagnosisCentre(String searchTest) throws SearchTestException {
		List<DiagnosisTest> dlist = dao.searchDiagnosisCentreForTests(searchTest);
		if(dlist.isEmpty())
			throw new SearchTestException(HealthCareConstants.NOT_AVAILABLE);
		dlist.sort((d1,d2)-> d1.getDiagnosisCentre().getCentreId().compareTo(d2.getDiagnosisCentre().getCentreId()));
		List<DiagnosisTest> dlist2 = dlist.subList(0, 1);
		return dlist2;
	}

	

	
}



