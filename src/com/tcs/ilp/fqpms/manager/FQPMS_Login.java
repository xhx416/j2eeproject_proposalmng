package com.tcs.ilp.fqpms.manager;
import java.util.ArrayList;

import com.tcs.ilp.fqpms.dao.ProposalDAO;
import com.tcs.ilp.fqpms.exception.FQException;
import com.tcs.ilp.fqpms.model.Insurance;
import com.tcs.ilp.fqpms.model.Proposal;
import com.tcs.ilp.fqpms.model.User;
import com.tcs.ilp.fqpms.model.Vehicle;

public class FQPMS_Login {



		public boolean login(String userId, String password) throws FQException {
			// TODO Auto-generated method stub
			ProposalDAO dao = new ProposalDAO();
			boolean flag = false;
			flag = dao.login(userId, password);
			
			if(!flag){
				throw new FQException("User ID or Password is not correct!");
				
			}
			return flag;		
		}

		public ArrayList<Proposal> getProposals(String userId) throws FQException {
			// TODO Auto-generated method stub
			ArrayList<Proposal> list = new ArrayList<Proposal>();
			ProposalDAO dao=new ProposalDAO();
			list = dao.getAllProposalsByUserID(userId);
			if(list == null){
				throw new FQException("User ID does not exist");
			}
			return list;
		}
		public User getUserByUserId(String userId) throws FQException{
			ProposalDAO dao=new ProposalDAO();
			User user = dao.getUserById(userId);
			return user;
		}

		public ArrayList<Vehicle> getVehicleByUserId(String userId) throws FQException {
			ProposalDAO dao=new ProposalDAO();
			return dao.getVehiclesByUserId(userId);
		}

		public ArrayList<Insurance> getInsuranceByUserId(String userId) throws FQException {
			ProposalDAO dao=new ProposalDAO();
			ArrayList<Insurance> insuranceList = dao.getVehicleInsurancebyUserId(userId);
			insuranceList.add(getUserByUserId(userId).getInsurace());
			return insuranceList;
		}
		public ArrayList<String> getVehicleLicenseNumberByUserId(String uid) throws FQException{
			ProposalDAO dao=new ProposalDAO();
			return dao.getLicenseNumber(uid);
		}
		
		public String getLicenseNumberByVid(String vid) throws FQException{
			ProposalDAO dao = new ProposalDAO();
			return dao.getLicenseNumberbyVid(vid);
		}
}
