package com.tcs.ilp.fqpms.bean;


public class BodyInjuryProposalBean extends ProposalBean {

	private String natureofinjury;
	private String hospitalname;

	public BodyInjuryProposalBean(String year, String month, String date,
			String amountclaimed, String natureofinjury, String hospitalname) {
		super(year, month, date, amountclaimed);
		this.natureofinjury = natureofinjury;
		this.hospitalname = hospitalname;
	}

	public String getNatureofinjury() {
		return natureofinjury;
	}

	public String getHospitalname() {
		return hospitalname;
	}

}