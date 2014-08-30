package com.tcs.ilp.fqpms.model;

import java.util.Date;

public class Proposal {
	String userid;
	String proposalid;
	Date dateofoccurance;
	double amountclaimed;
	String quoteId;
	String insuranceid;
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getProposalid() {
		return proposalid;
	}
	public void setProposalid(String proposalid) {
		this.proposalid = proposalid;
	}
	public Date getDateofoccurance() {
		return dateofoccurance;
	}
	public void setDateofoccurance(Date dateofoccurance) {
		this.dateofoccurance = dateofoccurance;
	}
	public double getAmountclaimed() {
		return amountclaimed;
	}
	public void setAmountclaimed(double amountclaimed) {
		this.amountclaimed = amountclaimed;
	}
	public String getQuoteId() {
		return quoteId;
	}
	public void setQuoteId(String quoteId) {
		this.quoteId = quoteId;
	}
	
	public String getInsuranceid() {
		return insuranceid;
	}
	public void setInsuranceid(String insuranceid) {
		this.insuranceid = insuranceid;
	}
	public Proposal(String proposalid, String userid, Date dateofoccurance,
			double amountclaimed, String quoteId) {
		super();
		this.userid = userid;
		this.proposalid = proposalid;
		this.dateofoccurance = dateofoccurance;
		this.amountclaimed = amountclaimed;
		this.quoteId = quoteId;
	}

}
