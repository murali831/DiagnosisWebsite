package com.cg.healthcare.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/*********************************************************************************************
 * @author Bhanu Prakash Kavuri
 * Class: CheckUpSlot
 *********************************************************************************************/
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name="lpu_checkup_slot")
public class CheckUpSlot {
	@Id
	@Column(name="test_slot_id")
	private String testSlotId;
	
	@Column(name="test_slot_date")
	private LocalDate slotDate;
	
	@Column(name="no_appointments")
	private int numOfApp;
	
	@ManyToOne
	@JoinColumn(name="diagnosis_test_id", referencedColumnName = "diagnosis_test_id")
	private DiagnosisTest diagnosisTest = new DiagnosisTest();
	
		
	public CheckUpSlot() {
		super();
		
	}


	public String getTestSlotId() {
		return testSlotId;
	}


	public void setTestSlotId(String testSlotId) {
		this.testSlotId = testSlotId;
	}


	public LocalDate getSlotDate() {
		return slotDate;
	}


	public void setSlotDate(LocalDate slotDate) {
		this.slotDate = slotDate;
	}


	public int getNumOfApp() {
		return numOfApp;
	}


	public void setNumOfApp(int numOfApp) {
		this.numOfApp = numOfApp;
	}


	public DiagnosisTest getDiagnosisTest() {
		return diagnosisTest;
	}


	public void setDiagnosisTest(DiagnosisTest diagnosisTest) {
		this.diagnosisTest = diagnosisTest;
	}


	
}
