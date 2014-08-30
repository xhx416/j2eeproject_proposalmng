package com.tcs.ilp.fqpms.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tcs.ilp.fqpms.bean.ProposalBean;
import com.tcs.ilp.fqpms.bean.SearchCriteriaBean;
import com.tcs.ilp.fqpms.exception.FQException;
import com.tcs.ilp.fqpms.manager.FQPMS_ProposalView;
import com.tcs.ilp.fqpms.manager.FQPMS_SearchFunction;
import com.tcs.ilp.fqpms.model.Proposal;
import com.tcs.ilp.fqpms.validate.ProposalToBeanConversion;
import com.tcs.ilp.fqpms.validate.SearchValidate;

/**
 * Servlet implementation class ProposalServlet
 */
public class ProposalSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	FQPMS_SearchFunction sf = new FQPMS_SearchFunction();
	ArrayList<Proposal> proposallist = new ArrayList<Proposal>();
	SearchCriteriaBean scb = new SearchCriteriaBean();
	FQPMS_ProposalView pv = new FQPMS_ProposalView();

	/**
	 * Default constructor.
	 */

	public ProposalSearchServlet() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		if (request.getParameter("searchtype") == null) {

			getAllProposals(request, response);

		} else {
			handleMergeSearchProposal(request, response);

		}

	}

	public void getAllProposals(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = null;
		String userid = "";
		ArrayList<Proposal> proposallist = null;
		ArrayList<ProposalBean> proposalbeanlist = null;
		RequestDispatcher rd = null;

		try {
			session=request.getSession(false);
			if (session == null)
				response.sendRedirect("/FQPMS/jsp/Login.jsp");
			else{
			
			
			userid = (String) session.getAttribute("userId");
			proposallist = pv.getAllProposalByUserID(userid);
			proposalbeanlist = ProposalToBeanConversion
					.proposalsToBean(proposallist);
			request.setAttribute("proposallist", proposalbeanlist);
			rd = request.getRequestDispatcher("/jsp/SearchResults.jsp");
			rd.forward(request, response);
		  }
		}catch (FQException e) {
			request.setAttribute("errormessage", e.getMessage());
			rd = request.getRequestDispatcher("/jsp/SearchResults.jsp");
			rd.forward(request, response);
		} catch (Exception e) {
			PrintWriter pw = response.getWriter();
			pw.print("System Error: " + e.getMessage());
			pw.close();
		}
		

	}

	public void handleMergeSearchProposal(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = null;
		String userid = "";
		Proposal p = null;
		RequestDispatcher rd = null;

		try {
			session = request.getSession(false);
			if (session != null)
				userid = (String) session.getAttribute("userId");
			if (request.getParameter("searchtype") != null
					&& request.getParameter("searchtype").equals("bypid")) {
				String proposalid = request.getParameter("proposalid");
				proposallist.clear();

				p = sf.searchProposalById(userid, proposalid);
				if (p != null)
					proposallist.add(p);
				request.setAttribute("clickfrom",
						request.getParameter("searchtype"));
				request.setAttribute("initsearchbypid", proposalid);
				request.setAttribute("proposallist",
						ProposalToBeanConversion.proposalsToBean(proposallist));
				rd = request.getRequestDispatcher("/jsp/SearchResults.jsp");
				rd.forward(request, response);
			}

			else if (request.getParameter("searchtype") != null
					&& request.getParameter("searchtype").equals("bydate")) {

				String day = request.getParameter("day");
				String month = request.getParameter("month");
				String year = request.getParameter("year");
				request.setAttribute("clickfrom",
						request.getParameter("searchtype"));
				request.setAttribute("proposallist", ProposalToBeanConversion
						.proposalsToBean(sf.searchProposayByDate(userid, day,
								month, year)));
				rd = request.getRequestDispatcher("/jsp/SearchResults.jsp");
				rd.forward(request, response);

			}

			else if (request.getParameter("searchtype") != null
					&& request.getParameter("searchtype").equals("bytype")) {
				String proposaltype = request.getParameter("proposaltype");

				request.setAttribute("clickfrom",
						request.getParameter("searchtype"));
				request.setAttribute("proposallist", ProposalToBeanConversion
						.proposalsToBean(sf.searchProposalByType(userid,
								proposaltype)));
				rd = request.getRequestDispatcher("/jsp/SearchResults.jsp");
				rd.forward(request, response);

			}

			else if (request.getParameter("searchtype") != null
					&& request.getParameter("searchtype").equals(
							"byhopitalname")) {
				String proposaltype = request.getParameter("proposaltype");
				String hospitalname = request.getParameter("hospitalname");

				request.setAttribute("clickfrom",
						request.getParameter("searchtype"));
				request.setAttribute("proposallist", ProposalToBeanConversion
						.proposalsToBean(sf.searchProposalByHoptialName(userid,
								proposaltype, hospitalname)));
				rd = request.getRequestDispatcher("/jsp/SearchResults.jsp");
				rd.forward(request, response);
			}

			else if (request.getParameter("searchtype") != null
					&& request.getParameter("searchtype").equals("byvehicle")) {
				String proposaltype = request.getParameter("proposaltype");
				String vehicleid = request.getParameter("vehicleid");
				request.setAttribute("clickfrom",
						request.getParameter("searchtype"));
				request.setAttribute("proposallist", ProposalToBeanConversion
						.proposalsToBean(sf.searchProposalByVehicleID(userid,
								proposaltype, vehicleid)));
				rd = request.getRequestDispatcher("/jsp/SearchResults.jsp");
				rd.forward(request, response);

			}

			else if (request.getParameter("searchtype") != null
					&& request.getParameter("searchtype").equals("byothers")) {

				String day = request.getParameter("day");
				String month = request.getParameter("month");
				String year = request.getParameter("year");
				String proposaltype = request.getParameter("proposaltype");
				String hospitalname = request.getParameter("hospitalname");
				String vehicleid = request.getParameter("vehicleid");

				scb.setDay(day);
				scb.setMonth(month);
				scb.setYear(year);
				scb.setType(proposaltype);
				scb.setHospitalname(hospitalname);
				scb.setVehicleid(vehicleid);

				String error = SearchValidate.validateMergeSearchInput(scb);

				if (error == null || error.isEmpty()) {
					request.setAttribute("clickfrom",
							request.getParameter("searchtype"));
					request.setAttribute("proposallist",
							ProposalToBeanConversion.proposalsToBean(sf
									.searchProposalByOther(userid, scb)));
					rd = request.getRequestDispatcher("/jsp/SearchResults.jsp");
					rd.forward(request, response);
				} else {
					request.setAttribute("clickfrom",
							request.getParameter("searchtype"));
					request.setAttribute("errormessage", error);
					rd = request.getRequestDispatcher("/jsp/SearchResults.jsp");
					rd.forward(request, response);
				}
			}

		} catch (FQException e) {
			request.setAttribute("clickfrom",
					request.getParameter("searchtype"));
			request.setAttribute("errormessage", e.getMessage());
			rd = request.getRequestDispatcher("/jsp/SearchResults.jsp");
			rd.forward(request, response);
		} catch (Exception e) {
			PrintWriter pw = response.getWriter();
			pw.print("System Error: " + e.getMessage());
			pw.close();
		}
	}
	

}
