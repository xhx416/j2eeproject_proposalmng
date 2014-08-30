package com.tcs.ilp.fqpms.model;

import java.util.Date;

import com.tcs.ilp.fqpms.dao.ProposalDAO;
import com.tcs.ilp.fqpms.exception.FQException;

public class Insurance {
	private String insuranceID;
	private String typeOfInsurance;
	private String vehicleID;
	private double coverageAmount;
	private int duration;
	Date startDate;
	private double premium;
	private String userID;
	private double balance;
	private double availableBalance;
	private String licenseNumber;
	public ProposalDAO dao = new ProposalDAO();
	public String getInsuranceID() {
		return insuranceID;
	}
	public void setInsuranceID(String insuranceID) {
		this.insuranceID = insuranceID;
	}
	public String getTypeOfInsurance() {
		return typeOfInsurance;
	}
	public void setTypeOfInsurance(String typeOfInsurance) {
		this.typeOfInsurance = typeOfInsurance;
	}
	public String getVehicleID() {
		return vehicleID;
	}
	public void setVehicleID(String vehicleID) {
		this.vehicleID = vehicleID;
	}
	public double getCoverageAmount() {
		return coverageAmount;
	}
	public void setCoverageAmount(double coverageAmount) {
		this.coverageAmount = coverageAmount;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public double getPremium() {
		return premium;
	}
	public void setPremium(double premium) {
		this.premium = premium;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public double getAvailableBalance() {
		return availableBalance;
	}
	public void setAvailableBalance(double availableBalance) {
		this.availableBalance = availableBalance;
	}
	public Insurance(String insuranceID, String typeOfInsurance,
			String vehicleID, double coverageAmount, int duration,
			Date startDate, double premium, String userID, double balance,
			double availableBalance) throws FQException {
		super();
		this.insuranceID = insuranceID;
		this.typeOfInsurance = typeOfInsurance;
		this.vehicleID = vehicleID;
		this.coverageAmount = coverageAmount;
		this.duration = duration;
		this.startDate = startDate;
		this.premium = premium;
		this.userID = userID;
		this.balance = balance;
		this.availableBalance = availableBalance;
		this.licenseNumber = dao.getLicenseNumberbyVid(vehicleID);
		
		
	}
	public String getLicenseNumber() {
		return licenseNumber;
	}
	public void setLicenseNumber(String licenseNumber) {
		this.licenseNumber = licenseNumber;
	}
	
	
	
	

	
	

}
