//package com.tcs.ilp.fqpms.controller;
//
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.util.ArrayList;
//import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//import com.tcs.ilp.fqpms.exception.FQException;
//import com.tcs.ilp.fqpms.manager.FQPMS_ProposalView;
//import com.tcs.ilp.fqpms.model.Proposal;
//
//
///**
// * Servlet implementation class ShowAllServlet
// */
//public class ViewProposalServlet extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//
//	/**
//	 * @see HttpServlet#HttpServlet()
//	 */
//	public ViewProposalServlet() {
//		super();
//		// TODO Auto-generated constructor stub
//	}
//
//	/**
//	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
//	 *      response)
//	 */
//	protected void doGet(HttpServletRequest request,
//			HttpServletResponse response) throws ServletException, IOException {
//		
//		doPost(request, response);
//		// TODO Auto-generated method stub
//	}
//
//	/**
//	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
//	 *      response)
//	 */
//	protected void doPost(HttpServletRequest request,
//			HttpServletResponse response) throws ServletException, IOException {
//
//		HttpSession session = request.getSession(false);
//		
//		FQPMS_ProposalView pv = new FQPMS_ProposalView();
//		ArrayList<Proposal> list;
//		try {
//			list = pv.getAllProposalByUserID(session.getAttribute("userId").toString());
//			RequestDispatcher rd = request.getRequestDispatcher("/jsp/SearchResults.jsp");
//			request.setAttribute("proposallist", list);
//			//request.setAttribute("uid", uid);
//			rd.forward(request, response);
//		} catch (FQException e) {
//			request.setAttribute("error", e.getMessage());
//			RequestDispatcher rd = request
//					.getRequestDispatcher("/jsp/SystemError.jsp");
//			rd.forward(request, response);
//		} catch (Exception e) {
//			PrintWriter pw = response.getWriter();
//			pw.print("System Error: " + e.getMessage());
//			pw.close();
//		}
//		
//	}
//
//}
