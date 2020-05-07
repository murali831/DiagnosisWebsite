package com.cg.healthcare.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cg.healthcare.dao.IHealthCareDao;
import com.cg.healthcare.entity.CheckUp;
import com.cg.healthcare.entity.DiagnosisCentre;
import com.cg.healthcare.exceptions.DiagnosisException;
import com.cg.healthcare.util.HealthCareConstants;

@Service
@Transactional
public class DiagnosisServiceImpl implements DiagnosisService{

	@Autowired
	IHealthCareDao dao;
	
	@Override
	public boolean addDiagnosisCentre(DiagnosisCentre centre) {
		
		return dao.addDiadnosisCentre(centre);
	}

	@Override
	public boolean editDiagnosisCentre(DiagnosisCentre centre) {
	
		return dao.editDiadnosisCentre(centre);
	}

	@Override
	public List<DiagnosisCentre> viewDiagmosisCentre() throws DiagnosisException {
		
		List<DiagnosisCentre> dlist = dao.viewDiagnosisCentre();
		if(dlist.isEmpty())
			throw new DiagnosisException(HealthCareConstants.CENTRE_NOT_AVAILABLE);
		dlist.sort((c1,c2)->c1.getCentreId().compareTo(c2.getCentreId()));
		return dlist;
	}

	@Override
	public DiagnosisCentre viewDiagnosisCentre(String centreId) throws DiagnosisException {
		DiagnosisCentre centre = dao.viewDiagnosisCentre(centreId);
		if(centre == null) throw new DiagnosisException(HealthCareConstants.CENTRE_NOT_AVAILABLE);
		return centre;
	}

	@Override
	public List<CheckUp> viewCheckUp() throws DiagnosisException {
		List<CheckUp> tlist = dao.viewCheckUp();
		if(tlist.isEmpty())
			throw new DiagnosisException(HealthCareConstants.TEST_NOT_AVAILABLE);
		tlist.sort((c1,c2)->c1.getTestId().compareTo(c2.getTestId()));
		return tlist;
	}

	
}
