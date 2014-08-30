package com.tcs.ilp.fqpms.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.text.SimpleDateFormat;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tcs.ilp.fqpms.bean.BodyInjuryProposalBean;
import com.tcs.ilp.fqpms.bean.LiabilityProposalBean;
import com.tcs.ilp.fqpms.bean.ProposalBean;
import com.tcs.ilp.fqpms.exception.FQException;
import com.tcs.ilp.fqpms.manager.FQPMS_ProposalSubmit;
import com.tcs.ilp.fqpms.model.Insurance;
import com.tcs.ilp.fqpms.model.Proposal;
import com.tcs.ilp.fqpms.validate.*;


public class ProposalSubmitServlet extends HttpServlet {
	private String userid = "";
	private FQPMS_ProposalSubmit ps = new FQPMS_ProposalSubmit();
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public ProposalSubmitServlet() {
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// server-side validation
		HttpSession session = request.getSession(false);
		if (session == null ||session.getAttribute("userId") ==null)
			response.sendRedirect("/FQPMS/jsp/Login.jsp");
		else{
			userid = (String) session.getAttribute("userId");
		if(request.getParameter("execute") !=null && request.getParameter("execute").equals("reset")){
			resetForm(request, response);
		}
		else if(request.getParameter("execute") !=null && request.getParameter("execute").equals("manage")){
			String pid = request.getParameter("pid");
			RequestDispatcher rd = null;
			
			try {
				ArrayList<ProposalBean> proposals = new ArrayList<ProposalBean>();
				Proposal proposal = ps.getProposalById(pid);
				proposals.add(ProposalToBeanConversion.toBean(proposal));
				request.setAttribute("proposallist", proposals);
				rd = request
						.getRequestDispatcher("/jsp/SearchResults.jsp");
				rd.forward(request, response);
				
			} catch (FQException e) {
				request.setAttribute("error", e.getMessage());
				rd = request
						.getRequestDispatcher("/jsp/SystemError.jsp");
				rd.forward(request, response);
			}
			
			
		}
		else if(request.getParameter("type")!=null){
		
		String type = request.getParameter("type");
		HashMap<String, String> msg = null;
		ProposalBean b = null;
		RequestDispatcher rd = null;
		String redirectpath = null;

		// create the beans
		if (type.equals("bodyinjury")) {
			b = new BodyInjuryProposalBean(
					request.getParameter("year"),
					request.getParameter("month"), request.getParameter("day"), request
							.getParameter("amount"), request
							.getParameter("natureofinjury"), request
							.getParameter("hospitalname"));
			msg = ps.validate(type, b);
			request.setAttribute("msg1", msg);
			request.setAttribute("bean", b);
			redirectpath = "jsp/SubmitBodyInjury.jsp";
		} else if (type.equals("vehicledamage")) {
			b = new VehicleDamageProposalBean(
					request.getParameter("year"),
					request.getParameter("month"), request.getParameter("day"), request
							.getParameter("amount"), request
							.getParameter("vehicleid"), request
							.getParameter("natureofdamage"));
			msg = ps.validate(type, b);
			request.setAttribute("msg2", msg);
			request.setAttribute("bean", b);
			redirectpath = "jsp/SubmitVehicleDamage.jsp";
		} else if (type.equals("liability")) {
			b = new LiabilityProposalBean(
					request.getParameter("year"),
					request.getParameter("month"), request.getParameter("day"), request
							.getParameter("amount"), request
							.getParameter("vehicleid"), request
							.getParameter("liability"));
			msg = ps.validate(type, b);

			request.setAttribute("msg3", msg);
			request.setAttribute("bean", b);

			redirectpath = "jsp/SubmitLiability.jsp";
		}
		// validate the inputs
		// msg = ps.validate(type, b);
		// error occurs
		String typeofliability = null;
		if (msg.size() != 0) {
			rd = request.getRequestDispatcher(redirectpath);
			rd.forward(request, response);
		} else {
			// server-side validation confirms no error
			try {
				StringBuffer sb = new StringBuffer();
				sb.append(request.getParameter("day") + "-");
				sb.append(request.getParameter("month") + "-");
				sb.append(request.getParameter("year"));
				Date dateofoccurance = new SimpleDateFormat("dd-MM-yyyy")
						.parse(sb.toString());
				double amountclaimed = Double.parseDouble(request
						.getParameter("amount"));
				String vid = null;
				if(request.getParameter("vehicleid")!=null)
				vid = ps.getVidByLicneseNumber(request.getParameter("vehicleid"));
				
				
				/* validate coverage date and amount claimed */
				String insuranceID = ps.getInsuranceID(userid,
						vid, type);
				if (insuranceID == null) {
					throw new Exception("System cannot find " + type+ " insurance for "+ request.getParameter("vehicleid") + ". ");
				}
				String path = getServletContext().getRealPath("/xml/module2-paidpremiumdetails.xml");
				boolean dateVal = ps.validateDuationCoverage(insuranceID,
						dateofoccurance, new File(path));
				System.out.println(dateVal);
				typeofliability = request.getParameter("liability");
				double tempAmt = amountclaimed;
				if(typeofliability!=null&&typeofliability.startsWith("Body")){
					tempAmt /= 0.85;
				}
				boolean amtVal = ps.validateAmount(insuranceID, tempAmt);
				System.out.println(amtVal);
				/* end of validation */
				if (!dateVal || !amtVal) {
					if (!dateVal) {
						msg.put("date",
								"Date is not within the insurance coverage duration.");
					}
					if (!amtVal) {
						msg.put("amount",
								"Amount claimed is beyond the coverage amount.");
					}
					request.setAttribute("msg", msg);
					request.setAttribute("bean", b);
					rd = request
							.getRequestDispatcher(redirectpath);
					rd.forward(request, response);
				} else {
					
					String natureofinjury = request
							.getParameter("natureofinjury");
					String natureofdamage = request
							.getParameter("natureofdamage");
					String hospitalname = request.getParameter("hospitalname");
					typeofliability = request.getParameter("liability");
					String pid = ps.addProposal(userid, type, dateofoccurance,
							amountclaimed, natureofinjury, natureofdamage,
							hospitalname, vid, typeofliability);
					request.setAttribute("proposalID", pid);
					request.setAttribute("bean", b);
					rd = request
							.getRequestDispatcher("/jsp/SubmitSuccess.jsp");
					ArrayList<Proposal> list= ps.getProposals(userid);
					session.setAttribute("proposallist",list);	
					checkAvailableInsurance(request, response, userid);
					setVehicleList(request, response);
					rd.forward(request, response);
				}
			}
				
			 catch (Exception e) {
				request.setAttribute("error", e.getMessage());
				//e.printStackTrace();
				rd = request
						.getRequestDispatcher("/jsp/SystemError.jsp");
				rd.forward(request, response);
			}
		}
		}
		}
	}
	
	private void checkAvailableInsurance(HttpServletRequest request,
			HttpServletResponse response, String userID) throws FQException {
		ArrayList<Proposal> proposals = null;
		ArrayList<Insurance> insurances = null;
		ArrayList<Insurance> availableInsuranceList = new ArrayList<Insurance>();
		HttpSession session = request.getSession(false);
		if(session!=null && session.getAttribute("proposallist")!=null){
			proposals = (ArrayList<Proposal>) session.getAttribute("proposallist");
		}
		else{
			proposals = ps.getProposals(userID);
		}	
		if(session!=null && session.getAttribute("insuranceList")!=null){
			insurances = (ArrayList<Insurance>)session.getAttribute("insuranceList");
		}
		else{
			insurances = ps.getInsuranceByUserId(userID);
		}
		

		for(Insurance insurance:insurances){
			boolean flag = true;
			for(Proposal proposal:proposals){
				if(proposal.getQuoteId()==null||proposal.getQuoteId().equals("")){
				if(insurance!=null && insurance.getInsuranceID().equals(proposal.getInsuranceid())){
				   flag = false;	
				   break;
				}
				}
			}
			if(flag){
				availableInsuranceList.add(insurance);
			}
		}
		session.setAttribute("availableInsuranceList", availableInsuranceList);	
		
	}

	public void resetForm(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException{
		    RequestDispatcher rd = null;
		    String type = null;
		    String path = null;
			try {
				type = request.getParameter("type");
				if(type!=null && type.equals("bodyinjury"))
					path = "/jsp/SubmitBodyInjury.jsp";
				if(type!=null && type.equals("vehicledamage"))
					path = "/jsp/SubmitVehicleDamage.jsp";
				if(type!=null && type.equals("liability"))
					path = "/jsp/SubmitLiability.jsp";
				
				request.setAttribute("bean", null);
				rd = request.getRequestDispatcher(path);
				rd.forward(request, response);
			} catch (Exception e) {
				request.setAttribute("error", e.getMessage());
				rd = request
						.getRequestDispatcher("/jsp/SystemError.jsp");
				rd.forward(request, response);
			}
		}
	
	public void setVehicleList(HttpServletRequest request, HttpServletResponse response) throws FQException{
		HttpSession session1 = request.getSession(false);
		if(session1!=null){
			ArrayList<Insurance> availableInsuranceList = null;
		availableInsuranceList = (ArrayList<Insurance>) session1.getAttribute("availableInsuranceList");
		ArrayList<String> availableVDVehicleList = new ArrayList<String>();
		ArrayList<String> availableLVehicleList = new ArrayList<String>();
		for(Insurance insurance:availableInsuranceList){
			if(insurance!=null&&insurance.getTypeOfInsurance().equalsIgnoreCase("Vehicle Damage Coverage")){
				availableVDVehicleList.add(ps.getLicenseNumberByVid(insurance.getVehicleID()));
			}
			if(insurance!=null&&insurance.getTypeOfInsurance().equalsIgnoreCase("Liability Coverage")){
				availableLVehicleList.add(ps.getLicenseNumberByVid(insurance.getVehicleID()));
			}
		}
		session1.setAttribute("vehicleLicenseNumberforVD", availableVDVehicleList);
		session1.setAttribute("vehicleLicenseNumberforL", availableLVehicleList);
		
	}
	}
		
}
