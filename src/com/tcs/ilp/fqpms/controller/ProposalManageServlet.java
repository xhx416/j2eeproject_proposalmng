package com.tcs.ilp.fqpms.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tcs.ilp.fqpms.bean.BodyInjuryProposalBean;
import com.tcs.ilp.fqpms.bean.InsuranceBean;
import com.tcs.ilp.fqpms.bean.LiabilityProposalBean;
import com.tcs.ilp.fqpms.bean.ProposalBean;
import com.tcs.ilp.fqpms.bean.VehicleBean;
import com.tcs.ilp.fqpms.exception.FQException;
import com.tcs.ilp.fqpms.manager.FQPMS_ProposalUpdate;
import com.tcs.ilp.fqpms.model.Insurance;
import com.tcs.ilp.fqpms.model.LiabilityProposal;
import com.tcs.ilp.fqpms.model.Proposal;
import com.tcs.ilp.fqpms.model.Vehicle;
import com.tcs.ilp.fqpms.model.VehicleDamageProposal;
import com.tcs.ilp.fqpms.validate.VehicleDamageProposalBean;

/**
 * Servlet implementation class ViewProposalServlet
 */
public class ProposalManageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	FQPMS_ProposalUpdate ps = new FQPMS_ProposalUpdate();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProposalManageServlet() {
		super();
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
		String pid = request.getParameter("pid");
		String action = request.getParameter("action");

		HttpSession session = request.getSession(false);
		String uid = "";
		if (session == null || session.getAttribute("userId") == null) {			
			response.sendRedirect("/FQPMS/jsp/Login.jsp");
		}
		else{
			uid = session.getAttribute("userId").toString();
			// session.setAttribute("pid_update", pid);
		
		Proposal p = null;
		ProposalBean b = null;

		// START-------------------------------------------------------------------------

		try {

			if (action.equalsIgnoreCase("update1")) {
				p = ps.getProposalByID(pid);
				b = com.tcs.ilp.fqpms.validate.ProposalValidate.toBean(p);
				session.setAttribute("proposal_update", p);
				RequestDispatcher rd = request
						.getRequestDispatcher("/jsp/UpdateProposalDetails.jsp");
				if (ps.getVehicleByUserId(uid) != null && session.getAttribute("vehiclelicensenumberList") == null)
					session.setAttribute("vehiclelicensenumberList",
							ps.getVehicleByUserId(uid));
				request.setAttribute("bean", b);
				rd.forward(request, response);
			} else if (action.equalsIgnoreCase("view")) {
				p = ps.getProposalByID(pid);
				b = com.tcs.ilp.fqpms.validate.ProposalValidate.toBean(p);
				String vid = null;
				String typeOfInsurance = null;

				Insurance insuranceDetails = null;
				Vehicle vehicleDetails = null;

				InsuranceBean insuranceBean = null;
				VehicleBean vehicleBean = null;

				if (p.getProposalid().startsWith("propi")) {
					typeOfInsurance = "bodyinjury";

					insuranceDetails = ps.getInsuranceDetails(uid, vid,
							typeOfInsurance);
					String amount = new String(""
							+ insuranceDetails.getCoverageAmount());
					String duration = new String(""
							+ insuranceDetails.getDuration());
					java.sql.Date date = new java.sql.Date(insuranceDetails
							.getStartDate().getTime());
					String premium = new String(""
							+ insuranceDetails.getPremium());
					double balance = insuranceDetails.getBalance();
					double availableBalance = insuranceDetails
							.getAvailableBalance();
					insuranceBean = new InsuranceBean(
							insuranceDetails.getInsuranceID(),
							insuranceDetails.getTypeOfInsurance(),
							insuranceDetails.getVehicleID(), amount, duration,
							date.toString(), premium, uid, balance,
							availableBalance);

				} else if (p.getProposalid().startsWith("propv")) {
					typeOfInsurance = "vehicledamage";
					vid = ((VehicleDamageProposal) p).getVehicleid();

					insuranceDetails = ps.getInsuranceDetails(uid, vid,
							typeOfInsurance);
					String amount = new String(""
							+ insuranceDetails.getCoverageAmount());
					String duration = new String(""
							+ insuranceDetails.getDuration());
					java.sql.Date date = new java.sql.Date(insuranceDetails
							.getStartDate().getTime());
					String premium = new String(""
							+ insuranceDetails.getPremium());
					double balance = insuranceDetails.getBalance();
					double availableBalance = insuranceDetails
							.getAvailableBalance();
					insuranceBean = new InsuranceBean(
							insuranceDetails.getInsuranceID(),
							insuranceDetails.getTypeOfInsurance(),
							insuranceDetails.getVehicleID(), amount, duration,
							date.toString(), premium, uid, balance,
							availableBalance);

					vehicleDetails = ps.getVehicleDetails(vid);
					String yearOfManufacture = new String(""
							+ vehicleDetails.getYearOfManufacture());
					String price = new String("" + vehicleDetails.getPrice());
					vehicleBean = new VehicleBean(
							vehicleDetails.getVehicleID(),
							vehicleDetails.getMake(),
							vehicleDetails.getModel(), yearOfManufacture,
							vehicleDetails.getVehicleType(), price);

				} else if (p.getProposalid().startsWith("propl")) {
					typeOfInsurance = "liability";
					vid = ((LiabilityProposal) p).getVehicleid();

					insuranceDetails = ps.getInsuranceDetails(uid, vid,
							typeOfInsurance);
					String amount = new String(""
							+ insuranceDetails.getCoverageAmount());
					String duration = new String(""
							+ insuranceDetails.getDuration());
					java.sql.Date date = new java.sql.Date(insuranceDetails
							.getStartDate().getTime());
					String premium = new String(""
							+ insuranceDetails.getPremium());
					double balance = insuranceDetails.getBalance();
					double availableBalance = insuranceDetails
							.getAvailableBalance();
					insuranceBean = new InsuranceBean(
							insuranceDetails.getInsuranceID(),
							insuranceDetails.getTypeOfInsurance(),
							insuranceDetails.getVehicleID(), amount, duration,
							date.toString(), premium, uid, balance,
							availableBalance);

					vehicleDetails = ps.getVehicleDetails(vid);
					String yearOfManufacture = new String(""
							+ vehicleDetails.getYearOfManufacture());
					String price = new String("" + vehicleDetails.getPrice());
					vehicleBean = new VehicleBean(
							vehicleDetails.getVehicleID(),
							vehicleDetails.getMake(),
							vehicleDetails.getModel(), yearOfManufacture,
							vehicleDetails.getVehicleType(), price);
				}

				RequestDispatcher rd = request
						.getRequestDispatcher("/jsp/ViewProposalDetails.jsp");
				request.setAttribute("bean", b);
				request.setAttribute("typeOfInsurance", typeOfInsurance);
				request.setAttribute("insuranceDetails", insuranceBean);

				if (vehicleBean != null) {
					request.setAttribute("vehicleDetails", vehicleBean);
				}

				rd.forward(request, response);

				// END------------------------------------------------------------------------------------------------------

			} else if (action.equalsIgnoreCase("delete")) {
				p = ps.getProposalByID(pid);
				if (ps.deleteById((String)session.getAttribute("userId"), pid)) {
					String vid = null;
					String typeOfInsurance = null;
					InsuranceBean insuranceBean = null;

					if (p.getProposalid().startsWith("propi")) {
						typeOfInsurance = "bodyinjury";
					}else if (p.getProposalid().startsWith("propv")) {
						typeOfInsurance = "vehicledamage";
						vid = ((VehicleDamageProposal) p).getVehicleid();
					}else if (p.getProposalid().startsWith("propl")) {
						typeOfInsurance = "liability";
						vid = ((LiabilityProposal) p).getVehicleid();
					}
					RequestDispatcher rd = request
							.getRequestDispatcher("/jsp/SearchResults.jsp?status=deleted");
					ArrayList<Proposal> list = ps.getProposals(request
							.getParameter("userId"));
					session.setAttribute("proposallist", list);
					checkAvailableInsurance(request, response, (String)session.getAttribute("userId"));
					setVehicleList(request, response);
					Insurance insuranceDetails = ps.getInsuranceDetails(uid, vid,
							typeOfInsurance);
					String amount = new String(""
							+ insuranceDetails.getCoverageAmount());
					String duration = new String(""
							+ insuranceDetails.getDuration());
					java.sql.Date date = new java.sql.Date(insuranceDetails
							.getStartDate().getTime());
					String premium = new String(""
							+ insuranceDetails.getPremium());
					double balance = insuranceDetails.getBalance();
					double availableBalance = insuranceDetails
							.getAvailableBalance();
					insuranceBean = new InsuranceBean(
							insuranceDetails.getInsuranceID(),
							insuranceDetails.getTypeOfInsurance(),
							insuranceDetails.getVehicleID(), amount, duration,
							date.toString(), premium, uid, balance,
							availableBalance);
					request.setAttribute("bean", b);
					request.setAttribute("typeOfInsurance", typeOfInsurance);
					request.setAttribute("insuranceDetails", insuranceBean);
					rd.forward(request, response);
				} else {
					throw new FQException("Delete failure.");
				}
				// request.setAttribute("proposal", p);
			} else if (action.equals("update2")) {
				p = ps.getProposalByID(pid);
				b = com.tcs.ilp.fqpms.validate.ProposalValidate.toBean(p);
				// server-side validation
				String type = request.getParameter("type");
				HashMap<String, String> msg = null;
				if (type.equals("bodyinjury")) {
					b = new BodyInjuryProposalBean(
							request.getParameter("year"),
							request.getParameter("month"),
							request.getParameter("day"),
							request.getParameter("amount"),
							request.getParameter("natureofinjury"),
							request.getParameter("hospitalname"));
					b.setPid(pid);
					b.setUid(uid);
					b.setType("Body Injury Proposal");
					b.setPid(p.getProposalid());
					if (p.getQuoteId() != "" || p.getQuoteId() != null) {
						b.setQuoteStatus("Quote Generated");
					} else {
						b.setQuoteStatus("Pending");
					}
				} else if (type.equals("vehicledamage")) {
					b = new VehicleDamageProposalBean(
							request.getParameter("year"),
							request.getParameter("month"),
							request.getParameter("day"),
							request.getParameter("amount"),
							request.getParameter("vehicleid"),
							request.getParameter("natureofdamage"));
					b.setPid(pid);
					b.setUid(uid);
					b.setType("Vehicle Damage Proposal");
					b.setPid(p.getProposalid());
					if (p.getQuoteId() != "" || p.getQuoteId() != null) {
						b.setQuoteStatus("Quote Generated");
					} else {
						b.setQuoteStatus("Pending");
					}
				} else if (type.equals("liability")) {
					b = new LiabilityProposalBean(request.getParameter("year"),
							request.getParameter("month"),
							request.getParameter("day"),
							request.getParameter("amount"),
							request.getParameter("vehicleid"),
							request.getParameter("liability"));
					msg = ps.validate(type, b);
					b.setPid(pid);
					b.setUid(uid);
					b.setType("Vehicle Damage Proposal");
					b.setPid(p.getProposalid());
					if (p.getQuoteId() != "" || p.getQuoteId() != null) {
						b.setQuoteStatus("Quote Generated");
					} else {
						b.setQuoteStatus("Pending");
					}
				}
				msg = ps.validate(type, b);
				// error occurs
				if (msg.size() != 0) {
					request.setAttribute("msg", msg);
					request.setAttribute("bean", b);
					RequestDispatcher rd = request
							.getRequestDispatcher("/jsp/UpdateProposalDetails.jsp");
					rd.forward(request, response);
				} else {
					String userid = uid;
					StringBuffer sb = new StringBuffer();
					sb.append(request.getParameter("day") + "-");
					sb.append(request.getParameter("month") + "-");
					sb.append(request.getParameter("year"));
					Date dateofoccurance = new SimpleDateFormat("dd-MM-yyyy")
							.parse(sb.toString());
					double amountclaimed = Double.parseDouble(request
							.getParameter("amount"));

					String vid = null;
					if (request.getParameter("vehicleid") != null)
						vid = ps.getVidByLicneseNumber(request
								.getParameter("vehicleid"));
					/* validate coverage date and amount claimed */
					String insuranceID = ps.getInsuranceID(userid, vid, type);
					if (insuranceID == null) {
						throw new FQException("System cannot find " + type
								+ " insurance for "
								+ request.getParameter("vehicleid") + ". ");
					}
					String path = getServletContext().getRealPath(
							"/xml/module2-paidpremiumdetails.xml");
					boolean dateVal = ps.validateDuationCoverage(insuranceID,
							dateofoccurance, new File(path));
					String typeofliability2 = request.getParameter("liability");
					double tempAmt = amountclaimed;
					if (typeofliability2!=null&&typeofliability2.startsWith("Body")) {
						tempAmt /= 0.85;
					}
					boolean amtVal = ps.validateAmount(insuranceID, tempAmt);
					/* end of validation */
					if (!dateVal || !amtVal) {
						if (!dateVal) {
							msg.put("date",
									"Date is not within the insurance coverage duration.");
						}
						if (!amtVal) {
							msg.put("amount",
									"Amount claimed is beyond the balance.");
						}
						request.setAttribute("msg", msg);
						request.setAttribute("bean", b);
						RequestDispatcher rd = request
								.getRequestDispatcher("/jsp/UpdateProposalDetails.jsp");
						rd.forward(request, response);
					} else {
						String natureofinjury = request
								.getParameter("natureofinjury");
						String natureofdamage = request
								.getParameter("natureofdamage");
						String hospitalname = request
								.getParameter("hospitalname");
						String typeofliability = request
								.getParameter("liability");
						if (ps.updateProposal(pid, userid, type,
								dateofoccurance, amountclaimed, natureofinjury,
								natureofdamage, hospitalname, vid,
								typeofliability)) {
							ArrayList<Proposal> list = ps.getProposals(userid);
							
							session.setAttribute("proposallist", list);
							checkAvailableInsurance(request, response, userid);
							RequestDispatcher rd = request
									.getRequestDispatcher("/jsp/SearchResults.jsp?status=updated");

							rd.forward(request, response);
						}
					}
				}
			} else if (action != null && action.equalsIgnoreCase("resetform")) {
				resetform(request, response);
			}
		} 
		catch (FQException e) {
			request.setAttribute("error", e.getMessage());
			RequestDispatcher rd = request
					.getRequestDispatcher("/jsp/SystemError.jsp");
			rd.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			PrintWriter pw = response.getWriter();
			pw.print("System Error: " + e.getMessage());
			pw.close();
		}
		}
	}

	public void resetform(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		Proposal p = null;
		ProposalBean proposalbean = null;
		try {
			HttpSession session = request.getSession(false);
			if (session != null
					&& session.getAttribute("proposal_update") != null) {
				p = (Proposal) session.getAttribute("proposal_update");
				// p = ps.getProposalByID(pid);
				proposalbean = com.tcs.ilp.fqpms.validate.ProposalValidate
						.toBean(p);
				request.setAttribute("bean", proposalbean);

				RequestDispatcher rd = request
						.getRequestDispatcher("/jsp/UpdateProposalDetails.jsp");
				rd.forward(request, response);

			}
		} catch (Exception e) {
			PrintWriter pw = response.getWriter();
			pw.print("System Error: " + e.getMessage());
			pw.close();
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