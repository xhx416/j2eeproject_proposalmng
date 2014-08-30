package com.tcs.ilp.fqpms.bean;

import java.util.Date;

public class InsuranceBean {
	private String insuranceID;
	private String typeOfInsurance;
	private String vehicleID;
	private String coverageAmount;
	private String duration;
	private String startDate;
	private String premium;
	private String userID;
	private double balance;
	private double availablebalance;
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
	public String getCoverageAmount() {
		return coverageAmount;
	}
	public void setCoverageAmount(String coverageAmount) {
		this.coverageAmount = coverageAmount;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getPremium() {
		return premium;
	}
	public void setPremium(String premium) {
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
	public double getAvailablebalance() {
		return availablebalance;
	}
	public void setAvailablebalance(double availablebalance) {
		this.availablebalance = availablebalance;
	}
	public InsuranceBean(String insuranceID, String typeOfInsurance,
			String vehicleID, String coverageAmount, String duration,
			String startDate, String premium, String userID, double balance,
			double availablebalance) {
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
		this.availablebalance = availablebalance;
	}

}
