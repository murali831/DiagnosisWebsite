package com.cg.healthcare.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cg.healthcare.dao.IHealthCareDao;
import com.cg.healthcare.dto.SlotForm;
import com.cg.healthcare.entity.CheckUp;
import com.cg.healthcare.entity.CheckUpSlot;
import com.cg.healthcare.entity.DiagnosisCentre;
import com.cg.healthcare.entity.DiagnosisTest;
import com.cg.healthcare.exceptions.DiagnosisException;
import com.cg.healthcare.exceptions.SlotException;
import com.cg.healthcare.util.HealthCareConstants;

@Service
@Transactional
public class SlotTestServiceImpl implements SlotTestService {

	@Autowired
	private IHealthCareDao dao;

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	/*
	 * Description:    The method  mentioned below adds the appointment to the appointment DB.
	 * Method:         addTestForDiagnosisCentre() - Here we are creating instance of diagnosis test by which we can check whether center is available.
	 *                 and add that test to center                                         
	 * Exceptions      If passed center is not available then it pops center not available exception and same as with test while adding  
	 * @return type is boolean 
	 */
	@Override
	public boolean addTestForDiagnosisCentre(String testId, String centreId) throws DiagnosisException {
		DiagnosisTest dtest = new DiagnosisTest();
		DiagnosisCentre centre = dao.viewDiagnosisCentre(centreId);
		if (centre == null)
			throw new DiagnosisException(HealthCareConstants.CENTRE_NOT_AVAILABLE);
		CheckUp test = dao.viewCheckup(testId);
		if(test == null)
			throw new DiagnosisException(HealthCareConstants.TEST_NOT_AVAILABLE);
		dtest.setDiagnosisCentre(centre);
		dtest.setCheckup(test);
		dtest.setDiagnosisTestId(centreId + testId);
		return dao.addTestForDiagnosisCentre(dtest);
	}
	/*
	 * Description:    The method  mentioned below view the slots available in DB.
	 * Method:         viewSlots() - It shows all the slots that are available and it filer the slot to show only those which are after presnt and after days available slots  
	 * Exceptions      If passed test id has not slots then its shows no slots available exception.   
	 * @return type is List
	 */
	@Override
	public List<CheckUpSlot> viewSlots(String testId, String centreId) throws SlotException {
		 List<CheckUpSlot> lst =  dao.viewSlots(testId, centreId);
		 if(lst.isEmpty())
			 throw new SlotException(HealthCareConstants.SLOT_NOT_AVAILABLE);
		 lst=lst.stream().filter(slot->slot.getSlotDate().equals(LocalDate.now()) || slot.getSlotDate().isAfter(LocalDate.now())).collect(Collectors.toList());
		 return lst;
	}
	/*
	 * Description:    The method  mentioned below adds slot to DB.
	 * Method:         createNewSlot() - It creates new slot it first checks for diagnosis test and center to exits and by editslot method it updates the count of appointment.  
     *                 and set slot id as center id , test id, slot date.  
	 * @return type is boolean
	 */
	@Override
	public boolean createNewSlot(SlotForm slotForm) {
		CheckUpSlot slot = new CheckUpSlot();
		DiagnosisTest diagnosisTest = dao.getDiagnosisTest(slotForm.getDiagnosisTestId());
		slot.setNumOfApp(slotForm.getNoOfApmts());
		slot.setSlotDate(slotForm.getSlotDate());
		slot.setDiagnosisTest(diagnosisTest);
		slot.setTestSlotId(diagnosisTest.getDiagnosisCentre().getCentreId()+diagnosisTest.getCheckup().getTestId()+slotForm.getSlotDate());
		return dao.createNewSlot(slot);
	}

}
