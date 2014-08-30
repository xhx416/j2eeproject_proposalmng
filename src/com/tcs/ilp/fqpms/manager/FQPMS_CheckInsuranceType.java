package com.tcs.ilp.fqpms.manager;

import java.util.ArrayList;

import com.tcs.ilp.fqpms.dao.ProposalDAO;
import com.tcs.ilp.fqpms.exception.FQException;


public class FQPMS_CheckInsuranceType {

	public ArrayList<String> getInsuranceType(String userID) throws FQException {
		ProposalDAO dao = new ProposalDAO();
		return dao.getInsuranceType(userID);
	}

}
