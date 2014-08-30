package com.tcs.ilp.fqpms.manager;

import java.sql.SQLException;
import java.util.ArrayList;

import com.tcs.ilp.fqpms.dao.ProposalDAO;
import com.tcs.ilp.fqpms.exception.FQException;
import com.tcs.ilp.fqpms.model.Proposal;



public class FQPMS_ProposalView {
	
	
	public ArrayList<Proposal> getAllProposalByUserID(String uid) throws FQException, SQLException {
		ProposalDAO dao = new ProposalDAO();
		return dao.getAllProposalsByUserID(uid);
	}

}
