package com.tcs.ilp.fqpms.manager;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.DOMException;
import org.xml.sax.SAXException;

import com.tcs.ilp.fqpms.bean.BodyInjuryProposalBean;
import com.tcs.ilp.fqpms.bean.LiabilityProposalBean;
import com.tcs.ilp.fqpms.bean.ProposalBean;
import com.tcs.ilp.fqpms.dao.ProposalDAO;
import com.tcs.ilp.fqpms.exception.FQException;
import com.tcs.ilp.fqpms.model.BodyInjuryProposal;
import com.tcs.ilp.fqpms.model.Insurance;
import com.tcs.ilp.fqpms.model.LiabilityProposal;
import com.tcs.ilp.fqpms.model.Proposal;
import com.tcs.ilp.fqpms.model.User;
import com.tcs.ilp.fqpms.model.VehicleDamageProposal;
import com.tcs.ilp.fqpms.validate.ProposalValidate;
import com.tcs.ilp.fqpms.validate.VehicleDamageProposalBean;


public class FQPMS_ProposalSubmit {

	private ProposalDAO dao = new ProposalDAO();

	public String addProposal(String userid, String type, Date dateofoccurance,
			double amountclaimed, String natureofinjury, String natureofdamage,
			String hospitalname, String vehicleid, String typeofliability)
			throws FQException {
		String proposalid = null;
		String insuranceId = "";
		double availableBalance = 0;
		if (type.equals("bodyinjury")) {
			StringBuffer sb = new StringBuffer("");
			sb.append("propi");
			sb.append(dao.getNextID() + 1);
			proposalid = sb.toString();
			BodyInjuryProposal b = new BodyInjuryProposal(proposalid, userid,
					dateofoccurance, amountclaimed, natureofinjury,
					hospitalname, "");
			insuranceId =dao.getUserById(userid).getInsurace().getInsuranceID();
			availableBalance = dao.getBalance(dao.getUserById(userid).getInsurace().getInsuranceID());
			b.setInsuranceid(insuranceId);
			dao.updateAvailableBalance(availableBalance-amountclaimed, insuranceId);
			dao.addProposal(b);
		} else if (type.equals("vehicledamage")) {
			StringBuffer sb = new StringBuffer("");
			sb.append("propv");
			sb.append(dao.getNextID() + 1);
			proposalid = sb.toString();
			VehicleDamageProposal b = new VehicleDamageProposal(proposalid,
					userid, vehicleid, dateofoccurance, amountclaimed,
					natureofdamage, "");
			
			insuranceId =dao.getInsuranceID(userid, vehicleid, type);
			availableBalance = dao.getBalance(insuranceId);
			b.setInsuranceid(insuranceId);
			dao.updateAvailableBalance(availableBalance-amountclaimed, insuranceId);
			dao.addProposal(b);
		} else if (type.equals("liability")) {
			StringBuffer sb = new StringBuffer("");
			sb.append("propl");
			sb.append(dao.getNextID() + 1);
			proposalid = sb.toString();
			LiabilityProposal b = new LiabilityProposal(proposalid, userid,
					vehicleid, dateofoccurance, amountclaimed, typeofliability, "");
			insuranceId =dao.getInsuranceID(userid, vehicleid, type);
			availableBalance = dao.getBalance(insuranceId);
			b.setInsuranceid(insuranceId);
			dao.updateAvailableBalance(availableBalance-amountclaimed, insuranceId);
			dao.addProposal(b);
		}
		return proposalid;
	}

	public String getInsuranceID(String uid, String vid, String type)
			throws FQException, SQLException {
		return dao.getInsuranceID(uid, vid, type);
	}

	public HashMap<String, String> validate(String type, ProposalBean b) {
		HashMap<String, String> result = null;
		if (type.equals("bodyinjury")) {
			result = ProposalValidate.validate((BodyInjuryProposalBean) b);
		} else if (type.equals("vehicledamage")) {
			result = ProposalValidate.validate((VehicleDamageProposalBean) b);
		} else if (type.equals("liability")) {
			result = ProposalValidate.validate((LiabilityProposalBean) b);
		} else {
			result = new HashMap<String, String>();
		}
		return result;
	}

	public boolean validateDuationCoverage(String insuranceId,
			Date dateOfOccurance, File xmlFile)
			throws DOMException,
			ParserConfigurationException,
			SAXException,
			IOException,
			ParseException,
			SQLException,
			com.sun.xml.internal.messaging.saaj.packaging.mime.internet.ParseException,
			FQException {
		return dao.validateDuationCoverage(insuranceId, dateOfOccurance,
				xmlFile);
	}

	public boolean validateAmount(String insuranceId, double amount)
			throws FQException, SQLException {
		return dao.validateAmount(insuranceId, amount);
	}
	public ArrayList<String> getVehicleByUserId(String uid) throws FQException{
		return dao.getLicenseNumber(uid);
	}
	
	public String getVidByLicneseNumber(String ln) throws FQException{
		if(dao.getVehicleIDbyLinceseNumber(ln)!=null)
			return dao.getVehicleIDbyLinceseNumber(ln);
		else 
			throw new FQException("System cannot find the matched Vehicle. ");
	}
	
	public ArrayList<Proposal> getProposals(String userId) throws FQException {
		// TODO Auto-generated method stub
		ArrayList<Proposal> list = new ArrayList<Proposal>();
		ProposalDAO dao=new ProposalDAO();
		list = dao.getAllProposalsByUserID(userId);
		if(list == null){
			throw new FQException("User does not have any proposal.");
		}
		return list;
	}
	public ArrayList<Insurance> getInsuranceByUserId(String userId) throws FQException {
		ProposalDAO dao=new ProposalDAO();
		ArrayList<Insurance> insuranceList = dao.getVehicleInsurancebyUserId(userId);
		insuranceList.add(getUserByUserId(userId).getInsurace());
		return insuranceList;
	}
	public User getUserByUserId(String userId) throws FQException{
		ProposalDAO dao=new ProposalDAO();
		User user = dao.getUserById(userId);
		return user;
	}
	public String getLicenseNumberByVid(String vid) throws FQException{
		ProposalDAO dao = new ProposalDAO();
		return dao.getLicenseNumberbyVid(vid);
	}
	public Proposal getProposalById(String pid) throws FQException{
		ProposalDAO dao = new ProposalDAO();
		Proposal prop = dao.getProposalById(pid);
		if(prop==null)
			throw new FQException("System cannot find the matched proposal.");
		return prop;
	}
}
