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
	/*
	 * Description:    The method  mentioned below gets the centers in which that test id test available. 
	 * Method:         getDiagnosisCentreForTests() - we'll get list of centers in a list and display to user
	 * Exceptions      If passed test is not available then it pops centers not available exception.  
	 * @return type is list 
	 */
	@Override
	public List<DiagnosisTest> getDiagnosisCentreForTests(String testID) throws DiagnosisException {
		List<DiagnosisTest> dlist= dao.getDiagnosisCentreForTests(testID);
		if(dlist.isEmpty())
			throw new DiagnosisException(HealthCareConstants.CENTRE_NOT_AVAILABLE);
		dlist.sort((d1,d2)-> d1.getDiagnosisCentre().getCentreId().compareTo(d2.getDiagnosisCentre().getCentreId()));
		return dlist;
	}
	/*
	 * Description:    The method  mentioned below gets the tests in which that center id center available. 
	 * Method:         viewTestsForDiagnosisCentre() - we'll get list of test in a list and display to user
	 * Exceptions      If passed test is not available then it pops tests not available exception.  
	 * @return type is List
	 */
	@Override
	public List<DiagnosisTest> viewTestsForDiagnosisCentre(String diagnosisCentreId) throws DiagnosisException {
		List<DiagnosisTest> dlist= dao.viewTestsForDiagnosisCentre(diagnosisCentreId);
		if(dlist.isEmpty())
			throw new DiagnosisException(HealthCareConstants.TEST_NOT_AVAILABLE);
		dlist.sort((d1,d2)-> d1.getCheckup().getTestId().compareTo(d2.getCheckup().getTestId()));
		return dlist;
	}
	/*
	 * Description:    The method  mentioned below search the test name entered by user and shows the tests in which that testname/ test id available. 
	 * Method:         searchTestsForDiagnosisCentre() - we'll get list of centers in a list and display to user
	 * Exceptions      If passed testname/ id  is not available then it pops tests not available exception.  
	 * @return type is list
	 */
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



