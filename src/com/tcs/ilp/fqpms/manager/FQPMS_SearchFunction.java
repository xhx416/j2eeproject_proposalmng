package com.tcs.ilp.fqpms.manager;

import java.util.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.tcs.ilp.fqpms.bean.SearchCriteriaBean;
import com.tcs.ilp.fqpms.dao.ProposalDAO;
import com.tcs.ilp.fqpms.exception.FQException;
import com.tcs.ilp.fqpms.model.BodyInjuryProposal;
import com.tcs.ilp.fqpms.model.LiabilityProposal;
import com.tcs.ilp.fqpms.model.Proposal;
import com.tcs.ilp.fqpms.model.SearchQuery;
import com.tcs.ilp.fqpms.model.VehicleDamageProposal;


public class FQPMS_SearchFunction {

	ProposalDAO pd = new ProposalDAO();
	// ArrayList<Proposal> listofProposals = new ArrayList<Proposal>();
	private ArrayList<Proposal> userProposals = new ArrayList<Proposal>();

	public Proposal searchProposalById(String userid, String proposalid)
			throws FQException {
		Proposal p = null;
		if (validateProposalId(proposalid))
			p = pd.getUserProposalById(userid, proposalid);

		if (p == null) {
			throw new FQException("No matched Proposal of " + proposalid);
		}
		return p;
	}

	public ArrayList<Proposal> searchProposayByDate(String userid, String day, String month, String year)
			throws FQException {
		ArrayList<Proposal> returnlist = new ArrayList<Proposal>();
		SearchQuery sq = new SearchQuery();
		
		StringBuffer sb = new StringBuffer("");
		sb.append(day);
		sb.append("-");
		sb.append(month);
		sb.append("-");
		sb.append(year);
		String date = sb.toString();
		
        if(day.equals("0")&&!month.equals("0")&&!year.equals("0")){
        	if(month.equals("FEB"))
        	sq.addContent(" and date_of_occurance BETWEEN  '01-" + month +"-" + year+"'"+ " AND '28-" + month+"-"+ year +"'");
        	else if(month.equals("JAN")||month.equals("MAR")||month.equals("MAY")||month.equals("JUL")||month.equals("AUG")||month.equals("OCT")||month.equals("DEC"))
        		sq.addContent(" and date_of_occurance BETWEEN  '01-" + month +"-" + year+"'"+ " AND '31-" + month+"-"+ year +"'");
        	else
        		sq.addContent(" and date_of_occurance BETWEEN  '01-" + month +"-" + year+"'"+ " AND '30-" + month+"-"+ year +"'");

            returnlist = pd.mergeSearchProposal(userid, sq);
        }
        else if(day.equals("0")&&month.equals("0")&&!year.equals("0")){
   
        	sq.addContent(" and date_of_occurance BETWEEN '01-Jan-" + year +"'" + " AND " + "'31-Dec-" + year +"'");
        	returnlist = pd.mergeSearchProposal(userid, sq);
        }	
        else {
        	if (!validateDate(date))        
			throw new FQException("Date Selection is not valid");
		
		else {
			sq.addContent(" and date_of_occurance = " + "'" + date + "'");
			returnlist = pd.mergeSearchProposal(userid, sq);
		   }
        }
		if (returnlist.isEmpty())
			throw new FQException("No matched Proposal ");
		return returnlist;

	}

	public ArrayList<Proposal> searchProposalByType(String userid, String type)
			throws FQException {
		ArrayList<Proposal> returnlist = new ArrayList<Proposal>();
		SearchQuery sq = new SearchQuery();
		
		if (!validateType(type))
			throw new FQException("Please select a type");
		else{
			sq.setType(type);
			returnlist = pd.mergeSearchProposal(userid, sq);
		}
		
		if (returnlist.isEmpty())
			throw new FQException("No matched Proposal ");
		return returnlist;	

	}
	
	public ArrayList<Proposal> searchProposalByHoptialName(String userid, String type, String hname)throws FQException{
		
		ArrayList<Proposal> returnlist = new ArrayList<Proposal>();
		SearchQuery sq = new SearchQuery();
		
		if(!validateHospitalName(hname))
		   throw new FQException("Please select a hospital");
		if(!validateType(type))
			throw new FQException("Please select a type");
		else if(!type.equals("Body Injury Proposal"))
			throw new FQException("Selected proposal type is not valid for this search");
		else{
			sq.setType(type);
			sq.addContent(" and hospital_name = " + "'" + hname
					+ "'");
			returnlist = pd.mergeSearchProposal(userid, sq);
		}
		
		if (returnlist.isEmpty())
			throw new FQException("No matched Proposal");
		return returnlist;	

		
		
	}
	
	public ArrayList<Proposal> searchProposalByVehicleID(String userid, String type, String lincesenumber) throws FQException{

		ArrayList<Proposal> returnlist = new ArrayList<Proposal>();
		SearchQuery sq = new SearchQuery();
		
		if(!validateType(type))
			throw new FQException("Please select a type");
		
		else if(!validateVehicleLicenseNumber(lincesenumber)){
			throw new FQException("Please select a vehicle license number");
		}
		
		else if(type.equals("Body Injury Proposal"))
			throw new FQException("Selected proposal type is not valid for this search");
		else{
			sq.setType(type);
			sq.addContent(" and vehicle_id = " + "'" + convertLincenseNumberToVid(lincesenumber) + "'");
			returnlist = pd.mergeSearchProposal(userid, sq); 
		}
		if (returnlist.isEmpty())
			throw new FQException("No matched Proposal");
		return returnlist;	
		
	}

	public ArrayList<Proposal> searchProposalByOther(String userid,
			SearchCriteriaBean scb) throws FQException {
		ArrayList<Proposal> returnlist = new ArrayList<Proposal>();
		SearchQuery sq = new SearchQuery();
		
		String proposaltype = scb.getType();
		String hospitalname = scb.getHospitalname();
		String licensenumber = scb.getVehicleid();
		
		String day = scb.getDay();
		String month = scb.getMonth();
		String year = scb.getYear();

		StringBuffer sb= new StringBuffer("");
		sb.append(day);
		sb.append("-");
		sb.append(month);
		sb.append("-");
		sb.append(year);
		String date_s = sb.toString();
		
		//to validate the date
        
		if(day.equals("0")&&!month.equals("0")&&!year.equals("0")){
        	if(month.equals("FEB"))
        	sq.addContent(" and date_of_occurance BETWEEN  '01-" + month +"-" + year+"'"+ " AND '28-" + month+"-"+ year +"'");
        	else if(month.equals("JAN")||month.equals("MAR")||month.equals("MAY")||month.equals("JUL")||month.equals("AUG")||month.equals("OCT")||month.equals("DEC"))
        		sq.addContent(" and date_of_occurance BETWEEN  '01-" + month +"-" + year+"'"+ " AND '31-" + month+"-"+ year +"'");
        	else
        		sq.addContent(" and date_of_occurance BETWEEN  '01-" + month +"-" + year+"'"+ " AND '30-" + month+"-"+ year +"'");

            returnlist = pd.mergeSearchProposal(userid, sq);
        }
        else if(day.equals("0")&&month.equals("0")&&!year.equals("0")){
   
        	sq.addContent(" and date_of_occurance BETWEEN '01-Jan-" + year +"'" + " AND " + "'31-Dec-" + year +"'");
        	returnlist = pd.mergeSearchProposal(userid, sq);
        }	
		
        else if (validateDate(date_s)) {
			sq.addContent(" and date_of_occurance = " + "'" + date_s + "'");
		}
		
		//to validate the hospital name and vehicle id

		if (proposaltype.equals("Body Injury Proposal")) {
			sq.setType("Body Injury Proposal");
			if (validateHospitalName(hospitalname))
				sq.addContent(" and hospital_name = " + "'" + hospitalname
						+ "'");
			if (licensenumber != null && !licensenumber.trim().isEmpty())
				throw new FQException("Vehicle Search is not valid for the selected type of proposal");
		}
		if (proposaltype.equals("Vehicle Damage Proposal")) {
			sq.setType("Vehicle Damage Proposal");
			if (validateVehicleLicenseNumber(licensenumber))
				sq.addContent(" and vehicle_id = " + "'" + convertLincenseNumberToVid(licensenumber) + "'");
			if (validateHospitalName(hospitalname))
				throw new FQException("Hospital name should not be entered.");
		}
		if (proposaltype.equals("Liability Proposal")) {
			sq.setType("Liability Proposal");
			if (validateVehicleLicenseNumber(licensenumber))
				sq.addContent(" and vehicle_id = " + "'" + convertLincenseNumberToVid(licensenumber) + "'");
			if (validateHospitalName(hospitalname))
				throw new FQException("Hospital name should not be entered.");
		}

		returnlist = pd.mergeSearchProposal(userid, sq);

		if (returnlist.isEmpty())
			throw new FQException("No matched Proposal ");
		return returnlist;
	}

	public boolean validateProposalId(String propid) throws FQException {
		boolean valid = true;
		Pattern pattern = Pattern.compile("^prop[ivl][0-9]+\\z");
		//Matcher matcher = pattern.matcher(propid);
		if (propid == null) {
			valid = false;
			throw new FQException("Proposal ID cannot be null");
		} else if (propid.trim().isEmpty()) {
			valid = false;
			throw new FQException("Proposal ID cannot be empty");
		}
		else{
			
			Matcher matcher = pattern.matcher(propid.trim());
			if(!matcher.lookingAt()){
		
			valid = false;
			throw new FQException(
				"Proposal ID should follow the pattern of propi/propv/propl + number");
		}
		}
//		else if (!(propid.startsWith("propi") || propid.startsWith("propb") || propid
//				.startsWith("propv"))) {
//			valid = false;
//			throw new FQException(
//					"Proposal ID should starts with propi/propb/propl");
//		}
		return valid;

	}
	


	public boolean validateDate(String date_s) throws FQException {
		boolean valid = true;
//		int year_i = Integer.parseInt(year);
//		int month_i = castMonth(month);
//		int day_i = Integer.parseInt(day);
//		Calendar c = Calendar.getInstance();
//		c.set(year_i, month_i, day_i);
//		
//		
//		StringBuffer sb= new StringBuffer("");
//		sb.append(day);
//		sb.append("-");
//		sb.append(month_i);
//		sb.append("-");
//		sb.append(year);
//		String date_s = sb.toString();
		
		if(date_s.trim().equals("")||date_s.trim().equals("0-0-0")){
			valid= false;
		}
     		
		else{
			

		SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
		sdf.setLenient(false);
		try {
			Date date = sdf.parse(date_s);
		} catch (Exception e) {
			valid = false;
		 throw new FQException("Please select a valid date, the date should be in dd/mmm/yyyy format");
		
		}
		}
		return valid;

	}
	
	public boolean validateType(String proposaltype) throws FQException{
		boolean valid = true;
		if(proposaltype == null)
			valid =false;
		else if(proposaltype.trim().isEmpty()||proposaltype.equals("no value"))
			valid = false;
		
		return valid;
	}

	public boolean validateHospitalName(String hname) throws FQException {
		boolean valid = true;
		if (hname == null) {
			valid = false;
		} else if (hname.equals("no value")) {
			valid = false;
		}
		return valid;

	}

	public boolean validateVehicleLicenseNumber(String vid) throws FQException {
		boolean valid = true;
		
		if (vid == null) {
			valid = false;
		} else if (vid.trim().isEmpty()) {
			valid = false;
		} 
//		else {
//			Pattern pattern = Pattern.compile("^vehicle[0-9]+\\z");
//			Matcher matcher = pattern.matcher(vid);
//			if (!matcher.lookingAt()) {		
//			valid = false;
//			throw new FQException("Vehicle ID should follow the pattern of vehicle + number");
//		
//			}
//		}
		return valid;

	}
	
	public int castMonth(String month){
		int month_i=0;
		if(month.equalsIgnoreCase("jan")){
			month_i=1;
		}
		if(month.equalsIgnoreCase("feb")){
			month_i=2;
		}
		if(month.equalsIgnoreCase("mar")){
			month_i=3;
		}
		if(month.equalsIgnoreCase("apr")){
			month_i=4;
		}
		if(month.equalsIgnoreCase("may")){
			month_i=5;
		}
		if(month.equalsIgnoreCase("jun")){
			month_i=6;
		}
		if(month.equalsIgnoreCase("jul")){
			month_i=7;
		}
		if(month.equalsIgnoreCase("aug")){
			month_i=8;
		}
		if(month.equalsIgnoreCase("sep")){
			month_i=9;
		}
		if(month.equalsIgnoreCase("oct")){
			month_i=10;
		}
		if(month.equalsIgnoreCase("nov")){
			month_i=11;
		}
		if(month.equalsIgnoreCase("dec")){
			month_i=12;
		}
		
		return month_i;
		
	}	
	
	
	public String convertLincenseNumberToVid(String ln) throws FQException{
		String vid = null;
		if(ln!=null){
			vid = pd.getVehicleIDbyLinceseNumber(ln);
		}
		if(vid ==null)
			throw new FQException("System cannot find the matched vehicle. ");
		else
			return vid;
	}
	

	public ArrayList<Proposal> getUserProposals() {
		return userProposals;
	}

	public void setUserProposals(String userid) throws FQException,
			SQLException {
		this.userProposals = pd.getAllProposalsByUserID(userid);
	}

	public void updateUserProposals(String userid) throws FQException,
			SQLException {
		this.userProposals = pd.getAllProposalsByUserID(userid);
	}

}
