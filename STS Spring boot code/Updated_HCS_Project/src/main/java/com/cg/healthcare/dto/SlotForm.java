package com.cg.healthcare.dto;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

public class SlotForm {

	@DateTimeFormat(pattern="yyyy-M-d")
	private LocalDate slotDate;
	
	private int noOfApmts;
	
	private String diagnosisTestId;

	public LocalDate getSlotDate() {
		return slotDate;
	}

	public void setSlotDate(LocalDate slotDate) {
		this.slotDate = slotDate;
	}

	public int getNoOfApmts() {
		return noOfApmts;
	}

	public void setNoOfApmts(int noOfApmts) {
		this.noOfApmts = noOfApmts;
	}

	public String getDiagnosisTestId() {
		return diagnosisTestId;
	}

	public void setDiagnosisTestId(String diagnosisTestId) {
		this.diagnosisTestId = diagnosisTestId;
	}
	
	
	
	
	
}
