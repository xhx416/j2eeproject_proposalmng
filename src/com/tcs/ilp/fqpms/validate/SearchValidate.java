package com.tcs.ilp.fqpms.validate;


import java.util.HashMap;

import com.tcs.ilp.fqpms.bean.SearchCriteriaBean;



public class SearchValidate {

	public static String validateMergeSearchInput(SearchCriteriaBean scb) {
		
		String error = null;
		String day = scb.getDay();
		String month = scb.getMonth();
		String year = scb.getYear();

		String type = scb.getType();
		String hospitalname = scb.getHospitalname();
		String vehicleid = scb.getVehicleid();
		StringBuffer sb= new StringBuffer("");
		sb.append(day);
		sb.append("-");
		sb.append(month);
		sb.append("-");
		sb.append(year);
        
		String date = sb.toString();

		if ((date == null ||date.trim().isEmpty()||date.equals("0-0-0"))
				&&(type ==null||type.trim().isEmpty()||type.equals("no value"))
				&&(hospitalname ==null||hospitalname.trim().isEmpty()||hospitalname.equals("no value"))
				&&(vehicleid ==null||vehicleid.trim().isEmpty())){
			
			error = "Please select at least one search criteria";
		}
		
//		else if(vehicleid!=null&&vehicleid.trim().length()>0&&!vehicleid.startsWith("vehicle"))
//			errorlist.put("vehicleid", "Vehicle ID should starts with vechile");
		
			
		return error;
	}
	
	

		
	

}
