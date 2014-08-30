package com.tcs.ilp.fqpms.validate;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import com.tcs.ilp.fqpms.bean.BodyInjuryProposalBean;
import com.tcs.ilp.fqpms.bean.LiabilityProposalBean;
import com.tcs.ilp.fqpms.bean.ProposalBean;
import com.tcs.ilp.fqpms.dao.ProposalDAO;
import com.tcs.ilp.fqpms.exception.FQException;
import com.tcs.ilp.fqpms.model.*;

public class ProposalValidate {

	// Server-side validations

	/**
	 * This methods validate a BodyInjuryProposalBean object from the user
	 * inputs.
	 * 
	 * @param b
	 * @return
	 * @throws FQException 
	 */

	public static HashMap<String, String> validate(BodyInjuryProposalBean b) {
		HashMap<String, String> list = new HashMap<String, String>();
		// date validation
		String year = b.getYear();
		String month = ((Integer)(Integer.parseInt(b.getMonth())-1)).toString();
		String date = b.getDate();
		boolean valid = true;

		if (date == null || date.trim().equals("0")) {
			list.put("date", "Date cannot be empty. ");
		} else if (month == null || month.trim().equals("-1")) {
			list.put("date", "Month cannot be empty. ");
		} else if (year == null || year.trim().equals("0")) {
			list.put("date", "Year cannot be empty. ");
		} 				
		
		else {
            StringBuffer sb = new StringBuffer("");
            sb.append(date);
            sb.append("-");
            sb.append(b.getMonth());
            sb.append("-");
            sb.append(year);
            String date_s = sb.toString();
			

			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			sdf.setLenient(false);
			try {
				java.util.Date date_d = sdf.parse(date_s);
			} catch (Exception e) {
				valid = false;
			    list.put("date", "Date of Injury entered is not valid. ");
			
			}
		}
		if(valid){
			int y = Integer.parseInt(year);
			int m = Integer.parseInt(month);          
			int d = Integer.parseInt(date);
			Calendar c = Calendar.getInstance();
			Date now = c.getTime();
			c.set(y, m, d);
            
			Date then = c.getTime();
			if (then.after(now)) {
				list.put("date", "Date of Injury cannot be in the future. ");
			}
		}
	

		// amount claimed validation
		boolean validAmount = true;
		String amt = b.getAmountclaimed();
		if (amt == null || amt.trim().equals("")) {
			list.put("amount", "Amount Claimed cannot be empty. ");
			validAmount = false;
		}
		else {
			for (int i = 0; i < amt.length(); i++) {
				if (!Character.isDigit(amt.charAt(i)) && amt.charAt(i) != '.') {
					list.put("amount", "Amount Claimed can only be digits. ");
					validAmount = false;
					break;
				}
			}
		}
		if(validAmount){
			if(Double.parseDouble(amt)<10)
				list.put("amount", "Amount Claimed can not be less than 10 USD. ");
		}

		// nature of injury validation
		String n = b.getNatureofinjury();
		if (n == null || n.trim().equals("")) {
			list.put("natureinjury", "Nature of Injury cannot be empty. ");
		}else if(n.length()>500){
			list.put("natureinjury", "Nature of Injury should not exceed 500 charators. ");
		}

		// hospital name validation
		String h = b.getHospitalname();
		if (h == null || h.trim().equals("")) {
			list.put("hospital", "Hospital Name cannot be empty. ");
		}
		return list;
	}

	/**
	 * This methods validate a VehicleDamageProposalBean object from the user
	 * inputs.
	 * 
	 * @param b
	 * @return
	 */
	public static HashMap<String, String> validate(VehicleDamageProposalBean b) {
		HashMap<String, String> list = new HashMap<String, String>();
		// vehicle id validation
		String n = b.getVehicleid();
		boolean valid = true;
		if (n == null || n.trim().equals("")) {
			list.put("vid", "Please select a license Number. ");
		}

		// date validation
		String year = b.getYear();
		String month = ((Integer)(Integer.parseInt(b.getMonth())-1)).toString();
		String date = b.getDate();
		if (date == null || date.trim().equals("0")) {
			list.put("date", "Date cannot be empty. ");
		} else if (month == null || month.trim().equals("-1")) {
			list.put("date", "Month cannot be empty. ");
		} else if (year == null || year.trim().equals("0")) {
			list.put("date", "Year cannot be empty. ");
		} else {
            StringBuffer sb = new StringBuffer("");
            sb.append(date);
            sb.append("-");
            sb.append(b.getMonth());
            sb.append("-");
            sb.append(year);
            String date_s = sb.toString();
			

			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			sdf.setLenient(false);
			try {
				java.util.Date date_d = sdf.parse(date_s);
			} catch (Exception e) {
				valid = false;
			    list.put("date", "Date of Damage entered is not valid. ");
			
			}
		}
		if(valid){
			int y = Integer.parseInt(year);
			int m = Integer.parseInt(month);          
			int d = Integer.parseInt(date);
			Calendar c = Calendar.getInstance();
			Date now = c.getTime();
			c.set(y, m, d);
            
			Date then = c.getTime();
			if (then.after(now)) {
				list.put("date", "Date of Damage cannot be in the future. ");
			}
		}

		// amount claimed validation
		
		boolean validAmount = true;
		String amt = b.getAmountclaimed();
		if (amt == null || amt.trim().equals("")) {
			list.put("amount", "Amount Claimed cannot be empty. ");
			validAmount = false;
		}
		else {
			for (int i = 0; i < amt.length(); i++) {
				if (!Character.isDigit(amt.charAt(i)) && amt.charAt(i) != '.') {
					list.put("amount", "Amount Claimed can only be digits. ");
					validAmount = false;
					break;
				}
			}
		}
		if(validAmount){
			if(Double.parseDouble(amt)<10)
				list.put("amount", "Amount Claimed can not be less than 10 USD. ");
		}
		// nature of damage validation
		String h = b.getNatureofdamage();
		if (h == null || h.trim().equals("")) {
	
			list.put("naturedamage", "Nature of Damage cannot be empty. ");
		}else if(h.length()>500){
			list.put("natureinjury", "Nature of Injury should not exceed 500 charators. ");
		}
		return list;
	}

	/**
	 * This methods validate a VehicleDamageProposalBean object from the user
	 * inputs.
	 * 
	 * @param b
	 * @return
	 */
	public static HashMap<String, String> validate(LiabilityProposalBean b) {
		HashMap<String, String> list = new HashMap<String, String>();
		// vehicle id validation
		String n = b.getVehicleid();
		if (n == null || n.trim().equals("")) {
			list.put("vid", "Please select a License Number. ");
		}

		// date validation
		String year = b.getYear();
		String month = ((Integer)(Integer.parseInt(b.getMonth())-1)).toString();
		String date = b.getDate();
		boolean valid = true;
		if (date == null || date.trim().equals("0")) {
			list.put("date", "Date cannot be empty. ");
		} else if (month == null || month.trim().equals("-1")) {
			list.put("date", "Month cannot be empty. ");
		} else if (year == null || year.trim().equals("0")) {
			list.put("date", "Year cannot be empty. ");
		} else {
            StringBuffer sb = new StringBuffer("");
            sb.append(date);
            sb.append("-");
            sb.append(b.getMonth());
            sb.append("-");
            sb.append(year);
            String date_s = sb.toString();
			

			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			sdf.setLenient(false);
			try {
				java.util.Date date_d = sdf.parse(date_s);
			} catch (Exception e) {
				valid = false;
			    list.put("date", "Date of Accident entered is not valid. ");
			
			}
		}
		if(valid){
			int y = Integer.parseInt(year);
			int m = Integer.parseInt(month);          
			int d = Integer.parseInt(date);
			Calendar c = Calendar.getInstance();
			Date now = c.getTime();
			c.set(y, m, d);
            
			Date then = c.getTime();
			if (then.after(now)) {
				list.put("date", "Date of Accident cannot be in the future. ");
			}
		}

		// amount claimed validation
		boolean validAmount = true;
		String amt = b.getAmountclaimed();
		if (amt == null || amt.trim().equals("")) {
			list.put("amount", "Amount Claimed cannot be empty. ");
			validAmount = false;
		}
		else {
			for (int i = 0; i < amt.length(); i++) {
				if (!Character.isDigit(amt.charAt(i)) && amt.charAt(i) != '.') {
					list.put("amount", "Amount Claimed can only be digits. ");
					validAmount = false;
					break;
				}
			}
		}
		if(validAmount){
			if(Double.parseDouble(amt)<10)
				list.put("amount", "Amount Claimed can not be less than 10 USD. ");
		}

		// type of liability validation
		String h = b.getTypeofliability();
		if (h == null || h.trim().equals("")) {
			list.put("typeliability", "Type of Liability cannot be empty. ");
		}else if(n.length()>500){
			list.put("natureinjury", "Nature of Injury should not exceed 500 charators. ");
		}
		return list;
	}

	public static ProposalBean toBean(Proposal p) throws FQException {
		ProposalBean b = null;
		ProposalDAO dao = new ProposalDAO();
		Calendar c = Calendar.getInstance();
		c.setTime(p.getDateofoccurance());
		String year = c.get(Calendar.YEAR) + "";
		String month = c.get(Calendar.MONTH) + 1 + "";
		String date = c.get(Calendar.DATE) + "";
		String amt = p.getAmountclaimed() + "";
		if (p.getProposalid().startsWith("propi")) {
			BodyInjuryProposal bip = (BodyInjuryProposal) p;
			b = new BodyInjuryProposalBean(year, month, date, amt,
					bip.getNatureofinjury(), bip.getHospitalname());
			if(bip.getQuoteId()!=""&&bip.getQuoteId()!=null){
				b.setQuoteStatus("Quote Generated");
			}
			else{
				b.setQuoteStatus("Pending");
			}
			b.setType("Body Injury Proposal");			
			b.setPid(p.getProposalid());
			b.setUid(p.getUserid());
		} else if (p.getProposalid().startsWith("propv")) {
			VehicleDamageProposal vdp = (VehicleDamageProposal) p;
			String licensenumber = dao.getLicenseNumberbyVid(vdp.getVehicleid());
			b = new VehicleDamageProposalBean(year, month, date, amt,
					licensenumber, vdp.getNatureofdamage());
			b.setPid(p.getProposalid());
			b.setUid(p.getUserid());
			b.setType("Vehicle Damage Proposal");			
			if(vdp.getQuoteId()!=""&&vdp.getQuoteId()!=null){
				b.setQuoteStatus("Quote Generated");
			}
			else{
				b.setQuoteStatus("Pending");
			}
		} else if (p.getProposalid().startsWith("propl")) {
			LiabilityProposal lp = (LiabilityProposal) p;
			String licensenumber = dao.getLicenseNumberbyVid(lp.getVehicleid());
			b = new LiabilityProposalBean(year, month, date, amt,
					licensenumber, lp.getTypeofliability());
			b.setPid(p.getProposalid());
			b.setUid(p.getUserid());
			b.setType("Liability Proposal");			
			if(lp.getQuoteId()!=""&&lp.getQuoteId()!=null){
				b.setQuoteStatus("Quote Generated");
			}
			else{
				b.setQuoteStatus("Pending");
			}
		}
		return b;
	}
}
