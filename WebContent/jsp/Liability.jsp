
<%@page import="com.tcs.ilp.fqpms.model.LiabilityProposal"%>
<%
	response.setHeader("Cache-Control", "no-cache"); //Forces caches to obtain a new copy of the page from the origin server
	response.setHeader("Cache-Control", "no-store"); //Directs caches not to store the page under any circumstance
	response.setDateHeader("Expires", 0); //Causes the proxy cache to see the page as "stale"
	response.setHeader("Pragma", "no-cache"); //HTTP 1.0 backward compatibility
%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.HashMap"%>
<%@page import="com.tcs.ilp.fqpms.bean.ProposalBean"%>
<%@page import="com.tcs.ilp.fqpms.bean.LiabilityProposalBean"%>
<%@page import="com.tcs.ilp.fqpms.model.Proposal"%>
<%@page import="com.tcs.ilp.fqpms.model.Insurance"%>
<html>
<head>
<script type="text/javascript" src="/FQPMS/js/submitform.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%
		if (session == null || session.getAttribute("userId") == null) {
			response.sendRedirect("/FQPMS/jsp/Login.jsp");
		} else {
	%>
<%ArrayList<Insurance> availableInsuranceList = (ArrayList<Insurance>)session.getAttribute("availableInsuranceList");
boolean isValidToSubmit = false;	
if(availableInsuranceList!=null) {
	for(Insurance insurance: availableInsuranceList){
		if(insurance!=null &&insurance.getTypeOfInsurance().equals("Liability Coverage")){
				isValidToSubmit = true; 
				break;
		}
		}
	}
		%>
	<div>
		<%
			boolean isLvalid = (Boolean) session.getAttribute("isLValid");
		if (isLvalid) {
		if(!isValidToSubmit){
		
		%>
			<p style="font-size: 15px; color:#336699; line-height: 25px">
			Dear Customer, <br/><br/>
			You currently cannot submit liability proposal because you have one in pending, <br/>
			Please kindly wait for our system to process your submitted proposal or you can <br/>
			delete/manage your current pending proposal to process further
			</p>
	
		<%
			} else {
		%>

		<h2>Liability Proposal</h2>

		<p class="proposalDescription">Liability Proposal is for the case
			when you cause damage to third party's vehicle or the person, a quote
			will be generated once the proposal is approved.</p>

		<form id="liabform" name="f3" onsubmit="return validate3()"
			method='POST' action='/FQPMS/ProposalSubmitServlet?type=liability'
			onreset="resetLI()">
			<hr></hr>
			<%
				HashMap<String, String> list_l = (HashMap<String, String>) request
								.getAttribute("msg3");
						ProposalBean proposalbean = (ProposalBean) request
								.getAttribute("bean");
						LiabilityProposalBean l = null;
						if (proposalbean instanceof LiabilityProposalBean) {
							l = (LiabilityProposalBean) proposalbean;
						}
			%>
			<table style="margin-top: 3%; width: 85%;">

				<tr>
					<td class="right" style="width:20%">License Number</td>
					<td class="left"><select style="font-size: 12px; width:auto"
						name="vehicleid" id="vehicleid3" onchange="validVehicleid3()">
							<%
								String vid = "";
										ArrayList<String> vidlist = new ArrayList<String>();
										vidlist = (ArrayList<String>) session
												.getAttribute("vehicleLicenseNumberforL");
										if (l != null) {
											vid = l.getVehicleid();
										}
							%>
							<option value="" <%if (vid.equals("")) {%> selected <%}%>>Select</option>
							<%
								for (String vehicleId : vidlist) {
							%>
							<option value=<%=vehicleId%> <%if (vid.equals(vehicleId)) {%>
								selected <%}%>><%=vehicleId%></option>
							<%
								}
							%>
					</select></td>
					<%
						String vidmsg = "";
								if (list_l != null && list_l.get("vid") != null) {
									vidmsg = list_l.get("vid");
								}
					%>
					<td class="error"><span id="vehicleiddisp3" style="color: red"><%=vidmsg%></span></td>
				</tr>

				<tr>
					<td class="right">Date of Accident</td>
					<td class="left"><select style="width: 60px; font-size: 12px"
						name="day" id="day3" onchange="checkDate3()">
							<%
								String day = "";
										String month = "";
										String year = "";
										if (l != null) {
											day = l.getDate();
											month = l.getMonth();
											year = l.getYear();
										}
							%>
							<option value="0" <%if (day.equals("0")) {%> selected <%}%>>dd</option>
							<option value="1" <%if (day.equals("1")) {%> selected <%}%>>01</option>
							<option value="2" <%if (day.equals("2")) {%> selected <%}%>>02</option>
							<option value="3" <%if (day.equals("3")) {%> selected <%}%>>03</option>
							<option value="4" <%if (day.equals("4")) {%> selected <%}%>>04</option>
							<option value="5" <%if (day.equals("5")) {%> selected <%}%>>05</option>
							<option value="6" <%if (day.equals("6")) {%> selected <%}%>>06</option>
							<option value="7" <%if (day.equals("7")) {%> selected <%}%>>07</option>
							<option value="8" <%if (day.equals("8")) {%> selected <%}%>>08</option>
							<option value="9" <%if (day.equals("9")) {%> selected <%}%>>09</option>
							<option value="10" <%if (day.equals("10")) {%> selected <%}%>>10</option>
							<option value="11" <%if (day.equals("11")) {%> selected <%}%>>11</option>
							<option value="12" <%if (day.equals("12")) {%> selected <%}%>>12</option>
							<option value="13" <%if (day.equals("13")) {%> selected <%}%>>13</option>
							<option value="14" <%if (day.equals("14")) {%> selected <%}%>>14</option>
							<option value="15" <%if (day.equals("15")) {%> selected <%}%>>15</option>
							<option value="16" <%if (day.equals("16")) {%> selected <%}%>>16</option>
							<option value="17" <%if (day.equals("17")) {%> selected <%}%>>17</option>
							<option value="18" <%if (day.equals("18")) {%> selected <%}%>>18</option>
							<option value="19" <%if (day.equals("19")) {%> selected <%}%>>19</option>
							<option value="20" <%if (day.equals("20")) {%> selected <%}%>>20</option>
							<option value="21" <%if (day.equals("21")) {%> selected <%}%>>21</option>
							<option value="22" <%if (day.equals("22")) {%> selected <%}%>>22</option>
							<option value="23" <%if (day.equals("23")) {%> selected <%}%>>23</option>
							<option value="24" <%if (day.equals("24")) {%> selected <%}%>>24</option>
							<option value="25" <%if (day.equals("25")) {%> selected <%}%>>25</option>
							<option value="26" <%if (day.equals("26")) {%> selected <%}%>>26</option>
							<option value="27" <%if (day.equals("27")) {%> selected <%}%>>27</option>
							<option value="28" <%if (day.equals("28")) {%> selected <%}%>>28</option>
							<option value="29" <%if (day.equals("29")) {%> selected <%}%>>29</option>
							<option value="30" <%if (day.equals("30")) {%> selected <%}%>>30</option>
							<option value="31" <%if (day.equals("31")) {%> selected <%}%>>31</option>
					</select> / <select style="width: 60px; font-size: 12px" name="month"
						id="month3" onchange="checkDate3()">
							<option value="-1" <%if (month.equals("-1")) {%> selected <%}%>>mmm</option>
							<option value="1" <%if (month.equals("1")) {%> selected <%}%>>JAN</option>
							<option value="2" <%if (month.equals("2")) {%> selected <%}%>>FEB</option>
							<option value="3" <%if (month.equals("3")) {%> selected <%}%>>MAR</option>
							<option value="4" <%if (month.equals("4")) {%> selected <%}%>>APR</option>
							<option value="5" <%if (month.equals("5")) {%> selected <%}%>>MAY</option>
							<option value="6" <%if (month.equals("6")) {%> selected <%}%>>JUN</option>
							<option value="7" <%if (month.equals("7")) {%> selected <%}%>>JUL</option>
							<option value="8" <%if (month.equals("8")) {%> selected <%}%>>AUG</option>
							<option value="9" <%if (month.equals("9")) {%> selected <%}%>>SEP</option>
							<option value="10" <%if (month.equals("10")) {%> selected <%}%>>OCT</option>
							<option value="11" <%if (month.equals("11")) {%> selected <%}%>>NOV</option>
							<option value="12" <%if (month.equals("12")) {%> selected <%}%>>DEC</option>
					</select> / <select style="width: 70px; font-size: 12px" name="year"
						id="year3" onchange="checkDate3()">
							<option value="0" <%if (year.equals("0")) {%> selected <%}%>>yyyy</option>
							<option value="2008" <%if (year.equals("2008")) {%> selected
								<%}%>>2008</option>
							<option value="2009" <%if (year.equals("2009")) {%> selected
								<%}%>>2009</option>
							<option value="2010" <%if (year.equals("2010")) {%> selected
								<%}%>>2010</option>
							<option value="2011" <%if (year.equals("2011")) {%> selected
								<%}%>>2011</option>
							<option value="2012" <%if (year.equals("2012")) {%> selected
								<%}%>>2012</option>
							<option value="2013" <%if (year.equals("2013")) {%> selected
								<%}%>>2013</option>
					</select></td>
					<%
						String datemsg = "";
								if (list_l != null && list_l.get("date") != null) {
									datemsg = list_l.get("date");
								}
					%>
					<td class="error"><span id="datedisp3" style="color: red"><%=datemsg%></span></td>
				</tr>

				<tr>
					<td class="right">Claim Amount(USD)</td>
					<%
						String amount = "";
								if (l != null) {
									amount = l.getAmountclaimed();
								}
					%>
					<td class="left"><input maxlength="11" type="text"
						style="width: 220px" name="amount" id="amount3"
						value="<%=amount%>" onblur="validAmount3()" /></td>
					<%
						String amtmsg = "";
								if (list_l != null && list_l.get("amount") != null) {
									amtmsg = list_l.get("amount");
								}
					%>
					<td class="error"><span id="amountdisp3" style="color: red"><%=amtmsg%></span></td>
				</tr>

				<tr>
					<td class="right">Type of Liability</td>
					<%
						String typeliability = "";
								if (l != null) {
									typeliability = l.getTypeofliability();
								}
					%>
					<td class="left"><select style="font-size: 12px"
						name="liability" id="liability" onchange="validLiability()">
							<option value="">Select</option>
							<option value="Body injury to third person"
								<%if (typeliability.equals("Body injury to third person")) {%>
								selected <%}%>>Body injury to third person</option>
							<option value="Third person vehicle damage"
								<%if (typeliability.equals("Third person vehicle damage")) {%>
								selected <%}%>>Third person vehicle damage</option>
					</select></td>
					<%
						String lmsg = "";
								if (list_l != null && list_l.get("typeliability") != null) {
									lmsg = list_l.get("typeliability");
								}
					%>
					<td class="error"><span id="liabilitydisp" style="color: red"><%=lmsg%></span></td>
				</tr>

			</table>

			<input type="submit" class="submitbutton" value="Submit"
				style="margin-left: 13.5%; margin-top: 50px; margin-bottom: 50px;" />
			<%
				if (proposalbean == null) {
			%>
			<input type="reset" class="resetbutton" value="Reset"
				style="margin-left: 2%; margin-top: 50px; margin-bottom: 50px;" />
			<%
				} else {
			%>
			<a href="/FQPMS/ProposalSubmitServlet?execute=reset&&type=liability"
				class="resetbutton"
				style="margin-left: 2%; margin-top: 50px; margin-bottom: 50px;">Reset</a>
			<%
				}
			%>
		</form>
		<%
			}
		}else{%>
			<p style="font-size: 15px; color:#336699; line-height: 25px">
						Dear Customer, <br/><br/>
						System detects that you are not eligible for raising a Liability Proposal.<br/>
						Please register a <font style="text-decoration: underline;">Liability Insurance </font>via Fast Quote User Management System first,<br/>	
						or you can check with our staff at the counter if have you have already registered one.	
					</p>
		<% }%>
		
	</div>
	<%
		}
	%>
</body>
</html>