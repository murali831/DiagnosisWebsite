package com.cg.healthcare.service;

import java.util.List;

import com.cg.healthcare.entity.CheckUp;
import com.cg.healthcare.entity.DiagnosisCentre;
import com.cg.healthcare.exceptions.DiagnosisException;
/*
 * @Author - Murali
 */
public interface DiagnosisService {

	public boolean addDiagnosisCentre(DiagnosisCentre centre);
	public boolean editDiagnosisCentre(DiagnosisCentre centre);
	public List<DiagnosisCentre> viewDiagmosisCentre() throws DiagnosisException;
	public DiagnosisCentre viewDiagnosisCentre(String centreId) throws DiagnosisException;
	public List<CheckUp> viewCheckUp() throws DiagnosisException;
}
