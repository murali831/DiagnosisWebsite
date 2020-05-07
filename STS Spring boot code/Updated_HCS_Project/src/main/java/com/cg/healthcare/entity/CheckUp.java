package com.cg.healthcare.entity;

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
 * Class: CheckUp
 *********************************************************************************************/
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name="lpu_checkup")
public class CheckUp {
	
	@Id
	@Column(name="test_id")
	private String testId;
	@Column(name="test_name", length=25)
	private String testName;
	@Column(name="amount")
	private double amount;
	
	
	
	

	/*****************************************************************************************
	 * Method: Constructor method - CheckUp
	 * @param testId
	 * @param testName
	 * @param amount
	 *****************************************************************************************/
	public CheckUp() {
		super();
		
	}

	public String getTestId() {
		return testId;
	}

	public void setTestId(String testId) {
		this.testId = testId;
	}

	public String getTestName() {
		return testName;
	}

	public void setTestName(String testName) {
		this.testName = testName;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	

	
	

}
