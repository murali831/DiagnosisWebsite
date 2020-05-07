package com.cg.healthcare.service;

import java.util.List;

import com.cg.healthcare.dto.SlotForm;
import com.cg.healthcare.entity.CheckUpSlot;
import com.cg.healthcare.exceptions.DiagnosisException;
import com.cg.healthcare.exceptions.SlotException;
/**
 * 
 * @author Bhanu
 *
 */
public interface SlotTestService {
	
	public boolean addTestForDiagnosisCentre(String testId, String centreId)throws DiagnosisException;
	
	public List<CheckUpSlot> viewSlots(String testId, String centreId)throws SlotException;

	public boolean createNewSlot(SlotForm form);

}
