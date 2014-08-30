package com.tcs.ilp.fqpms.bean;


public class LiabilityProposalBean extends ProposalBean {

	private String vehicleid;
	private String typeofliability;

	public LiabilityProposalBean(String year, String month, String date,
			String amountclaimed, String vehicleid, String typeofliability) {
		super(year, month, date, amountclaimed);
		this.vehicleid = vehicleid;
		this.typeofliability = typeofliability;
	}

	public String getVehicleid() {
		return vehicleid;
	}

	public String getTypeofliability() {
		return typeofliability;
	}

}
