package com.tcs.ilp.fqpms.model;

import java.util.Date;

public class LiabilityProposal extends Proposal{
	
	private String vehicleid;
	private String typeofliability;

	public LiabilityProposal(String proposalid,String userid,String vehicleid, Date dateofoccurance,
			double amountclaimed,  String typeofliability, String quoteId) {
		super(proposalid, userid, dateofoccurance, amountclaimed, quoteId);
		this.vehicleid = vehicleid;
		this.typeofliability = typeofliability;
		// TODO Auto-generated constructor stub
	}

	public String getVehicleid() {
		return vehicleid;
	}

	public void setVehicleid(String vehicleid) {
		this.vehicleid = vehicleid;
	}

	public String getTypeofliability() {
		return typeofliability;
	}

	public void setTypeofliability(String typeofliability) {
		this.typeofliability = typeofliability;
	}
	

}
