package com.tcs.ilp.fqpms.validate;

import com.tcs.ilp.fqpms.bean.ProposalBean;

public class VehicleDamageProposalBean extends ProposalBean {

	private String vehicleid;
	private String natureofdamage;

	public VehicleDamageProposalBean(String year, String month, String date,
			String amountclaimed, String vehicleid, String natureofdamage) {
		super(year, month, date, amountclaimed);
		this.vehicleid = vehicleid;
		this.natureofdamage = natureofdamage;
	}

	public String getVehicleid() {
		return vehicleid;
	}

	public String getNatureofdamage() {
		return natureofdamage;
	}

}
