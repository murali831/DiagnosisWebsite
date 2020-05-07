package com.cg.healthcare.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@DynamicInsert
@DynamicUpdate
@Table(name="lpu_diagnosis_test")
public class DiagnosisTest {

	@Id
	@Column(name="diagnosis_test_id")
	private String diagnosisTestId;
	
	@ManyToOne
	@JoinColumn(name="test_id", referencedColumnName = "test_id")
	private CheckUp checkup = new CheckUp();
	
	@ManyToOne
	@JoinColumn(name="centre_id", referencedColumnName = "centre_id")
	private DiagnosisCentre diagnosisCentre = new DiagnosisCentre();

	public String getDiagnosisTestId() {
		return diagnosisTestId;
	}

	public void setDiagnosisTestId(String diagnosisTestId) {
		this.diagnosisTestId = diagnosisTestId;
	}

	public CheckUp getCheckup() {
		return checkup;
	}

	public void setCheckup(CheckUp checkup) {
		this.checkup = checkup;
	}

	public DiagnosisCentre getDiagnosisCentre() {
		return diagnosisCentre;
	}

	public void setDiagnosisCentre(DiagnosisCentre diagnosisCentre) {
		this.diagnosisCentre = diagnosisCentre;
	}
	
	
}
