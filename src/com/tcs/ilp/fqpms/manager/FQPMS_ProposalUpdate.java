package com.tcs.ilp.fqpms.manager;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.io.File;

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
import com.tcs.ilp.fqpms.model.Vehicle;
import com.tcs.ilp.fqpms.model.VehicleDamageProposal;
import com.tcs.ilp.fqpms.validate.ProposalValidate;
import com.tcs.ilp.fqpms.validate.VehicleDamageProposalBean;


public class FQPMS_ProposalUpdate {

	private ProposalDAO dao = new ProposalDAO();
	
	
	
	/**
	 * view insurance and vehicle details
	 * 
	 * @param uid
	 * @return
	 * @throws
	 * @throws FQException
	 */
	public Insurance getInsuranceDetails(String uid, String vid, String type) throws FQException, SQLException{
		return dao.getInsuranceDetails(uid, vid, type);
	}
	
	public Vehicle getVehicleDetails(String vid) throws FQException, SQLException{
		return dao.getVehicleDetails(vid);
	}

	/**
	 * search function
	 * 
	 * @param uid
	 * @return
	 * @throws
	 * @throws FQException
	 */
	public ArrayList<Proposal> getAllProposalByUserID(String uid)
			throws FQException {
		ArrayList<Proposal> proposals = dao.getAllProposalsByUserID(uid);
		if(proposals==null || proposals.isEmpty())
			throw new FQException("No proposal exist.");
		return proposals;
	}

	public Proposal getProposalByID(String pid) throws FQException {
		Proposal p = dao.getProposalById(pid);
		if(p==null)
			throw new FQException("No matched Proposal.");
		return p;
	}

	public boolean updateProposal(String pid, String userid, String type,
			Date dateofoccurance, double amountclaimed, String natureofinjury,
			String natureofdamage, String hospitalname, String vehicleid,
			String typeofliability) throws FQException {
		boolean flag = false;
		String insuranceId = "";
		double availableBalance = 0;
		if (type.equals("bodyinjury")) {
			BodyInjuryProposal b = new BodyInjuryProposal(pid, userid,
					dateofoccurance, amountclaimed, natureofinjury,
					hospitalname, "");
			insuranceId =dao.getUserById(userid).getInsurace().getInsuranceID();
			availableBalance = dao.getBalance(insuranceId);
			b.setInsuranceid(insuranceId);
			dao.updateAvailableBalance(availableBalance-amountclaimed, insuranceId);
			if (dao.updateProposal(b))
				flag = true;

		}

		if (type.equals("vehicledamage")) {
			VehicleDamageProposal v = new VehicleDamageProposal(pid, userid,
					vehicleid, dateofoccurance, amountclaimed, natureofdamage, "");
			insuranceId =dao.getInsuranceID(userid, vehicleid, type);
			availableBalance = dao.getBalance(insuranceId);
			v.setInsuranceid(insuranceId);
			dao.updateAvailableBalance(availableBalance-amountclaimed, insuranceId);
			if (dao.updateProposal(v))
				flag = true;
		}

		if (type.equals("liability")) {
			LiabilityProposal l = new LiabilityProposal(pid, userid, vehicleid,
					dateofoccurance, amountclaimed, typeofliability, "");
			insuranceId =dao.getInsuranceID(userid, vehicleid, type);
			availableBalance = dao.getBalance(insuranceId);
			l.setInsuranceid(insuranceId);
			dao.updateAvailableBalance(availableBalance-amountclaimed, insuranceId);
			if (dao.updateProposal(l))
				flag = true;
		}
		return flag;

	}

	public boolean deleteById(String uid, String pid) throws FQException {
		double balance = 0;
		String insuranceId = "";
		Insurance insurance = null;
		insurance = dao.getUserById(uid).getInsurace();
		if(insurance!=null)
		insuranceId = insurance.getInsuranceID();

		balance = dao.getBalance(insuranceId);
		dao.updateAvailableBalanceToBalance(insuranceId, balance);
		return dao.deleteProposalByID(pid);
	}

	public String getInsuranceID(String uid, String vid, String type)
			throws FQException {
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
			Date dateOfOccurance, File xmlFile) throws FQException {
		return dao.validateDuationCoverage(insuranceId, dateOfOccurance,
				xmlFile);
	}

	public boolean validateAmount(String insuranceId, double amount)
			throws FQException {
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
}
