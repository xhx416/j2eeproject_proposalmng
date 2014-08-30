package com.tcs.ilp.fqpms.bean;

public class VehicleBean {
   private String vehicleID;
   private String make;
   private String model;
   private String yearOfManufacture;
   private String vehicleType;
   private String price;
   private String userID;
   
public VehicleBean(String vehicleID, String make, String model,
		String yearOfManufacture, String vehicleType, String price) {
	super();
	this.vehicleID = vehicleID;
	this.make = make;
	this.model = model;
	this.yearOfManufacture = yearOfManufacture;
	this.vehicleType = vehicleType;
	this.price = price;
}

public String getVehicleID() {
	return vehicleID;
}

public void setVehicleID(String vehicleID) {
	this.vehicleID = vehicleID;
}

public String getMake() {
	return make;
}

public void setMake(String make) {
	this.make = make;
}

public String getModel() {
	return model;
}

public void setModel(String model) {
	this.model = model;
}

public String getYearOfManufacture() {
	return yearOfManufacture;
}

public void setYearOfManufacture(String yearOfManufacture) {
	this.yearOfManufacture = yearOfManufacture;
}

public String getVehicleType() {
	return vehicleType;
}

public void setVehicleType(String vehicleType) {
	this.vehicleType = vehicleType;
}

public String getPrice() {
	return price;
}

public void setPrice(String price) {
	this.price = price;
}

public String getUserID() {
	return userID;
}

public void setUserID(String userID) {
	this.userID = userID;
}
   
   
	
}
