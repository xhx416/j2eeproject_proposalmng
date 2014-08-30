package com.tcs.ilp.fqpms.validate;


import java.util.ArrayList;
import java.util.Calendar;

import com.tcs.ilp.fqpms.bean.BodyInjuryProposalBean;
import com.tcs.ilp.fqpms.bean.LiabilityProposalBean;
import com.tcs.ilp.fqpms.bean.ProposalBean;
import com.tcs.ilp.fqpms.exception.FQException;
import com.tcs.ilp.fqpms.model.*;

public class ProposalToBeanConversion {
	
	
	public static ArrayList<ProposalBean> proposalsToBean(ArrayList<Proposal> proposallist) throws FQException{
		ArrayList<ProposalBean> temp  = new ArrayList<ProposalBean>();
		
		if(proposallist!=null &&!proposallist.isEmpty()){
		for(int i=0;i<proposallist.size();i++){
			temp.add(toBean(proposallist.get(i)));
		}
		}
		else {
			throw new FQException("Proposal List is empty");
		}
		return temp;
	}
	
	public static ProposalBean toBean(Proposal p) {
		ProposalBean b = null;
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
			if(bip.getQuoteId()!=null && bip.getQuoteId().trim()!=""){
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
			b = new VehicleDamageProposalBean(year, month, date, amt,
					vdp.getVehicleid(), vdp.getNatureofdamage());
			b.setPid(p.getProposalid());
			b.setUid(p.getUserid());
			b.setType("Vehicle Damage Proposal");			
			if(vdp.getQuoteId()!=null && vdp.getQuoteId().trim()!=""){
				b.setQuoteStatus("Quote Generated");
			}
			else{
				b.setQuoteStatus("Pending");
			}
		} else if (p.getProposalid().startsWith("propl")) {
			LiabilityProposal lp = (LiabilityProposal) p;
			b = new LiabilityProposalBean(year, month, date, amt,
					lp.getVehicleid(), lp.getTypeofliability());
			b.setPid(p.getProposalid());
			b.setUid(p.getUserid());
			b.setType("Liability Proposal");			
			if(lp.getQuoteId()!=null && lp.getQuoteId().trim()!=""){
				b.setQuoteStatus("Quote Generated");
			}
			else{
				b.setQuoteStatus("Pending");
			}
		}
		return b;
	}
	

}
