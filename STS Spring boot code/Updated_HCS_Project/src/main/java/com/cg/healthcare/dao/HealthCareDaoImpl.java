package com.cg.healthcare.dao;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.cg.healthcare.entity.Appointment;
import com.cg.healthcare.entity.CheckUp;
import com.cg.healthcare.entity.CheckUpSlot;
import com.cg.healthcare.entity.DiagnosisCentre;
import com.cg.healthcare.entity.DiagnosisTest;
import com.cg.healthcare.entity.User;

@Repository
public class HealthCareDaoImpl implements IHealthCareDao{

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<DiagnosisCentre> viewDiagnosisCentre() {
		TypedQuery<DiagnosisCentre> query = em.createQuery("from DiagnosisCentre", DiagnosisCentre.class);
		return query.getResultList();
	}

	@Override
	public boolean addDiadnosisCentre(DiagnosisCentre centre) {
		em.persist(centre);
		return true;
	}

	@Override
	public DiagnosisCentre viewDiagnosisCentre(String diagnosisId) {
		
		return em.find(DiagnosisCentre.class, diagnosisId);
	}

	@Override
	public boolean addTest(CheckUp checkUp) {
		em.persist(checkUp);
		return true;
	}

	@Override
	public boolean addTestForDiagnosisCentre(DiagnosisTest diagnosisTest) {
		em.persist(diagnosisTest);
		return true;
	}

	@Override
	public List<DiagnosisTest> viewTestsForDiagnosisCentre(String diagnosisCentreId) {
		String jpql = "from DiagnosisTest dt inner join fetch dt.checkup chkup inner join fetch dt.diagnosisCentre dc where dc.centreId=:cid";
		TypedQuery<DiagnosisTest> query = em.createQuery(jpql, DiagnosisTest.class);
		query.setParameter("cid", diagnosisCentreId);
		return query.getResultList();
	}

	@Override
	public List<CheckUpSlot> viewSlots(String testId, String centreID) {
		String jpql = "from CheckUpSlot cs inner join fetch cs.diagnosisTest dt inner join fetch dt.checkup chkup inner join fetch dt.diagnosisCentre dc "
				+ "where chkup.testId=:tid and dc.centreId=:cid";
		TypedQuery<CheckUpSlot> query = em.createQuery(jpql, CheckUpSlot.class);
		query.setParameter("tid", testId);
		query.setParameter("cid", centreID);
		return query.getResultList();
		
	}

	@Override
	public boolean createNewSlot(CheckUpSlot slot) {
		em.persist(slot);
		return true;
	}

	@Override
	public List<Appointment> viewAppointments(LocalDate appDate, String centreId, String testID) {
		String jpql = "from Appointment a inner join fetch a.slot s inner join fetch s.diagnosisTest dt "
				+ "inner join fetch dt.diagnosisCentre dc inner join fetch dt.checkup chkup where  s.slotDate = :sdate and chkup.testId=:tid and dc.centreId=:cid";
		TypedQuery<Appointment> query = em.createQuery(jpql, Appointment.class);
		query.setParameter("sdate", appDate);
		query.setParameter("tid", testID);
		query.setParameter("cid", centreId);
		return query.getResultList();
		
	}

	@Override
	public boolean makeAppointment(Appointment appointment) {
		em.persist(appointment);
		return true;
	}

	@Override
	public boolean editDiadnosisCentre(DiagnosisCentre centre) {
		em.merge(centre);
		return true;
	}

	@Override
	public boolean removeAppointment(Appointment appointment) {
		em.remove(appointment);
		return true;
	}

	@Override
	public Appointment viewAppointment(String apmtID) {
	
		return em.find(Appointment.class, apmtID);
	}

	@Override
	public List<DiagnosisTest> getDiagnosisCentreForTests(String testID) {
		String jpql = "from DiagnosisTest dt inner join fetch dt.checkup chkup inner join fetch dt.diagnosisCentre dc where chkup.testId=:tid";
		TypedQuery<DiagnosisTest> query = em.createQuery(jpql, DiagnosisTest.class);
		query.setParameter("tid", testID);
		return query.getResultList();
	}

	@Override
	public List<DiagnosisTest> searchDiagnosisCentreForTests(String strSearch) {
		String jpql = "from DiagnosisTest dt inner join fetch dt.checkup chkup inner join fetch dt.diagnosisCentre dc where chkup.testId like :str or chkup.testName like :str";
		TypedQuery<DiagnosisTest> query = em.createQuery(jpql, DiagnosisTest.class);
		query.setParameter("str", strSearch);
		return query.getResultList();
	}
	@Override
	public CheckUp viewCheckup(String testId) {
		
		return em.find(CheckUp.class, testId);
	}

	@Override
	public int countDiagnosisTest() {
		String jpql = "select count(diagnosisTestId) from DiagnosisTest";
		TypedQuery<Integer> query = em.createQuery(jpql, Integer.class);
		
		return query.getSingleResult();
	}

	@Override
	public DiagnosisTest getDiagnosisTest(String centreID, String testID) {
		String jpql = "from DiagnosisTest dt inner join fetch dt.checkup chkup inner join fetch dt.diagnosisCentre dc where chkup.testId=:tid and dc.centreId=:cid";
		TypedQuery<DiagnosisTest> query = em.createQuery(jpql, DiagnosisTest.class);
		query.setParameter("tid", testID);
		query.setParameter("cid", centreID);
		return query.getSingleResult();
		
	}

	@Override
	public CheckUpSlot getCheckupslot(String slotID) {
		
		return em.find(CheckUpSlot.class, slotID);
	}

	@Override
	public List<CheckUp> viewCheckUp() {
		String jpql = "from CheckUp";
		TypedQuery<CheckUp> query = em.createQuery(jpql, CheckUp.class);
		
		return query.getResultList();
	}

	@Override
	public User getUser(String userID) {
		
		return em.find(User.class, userID);
	}

	@Override
	public DiagnosisTest getDiagnosisTest(String diagnosisTestID) {
		
		return em.find(DiagnosisTest.class, diagnosisTestID);
	}

	@Override
	public boolean editSlot(CheckUpSlot slot) {
		em.merge(slot);
		return true;
	}

	@Override
	public List<Appointment> viewAppointments(String slotId) {
		String jpql = "from Appointment a inner join fetch a.slot s inner join fetch s.diagnosisTest dt "
				+ "inner join fetch dt.diagnosisCentre dc inner join fetch dt.checkup chkup where  s.testSlotId=:sid";
		TypedQuery<Appointment> query = em.createQuery(jpql, Appointment.class);
		query.setParameter("sid", slotId);
	
		return query.getResultList();
	}

	@Override
	public List<Appointment> viewUserAppointments(String contactNo) {
		String jpql = "from Appointment a inner join fetch a.slot s inner join fetch s.diagnosisTest dt "
				+ "inner join fetch dt.diagnosisCentre dc inner join fetch dt.checkup chkup where  a.userContactNum=:cno";
		TypedQuery<Appointment> query = em.createQuery(jpql, Appointment.class);
		query.setParameter("cno", Long.parseLong(contactNo));
	
		return query.getResultList();
		
	}

}



