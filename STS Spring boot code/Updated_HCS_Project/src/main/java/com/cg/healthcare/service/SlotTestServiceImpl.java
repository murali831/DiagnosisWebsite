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

	@Override
	public List<CheckUpSlot> viewSlots(String testId, String centreId) throws SlotException {
		 List<CheckUpSlot> lst =  dao.viewSlots(testId, centreId);
		 if(lst.isEmpty())
			 throw new SlotException(HealthCareConstants.ID_ALREADY_EXISTS);
		 lst=lst.stream().filter(slot->slot.getSlotDate().equals(LocalDate.now()) || slot.getSlotDate().isAfter(LocalDate.now())).collect(Collectors.toList());
		 return lst;
	}

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
