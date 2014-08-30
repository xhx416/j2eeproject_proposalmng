
<%
	response.setHeader("Cache-Control", "no-cache"); //Forces caches to obtain a new copy of the page from the origin server
	response.setHeader("Cache-Control", "no-store"); //Directs caches not to store the page under any circumstance
	response.setDateHeader("Expires", 0); //Causes the proxy cache to see the page as "stale"
	response.setHeader("Pragma", "no-cache"); //HTTP 1.0 backward compatibility
%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="com.tcs.ilp.fqpms.model.Proposal"%>
<%@page import="com.tcs.ilp.fqpms.model.BodyInjuryProposal"%>
<%@page import="com.tcs.ilp.fqpms.model.VehicleDamageProposal"%>
<%@page import="com.tcs.ilp.fqpms.model.LiabilityProposal"%>
<%@page import="com.tcs.ilp.fqpms.bean.InsuranceBean"%>
<%@page import="com.tcs.ilp.fqpms.bean.VehicleBean"%>
<%@page import="com.tcs.ilp.fqpms.bean.ProposalBean"%>
<%@page import="com.tcs.ilp.fqpms.bean.BodyInjuryProposalBean"%>
<%@page import="com.tcs.ilp.fqpms.validate.VehicleDamageProposalBean"%>
<%@page import="com.tcs.ilp.fqpms.bean.LiabilityProposalBean"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View Proposal Information</title>
<link rel="stylesheet" href="/FQPMS/css/Template.css" type="text/css">
<script type="text/javascript" src="/FQPMS/js/ToggleVisibility.js"></script>
<link rel="stylesheet" href="/FQPMS/css/jquery-cupertino-ui.css" />
<script src="/FQPMS/js/jquery-1.9.1.js"></script>
<script src="/FQPMS/js/jquery-ui.js"></script>


<script type="text/javascript">


	function validateDelete() {
		var flag = confirm("Do you want to proceed?");
		return flag;
	}

	$(function() {
		$("#updatebutton, #deletebutton, #backbutton").button().click(function(event) {
		
		});
	});
</script>
</head>
<body>
	<%
		if (session == null || session.getAttribute("userId") == null) {
			response.sendRedirect("/FQPMS/jsp/Login.jsp");
		} else {
			String username = (String)session.getAttribute("userName");
	%>

	<div id="mainframe">
		<div id="topbar"><jsp:include page="/layout/TopBar.jsp" /></div>
		<div id="header"><jsp:include page="/layout/Head.jsp" /></div>
		<div id="navibar"><jsp:include page="/layout/NaviBar.jsp" /></div>
		<div id="sidebar"><jsp:include page="/layout/SideBar.jsp" /></div>
		<div id="rightContainer">
		<h2 style="font-size: 30px; margin-left:1%">View Proposal</h2>
				<hr />
       <a style="font-size:18px; color:black; margin-right:0.5%; float:right; margin-bottom:50px; margin-left: 3%; left: 58%;" id="backbutton1"
						href="javascript:history.go(-1)"
						>Back</a>	
			<table style="margin-left:20%; margin-top:3%; width: 65%; border: 1px solid #f2f2f2">
			
			<tr class="tr_head">
			<td style="text-algin:left"colspan="2">
			<h2 >Proposal Details</h2>
              </td>			
             </tr>
				<%
					ProposalBean p = (ProposalBean) request.getAttribute("bean");

						String typeOfInsurance = (String) request
								.getAttribute("typeOfInsurance");

						InsuranceBean insurance = (InsuranceBean) request
								.getAttribute("insuranceDetails");

						VehicleBean vehicle = (VehicleBean) request
								.getAttribute("vehicleDetails");

						String type = null;
						String natureInjury = null;
						String hospital = null;
						String vid = null;
						String natureDamage = null;
						String typeLiability = null;

						if (p.getPid().startsWith("propi")) {
							type = "Body Injury";
							natureInjury = ((BodyInjuryProposalBean) p)
									.getNatureofinjury();
							hospital = ((BodyInjuryProposalBean) p).getHospitalname();
				%>


				<tr class="td_odd">
					<td class="viewtable_right">Proposal ID</td>
					<td class="viewtable_left"><%=p.getPid()%></td>
				</tr>
				<tr class="td_even">
					<td class="viewtable_right">User Name</td>
					<td class="viewtable_left"><%=username%></td>
				</tr>
				<tr class="td_odd">
					<td class="viewtable_right">Proposal Type</td>
					<td class="viewtable_left"><a href="#"
						onclick="ToggleInsurance()"><%=type%></a>
						<table id="insuranceTable" style="display: none; font-size: 12px">
							<tr>
								<td style="width:120px">Insurance ID:</td>
								<td><%=insurance.getInsuranceID()%></td>
							</tr>
							<tr>
								<td>Type Of Insurance:</td>
								<td><%=insurance.getTypeOfInsurance()%></td>
							</tr>
							<tr>
								<td>Vehicle:</td>
								<%if(insurance.getVehicleID()!=null){ %>
								<td><%=insurance.getVehicleID()%></td>
								<%} else{ %>
								<td>N.A.</td>
								<%} %>
							</tr>
							<tr>
								<td>Coverage Amount(USD):</td>
								<td><%=insurance.getCoverageAmount()%></td>
							</tr>
							<tr>
							<% SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
							SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-mm-dd");
							Date date = sdf2.parse(insurance.getStartDate());
							String startDate = sdf1.format(date);%>
								<td>Start Date(dd/mm/yyyy)</td>
								<td><%=startDate%></td>
							</tr>
							<tr>
								<td>Duration(Years)</td>
								<td><%=Integer.parseInt(insurance.getDuration())/12%></td>
							</tr>
							<tr>
								<td>Premium(USD):</td>
								<td><%=insurance.getPremium()%></td>
							</tr>
						</table>
						</td>
				</tr>
				<tr class="td_even">
					<td class="viewtable_right">Date of Injury</td>
					<%
						String m = "" + (Integer.parseInt(p.getMonth()) + 1);
					%>
					<td class="viewtable_left"><%=p.getDate()%>/<%=m%>/<%=p.getYear()%></td>
				</tr>
				<tr class="td_odd">
					<td class="viewtable_right">Amount Claimed(USD)</td>
					<td class="viewtable_left"><%=p.getAmountclaimed()%></td>
				</tr>
				<tr class="td_even">
					<td class="viewtable_right">Nature of Injury</td>
					<td class="viewtable_left"><%=natureInjury%></td>
				</tr>
				<tr>
					<td class="viewtable_right">Hospital Name</td>
					<td class="viewtable_left"><%=hospital%></td>
				</tr>
				<%
					} else if (p.getPid().startsWith("propv")) {
							type = "Vehicle Damage";
							vid = ((VehicleDamageProposalBean) p).getVehicleid();
							natureDamage = ((VehicleDamageProposalBean) p)
									.getNatureofdamage();
				%>
				<tr class="td_odd">
					<td class="viewtable_right">Proposal ID</td>
					<td class="viewtable_left"><%=p.getPid()%></td>
				</tr>
				<tr class="td_even">
					<td class="viewtable_right">User Name</td>
					<td class="viewtable_left"><%=username%></td>
				</tr>
				<tr class="td_odd">
					<td class="viewtable_right">Proposal Type</td>
					<td class="viewtable_left"> <a href="#" onclick="ToggleInsurance()"><%=type%></a>
						<table id="insuranceTable" style="display: none; font-size: 12px">
							<tr>
								<td style="width:120px">Insurance ID:</td>
								<td><%=insurance.getInsuranceID()%></td>
							</tr>
							<tr>
								<td>Type Of Insurance:</td>
								<td><%=insurance.getTypeOfInsurance()%></td>
							</tr>
							<tr>
								<td>Vehicle:</td>
								<%if(insurance.getVehicleID()!=null){ %>
								<td><%=insurance.getVehicleID()%></td>
								<%} else{ %>
								<td>N.A.</td>
								<%} %>
							</tr>
							<tr>
								<td>Coverage Amount(USD):</td>
								<td><%=insurance.getCoverageAmount()%></td>
							</tr>
							<tr>
								<% SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
							SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-mm-dd");
							Date date = sdf2.parse(insurance.getStartDate());
							String startDate = sdf1.format(date);%>
								<td>Start Date(dd/mm/yyyy)</td>
								<td><%=startDate%></td>
							</tr>
							<tr>
								<td>Duration(Years):</td>
								<td><%=Integer.parseInt(insurance.getDuration())/12%></td>
							</tr>
							<tr>
								<td>Premium(USD):</td>
								<td><%=insurance.getPremium()%></td>
							</tr>
						</table></td>
				</tr>
				<tr class="td_even">
					<td class="viewtable_right">Vehicle</td>
					<td class="viewtable_left"><a href="#" onclick="ToggleVehicle()"><%=vid%></a>
						<table id="vehicleTable" style="display: none; font-size: 12px">
							<tr>
								<td style="width:120px">Vehicle ID:</td>
								<td><%=vehicle.getVehicleID()%></td>
							</tr>
							<tr>
								<td>Make:</td>
								<td><%=vehicle.getMake()%></td>
							</tr>
							<tr>
								<td>Model:</td>
								<td><%=vehicle.getModel()%></td>
							</tr>
							<tr>
								<td>Year of Manufacture:</td>
								<td><%=vehicle.getYearOfManufacture()%></td>
							</tr>
							<tr>
								<td>Vehicle Type:</td>
								<td><%=vehicle.getVehicleType()%></td>
							</tr>
							<tr>
								<td>On the Road Price(USD):</td>
								<td><%=vehicle.getPrice()%></td>
							</tr>
						</table></td>
				</tr>
				<tr class="td_odd">
					<td class="viewtable_right">Date of Injury</td>
					<%
						String m = "" + (Integer.parseInt(p.getMonth()) + 1);
					%>
					<td class="viewtable_left"><%=p.getDate()%>/<%=m%>/<%=p.getYear()%></td>
				</tr>
				<tr class="td_even">
					<td class="viewtable_right">Amount Claimed(USD)</td>
					<td class="viewtable_left"><%=p.getAmountclaimed()%></td>
				</tr>
				<tr class="td_odd">
					<td class="viewtable_right">Nature of Damage</td>
					<td class="viewtable_left"><%=natureDamage%></td>
				</tr>

				<%
					} else if (p.getPid().startsWith("propl")) {
											type = "Liability";
											vid = ((LiabilityProposalBean) p).getVehicleid();
											typeLiability = ((LiabilityProposalBean) p)
													.getTypeofliability();
				%>
				<tr class="td_odd">
					<td class="viewtable_right">Proposal ID</td>
					<td class="viewtable_left"><%=p.getPid()%></td>
				
				</tr>
				<tr class="td_even">
					<td class="viewtable_right">User Name</td>
					<td class="viewtable_left"><%=username%></td>
				</tr>
				<tr class="td_odd">
					<td class="viewtable_right">Proposal Type</td>
					<td class="viewtable_left"><a href="#" onclick="ToggleInsurance()"><%=type%></a>
						<table id="insuranceTable" style="display: none; font-size: 12px">
							<tr>
								<td style="width:120px">Insurance ID</td>
								<td><%=insurance.getInsuranceID()%></td>
							</tr>
							<tr>
								<td>Type Of Insurance</td>
								<td><%=insurance.getTypeOfInsurance()%></td>
							</tr>
							<tr>
								<td>Vehicle ID</td>
								<td><%=insurance.getVehicleID()%></td>
							</tr>
							<tr>
								<td>Coverage Amount</td>
								<td><%=insurance.getCoverageAmount()%></td>
							</tr>
							<tr>
								<% SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
							SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-mm-dd");
							Date date = sdf2.parse(insurance.getStartDate());
							String startDate = sdf1.format(date);%>
								<td>Start Date(dd/mm/yyyy)</td>
								<td><%=startDate%></td>
							</tr>
							<tr>
								<td>Duration(Years)</td>
								<td><%=Integer.parseInt(insurance.getDuration())/12%></td>
							</tr>
							<tr>
								<td>Premium(USD)</td>
								<td><%=insurance.getPremium()%></td>
							</tr>
						</table></td>
				</tr>
				<tr class="td_even">
					<td class="viewtable_right">Vehicle</td>
					<td class="viewtable_left"><a href="#" onclick="ToggleVehicle()"><%=vid%></a>
						<table id="vehicleTable" style="display: none; font-size: 12px">
							<tr>
								<td style="width:120px">Vehicle ID</td>
								<td><%=vehicle.getVehicleID()%></td>
							</tr>
							<tr>
								<td>Make</td>
								<td><%=vehicle.getMake()%></td>
							</tr>
							<tr>
								<td>Model</td>
								<td><%=vehicle.getModel()%></td>
							</tr>
							<tr>
								<td>Year of Manufacture</td>
								<td><%=vehicle.getYearOfManufacture()%></td>
							</tr>
							<tr>
								<td>Vehicle Type</td>
								<td><%=vehicle.getVehicleType()%></td>
							</tr>
							<tr>
								<td>On the Road Price(USD)</td>
								<td><%=vehicle.getPrice()%></td>
							</tr>
						</table></td>
				</tr>
				<tr class="td_odd">
					<td class="viewtable_right">Date of Accident</td>
					<%
						String m = "" + (Integer.parseInt(p.getMonth()) + 1);
					%>
					<td class="viewtable_left"><%=p.getDate()%>/<%=m%>/<%=p.getYear()%></td>
				</tr>
				<tr class="td_even">
					<td class="viewtable_right">Amount Claimed(USD)</td>
					<td class="viewtable_left"><%=p.getAmountclaimed()%></td>
				</tr>
				<tr class="td_odd">
					<td class="viewtable_right">Type of Liability</td>
					<td class="viewtable_left"><%=typeLiability%></td>
				</tr>

				<%
					}
				%>

				<tr>
				</tr>
			</table>
			<%if((p.getQuoteStatus()).equals("Pending")) {%>
            <a style="margin-top: 50px; margin-bottom:50px; margin-left: 35%; font-size:15px" id="updatebutton"
						href="/FQPMS/ProposalManageServlet?pid=<%=p.getPid()%>&action=update1">Update</a>
						<a style="font-size:15px; margin-top: 50px; margin-bottom:50px; margin-left: 3%"id="deletebutton"
						href="/FQPMS/ProposalManageServlet?pid=<%=p.getPid()%>&action=delete"
						onclick="return validateDelete()">Delete</a>
			<%} %>
		</div>

		<div id="footer"><jsp:include page="/layout/Footer.jsp" /></div>
	</div>
	<%
		}
	%>
</body>


</html>