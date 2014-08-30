package com.tcs.ilp.fqpms.model;

import java.util.Date;

public class VehicleDamageProposal extends Proposal {

	private String vehicleid;
	private String natureofdamage;
	
	public VehicleDamageProposal(String proposalid, String userid,String vehicleid,Date dateofoccurance,
			double amountclaimed, String natureofdamage, String quoteId) {
		super(proposalid,userid, dateofoccurance, amountclaimed, quoteId);
		this.vehicleid = vehicleid;
		this.natureofdamage = natureofdamage;
		
	}

	public String getVehicleid() {
		return vehicleid;
	}

	public void setVehicleid(String vehicleid) {
		this.vehicleid = vehicleid;
	}

	public String getNatureofdamage() {
		return natureofdamage;
	}

	public void setNatureofdamage(String natureofdamage) {
		this.natureofdamage = natureofdamage;
	}
	
	

}
