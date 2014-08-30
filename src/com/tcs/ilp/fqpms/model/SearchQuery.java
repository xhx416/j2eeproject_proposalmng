package com.tcs.ilp.fqpms.model;

public class SearchQuery {
	private String type;
	private StringBuffer content;
	
	public SearchQuery(){
		this.type="All";
		content = new StringBuffer("");
		
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public StringBuffer getContent() {
		return content;
	}


	public void addContent(String content) {
		this.content.append(content);
	}
	


}
