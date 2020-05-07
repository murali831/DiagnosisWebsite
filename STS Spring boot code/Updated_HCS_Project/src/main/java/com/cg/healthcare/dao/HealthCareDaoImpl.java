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
	/*
	 * Description:    The method  mentioned below shows the list of diagnostic center that are avaiable in DB
	 * Method:         viewDiagnosisCentre() - View Diagnosis Centre is uses jpql query to call all the diagnosis centre's available                    
	 * @return type is List 
	 */
	@Override
	public List<DiagnosisCentre> viewDiagnosisCentre() {
		TypedQuery<DiagnosisCentre> query = em.createQuery("from DiagnosisCentre", DiagnosisCentre.class);
		return query.getResultList();
	}
	/*
	 * Description:    The method  mentioned below add the instance of entity manager to diagnostic center that are added to DB using persist method.
	 * Method:         addDiadnosisCentre() - add Diagnosis Centre  uses entity manager persist method to add the centre to DB by the instance of Diagnosis centre                    
	 * @return type is Boolean 
	 */
	@Override
	public boolean addDiadnosisCentre(DiagnosisCentre centre) {
		em.persist(centre);
		return true;
	}
	/*
	 * Description:    The method  mentioned below shows the diagnostic center based on centre id that are avaiable in DB
	 * Method:         viewDiagnosisCentre() - View Diagnosis Centre is uses entity manager find method to find the diagnosis centre's available                    
	 * @return type is  DiagnosisCentre 
	 */
	@Override
	public DiagnosisCentre viewDiagnosisCentre(String diagnosisId) {
		
		return em.find(DiagnosisCentre.class, diagnosisId);
	}
	/*
	 * Description:    The method  mentioned below adds test using checkup class to DB 	
	 * Method:         addTest() - add test is uses uses entity manager persist method to add the centre to DB by the instance of Checkup class                     
	 * @return type is boolean 
	 */
	@Override
	public boolean addTest(CheckUp checkUp) {
		em.persist(checkUp);
		return true;
	}
	/*
	 * Description:    The method  mentioned below adds the test to particular center
	 * Method:         addTestForDiagnosisCentre() - It adds the test to center firstly it uses entity manager persist method.                
	 * @return type is boolean 
	 */
	@Override
	public boolean addTestForDiagnosisCentre(DiagnosisTest diagnosisTest) {
		em.persist(diagnosisTest);
		return true;
	}
	/*
	 * Description:    The method  mentioned below shows the list of tests (Checkup class) for a particular centre that are avaiable in DB
	 * Method:         viewTestsForDiagnosisCentre() - This methods shows the tests based on inner join it first takes the diagnosis test center id and from 
	 *                 it joins the both checkup class and from their to diagnosis centre so now we have all the data in one so from there we fetch for that particular 
	 *                 centre id                    
	 * @return type is List 
	 */
	@Override
	public List<DiagnosisTest> viewTestsForDiagnosisCentre(String diagnosisCentreId) {
		String jpql = "from DiagnosisTest dt inner join fetch dt.checkup chkup inner join fetch dt.diagnosisCentre dc where dc.centreId=:cid";
		TypedQuery<DiagnosisTest> query = em.createQuery(jpql, DiagnosisTest.class);
		query.setParameter("cid", diagnosisCentreId);
		return query.getResultList();
	}
	/*
	 * Description:    The method  mentioned below shows the list of slots avaible for that particular test of particular diagnostic center that are avaiable in DB
	 * Method:         viewSlots() - It uses jpql query to call all the solt's available for that particular test, centre in DB                    
	 * @return type is List 
	 */
	@Override
	public List<CheckUpSlot> viewSlots(String testId, String centreID) {
		String jpql = "from CheckUpSlot cs inner join fetch cs.diagnosisTest dt inner join fetch dt.checkup chkup inner join fetch dt.diagnosisCentre dc "
				+ "where chkup.testId=:tid and dc.centreId=:cid";
		TypedQuery<CheckUpSlot> query = em.createQuery(jpql, CheckUpSlot.class);
		query.setParameter("tid", testId);
		query.setParameter("cid", centreID);
		return query.getResultList();
		
	}
	/*
	 * Description:    The method  mentioned below shows the list of diagnostic center that are available in DB
	 * Method:         createNewSlot() - It is uses persist methods to add the slots to test in DB                    
	 * @return type is boolean 
	 */
	@Override
	public boolean createNewSlot(CheckUpSlot slot) {
		em.persist(slot);
		return true;
	}
	/*
	 * Description:    The method  mentioned below shows the list of appointments that are avaiable in DB
	 * Method:         viewAppointments() - It uses jpql query to call all the appointment's available in the db by giving all the required parameters                    
	 * @return type is List 
	 */
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
	/*
	 * Description:    The method  mentioned below adds the appointment to the appointment DB.
	 * Method:         makeAppointment() - it uses persist method to add the appointment to DB.                    
	 * @return type is boolean 
	 */
	@Override
	public boolean makeAppointment(Appointment appointment) {
		em.persist(appointment);
		return true;
	}
	/*
	 * Description:    The method  mentioned below edits the diagnostic center that is available in DB
	 * Method:         editDiadnosisCentre() - it uses merge method to update the  diagnosis centre's.                    
	 * @return type is boolean
	 */
	@Override
	public boolean editDiadnosisCentre(DiagnosisCentre centre) {
		em.merge(centre);
		return true;
	}
	/*
	 * Description:    The method  mentioned below removes the appointment from the DB
	 * Method:         removeAppointment() - it uses remove method to remove the data from the appointment table                     
	 * @return type is boolean 
	 */
	@Override
	public boolean removeAppointment(Appointment appointment) {
		em.remove(appointment);
		return true;
	}
	/*
	 * Description:    The method  mentioned below shows the appointment for that passed appointment id
	 * Method:         viewAppointment() - it uses find method to find the appointment by the instance of Appointment                    
	 * @return type is Appointment 
	 */
	@Override
	public Appointment viewAppointment(String apmtID) {
	
		return em.find(Appointment.class, apmtID);
	}
	/*
	 * Description:    The method  mentioned below shows the list of diagnostic center in which this test is available
	 * Method:         getDiagnosisCentreForTests() - It uses jpql query to call all the diagnosis centre's available for that test.                    
	 * @return type is List 
	 */
	@Override
	public List<DiagnosisTest> getDiagnosisCentreForTests(String testID) {
		String jpql = "from DiagnosisTest dt inner join fetch dt.checkup chkup inner join fetch dt.diagnosisCentre dc where chkup.testId=:tid";
		TypedQuery<DiagnosisTest> query = em.createQuery(jpql, DiagnosisTest.class);
		query.setParameter("tid", testID);
		return query.getResultList();
	}
	/*
	 * Description:    The method  mentioned below search the DB by using test name and test id and return in which the test are available 
	 * Method:         searchDiagnosisCentreForTests() -It uses jpql query to call all the diagnosis centre's available in whch that test is available                    
	 * @return type is List 
	 */
	@Override
	public List<DiagnosisTest> searchDiagnosisCentreForTests(String strSearch) {
		String jpql = "from DiagnosisTest dt inner join fetch dt.checkup chkup inner join fetch dt.diagnosisCentre dc where chkup.testId like :str or chkup.testName like :str";
		TypedQuery<DiagnosisTest> query = em.createQuery(jpql, DiagnosisTest.class);
		query.setParameter("str", strSearch);
		return query.getResultList();
	}
	/*
	 * Description:    The method  mentioned below show the test by testid passed
	 * Method:         viewCheckup() - It uses find method to find the test by using testid as parameter from checkup table DB                     
	 * @return type is Checkup 
	 */
	@Override
	public CheckUp viewCheckup(String testId) {
		
		return em.find(CheckUp.class, testId);
	}
	/*
	 * Description:    The method  mentioned below counts the test available in center.
	 * Method:         countDiagnosisTest() - it uses jpql query to count number of test avaible in that center.                    
	 * @return type is Int 
	 */
	@Override
	public int countDiagnosisTest() {
		String jpql = "select count(diagnosisTestId) from DiagnosisTest";
		TypedQuery<Integer> query = em.createQuery(jpql, Integer.class);
		
		return query.getSingleResult();
	}
	/*
	 * Description:    The method  mentioned below shows the diagnostic center for particular center id and test id that are available in DB
	 * Method:         getDiagnosisTest() - It uses jpql query to call the diagnosis centre's available for that centre id and test id                    
	 * @return type is DiagnosisTest 
	 */
	@Override
	public DiagnosisTest getDiagnosisTest(String centreID, String testID) {
		String jpql = "from DiagnosisTest dt inner join fetch dt.checkup chkup inner join fetch dt.diagnosisCentre dc where chkup.testId=:tid and dc.centreId=:cid";
		TypedQuery<DiagnosisTest> query = em.createQuery(jpql, DiagnosisTest.class);
		query.setParameter("tid", testID);
		query.setParameter("cid", centreID);
		return query.getSingleResult();
		
	}
	/*
	 * Description:    The method  mentioned below is used to get the slot for passed slot id
	 * Method:         getCheckupslot() - It used find method to find the slot from checkup slot table                   
	 * @return type is CheckUpSlot 
	 */
	@Override
	public CheckUpSlot getCheckupslot(String slotID) {
		
		return em.find(CheckUpSlot.class, slotID);
	}
	/*
	 * Description:    The method  mentioned below shows the list of test that are in DB.
	 * Method:         viewCheckUp() - It uses jpql query to call all the test available in checkup table                      
	 * @return type is List 
	 */
	@Override
	public List<CheckUp> viewCheckUp() {
		String jpql = "from CheckUp";
		TypedQuery<CheckUp> query = em.createQuery(jpql, CheckUp.class);
		
		return query.getResultList();
	}
	/*
	 * Description:    The method  mentioned below shows the role of user either customer or admin
	 * Method:         getUser() - It uses find methods to find the role whether it is customer or admin from the DB table                  
	 * @return type is User 
	 */
	@Override
	public User getUser(String userID) {
		
		return em.find(User.class, userID);
	}
	/*
	 * Description:    The method  mentioned below get the test from diagnosis test table passing diagnosis id
	 * Method:         getDiagnosisTest() - It uses find method to check all the diagnosis test's available and find the test in diagnosis test table and return                   
	 * @return type is DiagnosisTest 
	 */
	@Override
	public DiagnosisTest getDiagnosisTest(String diagnosisTestID) {
		
		return em.find(DiagnosisTest.class, diagnosisTestID);
	}
	/*
	 * Description:    The method  mentioned below update the slots according to admins request 
	 * Method:         editSlot() - It uses merge methods to update the slot in checkup slot table.                    
	 * @return type is boolean 
	 */
	@Override
	public boolean editSlot(CheckUpSlot slot) {
		em.merge(slot);
		return true;
	}
	/*
	 * Description:    The method  mentioned below shows the list of appointments for that particular slot id
	 * Method:         viewAppointments() - It uses jpql query to call all the appointment's available for that particular slot passed by user and return those to user                       
	 * @return type is List 
	 */
	@Override
	public List<Appointment> viewAppointments(String slotId) {
		String jpql = "from Appointment a inner join fetch a.slot s inner join fetch s.diagnosisTest dt "
				+ "inner join fetch dt.diagnosisCentre dc inner join fetch dt.checkup chkup where  s.testSlotId=:sid";
		TypedQuery<Appointment> query = em.createQuery(jpql, Appointment.class);
		query.setParameter("sid", slotId);
	
		return query.getResultList();
	}
	/*
	 * Description:    The method  mentioned below shows the list of appointments that user has booked by using contact as key
	 * Method:         viewUserAppointments() - it uses jpql query to call all the appointment's avaible and that are made by user and we fetcgh data by contact number from DB                    
	 * @return type is List 
	 */
	@Override
	public List<Appointment> viewUserAppointments(String contactNo) {
		String jpql = "from Appointment a inner join fetch a.slot s inner join fetch s.diagnosisTest dt "
				+ "inner join fetch dt.diagnosisCentre dc inner join fetch dt.checkup chkup where  a.userContactNum=:cno";
		TypedQuery<Appointment> query = em.createQuery(jpql, Appointment.class);
		query.setParameter("cno", Long.parseLong(contactNo));
	
		return query.getResultList();
		
	}

}



