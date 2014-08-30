package com.tcs.ilp.fqpms.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import oracle.net.ns.SessionAtts;

import com.tcs.ilp.fqpms.exception.FQException;
import com.tcs.ilp.fqpms.manager.FQPMS_CheckInsuranceType;
import com.tcs.ilp.fqpms.manager.FQPMS_Login;
import com.tcs.ilp.fqpms.manager.FQPMS_ProposalSubmit;
import com.tcs.ilp.fqpms.model.BodyInjuryProposal;
import com.tcs.ilp.fqpms.model.Insurance;
import com.tcs.ilp.fqpms.model.LiabilityProposal;
import com.tcs.ilp.fqpms.model.Proposal;
import com.tcs.ilp.fqpms.model.User;
import com.tcs.ilp.fqpms.model.Vehicle;
import com.tcs.ilp.fqpms.model.VehicleDamageProposal;




/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	FQPMS_Login service = new FQPMS_Login();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		
//		RequestDispatcher rd = request
//				.getRequestDispatcher("/jsp/Login.jsp");


		if(request.getParameter("action")!=null&& request.getParameter("action").equals("login")){
			
		HttpSession session1 = request.getSession(true);
			try {
				
				
				boolean flag = service.login(request.getParameter("userId"), request.getParameter("Password"));
				
				if(flag){
					ArrayList<Proposal> list=service.getProposals(request.getParameter("userId"));
					session1.setAttribute("proposallist",list);	
					session1.setAttribute("userId", request.getParameter("userId"));
					session1.setAttribute("userName", service.getUserByUserId(request.getParameter("userId")).getUserName());
					session1.setAttribute("vehicleList", service.getVehicleByUserId(request.getParameter("userId")));
					session1.setAttribute("insuranceList", service.getInsuranceByUserId(request.getParameter("userId")));
					session1.setAttribute("vehicleIdList", service.getVehicleLicenseNumberByUserId(request.getParameter("userId")));
					checkInsuranceAvailability(request, response, request.getParameter("userId"));
					checkAvailableInsurance(request, response, request.getParameter("userId"));
					
					
					
					setVehicleList(request, response);
					
					session1.setAttribute("errorMessage", null);
					response.sendRedirect("/FQPMS/jsp/FQPMS_index.jsp");
				}else{
					session1.invalidate();
					throw new FQException("The User with this ID does not exist!");
				}
			 }catch (FQException e) {
					// TODO Auto-generated catch block
				    String errorMessage = e.getMessage();
				    session1.setAttribute("errorMessage", errorMessage);
				    
				    response.sendRedirect("/FQPMS/jsp/Login.jsp");
				}
	
		}else if(request.getParameter("action")!=null&&request.getParameter("action").equals("logout")){
			HttpSession session = request.getSession(false);
			if(session!=null){
			session.setAttribute("userId", null);
			session.invalidate();
			}
			//rd.forward(request,response);
			response.sendRedirect("/FQPMS/jsp/Login.jsp");
	
		}
		else{
			HttpSession session = request.getSession(false);
			if(session ==null || session.getAttribute("userId") == null){
				response.sendRedirect("/FQPMS/jsp/Login.jsp");
			}
		}
	}
	
	public void checkInsuranceAvailability(HttpServletRequest request, HttpServletResponse response,String userID) throws ServletException, IOException{
		HttpSession session = request.getSession(false);
		if(session!=null){
	    User user = new User();
		user.setUserId(userID);
		FQPMS_CheckInsuranceType checkInsuranceType = new FQPMS_CheckInsuranceType();
		ArrayList<String> list;
		try {
			list = checkInsuranceType.getInsuranceType(userID);
			boolean isBValid = false;
			boolean isVValid = false;
			boolean isLValid = false;
			if (list != null) {
				for (int i = 0; i < list.size(); i++) {
					String type = list.get(i);

					if (type.startsWith("Body")) {
						isBValid = true;

					} else if (type.startsWith("Vehicle")) {
						isVValid = true;

					} else if (type.startsWith("Liability")) {
						isLValid = true;

					}

				}
			}
			FQPMS_ProposalSubmit ps = new FQPMS_ProposalSubmit();
			if(ps.getVehicleByUserId(userID)!=null)
			
			session.setAttribute("isBValid", isBValid);
			session.setAttribute("isVValid", isVValid);
			session.setAttribute("isLValid", isLValid);
//			RequestDispatcher rd = request
//					.getRequestDispatcher("/jsp/SubmitBodyInjury.jsp");
//			rd.forward(request, response);
		} catch (FQException e) {
			request.setAttribute("error", e.getMessage());
			RequestDispatcher rd = request
					.getRequestDispatcher("/jsp/SystemError.jsp");
			rd.forward(request, response);
		}

	}

	}
	
	public void checkAvailableInsurance(HttpServletRequest request, HttpServletResponse response,String userID) throws FQException{
		//boolean flag = false;
		ArrayList<Proposal> proposals = null;
		ArrayList<Insurance> insurances = null;
		ArrayList<Insurance> availableInsuranceList = new ArrayList<Insurance>();
		HttpSession session = request.getSession(false);
		if(session!=null && session.getAttribute("proposallist")!=null){
			proposals = (ArrayList<Proposal>) session.getAttribute("proposallist");
		}
		else{
			proposals = service.getProposals(userID);
		}	
		if(session!=null && session.getAttribute("insuranceList")!=null){
			insurances = (ArrayList<Insurance>)session.getAttribute("insuranceList");
		}
		else{
			insurances = service.getInsuranceByUserId(userID);
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
	
	public void setVehicleList(HttpServletRequest request, HttpServletResponse response) throws FQException{
		HttpSession session1 = request.getSession(false);
		if(session1!=null){
			ArrayList<Insurance> availableInsuranceList = null;
		availableInsuranceList = (ArrayList<Insurance>) session1.getAttribute("availableInsuranceList");
		ArrayList<String> availableVDVehicleList = new ArrayList<String>();
		ArrayList<String> availableLVehicleList = new ArrayList<String>();
		for(Insurance insurance:availableInsuranceList){
			if(insurance!=null&&insurance.getTypeOfInsurance().equalsIgnoreCase("Vehicle Damage Coverage")){
				availableVDVehicleList.add(service.getLicenseNumberByVid(insurance.getVehicleID()));
			}
			if(insurance!=null&&insurance.getTypeOfInsurance().equalsIgnoreCase("Liability Coverage")){
				availableLVehicleList.add(service.getLicenseNumberByVid(insurance.getVehicleID()));
			}
		}
		session1.setAttribute("vehicleLicenseNumberforVD", availableVDVehicleList);
		session1.setAttribute("vehicleLicenseNumberforL", availableLVehicleList);
		
	}
	}
	

}
