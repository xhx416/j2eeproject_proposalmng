package com.tcs.ilp.fqpms.model;

public class Vehicle {
   private String vehicleID;
   private String make;
   private String model;
   private int yearOfManufacture;
   private String vehicleType;
   private double price;
   private String userID;
   private String lincesenumber;


public Vehicle(String vehicleID, String make, String model,
		int yearOfManufacture, String vehicleType, double price) {
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

public int getYearOfManufacture() {
	return yearOfManufacture;
}

public void setYearOfManufacture(int yearOfManufacture) {
	this.yearOfManufacture = yearOfManufacture;
}

public String getVehicleType() {
	return vehicleType;
}

public void setVehicleType(String vehicleType) {
	this.vehicleType = vehicleType;
}

public double getPrice() {
	return price;
}

public void setPrice(double price) {
	this.price = price;
}

public String getUserID() {
	return userID;
}

public void setUserID(String userID) {
	this.userID = userID;
}


public String getLincesenumber() {
	return lincesenumber;
}

public void setLincesenumber(String lincesenumber) {
	this.lincesenumber = lincesenumber;
}
	
   
	
	
}
