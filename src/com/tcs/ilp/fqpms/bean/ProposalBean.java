package com.tcs.ilp.fqpms.bean;

public class ProposalBean {
	private String year;
	private String date;
	private String month;
	private String date_s;
	private String amountclaimed;
	private String uid;
	private String pid;
	private String type;
	private String quoteStatus;
	public ProposalBean(String year, String month, String date,
			String amountclaimed) {
		super();
		//this.date_s = new String(year+"-"+month+"-"+date);
		this.date_s = new String(date+"/"+castMonth(month)+"/"+year);
		this.year = year;
		this.date = date;
		this.month = month;
		this.amountclaimed = amountclaimed;
	}
	public String getYear() {
		return year;
	}
	public String getDate() {
		return date;
	}
	public String getMonth() {
		return month;
	}
	public String getAmountclaimed() {
		return amountclaimed;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getQuoteStatus() {
		return quoteStatus;
	}
	public void setQuoteStatus(String quoteStatus) {
		this.quoteStatus = quoteStatus;
	}
	public String getDate_s() {
		return date_s;
	}
	public void setDate_s(String date_s) {
		this.date_s = date_s;
	}
	public String castMonth(String month){
		String month_i = null;
		if(month.equalsIgnoreCase("1")){
			month_i="Jan";
		}
		if(month.equalsIgnoreCase("2")){
			month_i="Feb";
		}
		if(month.equalsIgnoreCase("3")){
			month_i="Mar";
		}
		if(month.equalsIgnoreCase("4")){
			month_i="Apr";
		}
		if(month.equalsIgnoreCase("5")){
			month_i="May";
		}
		if(month.equalsIgnoreCase("6")){
			month_i="Jun";
		}
		if(month.equalsIgnoreCase("7")){
			month_i="Jul";
		}
		if(month.equalsIgnoreCase("8")){
			month_i="Aug";
		}
		if(month.equalsIgnoreCase("9")){
			month_i="Sep";
		}
		if(month.equalsIgnoreCase("10")){
			month_i="Oct";
		}
		if(month.equalsIgnoreCase("11")){
			month_i="Nov";
		}
		if(month.equalsIgnoreCase("12")){
			month_i="Dec";
		}
		
		return month_i;
		
	}
	
}
