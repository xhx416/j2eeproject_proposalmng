package com.tcs.ilp.fqpms.model;

import java.util.Date;

public class BodyInjuryProposal extends Proposal {
	
	private String natureofinjury;
	private String hospitalname;
	
	public BodyInjuryProposal(String proposalid, String userid,Date dateofoccurance,
			double amountclaimed, String natureofinjury, String hospitalname, String quoteId) {
		super(proposalid, userid,dateofoccurance, amountclaimed, quoteId);
		this.natureofinjury = natureofinjury;
		this.hospitalname = hospitalname;
		
	}

	public String getNatureofinjury() {
		return natureofinjury;
	}

	public void setNatureofinjury(String natureofinjury) {
		this.natureofinjury = natureofinjury;
	}

	public String getHospitalname() {
		return hospitalname;
	}

	public void setHospitalname(String hospitalname) {
		this.hospitalname = hospitalname;
	}
	
	
	
	

}
