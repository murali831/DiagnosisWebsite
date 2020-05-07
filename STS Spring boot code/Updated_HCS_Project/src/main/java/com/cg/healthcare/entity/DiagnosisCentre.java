package com.cg.healthcare.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/*********************************************************************************************
 * @author Bhanu Prakash Kavuri
 * Class: DiagnosisCentre
 *********************************************************************************************/
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name="lpu_diagnosis_centre")
public class DiagnosisCentre {
	
	@Id
	@Column(name="centre_id")
	private String centreId;
	@Column(name="centre_name", length=25)
	private String centreName;
	@Column(name="centre_addr", length=50)
	private String address;
	@Column(name="area", length=25)
	private String area;
	@Column(name="city", length=25)
	private String city;
	@Column(name="contact", length=10)
	private String contact;
	
	public DiagnosisCentre() {
		
	}


	public String getCentreId() {
		return centreId;
	}


	public void setCentreId(String centreId) {
		this.centreId = centreId;
	}


	public String getCentreName() {
		return centreName;
	}


	public void setCentreName(String centreName) {
		this.centreName = centreName;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getArea() {
		return area;
	}


	public void setArea(String area) {
		this.area = area;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public String getContact() {
		return contact;
	}


	public void setContact(String contact) {
		this.contact = contact;
	}


	

	

	

}
